package chatBot;


public class Millionaire implements Game
{
	private MillionaireContent reader = new MillionaireContent("Вопросы Миллионер");
	public static int level;
	public AskMillionaire ask = new AskMillionaire();
	private boolean flagReturn = false;
	private static String numberQuestions = "";
	Bot bot;
	
	public Millionaire(Bot bot)
	{
		level = 1;
		this.bot = bot;
	}
			
	public String reply(String userInput)
	{
		String botAnswer = "";			
		numberQuestions = "Вопрос №" + level + ":\n";
		if (flagReturn)
		{
			if (!(userInput.equals("да") || (userInput.equals("Да"))))
			{
				bot.fsm.stackReboot(bot::start);
				return ("Возвращайся, как-нибудь сыграем еще!🦆");
			}
			flagReturn = false;
		}
		if (userInput.equals(""))
		{
			return "";
		}
		if (level == 1) 
		{
			ask = reader.nextAsk(level);
			botAnswer = ask.stringAsk();
		    level++;
			return numberQuestions + botAnswer;
		}
		else
		{
		    return gameNext(userInput);
		}		
	}
	
	public String gameNext(String userInput) throws NumberFormatException
	{
		String botAnswer = "";
		try 
		{
			if (!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4")))
				throw new NumberFormatException(); 
			if (ask.checkAsk(Integer.parseInt(userInput))) 
			{		
				if (level == 13)
				{
					flagReturn = true;
					level = 1;
					return ("Молодец, ты победил в игре \"Миллионер\"!🏆 Твой выигрыш составил 1200 очков! \nСыграем ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎");
				}
				ask = reader.nextAsk(level);
				botAnswer = ask.stringAsk();
				numberQuestions = "Вопрос №" + level + ":\n";
				level++;
				return("Молодец, ты выиграл " + 100 * (level - 2) + " очков!✋ Внимание, следующий вопрос!🔔 \n" + numberQuestions + botAnswer);

			}
			else 
			{				
				if ((level - 1) / 3 != 0) 
				{
					int point;
					point = ((level - 1) / 3) * 3 * 100;
					level = 1;
					flagReturn = true;
					return("Вы выиграли " + point + " очков, в следующий раз получится лучше!😉👌 \n Ещё разок?😏  Отвечай \'да\'👍 или \'нет\'👎");
				}
				else 
				{
					level = 1;
					flagReturn = true;
					return("Вы ничего не выиграли!😣 Ещё разок?😏 Отвечай \'да\'👍 или \'нет\'👎");
				}
			}
		}
		catch(NumberFormatException e)
		{
			return "Попробуйте ввести цифру от 1⃣ до 4⃣.";
		}
	}
}