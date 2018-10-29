package testing;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import chatBot.Bot;
import chatBot.PhrasesBot;


public class AllLogicTest {
	private static Bot bot = new Bot();
	private static String botAnswer = "";
		
	@Test
	public void startBotTest() 
	{		
		botAnswer = bot.start(" ");
		assertEquals("🦆Приветствую тебя, мой дорогой друг!👋\n" + PhrasesBot.s_aboutMe, botAnswer);
	}
	
	@Test
	public void townsBotTest() 
	{
		botAnswer = bot.reply("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", botAnswer);
		botAnswer = bot.reply("игра");
		assertEquals("У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
				+ "Пиши название той игры, в которую хочешь сыграть😊. \n"
				+ "Во что будем играть❔", botAnswer);	
		botAnswer = bot.reply("города");
	    assertTrue(botAnswer.startsWith("Мой ход"));
	}
	
	@Test
	public void dialogueBotTest()
	{
		botAnswer = bot.reply("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", botAnswer);
	    botAnswer = bot.reply("диалог");
	    assertEquals("Как тебя зовут?👤", botAnswer);	
	}
	
	@Test
	public void emptyLineBotTest()
	{
		botAnswer = bot.reply("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", botAnswer);
	    botAnswer = bot.reply("");
	    assertEquals("Скажи что-нибудь☺", botAnswer);	
	}
	
	@Test
	public void incorrectInputBotTest()
	{
		botAnswer = bot.reply("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", botAnswer);
	    botAnswer = bot.reply("lalala");
	    assertEquals("Извините, я вас не понял☹", botAnswer);	
	}
	
	@Test
	public void helpBotTest()
	{
		botAnswer = bot.reply("помощь");
		assertEquals("ℹ" + PhrasesBot.s_aboutMe, botAnswer);
	}
	
	@Test
	public void millionaireBotTest()
	{
		botAnswer = bot.reply("устал");
	    assertEquals("Поиграем или пообщаемся?😏 Пиши: \"игра\"🕹 или \"диалог\"📨", botAnswer);
	    botAnswer = bot.reply("игра");
		assertEquals("У меня есть две игры на выбор: \"Города\"🏘 и \"Миллионер\"💰. \n"
				+ "Пиши название той игры, в которую хочешь сыграть😊. \n"
				+ "Во что будем играть❔", botAnswer);	
		botAnswer = bot.reply("миллионер");
	    assertTrue(botAnswer.startsWith("Вопрос №"));	
	}
	
	@Test
	public void endBotTest()
	{
		String botAnswer = bot.reply("пока");
	    assertEquals("До скорого!👋 Я всегда к твоим услугам🦆 \n", botAnswer);	
	}	
}
