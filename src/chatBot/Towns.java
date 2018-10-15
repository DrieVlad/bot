package chatBot;

import java.util.Random;
import java.util.HashSet;


public class Towns implements Game
{
	private  HashSet<String> usedCities = new HashSet<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	private static AccessoryTowns helper = new AccessoryTowns();
	private static TownsContent reader = new TownsContent("Города");	
	private Random ran = new Random();	
    private static int countPlayer;
    private static int playerNumber;
	private boolean turnBot = true;
	private boolean turnPlayer = false;
	private String alphabet = "абвгдежзиклмнопрстуфхцчшщэюя";	
	
	Towns(){
		countPlayer = 0;
		playerNumber = 1;		
	}
	public String game(String userInput)
	{		
		if (countPlayer == 0)
		{
		    try 
		    {
			    countPlayer = Integer.valueOf(userInput);	
	        }
		    catch (NumberFormatException e) 
		    {  
	            return "Неверный формат строки! Попробуй еще разок.";  
	        }
		}		
		if (turnBot)
		{							
			int count = ran.nextInt(alphabet.length());
			lastLetter = String.valueOf(alphabet.charAt(count));
			return runBot();
		}			            
		if (turnPlayer)
		{
		    return runPlayer(userInput);
		}
		return "";
	}	
	
	private String runBot()
	{		
		String botTown = reader.nextTown(lastLetter);
		if (turnBot == true)
		{
			turnBot = false;
		    turnPlayer = true;
		}
		lastLetter = helper.getLastSignificantLetter(botTown);
	    return "Мой ход: " + botTown + "\n" + "Ходит 1 игрок. Слово на букву: " + lastLetter.toUpperCase() + "!";				
	}
	
	private String runPlayer(String userInput)
	{		
		if (userInput.trim().isEmpty())
		{
			return "Что же вы ничего не ввели?! Говорите город на букву: " + lastLetter.toUpperCase() + "!";			
		}
		userInput = userInput.toLowerCase();       		
        firstLetter = userInput.substring(0, 1);  	
        if (!lastLetter.equals(firstLetter))
        {
        	return "Ты играешь не по правилам. Назови город на букву " + lastLetter.toUpperCase() + "!";        	
        }
        boolean isItACity = helper.checkCity(reader, userInput, firstLetter.toLowerCase());	            
        if (!isItACity)
        {
        	return "Не жульничай! Ты называешь не город :)))";
        }
            
        if (helper.checkWordDictionary(userInput, usedCities))
        {
        	lastLetter = helper.getLastSignificantLetter(userInput);
        } 
        else
        	return "Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!";
        if (countPlayer == 1 || playerNumber == countPlayer)
        {
        	playerNumber = 1;
        	return runBot();
        }
        else
        {
        	playerNumber += 1;
        	return "Ходит "+ playerNumber + " игрок. Слово на букву: " + lastLetter.toUpperCase() + "!";
        }			
	}	
}
