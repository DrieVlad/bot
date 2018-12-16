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

    public void initFirebase() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream(System.getProperty("user.dir") + "\\chatbot-bce26-firebase-adminsdk-fh9e3-2730ceecc9.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://chatbot-bce26.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/");

    }

    public final synchronized void getDatafromDatabase(String userName, Statistic stats)
    {
        DatabaseReference childReference = databaseReference.child("users").child(userName);
        Object event = new Object();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object name = dataSnapshot.child("username").getValue();
                Object wins = dataSnapshot.child("wins").getValue();
                Object fails = dataSnapshot.child("fails").getValue();
                stats.win = wins.toString();
                stats.los = fails.toString();
                WinLose winLose = new WinLose();
                winLose.win = wins.toString();
                winLose.lose = fails.toString();
                winLose.username = name.toString();
                //topStats.add(winLose);
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
                event.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final static synchronized Boolean checkTownsFromDatabase(String letter, String townToCheck)
    {
        Object event = new Object();
        Boolean[] outBool = new Boolean[1];
        DatabaseReference childReference = databaseReference.child("города").child(letter);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String towns = dataSnapshot.getValue().toString().toLowerCase();
                System.out.println(towns);
                if (towns.lastIndexOf(townToCheck) != -1){
                    outBool[0] = true;
                }
                else {
                    outBool[0] = false;
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
                event.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outBool[0];
    }

    public final synchronized String getPhraseFromDatabase(String nameSection, String childName)
    {
        Object event = new Object();
        String[] outString = new String[1];
        DatabaseReference childReference = databaseReference.child(nameSection).child(childName);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                long num = dataSnapshot.getChildrenCount();
                int r = random.nextInt((int) num);
                DataSnapshot quest =  dataSnapshot.child(Integer.toString(r+1));

                String askID = quest.getKey();
                System.out.println("id   " + askID);
                System.out.println("rand   " +r + 1);
                Object question = quest.getValue();

                System.out.println("quest   "+question.toString());
                outString[0] = question.toString();
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
                event.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString[0];
    }

    public final synchronized String[] getFeedbackFromDatabase()
    {
        Object event = new Object();
        String[] outString = new String[2];
        DatabaseReference childReference = databaseReference.child("критика").child("сообщения");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                int k = 0;
                long num = dataSnapshot.getChildrenCount();
                int r = random.nextInt((int) num);

                for (DataSnapshot quest: dataSnapshot.getChildren()){
                    if (k == r){
                        outString[0] = quest.getKey().toString();
                        outString[1] = quest.getValue().toString();
                        break;
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
                event.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString;
    }

    public void removeFeedback(String key) {

        DatabaseReference childReference = databaseReference.child("критика").child("сообщения");

        childReference.child(key).removeValueAsync();;
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
                event.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outString[0];
    }

    public ArrayList<WinLose> takeFiveFirst() {
        ArrayList<WinLose> topStats = new ArrayList<WinLose>();
        Object event = new Object();

        if (childReference != null && eventListener != null)
        {
            childReference.removeEventListener(eventListener);
        }

        childReference = databaseReference.child("users").orderByChild("wins").limitToLast(5);
        eventListener = new ChildEventListener() {

            @Override
            public void onCancelled(DatabaseError databaseError) {
                event.notifyAll();
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
                topStats.add(0, winLose);
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                synchronized(event)
                {
                    event.notify();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {}
        };

        try {
            synchronized(event) {
                childReference.addChildEventListener(eventListener);
                event.wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return topStats;
    }

    public final static synchronized String[] getCriticismPhraseFromDatabase()
    {
        Object event = new Object();
        String[] swearings = new String[635];
        DatabaseReference childReference = databaseReference.child("критика").child("слова");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String[] foo = dataSnapshot.getValue().toString().toLowerCase().split(", ");
                for(int i = 0; i < foo.length; i++) {
                    swearings[i] = foo[i];
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
                event.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return swearings;
    }

    public void saveCriticiszmInDatabase(String key,String answer) {

        DatabaseReference childReference = databaseReference.child("критика").child("сообщения");

        childReference.child(key).setValueAsync(answer);;
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
