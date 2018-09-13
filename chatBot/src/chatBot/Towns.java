package chatBot;


import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.*;
import java.io.File;
import java.nio.charset.Charset;
import java.lang.Character;

public class Towns {
	private static ArrayList<String> usedCities = new ArrayList<String>();
	
	public static String check(String lastLetter, String line)
	{
		if (line.substring(line.length() - 2, line.length()).toUpperCase().equals("ЫЙ"))
			lastLetter = line.substring(line.length() - 3, line.length() - 2).toUpperCase();
		else if (lastLetter.equals("Ь") || lastLetter.equals("Ъ") || lastLetter.equals("Ы") || lastLetter.equals("Й") || lastLetter.equals("Ё"))
        	lastLetter = line.substring(line.length() - 2, line.length() - 1).toUpperCase();
		return lastLetter;
	}
	
	public static void cities()
	{
		String alphabet = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЮЯ";
		Random ran = new Random();
		int linesCount = 0;
		int n = ran.nextInt(28);
		String lastLetter = alphabet.substring(n, n+1);
		String line = "";
		while(true)
		{
			try {
	            File file = new File(System.getProperty("user.dir") + "\\Города\\" + lastLetter + ".txt");
	            FileReader fr = new FileReader(file);
	            BufferedReader reader = new BufferedReader(fr);
	            line = reader.readLine();
				Path path = Paths.get(System.getProperty("user.dir") + "\\Города\\" + lastLetter + ".txt");
				Stream<String> lines = Files.lines(path, Charset.defaultCharset());
				linesCount = (int)lines.count();				
				n = ran.nextInt(linesCount);
				line = Files.lines(path, Charset.defaultCharset()).skip(n).findFirst().get();
	            String firstLetter = "";
	            Boolean flag = false;
	            while (flag != true ) {
	            	                  
                    //System.out.println("Hi");
	            	if (usedCities.indexOf(line) == -1)
	            	{
	            		System.out.println(line);
                        usedCities.add(line);
                        lastLetter = line.substring(line.length() - 1, line.length()).toUpperCase();
                        lastLetter = check(lastLetter, line);
        				flag = true;
	            		break;
	            	}            		
	            	else 
	            		continue;	            	
	            }
	            if (line == null)
	            {
	            	System.out.println("Я не знаю больше городов на букву " + lastLetter + ". Вы выиграли!");
	            }
	            System.out.println("Ваш ход на букву " + lastLetter + "!");
	            Scanner input = new Scanner(System.in);
                while(true) {
                	String userTown = input.nextLine(); 
                	Boolean chekHelp = Skeleton.help(userTown);
                	if (chekHelp)
                		System.out.println("Ничего страшного, продолжай");
    	            firstLetter = userTown.substring(0, 1);
                	 if (!(lastLetter.equals(firstLetter)|| lastLetter.toLowerCase().equals(firstLetter) ))
                     {
                     	System.out.println("Ты играешь не по правилам. Назови город на букву " + lastLetter.toUpperCase());
                     	continue;
                     }
     	            if (usedCities.indexOf(userTown) == -1 && usedCities.indexOf(userTown.toLowerCase()) == -1 && usedCities.indexOf(Character.toString(userTown.charAt(0)).toUpperCase() + userTown.substring(1, userTown.length())) == -1)
                 	{  	            	
                        usedCities.add(Character.toString(userTown.charAt(0)).toUpperCase() + userTown.substring(1, userTown.length()));                       
                        lastLetter = userTown.substring(userTown.length() - 1, userTown.length()).toUpperCase();
                        lastLetter = check(lastLetter, userTown);
                 		break;
                 	}            		
                 	else 
                 	{
                 		System.out.println("Ай-яй-яй. Это слово уже было названо. Попробуй еще раз!");                     
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
