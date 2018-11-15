package edu.gatech.cs2340.myapplication.models;

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
import edu.gatech.cs2340.myapplication.Callback;

public class TheCloud {
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final FirebaseFunctions mFunctions = FirebaseFunctions
            .getInstance();
    private static User mUser = new User();

    public static Task<Boolean> signIn(final String username, final String
            password) {
        return getToken(username, password).continueWithTask(new Continuation
                <String, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(@NonNull Task<String> task) {
                String token = task.getResult();
                User.Type mUserType = User.Type.GUEST;

                switch (JWTDecoder.getJwtType(token)) {
                    case "employee":
                        mUserType = User.Type.EMPLOYEE;
                        break;
                    case "manager":
                        mUserType = User.Type.MANAGER;
                        break;
                    case "admin":
                        mUserType = User.Type.ADMIN;
                        break;
                    default:
                        break;
                }
                mUser = new User(username, password, mUserType);
                mAuth.signInWithCustomToken(token);
                return mAuth.signInWithCustomToken(token)
                        .continueWith(new Continuation<AuthResult, Boolean>() {
                            public Boolean then(@NonNull Task<AuthResult>
                                                        task) {
                                return task.isSuccessful();
                            } });
            }
        });
    }

    public static void signOut() {
        mUser = new User();
        mAuth.signOut();
    }

    private static Task<String> getToken(String username, String password) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        return mFunctions
                .getHttpsCallable("getToken")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    // tokens are strings
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult>
                                               task) {
                        return (String) task.getResult().getData();
                    }
                });
    }

    public static Task<Boolean> addInventoryEntry(InventoryEntry entry) {
        // Create the arguments to the callable function.
        Map<String, String> data = new HashMap<>();
        data.put("timeStamp", entry.getTimeStamp());
        data.put("location", entry.getLocation());
        data.put("smallDescription", entry.getSmallDescription());
        data.put("fullDescription", entry.getFullDescription());
        data.put("value", entry.getValue());
        data.put("category", entry.getCategory());

        return mFunctions
                .getHttpsCallable("addInventoryEntry")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    // tokens are strings
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult>
                                               task) {
                        return (Boolean) task.getResult().getData();
                    }
                });
    }

    public static Task<Boolean> registerUser(String username, String
            password, String type) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("type", type);
        return mFunctions
                .getHttpsCallable("register")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    // tokens are strings
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult>
                                                task) {
                        return (Boolean) task.getResult().getData();
                    }
                });
    }

    public static void getLocations(final Callback<List<LocationEntry>>
                                            callback) {
        DatabaseReference tmpDb = FirebaseDatabase.getInstance().getReference();
        tmpDb = tmpDb.child("locations");
        tmpDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                List<LocationEntry> locationEntries = new ArrayList<>();

                Iterable<DataSnapshot> locations = dataSnapshot.getChildren();

                for (DataSnapshot location : locations) {
                    GenericTypeIndicator<HashMap<String, String>> t = new
                            GenericTypeIndicator<HashMap<String, String>>() {
                    };
                    Map<String, String> locationHashmap = location
                            .getValue(t);
                    locationEntries.add(new LocationEntry(
                            locationHashmap.get("City"),
                            locationHashmap.get("Latitude"),
                            locationHashmap.get("Longitude"),
                            locationHashmap.get("Name"),
                            locationHashmap.get("Phone"),
                            locationHashmap.get("State"),
                            locationHashmap.get("Street Address"),
                            locationHashmap.get("Type"),
                            locationHashmap.get("Website"),
                            locationHashmap.get("Zip")));
                }
                callback.callback(locationEntries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e("DATABASE_ERROR", "loadPost:onCancelled",
                        databaseError.toException());
                callback.callback(null);
            }
        });
    }

    public static void getInventory(final Callback<List<InventoryEntry>>
                                            callback) {
        DatabaseReference tmpDb = FirebaseDatabase.getInstance().getReference();
        tmpDb = tmpDb.child("inventory");
        tmpDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                List<InventoryEntry> inventoryEntries = new ArrayList<>();

                Iterable<DataSnapshot> locations = dataSnapshot.getChildren();

                for (DataSnapshot location : locations) {
                    GenericTypeIndicator<HashMap<String, String>> t = new
                            GenericTypeIndicator<HashMap<String, String>>() {
                            };
                    Map<String, String> locationHashmap = location
                            .getValue(t);
                    inventoryEntries.add(new InventoryEntry(
                            locationHashmap.get("timeStamp"),
                            locationHashmap.get("location"),
                            locationHashmap.get("smallDescription"),
                            locationHashmap.get("fullDescription"),
                            locationHashmap.get("value"),
                            locationHashmap.get("category")));
                }
                callback.callback(inventoryEntries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e("DATABASE_ERROR", "loadPost:onCancelled",
                        databaseError.toException());
                callback.callback(null);
            }
        });
    }

    public static User.Type getUserType() {
        return mUser.getType();
    }

    public static String getUsername() {
        return mUser.getUsername();
    }

}

class JWTDecoder {
    public static String getJwtType(String jwt) {
        try {
            return getTypeFromBody(getJwtBody(jwt));
        } catch (Exception e) {
            return "";
        }
    }
    private static String getJwtBody(String jwtEncoded) {
        return jwtEncoded.split("\\.")[1];
    }

    private static String getTypeFromBody(String strEncoded) throws
            UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        String decodedBody = new String(decodedBytes, "UTF-8");
        String claims = decodedBody.split(",\"claims\":")[1];
        claims = claims.substring(1, claims.length() - 2);
        String userType = claims.split(":")[1];
        userType = userType.substring(1, userType.length() - 1);
        return userType;
    }
}