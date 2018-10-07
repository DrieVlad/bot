package chatBot;

import java.util.Random;
import java.util.HashSet;


public class Towns 
{
	private static HashSet<String> usedCities = new HashSet<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	private static AccessoryTowns helper = new AccessoryTowns();
	private static FileTownsReader reader = new FileTownsReader();	
	private Random ran = new Random();	
	private static int countPlayer = 1;
	public boolean flagInterrupt = true;
	
	public void gameOfCities()
	{		
		reader.getTowns();		
		String alphabet = "абвгдежзиклмнопрстуфхцчшщэюя";					
		int count = ran.nextInt(alphabet.length());
		lastLetter = String.valueOf(alphabet.charAt(count));
		Bot.bot.fsm.popState();
		Bot.bot.fsm.pushState(this::countPlayers);
		Bot.bot.fsm.update();
		
        while(flagInterrupt)
		{       
			    Bot.bot.fsm.popState();
			    Bot.bot.fsm.pushState(this::runPlayer);
			    Bot.bot.fsm.update();
		}		
	}	
		
	public void runPlayer() 
	{
        label: for (int i = 0; i <= countPlayer; i++) 
        {   
			
        	boolean botsTurn = (i == 0);
			if (botsTurn) 
			{
				botsTurn = true;
			}
			
			if (botsTurn) 
            {
				InOut.printer.writeDataString("Мой ход: "); 
            }
			else
			{
				InOut.printer.writeDataString("Ходит "+ i + " игрок. Слово на букву: "  + lastLetter.toUpperCase() + "!");
			}
			        
			boolean flagInterrupt = true;
            while(flagInterrupt) 
            {
            	String userTown;
            	if (botsTurn) 
            	{
            	    userTown = reader.nextTown(lastLetter);
            	    InOut.printer.writeDataString(userTown);
            	}
            	else 
            	{
            		userTown = InOut.printer.readDataString();
            	}
            	
        		if (userTown.trim().isEmpty())
        		{
        			InOut.printer.writeDataString("Что же вы ничего не ввели?! Говорите город на букву: " + lastLetter.toUpperCase() + "!");
        			continue;
        		}
        		userTown = userTown.toLowerCase();
        		if (userTown.equals("помощь"))
        		{
                    Bot.bot.fsm.pushState(Bot.bot::printHelp);
        			Bot.bot.fsm.update();
                    InOut.printer.writeDataString("Ходит "+ i + " игрок. Слово на букву: "  + lastLetter.toUpperCase() + "!");
        			continue;        
        		}
        		if (userTown.equals("устал"))
        		{
                    Bot.bot.fsm.popState();
        			Bot.bot.fsm.pushState(Bot.bot::choiseGame);      			
        			Bot.bot.fsm.update();
        			this.flagInterrupt = false;
        			break label;
        			
                }
        		if (userTown.equals("пока"))
        		{
                    Bot.bot.fsm.popState();
        			Bot.bot.fsm.pushState(Bot.bot::sayGoodbye);      			
        			Bot.bot.fsm.update();
        			this.flagInterrupt = false;
        			break label;
        		}
	            firstLetter = userTown.substring(0, 1);  	
	            
	            if (!lastLetter.equals(firstLetter))
                {
	            	InOut.printer.writeDataString("Ты играешь не по правилам. Назови город на букву " + lastLetter.toUpperCase());
                 	continue;
                }

	            boolean isItACity = helper.checkCity(reader, userTown, firstLetter.toLowerCase());	            
	            if (!isItACity)
	            {
	            	InOut.printer.writeDataString("Не жульничай! Ты называешь не город:)))");
                    continue;
	            }
	                
	            if (helper.checkWordDictionary(userTown, usedCities))
	            {
	            	lastLetter = helper.getLastSignificantLetter(userTown);
	            	flagInterrupt = false;
	            	continue;
	            }          		
             	else 
             	{
             		if (i == 0)
             		{
             			continue;
             		}
             		InOut.printer.writeDataString("Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!");                     
             		continue;
             	} 
            }
        }		
	}
	
	public void countPlayers() 
	{		
		boolean flagInterrupt = true;
		while (flagInterrupt) 
		{
			InOut.printer.writeDataString("Сколько человек будет играть со мной?");	
			try 
			{
				String userInput = InOut.printer.readDataString();
				if (userInput.equals("помощь"))
        		{
        			Bot.bot.fsm.popState();
        			Bot.bot.fsm.pushState(Bot.bot::printHelp);
        			Bot.bot.fsm.update();
        			continue;
        		}
				else
				{
					countPlayer = Integer.valueOf(userInput);
		            flagInterrupt = false;
		            continue;
				}		        
		    }
			catch (NumberFormatException e) 
			{  
		        InOut.printer.writeDataString("Неверный формат строки! Попробуй еще разок.");  
		    }
		}
	}
}
