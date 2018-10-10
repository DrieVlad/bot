package chatBot;

import java.text.Format;



public class Millionaire
{
	
	MillionaireContent reader = new MillionaireContent("Вопросы Миллионер");
	private int level;
	AskMillionaire ask;
	boolean flagReturn = false;
	
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
				ConsoleEntryPoint.bot.fsm.stackReboot();
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
		    return next(userInput);
		}
		
	}
	
	private String next(String userInput) 
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
					level++;
					return("Вы выиграли  " + point + " очков, в следующй раз поулчится лучше!");
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
