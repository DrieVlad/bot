package chatBot;


import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;

public class Towns {
	private static HashSet<String> usedCities = new HashSet<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	private static AccessoryTowns helper = new AccessoryTowns();
		
	public static void gameOfCities()
	{
		
		String alphabet = "абвгдежзиклмнопрстуфхцчшщэюя";		
		Random ran = new Random();		
		int count = ran.nextInt(alphabet.length());
		lastLetter = String.valueOf(alphabet.charAt(count));
		int countPlayer = countPlayers();
		while(true)
		{    
	            runComputer(helper, ran);
	            
	            runPlayer(helper, countPlayer);	            
	               
                            	      
		}		
	}	
	
	public static void runComputer(AccessoryTowns helper, Random ran) 
	{
		System.out.print("Мой ход: "); 
		Boolean flagCityOfBot = false;
		
        while (flagCityOfBot != true ) 
        {	   
        	String line = helper.getCityFromFile(ran, lastLetter.toLowerCase());
        	String botCity = line.toLowerCase();	
			if (helper.checkWordDictionary(botCity, usedCities))
        	{    
				System.out.println(line);
				
                lastLetter = line.substring(line.length() - 1, line.length());                       
                lastLetter =  helper.check(lastLetter, line).toUpperCase();
                flagCityOfBot = true;	            		
        	}  
        	else 
        	{
        		continue;	     
        	}
        }
        if (flagCityOfBot == false)
        {
        	System.out.println("Я не знаю больше городов на букву " + lastLetter + ". Вы выиграли!");
        }
	}
	
	public static void runPlayer(AccessoryTowns helper, int countPlayer) 
	{

		for (int i = 1; i <= countPlayer; i++) 
        {
        	System.out.println("Ходит "+ i + " игрок. Слово на букву: "  + lastLetter + "!");
               
            while(true) 
            {
            	Scanner input = new Scanner(System.in);	  
            	String userTown = "";
            	if (input.hasNextLine())
            	{
            		userTown = input.nextLine();
            		if (userTown.trim().isEmpty())
            		{
            			System.out.println("Что же вы ничего не ввели?! Говорите город на букву: " + lastLetter + "!");
            			continue;
            		}
            		userTown = userTown.toLowerCase();
            		if (Skeleton.help(userTown))
            		{
            			System.out.println("Продолжай");
            			continue;        
            		}
            	}              	
	            firstLetter = userTown.substring(0, 1).toUpperCase();    	
	            
	            if (!lastLetter.equals(firstLetter))
                {
                 	System.out.println("Ты играешь не по правилам. Назови город на букву " + lastLetter);
                 	continue;
                }

	            boolean isItACity = helper.checkCity(userTown, firstLetter.toLowerCase());	            
	            if (!isItACity)
	            {
	                System.out.println("Не жульничай! Ты называешь не город:)))");
                    continue;
	            }
	                
	            if ( helper.checkWordDictionary(userTown, usedCities))
	            {
	            	lastLetter = userTown.substring(userTown.length() - 1, userTown.length());
	            	lastLetter =  helper.check(lastLetter, userTown).toUpperCase();
             		break;
	            }        		
             	else 
             	{
             		System.out.println("Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!");                     
             		continue;
             	} 
            }      
        }
		
	}
	
	public static int countPlayers() 
	{
		int countPlayer = 1;
		while (true) 
		{
			System.out.println("Сколько человек будет играть со мной?");
			Scanner input = new Scanner(System.in);	
			try 
			{
		        countPlayer = Integer.valueOf(input.nextLine());
		        break;
		    }
			catch (NumberFormatException e) 
			{  
		        System.err.println("Неверный формат строки! Попробуй еще разок.");  
		    }
		}
		return countPlayer;
	}
}
