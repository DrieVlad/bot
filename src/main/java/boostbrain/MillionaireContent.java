package boostbrain;

import java.util.Random;
import java.util.ArrayList;


public class MillionaireContent extends GameContentFromFile
{    
    public MillionaireContent(String folderName)
    {
        super(folderName);
    }

    public AskMillionaire nextAsk(int level) 
    {
        Random ran = new Random();
        ArrayList<String> allAskLevel = dictContentForGames.get(Integer.toString(level));
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
