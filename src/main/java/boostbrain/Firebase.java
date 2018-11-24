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
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

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

    public void getDatafromDatabase()
    {
        System.out.println(1);
        DatabaseReference childReference = databaseReference.child("example");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(2);

                Object a = dataSnapshot.getKey();
                Object b = dataSnapshot.getValue();
                for (DataSnapshot user: dataSnapshot.getChildren()){
                    String chatId = user.getKey();
                    DatabaseReference chatReference = databaseReference.child("123").child(chatId);
                    ValueEventListener valueEventListener = new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Object name = dataSnapshot.child("username").getValue();
                            Object wins = dataSnapshot.child("wins").getValue();
                            Object fails = dataSnapshot.child("fails").getValue();
                            Statistic.win_Users.put(name.toString(), Integer.parseInt(wins.toString()));
                            Statistic.loos_Users.put(name.toString(), Integer.parseInt(fails.toString()));
                            System.out.println(Statistic.win_Users.toString());
                            System.out.println(Statistic.loos_Users.toString());
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

    public void saveDataInDatabase(String username) {

        DatabaseReference childReference = databaseReference.child("example");
            Map<String, Object> hopperUpdates = new HashMap<>();
            hopperUpdates.put("username", username);
            hopperUpdates.put("wins", Statistic.getWin_Users(username));
            hopperUpdates.put("fails", Statistic.getLos_Users(username));
            String key = "123";
            childReference.child(key).setValueAsync(hopperUpdates);
    }
}
