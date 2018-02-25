package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by MGrabarski on 25.02.2018.
 */

public interface SLDatabaseReferenceListener {
    void onShoppingListDatabaseReference(DatabaseReference reference);
}
