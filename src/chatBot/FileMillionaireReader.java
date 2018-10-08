package chatBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FileMillionaireReader 
{
	public Map<String, ArrayList<String>> dictTowns = new HashMap<String, ArrayList<String>>();
	
	public void getAsk()
	{

        String path = System.getProperty("user.dir") + "\\chatBot\\Вопросы Миллионер\\";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) 
		{
		    if (file.isFile()) 
		    {
		    	try
		    	{
		    	   String fileName = file.getName();
		    	   String nemberLevel = String.valueOf(fileName.substring(0, fileName.indexOf('.')));
		    	   FileInputStream fstream = new FileInputStream(path + fileName);
		    	   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		    	   String strLine;
		    	   ArrayList<String> asks = new ArrayList<String>();
		    	   while ((strLine = br.readLine()) != null)
		    	   {
		    		   asks.add(strLine);			    		   
		    	   }
		    	   dictTowns.put(nemberLevel, asks);
		    	   br.close();
		    	}
		    	catch (IOException e)
		    	{
		    		InOut.printer.writeDataString("Ошибка");
		        }
		    }
		}
	}

	public AskMillionaire nextAsk(int level) 
	{
		Random ran = new Random();
		ArrayList<String> allAskLevel = dictTowns.get(Integer.toString(level));
		int linesCount = (int)allAskLevel.size();		
	    int count = ran.nextInt(linesCount);
	    String qestionUp = allAskLevel.get(count);
	    String[] parsAsk = qestionUp.split("/");
	    AskMillionaire askAfter = new AskMillionaire();
	    askAfter.question = parsAsk[0];
	    for (int i = 1; i < 5; i++)
	    {
	    	askAfter.answers[i - 1] = parsAsk[i];
	    	if(parsAsk[i].startsWith("#"))
	    	{
	    		askAfter.trueAnswer = i - 1;
	    		askAfter.answers[i - 1] = parsAsk[i].substring(1);
	    	}
	    }
	    return askAfter;
	}
}
