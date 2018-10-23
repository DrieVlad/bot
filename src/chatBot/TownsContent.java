package chatBot;

import java.util.ArrayList;
import java.util.Random;


public class TownsContent extends GameContentFromFile
{	
	public TownsContent(String folderName)
	{
        super(folderName);
	}
	
	public String nextTown(String firstLetter)
	{		
		Random ran = new Random();
		firstLetter = firstLetter.toLowerCase();
		ArrayList<String> allTownsOnLetter = dictContentForGames.get(firstLetter);
		int linesCount = (int)allTownsOnLetter.size();		
	    int count = ran.nextInt(linesCount);
	    String town = allTownsOnLetter.get(count);
	    return town;
	}
}
