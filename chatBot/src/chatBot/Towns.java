package chatBot;


import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Towns {
	private static ArrayList<String> usedCities = new ArrayList<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
		
	public static void gameOfCities()
	{
		String alphabet = "����������������������������";
		Random ran = new Random();
		int count = ran.nextInt(28);
		lastLetter = alphabet.substring(count, count+1);
		AccessoryTowns helper = new AccessoryTowns();
		while(true)
		{         				
	            Boolean flagCityOfBot = false;
	            while (flagCityOfBot != true ) 
	            {	   
	            	String line = helper.getCityFromFile(ran, lastLetter);
	            	if (line.equals("error"))
	            		System.exit(1);
					if (helper.checkTheWordInTheDictionary(line, usedCities))
	            	{    
						System.out.println(line);
                        lastLetter = line.substring(line.length() - 1, line.length()).toUpperCase();                       
                        lastLetter =  helper.check(lastLetter, line);
                        flagCityOfBot = true;	            		
	            	}  
	            	else 
	            		continue;	            	
	            }
	            if (flagCityOfBot == false)
	            {
	            	System.out.println("� �� ���� ������ ������� �� ����� " + lastLetter + ". �� ��������!");
	            }
	            System.out.println("���� ��� �� ����� " + lastLetter + "!");
	                   
                while(true) {
                	Scanner input = new Scanner(System.in);	  
                	String userTown = "";
                	if (input.hasNextLine())
                	{
                		String noUseLine = input.nextLine();
                		userTown = noUseLine;
                		if (Skeleton.help(userTown))
                			continue;               	                		
                	}              	
    	            firstLetter = userTown.substring(0, 1);    	
    	            
    	            if (!(lastLetter.equals(firstLetter) || lastLetter.toLowerCase().equals(firstLetter)))
                    {
                     	System.out.println("�� ������� �� �� ��������. ������ ����� �� ����� " + lastLetter);
                     	continue;
                    }

    	            String isItACity = helper.checkCity(userTown, firstLetter);
    	            if (isItACity.equals("Error"))
    	            {
    	            	System.exit(1);
    	            }
    	            if (!isItACity.equals("+"))
    	            {
    	                System.out.println("�� ���������! �� ��������� �� �����:)))");
	                    continue;
    	            }
    	                
    	            if ( helper.checkTheWordInTheDictionary(userTown, usedCities))
    	            {
    	            	lastLetter = userTown.substring(userTown.length() - 1, userTown.length()).toUpperCase();
    	            	lastLetter =  helper.check(lastLetter, userTown);
                 		break;
    	            }        		
                 	else 
                 	{
                 		System.out.println("��-��-��! ��� ����� ��� ���� �������. �������� ��� ���!");                     
                 		continue;
                 	} 
                }                
                System.out.println("��� ���!");             	      
		}		
	}	
}