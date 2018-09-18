package chatBot;


import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Towns {
	private static ArrayList<String> usedCities = new ArrayList<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	private static AccessoryTowns helper = new AccessoryTowns();
		
	public static void gameOfCities()
	{
		
		String alphabet = "����������������������������";
		
		Random ran = new Random();
		
		int count = ran.nextInt(28);
		lastLetter = alphabet.substring(count, count+1);
		int countPlayer = countPlayers();
		while(true)
		{    
	            runComputer(helper, ran);
	            
	            runPlayer(countPlayer, helper);
	            
	            Boolean flagCityOfBot = false;
	               
                System.out.println("��� ���!");             	      
		}		
	}	
	
	public static void runComputer(AccessoryTowns helper, Random ran) 
	{
		Boolean flagCityOfBot = false;
		
        while (flagCityOfBot != true ) 
        {	   
        	String line = helper.getCityFromFile(ran, lastLetter);
        		
			if (helper.checkWordDictionary(line, usedCities))
        	{    
				System.out.println(line);
				
                lastLetter = line.substring(line.length() - 1, line.length());                       
                lastLetter =  helper.check(lastLetter, line);
                flagCityOfBot = true;	            		
        	}  
        	else 
        	{
        		continue;	     
        	}
        }
        if (flagCityOfBot == false)
        {
        	System.out.println("� �� ���� ������ ������� �� ����� " + lastLetter + ". �� ��������!");
        }
	}
	
	public static void runPlayer(int countPlayer, AccessoryTowns helper) 
	{

		for (int i = 1; i <= countPlayer; i++) 
        {
        	System.out.println("����� "+ i + " �����. ����� �� �����:"  + lastLetter + "!");
               
            while(true) 
            {
            	Scanner input = new Scanner(System.in);	  
            	String userTown = "";
            	if (input.hasNextLine())
            	{
            		userTown = input.nextLine();
            		userTown = userTown.toLowerCase();
            		if (Skeleton.help(userTown))
            		{
            			System.out.println("���������");
            			continue;        
            		}
            	}              	
	            firstLetter = userTown.substring(0, 1);    	
	            
	            if (!(lastLetter.equals(firstLetter) 
	            		|| lastLetter.toLowerCase().equals(firstLetter)))
                {
                 	System.out.println("�� ������� �� �� ��������. ������ ����� �� ����� " + lastLetter);
                 	continue;
                }

	            boolean isItACity = helper.checkCity(userTown, firstLetter);
	            
	            if (!isItACity)
	            {
	                System.out.println("�� ���������! �� ��������� �� �����:)))");
                    continue;
	            }
	                
	            if ( helper.checkWordDictionary(userTown, usedCities))
	            {
	            	lastLetter = userTown.substring(userTown.length() - 1, userTown.length());
	            	lastLetter =  helper.check(lastLetter, userTown);
             		break;
	            }        		
             	else 
             	{
             		System.out.println("��-��-��! ��� ����� ��� ���� �������. �������� ��� ���!");                     
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
			System.out.println("������� ������� ����� ������ �� ����?");
			Scanner input = new Scanner(System.in);	
			try 
			{
		        countPlayer = Integer.valueOf(input.nextLine());
		        break;
		    }
			catch (NumberFormatException e) 
			{  
		        System.err.println("�������� ������ ������! �������� ��� �����.");  
		    }
		}
		return countPlayer;
	}
}