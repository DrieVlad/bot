package chatBot;

public class Millionaire
{
	MillionaireContent reader = new MillionaireContent("Вопросы Миллионер");
	private int level;
	AskMillionaire ask;
	
	Millionaire()
	{
		level = 1;
	}
			
	public String game(String userInput)
	{
		String botAnswer = "";
		botAnswer = "Вопрос " + level + "\n"; 
		
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
		if (ask.checkAsk(Integer.parseInt(userInput))) 
		{
			level++;
			ask = reader.nextAsk(level);
			botAnswer = ask.stringAsk();
			return("Молодец, ты выиграл " + 100 * (level - 1) + " очков внимание, следующий вопрос!\n" + botAnswer);			
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
				level++;
				return("Вы ничего не выиграли, попробуйте еще раз!");
			}
		}
	}
}
