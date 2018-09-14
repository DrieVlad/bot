package chatBot;


import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.*;
import java.nio.charset.Charset;
import java.lang.Character;

public class Towns {
	private static ArrayList<String> usedCities = new ArrayList<String>();
	private static String firstLetter = "";
	private static String lastLetter = "";
	
		
	public static void gameOfCities()
	{
		String alphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЭЮЯ";
		Random ran = new Random();
		int linesCount = 0;
		int count = ran.nextInt(28);
		lastLetter = alphabet.substring(count, count+1);
		String line = "";
		while(true)
		{
			try 
			{            
				Path pathToCities = Paths.get(System.getProperty("user.dir") + "\\chatBot\\Города\\" + lastLetter + ".txt");
				Stream<String> lines = Files.lines(pathToCities, Charset.defaultCharset());
            	linesCount = (int)lines.count();		
	            //String firstLetter = "";
	            Boolean flag = false;
	            while (flag != true ) 
	            {	            	                              			
	            	count = ran.nextInt(linesCount);
					line = Files.lines(pathToCities, Charset.defaultCharset()).skip(count).findFirst().get();
					if (checkTheWordInTheDictionary(line))
	            	{            		
                        System.out.println(line);
                        lastLetter = check(lastLetter, line);
        				flag = true;
	            		break;
	            	}  
	            	else 
	            		continue;	            	
	            }
	            if (flag == false)
	            {
	            	System.out.println("Я не знаю больше городов на букву " + lastLetter + ". Вы выиграли!");
	            }
	            lines.close();
	            System.out.println("Твой ход на букву " + lastLetter + "!");
	                   
                while(true) {
                	Scanner input = new Scanner(System.in);	  
                	String userTown = "";
                	if (input.hasNextLine())
                	{
                		String noUseLine = input.nextLine();
                		userTown = noUseLine;
                		if (Skeleton.help(noUseLine))
                			continue;               	                		
                	}              	
    	            firstLetter = userTown.substring(0, 1);    	           
    	            if (!(lastLetter.equals(firstLetter) || lastLetter.toLowerCase().equals(firstLetter)))
                    {
                     	System.out.println("Ты играешь не по правилам. Назови город на букву " + lastLetter.toUpperCase());
                     	continue;
                    }

    	            String isItACity = сheckCity(userTown, firstLetter);
    	            if (isItACity.equals("Error"))
    	            {
    	            	System.exit(0);
    	            }
    	            if (!isItACity.equals("+"))
    	            {
    	                System.out.println("Не жульничай! Ты называешь не город:)))");
	                    continue;
    	            }
    	                
    	            if (checkTheWordInTheDictionary(userTown))
    	            {
    	            	lastLetter = check(lastLetter, userTown);
                 		break;
    	            }        		
                 	else 
                 	{
                 		System.out.println("Ай-яй-яй! Это слово уже было названо. Попробуй еще раз!");                     
                 		continue;
                 	} 
     	            //input.close(); 
                }                
                System.out.println("Мой ход!");             
	        } 
			catch (FileNotFoundException e) 
			{
	            e.printStackTrace();
	        } 
			catch (IOException e) 
			{
	            e.printStackTrace();
	        }
		}		
	}
	
	public static String check(String lastLetter, String line)
	{
        //Если город оканчивается на ЬЪЫЙЁ, то нужно вводить город на предпоследнюю букву
		//Если город оканчивается на ЫЙ, то нужно вводить город на препредпоследнюю букву
		if (line.substring(line.length() - 2, line.length()).toUpperCase().equals("ЫЙ"))
			lastLetter = line.substring(line.length() - 3, line.length() - 2).toUpperCase();
		else if (lastLetter.equals("Ь") || lastLetter.equals("Ъ") || lastLetter.equals("Ы") || lastLetter.equals("Й") || lastLetter.equals("Ё"))
        	lastLetter = line.substring(line.length() - 2, line.length() - 1).toUpperCase();
		return lastLetter;
	}
	
	public static String сheckCity(String userCity, String firstLetter)
	{
		//Этот метод проверяет то, что вводимая пользователем строка является городом.
		try
		{
			String lineInFile = "";
			String isItACity = "";
			LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\chatBot\\Города\\" + firstLetter.toUpperCase() + ".txt")));
	        while((lineInFile = lnr.readLine()) != null)
	        {
	            if (lineInFile.equals(userCity) || lineInFile.equals(Character.toString(userCity.charAt(0)).toUpperCase() + userCity.substring(1, userCity.length())))
	            {
	            	isItACity += "+";
	            } 
	        }
	        lnr.close();
	        return isItACity;
		}		
		catch (IOException e) 
        {
           e.printStackTrace();
           return "Error";
        } 
	}
	
	public static Boolean checkTheWordInTheDictionary(String userCity)
	{
		//Проверка на то, что слово еще не называлось в игре.
		if (usedCities.indexOf(userCity) == -1 && usedCities.indexOf(userCity.toLowerCase()) == -1 
				&& usedCities.indexOf(Character.toString(userCity.charAt(0)).toUpperCase() + userCity.substring(1, userCity.length())) == -1)
     	{  	            	
            usedCities.add(Character.toString(userCity.charAt(0)).toUpperCase() + userCity.substring(1, userCity.length()));                       
            lastLetter = userCity.substring(userCity.length() - 1, userCity.length()).toUpperCase();
            return true;
     	}
        return false;
	}
}