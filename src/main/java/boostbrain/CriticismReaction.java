package boostbrain;

public class CriticismReaction {
    public static Firebase firebase;

    public CriticismReaction(Statistic stat){
        firebase = stat.firebase;
    }

    public void criticsCheck(Long chatId, String userInput) {
        boolean fuck = false;
        Object event = new Object();
        String swearing = firebase.getCriticismPhraseFromDatabase();

        for (String swear : userInput.split(" "))
        {
            System.out.println(swear);
            if (swearing.contains(swear)){
                fuck = true;
            }

        }

        if(fuck)
        {
            System.out.println(userInput);
            firebase.saveCriticiszmInDatabase(Long.toString(chatId) + userInput, userInput);
        }
    }
}
