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
			Path pathToCities = Paths.get(System.getProperty("user.dir") + "\\src\\chatBot\\������\\"
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
        //���� ����� ������������ �� ���ɨ, �� ����� ������� ����� �� ������������� �����
		//���� ����� ������������ �� ��, �� ����� ������� ����� �� ���������������� �����
        //String lastLetter = "";
		if (line.substring(line.length() - 2, line.length()).equals("��")) 
		{
			lastLetter = line.substring(line.length() - 3, line.length() - 2);
		}	
		else if (lastLetter.equals("�")
				|| lastLetter.equals("�")
				|| lastLetter.equals("�")
				|| lastLetter.equals("�")
				|| lastLetter.equals("�"))
		{
        	lastLetter = line.substring(line.length() - 2, line.length() - 1);
		}
		return lastLetter;
	}
	
	public boolean checkCity(String userCity, String firstLetter)
	{
		//���� ����� ��������� ��, ��� �������� ������������� ������ �������� �������.
		try
		{
			String lineInFile = "";
			boolean isItACity = false;
			LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(System.getProperty("user.dir")
					+ "\\src\\chatBot\\������\\" + firstLetter + ".txt")));
			
	        while((lineInFile = lnr.readLine()) != null)
	        {
	        	lineInFile = lineInFile.toLowerCase();
	            if (lineInFile.equals(userCity)
	            		|| lineInFile.equals(Character.toString(userCity.charAt(0))
	            				+ userCity.substring(1, userCity.length())))

	            if (lineInFile.toLowerCase().equals(userCity))
	            if (lineInFile.toLowerCase().equals(userCity))
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
		//�������� �� �� ��� ����� �� �����������
		if (usedCities.indexOf(city) == -1 && usedCities.indexOf(city.toLowerCase()) == -1 
				&& usedCities.indexOf(Character.toString(city.charAt(0))
						+ city.substring(1, city.length())) == -1)
     	{  	            	
            usedCities.add(Character.toString(city.charAt(0))
            		+ city.substring(1, city.length()));                       
            return true;
     	}
        return false;
	}
}
