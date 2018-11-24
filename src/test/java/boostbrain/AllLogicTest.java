package testing;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import chatBot.Bot;
import chatBot.Message;
import chatBot.PhrasesBot;


public class AllLogicTest {
	private static Bot bot = new Bot();
	private static Message message = new Message();
		
	@Test
	public void startBotTest() 
	{		
		message.setTextMessage(" ");
		assertEquals("🦆Приветствую тебя, мой дорогой друг!👋\n" + PhrasesBot.s_aboutMe, bot.start(message).getTextMessage());
	}
	
	@Test
	public void townsBotTest() 
	{
		
		message.setTextMessage("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", bot.reply(message).getTextMessage());
	    message.setTextMessage("игра");
		assertEquals("У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
				+ "Пиши название той игры, в которую хочешь сыграть😊. \n"
				+ "Во что будем играть❔", bot.reply(message).getTextMessage());	
		message.setTextMessage("города");
	    assertTrue(bot.reply(message).getTextMessage().startsWith("Мой ход"));
	}
	
	@Test
	public void dialogueBotTest()
	{
		message.setTextMessage("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", bot.reply(message).getTextMessage());
	    message.setTextMessage("диалог");
	    assertEquals("Как тебя зовут?👤", bot.reply(message).getTextMessage());	
	}
	
	@Test
	public void emptyLineBotTest()
	{
		message.setTextMessage("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", bot.reply(message).getTextMessage());
	    message.setTextMessage("");
	    assertEquals("Скажи что-нибудь☺", bot.reply(message).getTextMessage());	
	}
	
	@Test
	public void incorrectInputBotTest()
	{
		message.setTextMessage("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", bot.reply(message).getTextMessage());
	    message.setTextMessage("lalala");
	    assertEquals("Извините, я вас не понял☹", bot.reply(message).getTextMessage());	
	}
	
	@Test
	public void helpBotTest()
	{
		message.setTextMessage("помощь");
		assertEquals("ℹ" + PhrasesBot.s_aboutMe, bot.reply(message).getTextMessage());
	}
	
	@Test
	public void millionaireBotTest()
	{
		message.setTextMessage("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", bot.reply(message).getTextMessage());
	    message.setTextMessage("игра");
		assertEquals("У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
				+ "Пиши название той игры, в которую хочешь сыграть😊. \n"
				+ "Во что будем играть❔", bot.reply(message).getTextMessage());	
		message.setTextMessage("миллионер");
	    assertTrue(bot.reply(message).getTextMessage().startsWith("Вопрос №"));	
	}
	
	@Test
	public void endBotTest()
	{
		message.setTextMessage("пока");
	    assertEquals("До скорого!👋 Я всегда к твоим услугам🦆 \n", bot.reply(message).getTextMessage());	
	}	
}