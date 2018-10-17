package chatBot;


public class Millionaire implements Game
{
	private MillionaireContent reader = new MillionaireContent("Вопросы Миллионер");
	private int level;
	private AskMillionaire ask;
	private boolean flagReturn = false;
	
	Millionaire()
	{
		level = 1;
	}
			
	public String game(String userInput)
	{
		String botAnswer = "";		
		botAnswer = "Вопрос " + level + "\n";
		if (flagReturn)
		{
			if (!userInput.equals("да"))
			{
				ConsoleEntryPoint.bot.fsm.stackReboot(ConsoleEntryPoint.bot::start);
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
	
	private String gameNext(String userInput) 
	{
		String botAnswer = "";
		try 
		{
			if (ask.checkAsk(Integer.parseInt(userInput))) 
			{
				level++;
				ask = reader.nextAsk(level);
				botAnswer = ask.stringAsk();
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
					return("Вы выиграли  " + point + " очков, в следующй раз поулчится лучше! \n Еще разок? Отвечай \\'да\\' или \\'нет\\'");
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