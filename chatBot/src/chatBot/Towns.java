package chatBot;

import java.util.Random;
import java.util.HashSet;


public class Towns {
	private static HashSet<String> usedCities = new HashSet<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	private static AccessoryTowns helper = new AccessoryTowns();
	private static InOut inOut = new InOut();
	private static FileTownsReader reader = new FileTownsReader();	
	
	public void gameOfCities()
	{
		
		reader.getTowns();
		
		String alphabet = "����������������������������";		
		Random ran = new Random();		
		int count = ran.nextInt(alphabet.length());
		lastLetter = String.valueOf(alphabet.charAt(count));
		int countPlayer = countPlayers();
		
		while(true)
		{             
	            runPlayer(helper, countPlayer, ran);	            	                                         	      
		}		
	}	
		
	public void runPlayer(AccessoryTowns helper, int countPlayer, Random ran) 
	{

		for (int i = 0; i <= countPlayer; i++) 
        {   
			if (i == 0) 
            {
				inOut.printData("��� ���: "); 
            }
			else
			{
				inOut.printData("����� "+ i + " �����. ����� �� �����: "  + lastLetter.toUpperCase() + "!");
			}
			           
            while(true) 
            {
            	String userTown;
            	if (i == 0) 
            	{
            	    userTown = reader.nextTown(lastLetter);
            	    inOut.printData(userTown);
            	}
            	else 
            	{
            		userTown = inOut.scanData();
            	}
            	
        		if (userTown.trim().isEmpty())
        		{
        			inOut.printData("��� �� �� ������ �� �����?! �������� ����� �� �����: " + lastLetter.toUpperCase() + "!");
        			continue;
        		}
        		userTown = userTown.toLowerCase();
        		if (Skeleton.help(userTown))
        		{
        			inOut.printData("���������");
        			continue;        
        		}           	
	            firstLetter = userTown.substring(0, 1);  	
	            
	            if (!lastLetter.equals(firstLetter))
                {
	            	inOut.printData("�� ������� �� �� ��������. ������ ����� �� ����� " + lastLetter.toUpperCase());
                 	continue;
                }

	            boolean isItACity = helper.checkCity(reader, userTown, firstLetter.toLowerCase());	            
	            if (!isItACity)
	            {
	            	inOut.printData("�� ���������! �� ��������� �� �����:)))");
                    continue;
	            }
	                
	            if ( helper.checkWordDictionary(userTown, usedCities))
	            {
	            	lastLetter = userTown.substring(userTown.length() - 1, userTown.length());
	            	lastLetter =  helper.check(lastLetter, userTown).toLowerCase();
             		break;
	            }          		
             	else 
             	{
             		if (i == 0)
             		{
             			continue;
             		}
             		inOut.printData("��-��-��! ��� ����� ��� ���� �������. �������� ��� ���!");                     
             		continue;
             	} 
            }      
        }
		
	}
	
	public int countPlayers() 
	{
		int countPlayer = 1;
		while (true) 
		{
			inOut.printData("������� ������� ����� ������ �� ����?");	
			try 
			{
		        countPlayer = Integer.valueOf(inOut.scanData());
		        break;
		    }
			catch (NumberFormatException e) 
			{  
		        inOut.printData("�������� ������ ������! �������� ��� �����.");  
		    }
		}
		return countPlayer;
	}
}