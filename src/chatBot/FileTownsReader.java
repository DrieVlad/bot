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

public class FileTownsReader
{
	public Map<String, ArrayList<String>> dictContentForGames = new HashMap<String, ArrayList<String>>();
	
	FileTownsReader(String folderName)
	{
        String path = System.getProperty("user.dir") + "\\chatBot\\" + folderName + "\\";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) 
		{
		    if (file.isFile()) 
		    {
		    	String fileName = file.getName();
		    	String letter = String.valueOf(fileName.substring(0, fileName.indexOf('.')));
		    	try(FileInputStream fstream = new FileInputStream(path + fileName))
		    	{		    	 
		    	   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		    	   String strLine;
		    	   ArrayList<String> towns = new ArrayList<String>();
		    	   while ((strLine = br.readLine()) != null)
		    	   {
		    		   towns.add(strLine);			    		   
		    	   }
		    	   dictContentForGames.put(letter, towns);	
		    	   br.close();
		    	}
		    	catch (IOException e)
		    	{
		    		System.out.println("Ошибка");
		        }
		    }
		}
	}
	
	public String nextTown(String lastLetter)
	{		
		Random ran = new Random();
		lastLetter = lastLetter.toLowerCase();
		ArrayList<String> allTownsOnLetter = dictContentForGames.get(lastLetter);
		int linesCount = (int)allTownsOnLetter.size();		
	    int count = ran.nextInt(linesCount);
	    String town = allTownsOnLetter.get(count);
	    return town;
	}
}
