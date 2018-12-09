package boostbrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TownsContent
{
    Map<String, ArrayList<String>> dictContentForGames = new HashMap<String, ArrayList<String>>();
    
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