package chatBot;


<<<<<<< HEAD
import java.util.Scanner;
import java.io.IOException;
import java.io.*;
=======
import java.nio.file.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.util.Random.*;
import java.util.Random;
>>>>>>> 54a823d2eec786c27c1076de0aa438df042a9d88
import java.util.ArrayList;
import java.util.stream.*;
import java.io.File;
import java.nio.charset.Charset;
import java.lang.Character;
import java.util.Arrays;

public class Towns {
	private static ArrayList<String> usedCities = new ArrayList<String>();
	
	public static String check(String lastLetter, String line)
	{
		if (line.substring(line.length() - 2, line.length()).toUpperCase().equals("��"))
			lastLetter = line.substring(line.length() - 3, line.length() - 2).toUpperCase();
		else if (lastLetter.equals("�") || lastLetter.equals("�") || lastLetter.equals("�") || lastLetter.equals("�") || lastLetter.equals("�"))
        	lastLetter = line.substring(line.length() - 2, line.length() - 1).toUpperCase();
		return lastLetter;
	}
	
	public static void main(String[] args)
	{
		String alphabet = "����������������������������";
		Random ran = new Random();
		int linesCount = 0;
		int n = ran.nextInt(5);
		String lastLetter = alphabet.substring(n, n+1);
		String line = "";
		while(true)
		{
			try {
<<<<<<< HEAD
	            File file = new File(lastLetter + ".txt");
	            FileReader fr = new FileReader(file);
	            BufferedReader reader = new BufferedReader(fr);
	            String line = reader.readLine();
=======
				Path path = Paths.get(System.getProperty("user.dir") + "\\chatBot\\������\\" + lastLetter + ".txt");
				Stream<String> lines = Files.lines(path, Charset.defaultCharset());
				linesCount = (int)lines.count();				
				n = ran.nextInt(linesCount);
				line = Files.lines(path, Charset.defaultCharset()).skip(n).findFirst().get();
                //System.out.println(line);
	            //File file = new File("C:\\Users\\1\\Chat-Bot\\bot\\chatBot\\" + lastLetter + ".txt");
	            //FileReader fr = new FileReader(file);
	            //BufferedReader reader = new BufferedReader(fr);
	            //String line = reader.readLine();
	            //System.out.println(line);
>>>>>>> 54a823d2eec786c27c1076de0aa438df042a9d88
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
	            	System.out.println("� �� ���� ������ ������� �� ����� " + lastLetter + ". �� ��������!");
	            }
	            System.out.println("��� ��� �� ����� " + lastLetter + "!");
	            Scanner input = new Scanner(System.in);	           
                while(true) {
                	String userTown = input.nextLine();   	            
    	            firstLetter = userTown.substring(0, 1);
                	 if (!(lastLetter.equals(firstLetter)|| lastLetter.toLowerCase().equals(firstLetter) ))
                     {
                     	System.out.println("�� ������� �� �� ��������. ������ ����� �� ����� " + lastLetter.toUpperCase());
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
                 		System.out.println("��-��-��. ��� ����� ��� ���� �������. �������� ��� ���!");                     
                 		continue;
                 	}    	      
                }
                System.out.println("��� ���!");
                  
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}		
	}
}
