package com.mateusz.grabarski.myshoppinglist.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mateusz.grabarski.myshoppinglist.utils.InputFormatter;

/**
 * Created by MGrabarski on 02.01.2018.
 */

public class FirebaseDatabaseLocation {

    public static final String FIREBASE_USERS = "/users/";

    public FirebaseDatabaseLocation() {
    }

    public DatabaseReference getDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getUserDatabaseReference() {
        return getDatabaseReference().child(FIREBASE_USERS);
    }

    public DatabaseReference getUserDatabaseReference(String email) {
        InputFormatter inputFormatter = new InputFormatter();
        return getUserDatabaseReference().child(inputFormatter.encodeEmail(email));
    }
}
