package chatBot;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
    	
	    return BOT_USERNAME;
            //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update update) 
    {
    	long chat_id = update.getMessage().getChatId();
    	String userInput = update.getMessage().getText();
    	String message_text = "";
    	if (dictionaryUser.containsKey(chat_id))
	    {
    		message_text = dictionaryUser.get(chat_id).reply(userInput);
	    }
	    else
	    {
		    dictionaryUser.put(chat_id, new Bot());
		    message_text = dictionaryUser.get(chat_id).reply(userInput);
	    }
        SendMessage message = new SendMessage().setChatId(chat_id).setText(message_text);
        try 
        {
            execute(message);
        } 
        catch (TelegramApiException e) 
        {
        	e.printStackTrace();
        }     
    }

    @Override
    public String getBotToken() 
    {
    	
    	return BOT_TOKEN;
            //Токен бота
    }
    
	private void sendMsg(long chat_id, String userInput) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
		sendMessage.setText(text);
		try { //Чтобы не крашнулась программа при вылете Exception 
			execute(sendMessage);
		} catch (TelegramApiException e){
			e.printStackTrace();
		}
	}
		
	}
