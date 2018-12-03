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
        DatabaseReference childReference = databaseReference.child("users");
        Object event = new Object();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();
                System.out.println(b);
                for (DataSnapshot user: dataSnapshot.getChildren()){
                    String chatId = user.getKey();
                    //user.child(chatId).child("username").getValue();
                    DatabaseReference chatReference = databaseReference.child("users").child(chatId);
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Object name = dataSnapshot.child("username").getValue();
                            Object wins = dataSnapshot.child("wins").getValue();
                            Object fails = dataSnapshot.child("fails").getValue();
                            if (userName.equals(chatId)){
                                stats.win = wins.toString();
                                stats.los = fails.toString();
                                WinLose winLose = new WinLose();
                                winLose.win = wins.toString();
                                winLose.lose = fails.toString();
                                winLose.username = name.toString();
                                topStats.add(winLose);
                            }
                            event.notify();
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    chatReference.addListenerForSingleValueEvent(valueEventListener);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            event.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return sdfsdf

    }


    public static synchronized void getTownsFromDatabase(String nameSection, String childName, Towns towns)
    {
        Object event = new Object();
        DatabaseReference childReference = databaseReference.child(nameSection);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();

                for (DataSnapshot cityChar: dataSnapshot.getChildren()){
                    String cityId = cityChar.getKey();

                    if (childName.equals(cityId)){
                        System.out.println(cityId);
                        System.out.println(childName);
                        DatabaseReference chatReference = databaseReference.child(nameSection).child(cityId);

                        long num = dataSnapshot.getChildrenCount();
                        int r = random.nextInt((int) num);
                        Object city = dataSnapshot.child(Long.toString(r+1)).getValue();

                        System.out.println(city.toString());
                        towns.botTown = city.toString();

                        event.notify();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
        try {
            event.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final synchronized void getQuestionFromDatabase(String nameSection, MillionaireContent mill)
    {
        DatabaseReference childReference = databaseReference.child(nameSection);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();

                for (DataSnapshot cityChar: dataSnapshot.getChildren()){
                    String askID = cityChar.getKey();
                    System.out.println("id   " + askID);

                    DatabaseReference chatReference = databaseReference.child(nameSection).child(askID);
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long num = dataSnapshot.getChildrenCount();
                            int r = random.nextInt((int) num);
                            System.out.println("rand   "+r);
                            Object question = dataSnapshot.child(Long.toString(r+1)).getValue();

                            System.out.println("quest   "+question.toString());
                            mill.dictQuestion.put(Integer.parseInt(askID), question.toString());
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    chatReference.addListenerForSingleValueEvent(valueEventListener);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
    }

    public final synchronized void getPhraseFromDatabase(String nameSection, String childName, Bot bot)
    {
        DatabaseReference childReference = databaseReference.child(nameSection);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();

                for (DataSnapshot cityChar: dataSnapshot.getChildren()){
                    String cityId = cityChar.getKey();

                    if (childName.equals(cityId)){
                        System.out.println(cityId);
                        System.out.println(childName);
                        DatabaseReference chatReference = databaseReference.child(nameSection).child(cityId);
                        ValueEventListener valueEventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                long num = dataSnapshot.getChildrenCount();
                                int r = random.nextInt((int) num);
                                Object phrase = dataSnapshot.child(Long.toString(r+1)).getValue();

                                System.out.println(phrase.toString());
                                bot.answer = phrase.toString();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {}
                        };
                        chatReference.addListenerForSingleValueEvent(valueEventListener);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        childReference.addListenerForSingleValueEvent(eventListener);
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
