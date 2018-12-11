package boostbrain;

public class CriticismReaction {
    public static Firebase firebase;
    private static String swearing;

    public CriticismReaction(Statistic stat){
        firebase = stat.firebase;
    }

    public Boolean isCritics(Long chatId, String userInput) {
        boolean checkCriticPhrase = false;
        swearing = firebase.getCriticismPhraseFromDatabase();

        for (String swear : userInput.split(" "))
        {
            System.out.println(swear);
            if (swearing.contains(swear)){
                checkCriticPhrase = true;
            }

        }

        if(checkCriticPhrase)
        {
            System.out.println(userInput);
            firebase.saveCriticiszmInDatabase(Long.toString(chatId) + userInput, userInput);
        }
        return checkCriticPhrase;
    }
}
