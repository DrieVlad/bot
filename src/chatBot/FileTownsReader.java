package chatBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FileTownsReader implements TownsReader
{
    public Map<String, ArrayList<String>> dictTowns = new HashMap<String, ArrayList<String>>();
    private static InOut inOut = new InOut();
	
	public void getTowns()
	{

        String path = System.getProperty("user.dir") + "\\chatBot\\Города\\";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) 
		{
		    if (file.isFile()) 
		    {
		    	try
		    	{
		    	   String fileName = file.getName();
		    	   String letter = String.valueOf(fileName.charAt(0));
		    	   FileInputStream fstream = new FileInputStream(path + fileName);
		    	   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		    	   String strLine;
		    	   ArrayList<String> towns = new ArrayList<String>();
		    	   while ((strLine = br.readLine()) != null)
		    	   {
		    		   towns.add(strLine);			    		   
		    	   }
		    	   dictTowns.put(letter, towns);
		    	   br.close();
		    	}
		    	catch (IOException e)
		    	{
		    		inOut.writeData("Ошибка");
		        }
		    }
		}
	}
	
	public String nextTown(String lastLetter)
	{
		Random ran = new Random();
		lastLetter = lastLetter.toLowerCase();
		ArrayList<String> allTownsOnLetter = dictTowns.get(lastLetter);
		int linesCount = (int)allTownsOnLetter.size();		
	    int count = ran.nextInt(linesCount);
	    String town = allTownsOnLetter.get(count);
	    return town;
	}
}