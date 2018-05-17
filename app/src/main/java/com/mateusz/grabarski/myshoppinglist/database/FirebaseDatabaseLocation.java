package com.mateusz.grabarski.myshoppinglist.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mateusz.grabarski.myshoppinglist.utils.InputFormatter;

/**
 * Created by MGrabarski on 02.01.2018.
 */

public class FirebaseDatabaseLocation {

    private static final String FIREBASE_USERS = "/users/";
    private static final String FIREBASE_SHOPPING_LIST = "/shoppingList/";

    public FirebaseDatabaseLocation() {
    }

    public DatabaseReference getDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getUsersDatabaseReference() {
        return getDatabaseReference().child(FIREBASE_USERS);
    }

    private DatabaseReference getShoppingListDatabaseReference() {
        return getDatabaseReference().child(FIREBASE_SHOPPING_LIST);
    }

    public DatabaseReference getUserDatabaseReference(String email) {
        InputFormatter inputFormatter = new InputFormatter();
        return getUsersDatabaseReference().child(inputFormatter.encodeEmail(email));
    }

    public DatabaseReference getShoppingListDatabaseReference(String ownerEmail) {
        InputFormatter inputFormatter = new InputFormatter();
        return getShoppingListDatabaseReference().child(inputFormatter.encodeEmail(ownerEmail));
    }
}
