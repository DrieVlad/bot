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
			Path pathToCities = Paths.get(System.getProperty("user.dir") + "\\chatBot\\������\\" + lastLetter + ".txt");
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
        //���� ����� ������������ �� ���ɨ, �� ����� ������� ����� �� ������������� �����
		//���� ����� ������������ �� ��, �� ����� ������� ����� �� ���������������� �����
        //String lastLetter = "";
		if (line.substring(line.length() - 2, line.length()).toUpperCase().equals("��"))
			lastLetter = line.substring(line.length() - 3, line.length() - 2).toUpperCase();
		else if (lastLetter.equals("�") || lastLetter.equals("�") || lastLetter.equals("�") || lastLetter.equals("�") || lastLetter.equals("�"))
        	lastLetter = line.substring(line.length() - 2, line.length() - 1).toUpperCase();
		return lastLetter;
	}
	
	public String checkCity(String userCity, String firstLetter)
	{
		//���� ����� ��������� ��, ��� �������� ������������� ������ �������� �������.
		try
		{
			String lineInFile = "";
			String isItACity = "";
			LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\chatBot\\������\\" + firstLetter.toUpperCase() + ".txt")));
	        while((lineInFile = lnr.readLine()) != null)
	        {
	            if (lineInFile.toLowerCase().equals(userCity))
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
		//�������� �� ��, ��� ����� ��� �� ���������� � ����.
		if (usedCities.indexOf(city.toLowerCase()) == -1)
     	{  	            	
            usedCities.add(city.toLowerCase());                       
            return true;
     	}
        return false;
	}

}
