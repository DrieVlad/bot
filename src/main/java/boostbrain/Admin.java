package boostbrain;

import java.util.ArrayList;

public class Admin {
    private Firebase firebase;
    private Bot bot;
    String[] botAnswer = new String[2];

    Admin(Firebase fire, Bot b){
        firebase = fire;
        bot = b;
    }

    public Message adminManage(Message userInput){
        Message message = new Message();
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();


        if (userInput.getTextMessage().equals("все четко") && botAnswer[0] != null){
            firebase.removeFeedback(botAnswer[0]);
        }
        rowButtons.add("Все четко");
        rowButtons.add("Пропустить");
        keyboard.add(rowButtons);
        bot.setHelpAndTired(new ArrayList<>(), keyboard);
        message.setKeyboard(keyboard);

        botAnswer =  firebase.getFeedbackFromDatabase();
        if (botAnswer[1] != null){
            message.setTextMessage(botAnswer[1]);
        }
        else {
            message.setTextMessage(firebase.getPhraseFromDatabase("фразы","админ"));
        }


        return message;
    }
}
