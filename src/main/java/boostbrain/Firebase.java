package boostbrain;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class Firebase {
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    final static Random random = new Random();
    private ChildEventListener eventListener;
    private Query childReference;
    public List<WinLose> topStats = new ArrayList<WinLose>();

    public void initFirebase() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("C:\\Users\\vladd\\Documents\\GitHub\\chatbot-bce26-firebase-adminsdk-fh9e3-2730ceecc9.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://chatbot-bce26.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/");

    }

    public final synchronized void getDatafromDatabase(String userName , Statistic stats)
    {
        DatabaseReference childReference = databaseReference.child("users").child(userName);
        Object event = new Object();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();
                System.out.println(b);
                for (DataSnapshot user: dataSnapshot.getChildren())
                {
                    String chatId = user.getKey();
                    if (userName.equals(chatId))
                    {
                        Object name = dataSnapshot.child("username").getValue();
                        Object wins = dataSnapshot.child("wins").getValue();
                        Object fails = dataSnapshot.child("fails").getValue();
                        stats.win = wins.toString();
                        stats.los = fails.toString();
                        WinLose winLose = new WinLose();
                        winLose.win = wins.toString();
                        winLose.lose = fails.toString();
                        winLose.username = name.toString();
                        topStats.add(winLose);
                    }


                }
                synchronized (event){
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            synchronized (event){
                event.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return sdfsdf

    }


    public static synchronized String getTownsFromDatabase(String childName)
    {
        Object event = new Object();
        String[] outString = new String[1];
        DatabaseReference childReference = databaseReference.child("города").child(childName);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                long num = dataSnapshot.getChildrenCount();
                int r = random.nextInt((int) num);
                int k = 1;
                for (DataSnapshot allCity: dataSnapshot.getChildren())
                {
                    if (k == r + 1)
                    {
                        System.out.println(childName);
                        Object city = dataSnapshot.child(Integer.toString(k)).getValue();
                        System.out.println(city.toString());
                        outString[0] = city.toString();
                    }
                    k++;
                }
                synchronized (event){
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            synchronized (event){
                event.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString[0];
    }

    public final synchronized String getQuestionFromDatabase(String nameSection)
    {
        Object event = new Object();
        String[] outString = new String[1];
        DatabaseReference childReference = databaseReference.child("вопросы").child(nameSection);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int k = 1;
                long num = dataSnapshot.getChildrenCount();
                int r = random.nextInt((int) num);

                for (DataSnapshot quest: dataSnapshot.getChildren())
                {
                    if (k == r + 1){
                        String askID = quest.getKey();
                        System.out.println("id   " + askID);
                        System.out.println("rand   " +r + 1);
                        Object question = quest.getValue();

                        System.out.println("quest   "+question.toString());
                        outString[0] = question.toString();
                    }
                    k++;
                }
                synchronized (event){
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            synchronized (event){
                event.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString[0];
    }

    public final synchronized String getPhraseFromDatabase(String childName)
    {
        Object event = new Object();
        String[] outString = new String[1];
        DatabaseReference childReference = databaseReference.child("фразы").child(childName);
        ValueEventListener eventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                long num = dataSnapshot.getChildrenCount();
                int r = random.nextInt((int) num);
                int k = 1;
                for (DataSnapshot allCity: dataSnapshot.getChildren())
                {
                    if (k == r + 1)
                    {
                        System.out.println(childName);
                        Object phrase = dataSnapshot.child(Integer.toString(k)).getValue();
                        System.out.println(phrase.toString());
                        outString[0] = phrase.toString();
                    }
                    k++;
                }
                synchronized (event){
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            synchronized (event){
                event.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString[0];
    }

    public final synchronized String getDialogFromDatabase(String childName)
    {
        Object event = new Object();
        String[] outString = new String[1];
        DatabaseReference childReference = databaseReference.child("фразы").child("диалог").child(childName);
        ValueEventListener eventListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                long num = dataSnapshot.getChildrenCount();
                int r = random.nextInt((int) num);
                int k = 1;
                for (DataSnapshot allCity: dataSnapshot.getChildren())
                {
                    if (k == r + 1)
                    {
                        System.out.println(childName);
                        Object dialoge = dataSnapshot.child(Integer.toString(k)).getValue();
                        System.out.println(dialoge.toString());
                        outString[0] = dialoge.toString();
                    }
                    k++;
                }
                synchronized (event){
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            synchronized (event){
                event.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString[0];
    }

    public void takeFiveFirst(Statistic stats)
    {
        if (childReference != null && eventListener != null) {
            childReference.removeEventListener(eventListener);
        }

        childReference = databaseReference.child("users").orderByChild("wins").limitToLast(5);
        eventListener = new ChildEventListener() {

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                Object name = dataSnapshot.child("username").getValue();
                Object wins = dataSnapshot.child("wins").getValue();
                Object fails = dataSnapshot.child("fails").getValue();
                WinLose winLose = new WinLose();
                winLose.win = wins.toString();
                winLose.lose = fails.toString();
                winLose.username = name.toString();
                topStats.add(winLose);
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                takeFiveFirst(stats);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
            }

        };
        childReference.addChildEventListener(eventListener);
    }


    public void saveDataInDatabase(String key, String userName, Integer wins, Integer fails) {

        DatabaseReference childReference = databaseReference.child("users");
        Map<String, Object> hopperUpdates = new HashMap<>();

        hopperUpdates.put("username", userName);
        hopperUpdates.put("wins", wins);
        hopperUpdates.put("fails", fails);
        childReference.child(key).setValueAsync(hopperUpdates);
    }
}
