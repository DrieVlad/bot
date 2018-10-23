package chatBot;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;


public class TelegramEntryPoint extends TelegramLongPollingBot
{
	private static String BOT_USERNAME = System.getenv("BOT_USERNAME");
	private static String BOT_TOKEN = System.getenv("BOT_TOKEN");
	public static Map<Long,Bot> dictionaryUser = new HashMap<Long,Bot>();
	
    public static void main(String[] args) 
    {
    	ApiContextInitializer.init(); // Инициализируем апи
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
    	//возвращаем имя бота
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
        sendMsg(chatId, messageText);    
    }

    @Override
    public String getBotToken() 
    {
    	//Токен бота
    	return BOT_TOKEN;          
    }
 
	private void sendMsg(long chatId, String messageText) 
	{
		SendMessage message = new SendMessage().setChatId(chatId).setText(messageText);
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
