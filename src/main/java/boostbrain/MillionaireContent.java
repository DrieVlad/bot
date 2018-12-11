package boostbrain;

public class MillionaireContent
{
    private Millionaire millionaire;

    public MillionaireContent(Millionaire million)
    {
        millionaire = million;
    }

    public AskMillionaire nextAsk(int level)
    {
        String qestionUp = millionaire.firebase.getPhraseFromDatabase("вопросы", Integer.toString(level));

        System.out.println(level);
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
