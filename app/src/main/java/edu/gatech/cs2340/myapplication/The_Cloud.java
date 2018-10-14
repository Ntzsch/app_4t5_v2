package edu.gatech.cs2340.myapplication;

import android.util.Base64;
import android.util.Log;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class The_Cloud {
    private static FirebaseAuth m_auth =  m_auth = FirebaseAuth.getInstance();
    private static FirebaseFunctions m_functions = FirebaseFunctions.getInstance();
    private static User m_user = new User();

    public static Task<Boolean> sign_in(final String username, final String password) {
        return getToken(username, password).continueWithTask(new Continuation<String, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(@NonNull Task<String> task) {
                String token = task.getResult();
                User.Type m_user_type = User.Type.GUEST;

                switch (JWT_Decoder.get_jwt_type(token)) {
                    case "employee":
                        m_user_type = User.Type.EMPLOYEE;
                        break;
                    case "manager":
                        m_user_type = User.Type.MANAGER;
                        break;
                    case "admin":
                        m_user_type = User.Type.ADMIN;
                        break;
                    default:
                        break;
                }
                m_user = new User(username, password, m_user_type);
                m_auth.signInWithCustomToken(token);
                return m_auth.signInWithCustomToken(token)
                        .continueWith(new Continuation<AuthResult, Boolean>() {
                            public Boolean then(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }});
            }
        });
    }

    public static void sign_out() {
        m_user = new User();
        m_auth.signOut();
    }

    private static Task<String> getToken(String username, String password) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        return m_functions
                .getHttpsCallable("getToken")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() { // tokens are strings
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        String token = (String) task.getResult().getData();
                        return token;
                    }
                });
    }

    public static Task<Boolean> register_user(String username, String password, String type) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("type", type);
        return m_functions
                .getHttpsCallable("register")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() { // tokens are strings
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        Boolean result = (Boolean) task.getResult().getData();
                        return result;
                    }
                });
    }

    public static void get_locations(final Callback<List<LocationEntry>> callback) {
        DatabaseReference tmp_db = FirebaseDatabase.getInstance().getReference();
        tmp_db = tmp_db.child("locations");
        tmp_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                ArrayList<LocationEntry> location_entries = new ArrayList<>();

                Iterable<DataSnapshot> locations = dataSnapshot.getChildren();

                for (DataSnapshot location : locations) {
                    GenericTypeIndicator<HashMap<String, String>> t = new GenericTypeIndicator<HashMap<String, String>>() {
                    };
                    HashMap<String, String> location_hashmap = location.getValue(t);
                    location_entries.add(new LocationEntry(location_hashmap.get("City"),
                                                            location_hashmap.get("Latitude"),
                                                            location_hashmap.get("Longitude"),
                                                            location_hashmap.get("Name"),
                                                            location_hashmap.get("Phone"),
                                                            location_hashmap.get("State"),
                                                            location_hashmap.get("Street Address"),
                                                            location_hashmap.get("Type"),
                                                            location_hashmap.get("Website"),
                                                            location_hashmap.get("Zip")));
                }
                callback.callback(location_entries);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e("DATABASE_ERROR", "loadPost:onCancelled", databaseError.toException());
                callback.callback(null);
            }
        });
    }

    public static User.Type get_user_type() {
        return m_user.getType();
    }

    public static String get_username() {
        return m_user.get_username();
    }

    private static void cleanup() {

    }
}

class JWT_Decoder {
    public static String get_jwt_type(String jwt) {
        try {
            return get_type_from_body(get_jwt_body(jwt));
        } catch (Exception e) {
            return "";
        }
    }
    private static String get_jwt_body(String JWTEncoded) {
        return JWTEncoded.split("\\.")[1];
    }

    private static String get_type_from_body(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        String decoded_body = new String(decodedBytes, "UTF-8");
        String claims = decoded_body.split(",\"claims\":")[1];
        claims = claims.substring(1, claims.length() - 2);
        String user_type = claims.split(":")[1];
        user_type = user_type.substring(1, user_type.length()-1);
        return user_type;
    }
}