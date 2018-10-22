package chatBot;


public class Millionaire implements Game
{
	private MillionaireContent reader = new MillionaireContent("Вопросы Миллионер");
	private static int level;
	private AskMillionaire ask;
	private boolean flagReturn = false;
	Bot bot;
	
	Millionaire(Bot bot)
	{
		level = 1;
		this.bot = bot;
	}
			
	public String reply(String userInput)
	{
		String botAnswer = "";		
		botAnswer = "Вопрос " + level + "\n";
		if (flagReturn)
		{
			if (!userInput.equals("да"))
			{
				bot.fsm.stackReboot(bot::start);
				return ("Возвращайся, как нибудь сыграем еще!");
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
			return botAnswer;
		}
		else
		{
		    return gameNext(userInput);
		}		
	}
	
	private String gameNext(String userInput) throws NumberFormatException
	{
		String botAnswer = "";
		try 
		{
			if (!"1234".contains(userInput))
				throw new NumberFormatException(); 
			if (ask.checkAsk(Integer.parseInt(userInput))) 
			{		
				if (level == 13)
				{
					flagReturn = true;
					level = 1;
					return ("Молодец, ты победил в игре \"Миллионер\" Твой выигрыш составил 1200 очков! \nСыграем ещё разок? Отвечай \\'да\\' или \\'нет\\'");
				}
				ask = reader.nextAsk(level);
				botAnswer = ask.stringAsk();
				level++;
				return("Молодец, ты выиграл " + 100 * (level - 2) + " очков внимание, следующий вопрос!\n" + botAnswer);

			}
			else 
			{				
				if ((level - 1) / 3 != 0) 
				{
					int point;
					point = ((level - 1) / 3) * 3 * 100;
					level = 1;
					flagReturn = true;
					return("Вы выиграли  " + point + " очков, в следующй раз получится лучше! \n Еще разок? Отвечай \\'да\\' или \\'нет\\'");
				}
				else 
				{
					level = 1;
					flagReturn = true;
					return("Вы ничего не выиграли, еще разок? Отвечай \'да\' или \'нет\'" );
				}
			}
		}
		catch(NumberFormatException e)
		{
			return "Попробуйте ввести цифру от 1 до 4";
		}
	}
}