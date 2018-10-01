package chatBot;

import java.util.Random;
import java.util.HashSet;


public class Towns 
{
	private static HashSet<String> usedCities = new HashSet<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	private static AccessoryTowns helper = new AccessoryTowns();
	private static InOut inOut = new InOut();
	private static FileTownsReader reader = new FileTownsReader();	
	
	public void gameOfCities()
	{		
		reader.getTowns();
		
		String alphabet = "абвгдежзиклмнопрстуфхцчшщэюя";		
		Random ran = new Random();		
		int count = ran.nextInt(alphabet.length());
		lastLetter = String.valueOf(alphabet.charAt(count));
		int countPlayer = countPlayers();
		
		boolean flagInterrupt = true;
		while(flagInterrupt)
		{             
	            runPlayer(helper, countPlayer, ran);	            	                                         	      
		}		
	}	
		
	public void runPlayer(AccessoryTowns helper, int countPlayer, Random ran) 
	{

		for (int i = 0; i <= countPlayer; i++) 
        {   
			boolean botsTurn = false;
			if (i == 0) 
			{
				botsTurn = true;
			}
			
			if (botsTurn) 
            {
				inOut.writeDataString("Мой ход: "); 
            }
			else
			{
				inOut.writeDataString("Ходит "+ i + " игрок. Слово на букву: "  + lastLetter.toUpperCase() + "!");
			}
			           
			boolean flagInterrupt = true;
            while(flagInterrupt) 
            {
            	String userTown;
            	if (botsTurn) 
            	{
            	    userTown = reader.nextTown(lastLetter);
            	    inOut.writeDataString(userTown);
            	}
            	else 
            	{
            		userTown = inOut.readDataString();
            	}
            	
        		if (userTown.trim().isEmpty())
        		{
        			inOut.writeDataString("Что же вы ничего не ввели?! Говорите город на букву: " + lastLetter.toUpperCase() + "!");
        			continue;
        		}
        		userTown = userTown.toLowerCase();
        		if (Skeleton.help(userTown))
        		{
        			inOut.writeDataString("Продолжай");
        			continue;        
        		}           	
	            firstLetter = userTown.substring(0, 1);  	
	            
	            if (!lastLetter.equals(firstLetter))
                {
	            	inOut.writeDataString("Ты играешь не по правилам. Назови город на букву " + lastLetter.toUpperCase());
                 	continue;
                }

	            boolean isItACity = helper.checkCity(reader, userTown, firstLetter.toLowerCase());	            
	            if (!isItACity)
	            {
	            	inOut.writeDataString("Не жульничай! Ты называешь не город:)))");
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
             		inOut.writeDataString("Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!");                     
             		continue;
             	} 
            }      
        }		
	}
	
	public int countPlayers() 
	{
		int countPlayer = 1;
		boolean flagInterrupt = true;
		while (flagInterrupt) 
		{
			inOut.writeDataString("Сколько человек будет играть со мной?");	
			try 
			{
		        countPlayer = Integer.valueOf(inOut.readDataString());
		        flagInterrupt = false;
		        continue;
		    }
			catch (NumberFormatException e) 
			{  
		        inOut.writeDataString("Неверный формат строки! Попробуй еще разок.");  
		    }
		}
		return countPlayer;
	}
}
