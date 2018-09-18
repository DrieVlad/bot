package chatBot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Random;

public class AccessoryTowns {
	
	public  String getCityFromFile(Random ran, String lastLetter)
	{
		try
		{
			Path pathToCities = Paths.get(System.getProperty("user.dir") + "\\chatBot\\Города\\" + lastLetter + ".txt");
		    Stream<String> lines = Files.lines(pathToCities, Charset.defaultCharset());
    	    int linesCount = (int)lines.count();		
    	    int count = ran.nextInt(linesCount);
		    String line = Files.lines(pathToCities, Charset.defaultCharset()).skip(count).findFirst().get();
		    return line;
		}
		catch (FileNotFoundException e) 
		{
            e.printStackTrace();
            return "error";
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
            return "error";
        }		
	}
	public String check(String lastLetter, String line)
	{
        //Если город оканчивается на ЬЪЫЙЁ, то нужно вводить город на предпоследнюю букву
		//Если город оканчивается на ЫЙ, то нужно вводить город на препредпоследнюю букву
        //String lastLetter = "";
		if (line.substring(line.length() - 2, line.length()).toUpperCase().equals("ЫЙ"))
			lastLetter = line.substring(line.length() - 3, line.length() - 2).toUpperCase();
		else if (lastLetter.equals("Ь") || lastLetter.equals("Ъ") || lastLetter.equals("Ы") || lastLetter.equals("Й") || lastLetter.equals("Ё"))
        	lastLetter = line.substring(line.length() - 2, line.length() - 1).toUpperCase();
		return lastLetter;
	}
	
	public String checkCity(String userCity, String firstLetter)
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

	
	public Boolean checkTheWordInTheDictionary(String city, ArrayList<String> usedCities)
	{
		//Проверка на то, что слово еще не называлось в игре.
		if (usedCities.indexOf(city) == -1 && usedCities.indexOf(city.toLowerCase()) == -1 
				&& usedCities.indexOf(Character.toString(city.charAt(0)).toUpperCase() + city.substring(1, city.length())) == -1)
     	{  	            	
            usedCities.add(Character.toString(city.charAt(0)).toUpperCase() + city.substring(1, city.length()));                       
            return true;
     	}
        return false;
	}

}
