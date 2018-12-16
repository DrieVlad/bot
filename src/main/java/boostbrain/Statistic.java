package boostbrain;

import java.util.List;

public class Statistic{
    public static Firebase firebase;

    Statistic(Firebase dataBase){
        firebase = dataBase;
    }
    public String win;
    public String los;

    public void updateDB(String chatId){
        firebase.getDatafromDatabase(chatId, this);
    }

    public Message get(String userId) {
        Message message = new Message();
        List<WinLose> topStats = firebase.takeFiveFirst();
        String top = "";
        if(topStats.size() == 0)
            top = "fuck, it's broken";
        else {
            for (int i = 0; i < topStats.size(); i++) {
                top += topStats.get(i).username + ": ➕ " +
                        topStats.get(i).win + " ➖ " +
                        topStats.get(i).lose + "\n";
            }
        }
        message.setTextMessage(top);

        return message;
    }


    public void set(String chatId, String userName, boolean chek_win) {
        int wins = Integer.parseInt(win);
        int lose = Integer.parseInt(los);
        System.out.println(chek_win);
        System.out.println(lose);
        if (chek_win){
            wins++;
        }
        else {
            lose++;

        }
        System.out.println("sdvm" + lose);
        firebase.saveDataInDatabase(chatId, userName, wins, lose);
        //firebase.topStats.clear();
        //firebase.takeFiveFirst(this);
    }
}

