package boostbrain;

import java.util.HashMap;
import java.util.Map;
import java.lang.Thread;

public class MillionaireContent extends GameContentFromFile
{
    public Map<Integer, String> dictQuestion = new HashMap<Integer, String>();
    private Millionaire millionaire;

    public MillionaireContent(String folderName, Millionaire million)
    {
        super(folderName);
        millionaire = million;

    }

    public AskMillionaire nextAsk(int level)
    {
      //  Random ran = new Random();
      //  ArrayList<String> allAskLevel = dictContentForGames.get(Integer.toString(level));
       // int linesCount = (int)allAskLevel.size();
        //int count = ran.nextInt(linesCount);
      // String qestionUp = allAskLevel.get(count);
        //System.out.println(qestionUp);


        try {
            synchronized (new Object()){
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(level);
        System.out.println(dictQuestion.toString());
        String qestionUp = dictQuestion.get(level);
        System.out.println(dictQuestion.get("level " +level));
        System.out.println(qestionUp);
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
