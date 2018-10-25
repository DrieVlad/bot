package chatBot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;


public class TelegramEntryPoint extends TelegramLongPollingBot
{
	private static String BOT_USERNAME = System.getenv("BOT_USERNAME");
	private static String BOT_TOKEN = System.getenv("BOT_TOKEN");
	private static Map<Long,Bot> dictionaryUser = new HashMap<Long,Bot>();
	private static ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
	
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
    public void onUpdateReceived(Update update) 
    {
    	long chatId = update.getMessage().getChatId();
    	String userInput = update.getMessage().getText();
    	String messageText = "";
    	dictionaryUser.putIfAbsent(chatId, new Bot());
		messageText = dictionaryUser.get(chatId).reply(userInput);	 
        sendMsg(chatId, messageText, userInput);    
    }

    @Override
    public String getBotToken() 
    {
    	return BOT_TOKEN;          
    }
    
    private void setButtons(String messageText, SendMessage message)
    {   	
    	keyboardMarkup.setOneTimeKeyboard(true);
		keyboardMarkup.setResizeKeyboard(true);
		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow row = new KeyboardRow();
			   
		if (messageText.startsWith("Поиграем") || messageText.startsWith("🦆Приветствую тебя")) 
		{
			row.add("Игра");
			row.add("Диалог");
		}
		else if (messageText.startsWith("У меня есть")) 
		{
			row.add("Города");
			row.add("Миллионер");			
		}	
		else if (messageText.contains("Вопрос")) 
		{
			row.add("1");
			row.add("2");
			row.add("3");
			row.add("4");
		}
		else if (messageText.contains("Отвечай")) 
		{
			row.add("Да");
			row.add("Нет");
		}
		
		if (!(messageText.startsWith("ℹЯ бот Евлампий") || messageText.startsWith("Попробуйте") 
				|| messageText.startsWith("Извините") || messageText.startsWith("Скажи")))
		{
			if (!(messageText.startsWith("До скорого!") || messageText.startsWith("Возвращайся")))
			{
				keyboard.add(row);
			    row = new KeyboardRow();
		        row.add("Помощь");
			    row.add("Устал");		    			    	
			}
			else
			{
				row.add("Я скучаю!"); 									
			}
			keyboard.add(row);
			keyboardMarkup.setKeyboard(keyboard); 
		}		
        message.setReplyMarkup(keyboardMarkup);	
    }
 
	private void sendMsg(long chatId, String messageText, String userInput) 
	{
		SendMessage message = new SendMessage().setChatId(chatId).setText(messageText);		
		setButtons(messageText, message);
       
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