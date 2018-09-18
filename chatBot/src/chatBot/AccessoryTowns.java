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
			Path pathToCities = Paths.get(System.getProperty("user.dir") + "\\src\\chatBot\\Города\\"
		        + lastLetter + ".txt");
			
		    Stream<String> lines = Files.lines(pathToCities, Charset.defaultCharset());
		    
    	    int linesCount = (int)lines.count();		
    	    int count = ran.nextInt(linesCount);
    	    
		    String line = Files.lines(pathToCities, Charset.defaultCharset()).skip(count).findFirst().get();
		    
		    return line.toLowerCase();
		}
		catch (FileNotFoundException e) 
		{
            e.printStackTrace();
            
            System.exit(1);
            return "";
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
            
            System.exit(1);
            return "";
        }		
	}
	public String check(String lastLetter, String line)
	{
        //Если город оканчивается на ЬЪЫЙЁ, то нужно вводить город на предпоследнюю букву
		//Если город оканчивается на ЫЙ, то нужно вводить город на препредпоследнюю букву
        //String lastLetter = "";
		if (line.substring(line.length() - 2, line.length()).equals("ый")) 
		{
			lastLetter = line.substring(line.length() - 3, line.length() - 2);
		}	
		else if (lastLetter.equals("ь")
				|| lastLetter.equals("ъ")
				|| lastLetter.equals("ы")
				|| lastLetter.equals("й")
				|| lastLetter.equals("ё"))
		{
        	lastLetter = line.substring(line.length() - 2, line.length() - 1);
		}
		return lastLetter;
	}
	
	public boolean checkCity(String userCity, String firstLetter)
	{
		//Этот метод проверяет то, что вводимая пользователем строка является городом.
		try
		{
			String lineInFile = "";
			boolean isItACity = false;
			LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(System.getProperty("user.dir")
					+ "\\src\\chatBot\\Города\\" + firstLetter + ".txt")));
			
	        while((lineInFile = lnr.readLine()) != null)
	        {
<<<<<<< HEAD
<<<<<<< HEAD
	        	lineInFile = lineInFile.toLowerCase();
	            if (lineInFile.equals(userCity)
	            		|| lineInFile.equals(Character.toString(userCity.charAt(0))
	            				+ userCity.substring(1, userCity.length())))
=======
	            if (lineInFile.toLowerCase().equals(userCity))
>>>>>>> 84a0ff709626e5a1643aeab96e4477344929b40a
=======
	            if (lineInFile.toLowerCase().equals(userCity))
>>>>>>> 84a0ff709626e5a1643aeab96e4477344929b40a
	            {
	            	isItACity = true;
	            	break;
	            } 
	        }
	        lnr.close();
	        return isItACity;
		}		
		catch (IOException e) 
        {
           e.printStackTrace();
           System.exit(1);
           return false;
        } 
	}

	
	public Boolean checkWordDictionary(String city, ArrayList<String> usedCities)
	{
		//Проверка на то, что слово еще не называлось в игре.
<<<<<<< HEAD
<<<<<<< HEAD
		if (usedCities.indexOf(city) == -1
				&& usedCities.indexOf(city.toLowerCase()) == -1 
				&& usedCities.indexOf(Character.toString(city.charAt(0))
						+ city.substring(1, city.length())) == -1)
     	{  	            	
            usedCities.add(Character.toString(city.charAt(0))
            		+ city.substring(1, city.length()));                       
=======
		if (usedCities.indexOf(city.toLowerCase()) == -1)
     	{  	            	
            usedCities.add(city.toLowerCase());                       
>>>>>>> 84a0ff709626e5a1643aeab96e4477344929b40a
=======
		if (usedCities.indexOf(city.toLowerCase()) == -1)
     	{  	            	
            usedCities.add(city.toLowerCase());                       
>>>>>>> 84a0ff709626e5a1643aeab96e4477344929b40a
            return true;
     	}
        return false;
	}

}
