package chatBot;

import java.util.ArrayList;
import java.util.Random;


public class TownsContent extends GameContentFromFile
{	
	public TownsContent(String folderName)
	{
        super(folderName);
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