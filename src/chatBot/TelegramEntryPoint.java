package chatBot;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.ArrayList;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;


public class TelegramEntryPoint extends TelegramLongPollingBot
{
    private static String BOT_USERNAME = System.getenv("BOT_USERNAME");
    private static String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private static ConcurrentMap<Long,Chat> dictionaryUser = new ConcurrentHashMap<Long,Chat>();
    
    public static void main(String[] args) 
    {
        ApiContextInitializer.init(); 
        TelegramBotsApi botapi = new TelegramBotsApi();
        try 
        {
            botapi.registerBot((LongPollingBot) new TelegramEntryPoint());
        }
        catch (TelegramApiException e) 
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getBotUsername() 
    {
        return BOT_USERNAME;            
    }

    @Override
    public String getBotToken() 
    {
        return BOT_TOKEN;          
    }

    @Override
    public void onUpdateReceived(Update update) 
    {
        long chatId = update.getMessage().getChatId();
        String userInput = update.getMessage().getText().toLowerCase();
        Chat chat = dictionaryUser.computeIfAbsent(chatId, x -> new Chat(new Bot(), new Object()));
        Bot bot = chat.getBot();
        Object locker = chat.getLocker();
        Message userMessage = new Message();
        Message botMessage = new Message();
        userMessage.setTextMessage(userInput);
        synchronized(locker)
        {
            botMessage = bot.reply(userMessage);
            sendMsg(chatId, botMessage, userInput);    
        }
    }
    
    private void setButtons(Message botMessage, SendMessage message, ReplyKeyboardMarkup keyboardMarkup)
    {             
        if (botMessage.isKeyboardNotEmpty) 
        {
        	keyboardMarkup.setOneTimeKeyboard(true);
            keyboardMarkup.setResizeKeyboard(true);
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow rowButtons = new KeyboardRow();
            ArrayList<ArrayList<String>> listKeyboards = botMessage.getKeyboard();
            for(ArrayList<String> line: listKeyboards) 
            {
                rowButtons = new KeyboardRow();
                for (String button: line) 
                {
                    rowButtons.add(button);
                }
                keyboard.add(rowButtons);
            }
            keyboardMarkup.setKeyboard(keyboard);
        }
    }
 
    private void sendMsg(long chatId, Message botMessage, String userInput) 
    {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        SendMessage message = new SendMessage().setChatId(chatId).setText(botMessage.getTextMessage());        
        setButtons(botMessage, message, keyboardMarkup);        
        message.setReplyMarkup(keyboardMarkup);    
       
        try 
        {            
            execute(message);
        } 
        catch (TelegramApiException e) 
        {
            e.printStackTrace();
        } 
    }    
}