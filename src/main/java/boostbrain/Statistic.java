package boostbrain;

public class Statistic{
    public static Firebase firebase;

    Statistic(Firebase dataBase){
        firebase = dataBase;
    }
    public String win = "-1";
    public String los = "-1";

    public void updateDB(String chatId){
        firebase.getDatafromDatabase(chatId, this);
    }

    public Message get(String userId) {
        Message message = new Message();
        firebase.topStats.clear();
        firebase.takeFiveFirst(this);
        try {
            synchronized (new Object()){
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String top = "";

        if(firebase.topStats.size() == 0)
            top = "fuck, it's broken";
        else {
            for (int i = firebase.topStats.size() - 2; i >= 0; i--) {
                top += firebase.topStats.get(i).username + ": ➕ " +
                        firebase.topStats.get(i).win + " ➖ " +
                        firebase.topStats.get(i).lose + "\n";
            }

            top += "... \nyou: ➕ " + firebase.topStats.get(firebase.topStats.size() - 1).win +
                    " ➖ " + firebase.topStats.get(firebase.topStats.size() - 1).lose + "\n";
        }
        message.setTextMessage(top);

        return message;
    }


    public void set(String chatId, String userName, boolean chek_win) {
        int wins = Integer.parseInt(win);
        int lose = Integer.parseInt(los);
        if (chek_win){
            wins++;
        }
        else {
            lose++;
        }
        firebase.saveDataInDatabase(chatId, userName, wins, lose);
        //firebase.topStats.clear();
        //firebase.takeFiveFirst(this);
    }
}

