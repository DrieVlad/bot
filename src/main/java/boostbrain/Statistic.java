package boostbrain;

public class Statistic{
    private static Firebase firebase;

    Statistic(Firebase dataBase){
        firebase = dataBase;
    }
    public String win = "-1";
    public String los = "-1";

    public void updateDB(String chatId){
        firebase.getDatafromDatabase(chatId, this);
        System.out.println(los);
        System.out.println(win);
    }

    public Message get(String userId) {
        Message message = new Message();
        firebase.getDatafromDatabase(userId, this);


        return message;
    }


    public void set(String user_name, boolean chek_win) {
        System.out.println(los);
        System.out.println(win);
        int wins = Integer.parseInt(win);
        int lose = Integer.parseInt(los);
        if (chek_win){
            wins++;
        }
        else {
            lose++;
        }
        firebase.saveDataInDatabase(user_name, wins, lose);
    }
}

