package boostbrain;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Firebase {
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

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

    public void getDatafromDatabase(String userName , Statistic stats)
    {
        DatabaseReference childReference = databaseReference.child("users");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();
                System.out.println(b);
                for (DataSnapshot user: dataSnapshot.getChildren()){
                    String chatId = user.getKey();
                    DatabaseReference chatReference = databaseReference.child("users").child(chatId);
                    ValueEventListener valueEventListener = new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Object wins = dataSnapshot.child("wins").getValue();
                            Object fails = dataSnapshot.child("fails").getValue();
                            if (userName.equals(chatId)){
                                stats.win = wins.toString();
                                stats.los = fails.toString();
                                System.out.println(chatId);
                                System.out.println(userName);
                            }
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


    public void saveDataInDatabase(String userName, Integer wins, Integer fails) {

        DatabaseReference childReference = databaseReference.child("users");
        String key = userName;
        Map<String, Object> hopperUpdates = new HashMap<>();

        hopperUpdates.put("username", userName);
        hopperUpdates.put("wins", wins);
        hopperUpdates.put("fails", fails);
        childReference.child(key).setValueAsync(hopperUpdates);
    }
}
