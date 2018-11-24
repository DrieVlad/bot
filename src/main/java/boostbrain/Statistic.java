package boostbrain;

import java.util.*;

public class Statistic{

    public static Map<String, Integer> win_Users = new HashMap<String, Integer>();
    public static Map<String, Integer> loos_Users = new HashMap<String, Integer>();
    public static Firebase firebase = new Firebase();

    Statistic()
    {

    }


    public static Message get() {
        Message message = new Message();
        message.setTextMessage(win_Users.toString() + loos_Users.toString());
        return message;
    }

    public static Integer getWin_Users(String user_name){
        return win_Users.get(user_name);
    }

    public static Integer getLos_Users(String user_name){
        return loos_Users.get(user_name);
    }

    public static void set(String user_name, boolean chek_win) {

        if (win_Users.containsKey(user_name))
        {
            Integer win_User = win_Users.get(user_name);
            Integer los_User = loos_Users.get(user_name);
            if (chek_win)
            {
                win_User++;
            }
            else
            {
                los_User++;
            }
            win_Users.put(user_name, win_User);
            loos_Users.put(user_name, los_User);
        }
        else
        {
            Integer win_User = 0;
            Integer los_user = 0;
            if (chek_win)
            {
                win_User = 1;
                los_user = 0;
            }
            else
            {
                win_User = 0;
                los_user = 1;
            }
            win_Users.put(user_name, win_User);
            loos_Users.put(user_name, los_user);

        }
        List list = new ArrayList(win_Users.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a,
                               Map.Entry<String, Integer> b)
            {
                return a.getValue() - b.getValue();
            }
        });
        firebase.saveDataInDatabase(user_name);
    }
}

