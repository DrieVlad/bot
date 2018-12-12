package boostbrain;

public class CriticismReaction {
    public static Firebase firebase;
    private static String[] swearings;

    public CriticismReaction(Statistic stat){
        firebase = stat.firebase;
    }

    public Boolean isCritics(Long chatId, String userInput) {
        boolean checkCriticPhrase = false;
        swearings = firebase.getCriticismPhraseFromDatabase();

        for (String swearing : swearings) {
            if(userInput.contains(swearing)) {
                checkCriticPhrase = true;

            }
        }

        return checkCriticPhrase;
    }
}
