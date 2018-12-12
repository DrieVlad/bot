package boostbrain;

import java.util.ArrayList;

public class Admin {
    private Firebase firebase;
    private Bot bot;

    Admin(Firebase fire, Bot b){
        firebase = fire;
        bot = b;
    }

    public Message adminManage(Message userInput){
        Message message = new Message();
        ArrayList<String> rowButtons = new ArrayList<>();
        ArrayList<ArrayList<String>> keyboard = new ArrayList<>();

        rowButtons.add("Все четко");
        rowButtons.add("Пропустить");
        keyboard.add(rowButtons);
        bot.setHelpAndTired(new ArrayList<>(), keyboard);
        message.setKeyboard(keyboard);
        String[] botAnswer  = firebase.getFeedbackFromDatabase();
        System.out.println(botAnswer);
        message.setTextMessage(botAnswer[1]);
        return message;
    }
}
