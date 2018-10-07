package chatBot;

public class Millionaire
{
	FileMillionaireReader helper = new FileMillionaireReader();
			
	public void game()
	{
		helper.getAsk();
		String userInput;
		int level = 1;
		while (level < 12)
		{
			InOut.printer.writeDataString("Вопрос " + level); 
			AskMillionaire ask = helper.nextAsk(level);
			ask.PrintAsk();
			userInput = InOut.printer.readDataString();
			if (ask.checkAsk(Integer.parseInt(userInput))) 
			{
				InOut.printer.writeDataString("Молодец, ты выиграл " + 100*level + " очков внимание, следующий вопрос!");
				level++;
			}
			else 
			{
				if ((level - 1) / 3 != 0) 
				{
					int point;
					point = ((level - 1) / 3) * 3 * 100;
					InOut.printer.writeDataString("Вы выиграли  " + point + " очков, в следующй раз поулчится лучше!");
					break;
				}
				else 
				{
					InOut.printer.writeDataString("Вы ничего не выиграли, попробуйте еще раз!");
					break;
				}
			}
		}
	}

}
