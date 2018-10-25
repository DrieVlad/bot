package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import chatBot.Bot;
import chatBot.Millionaire;


public class MillionaireTest {
	private Bot bot = new Bot();
	private Millionaire millionaire = new Millionaire(bot);
	
	@Test
	public void startMillionaireTest()
	{
		assertTrue(millionaire.reply("2").startsWith("Вопрос №1"));	
	}
	
	@Test
	public void incorrectInputMillionaireTest()
	{
		assertEquals("Попробуйте ввести цифру от 1 до 4", millionaire.gameNext("25"));	
		assertEquals("Попробуйте ввести цифру от 1 до 4", millionaire.gameNext("lalala"));
	}
	
	@Test
	public void winMillionaireTest()
	{
		millionaire.ask.trueAnswer = 1;		
		Millionaire.level = 5;
		assertTrue(millionaire.gameNext("2").startsWith("Молодец, ты выиграл 400 очков внимание, следующий вопрос! \nВопрос №5"));
		millionaire.ask.trueAnswer = 3;
		Millionaire.level = 13;
		assertEquals("Молодец, ты победил в игре \"Миллионер\" Твой выигрыш составил 1200 очков! \nСыграем ещё разок? Отвечай \\'да\\' или \\'нет\\'", millionaire.gameNext("4"));
		millionaire.ask.trueAnswer = 2;
		Millionaire.level = 4;
		assertEquals("Вы выиграли 300 очков, в следующий раз получится лучше! \n Еще разок? Отвечай \\'да\\' или \\'нет\\'", millionaire.gameNext("1"));	
		Millionaire.level = 2;
		millionaire.ask.trueAnswer = 4;
		assertEquals("Вы ничего не выиграли, еще разок? Отвечай \'да\' или \'нет\'", millionaire.gameNext("4"));
	}
}