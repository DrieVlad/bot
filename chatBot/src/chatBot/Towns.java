package chatBot;


import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Towns {
	private static ArrayList<String> usedCities = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		String lastLetter = "A";
		while(true)
		{
			try {
	            File file = new File(lastLetter + ".txt");
	            FileReader fr = new FileReader(file);
	            BufferedReader reader = new BufferedReader(fr);
	            String line = reader.readLine();
	            String firstLetter = "";
	            
	            while (line != null) {
	            	
                    line = reader.readLine();
                    System.out.println("Hi");
	            	if (usedCities.indexOf(line) == -1)
	            	{
	            		System.out.println(line);
                        usedCities.add(line);
                        lastLetter = line.substring(line.length() - 1, line.length()).toLowerCase();
	            		break;
	            	}            		
	            	else 
	            		continue;	            	
	            }
	            System.out.println("Ваш ход!");
	            Scanner input = new Scanner(System.in);	           
                while(true) {
                	String userTown = input.nextLine();   	            
    	            firstLetter = userTown.substring(0, 1).toLowerCase();
                	 if (!lastLetter.equals(firstLetter))
                     {
                     	System.out.println("Ты играешь не по правилам. Попробуй еще раз!");
                     	continue;
                     }
     	            if (usedCities.indexOf(userTown) == -1)
                 	{  	            	
                        usedCities.add(userTown);
                        lastLetter = userTown.substring(userTown.length() - 1, userTown.length()).toUpperCase();
                 		break;
                 	}            		
                 	else 
                 	{
                 		System.out.println("Ты играешь не по правилам. Попробуй еще раз!");                     
                 		continue;
                 	}
    	            
                }
                System.out.println("Мой ход!");
                  
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		}
		

	}


}
