package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import chatBot.Bot;
import chatBot.Message;
import chatBot.Millionaire;


public class MillionaireTest {
	private Bot bot = new Bot();
	private Millionaire millionaire = new Millionaire(bot);
	private Message message = new Message();
	
	@Test
	public void startMillionaireTest()
	{
		message.setTextMessage("2");
		assertTrue(millionaire.reply(message).getTextMessage().startsWith("Вопрос №1"));	
	}
	
	@Test
	public void incorrectInputMillionaireTest()
	{
		message.setTextMessage("25");
		assertEquals("Попробуйте ввести цифру от 1⃣ до 4⃣.", millionaire.gameNext(message).getTextMessage());	
		message.setTextMessage("lalala");
		assertEquals("Попробуйте ввести цифру от 1⃣ до 4⃣.", millionaire.gameNext(message).getTextMessage());
	}
	
	@Test
	public void winMillionaireTest()
	{
		millionaire.ask.trueAnswer = 1;		
		Millionaire.level = 5;
		message.setTextMessage("2");
		assertTrue(millionaire.gameNext(message).getTextMessage().startsWith("Молодец, ты выиграл 400 очков!✋ Внимание, следующий вопрос!🔔 \nВопрос №5"));
		millionaire.ask.trueAnswer = 3;
		Millionaire.level = 13;
		message.setTextMessage("4");
		assertEquals("Молодец, ты победил в игре \"Миллионер\"!🏆 Твой выигрыш составил 1200 очков! \nСыграем ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎", 
				millionaire.gameNext(message).getTextMessage());
		millionaire.ask.trueAnswer = 2;
		Millionaire.level = 4;
		message.setTextMessage("1");
		assertEquals("Вы выиграли 300 очков, в следующий раз получится лучше!😉👌 \n Ещё разок?😏  Отвечай \'да\'👍 или \'нет\'👎", 
				millionaire.gameNext(message).getTextMessage());	
		Millionaire.level = 2;
		millionaire.ask.trueAnswer = 4;
		message.setTextMessage("4");
		assertEquals("Вы ничего не выиграли!😣 Ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎", 
				millionaire.gameNext(message).getTextMessage());
	}
}