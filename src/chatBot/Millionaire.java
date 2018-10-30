package chatBot;

import java.util.ArrayList;

public class Millionaire implements Game
{
	private MillionaireContent reader = new MillionaireContent("Вопросы Миллионер");
	public static int level;
	public AskMillionaire ask = new AskMillionaire();
	private boolean flagReturn = false;
	private static String numberQuestions = "";
	private Message message = null;
	private Bot bot;
	
	public Millionaire(Bot bot)
	{
		level = 1;
		this.bot = bot;
		this.message = bot.message;
	}
			
	public Message reply(Message userInput)
	{
		String botAnswer = "";	
		ArrayList<String> row = new ArrayList<>();
		ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
		numberQuestions = "Вопрос №" + level + ":\n";
		if (flagReturn)
		{
			if (userInput.getTextMessage().equals("нет"))
			{
				bot.fsm.stackReboot(bot::start);
				message.setTextMessage("Возвращайся, как-нибудь сыграем еще!🦆");
				row.add("Я скучаю!");
			    keyboard.add(row);
			    message.setKeyboard(keyboard);
				return message;
			}
			if (userInput.getTextMessage().equals("да"))
			   flagReturn = false;
			else
			{
				message.setTextMessage("Я тебя не понял :(");
				return message;
			}
		}
		if (level == 1) 
		{
			ask = reader.nextAsk(level);
			botAnswer = ask.stringAsk();
		    level++;
		    message.setTextMessage(numberQuestions + botAnswer);	
		    setAnswerChoise(row, keyboard);
			row = new ArrayList<>();
			bot.setHelpAndTired(row, keyboard);
			return message;
		}
		else
		{
		    return gameNext(userInput);
		}			
	}
	
	public void setAnswerChoise(ArrayList<String> row, ArrayList<ArrayList<String>> keyboard)
	{
		row.add("1");
		row.add("2");
		row.add("3");
		row.add("4");
		keyboard.add(row);
	}
	
	public Message gameNext(Message userInput) throws NumberFormatException
	{
		String botAnswer = "";
		ArrayList<String> row = new ArrayList<>();
		ArrayList<ArrayList<String>> keyboard = new ArrayList<>();
		try 
		{
			if (!(userInput.getTextMessage().equals("1")
					|| userInput.getTextMessage().equals("2") 
					|| userInput.getTextMessage().equals("3")
					|| userInput.getTextMessage().equals("4")))
				throw new NumberFormatException(); 
			if (ask.checkAsk(Integer.parseInt(userInput.getTextMessage()))) 
			{		
				if (level == 13)
				{
					flagReturn = true;
					level = 1;
					message.setTextMessage("Молодец, ты победил в игре \"Миллионер\"!🏆 Твой выигрыш составил 1200 очков! \nСыграем ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎");
					row.add("Да");
					row.add("Нет");
					keyboard.add(row);
					row = new ArrayList<>();
					bot.setHelpAndTired(row, keyboard);
					return message;
				}
				ask = reader.nextAsk(level);
				botAnswer = ask.stringAsk();
				numberQuestions = "Вопрос №" + level + ":\n";
				level++;
				message.setTextMessage("Молодец, ты выиграл " + 100 * (level - 2) + " очков!✋ Внимание, следующий вопрос!🔔 \n" + numberQuestions + botAnswer);
				setAnswerChoise(row, keyboard);
			}
			else 
			{				
				if ((level - 1) / 3 != 0) 
				{
					int point;
					point = ((level - 1) / 3) * 3 * 100;
					message.setTextMessage("Вы выиграли " + point + " очков, в следующий раз получится лучше!😉👌 \n Ещё разок?😏  Отвечай \'да\'👍 или \'нет\'👎");					
				}
				else 
				{
					message.setTextMessage("Вы ничего не выиграли!😣 Ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎");					
				}
				level = 1;
				flagReturn = true;			
				row.add("Да");
				row.add("Нет");
				keyboard.add(row);	
			}
			row = new ArrayList<>();
			bot.setHelpAndTired(row, keyboard);
			return message;
		}
		catch(NumberFormatException e)
		{
			message.setTextMessage("Попробуйте ввести цифру от 1⃣ до 4⃣.");
			return message;
		}
	}
}