package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import com.google.firebase.database.DatabaseReference;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public class ShoppingRepoFirebaseImpl implements ShoppingListRepository {

    private FirebaseDatabaseLocation mFirebaseDatabaseLocation;

    public ShoppingRepoFirebaseImpl() {
        mFirebaseDatabaseLocation = new FirebaseDatabaseLocation();
    }

    @Override
    public void insertShoppingList(ShoppingList list, InsertShoppingListListener listener) {
        DatabaseReference reference = mFirebaseDatabaseLocation
                .getShoppingListDatabaseReference(list.getOwnerEmail())
                .push();

        list.setId(reference.getKey());

        mFirebaseDatabaseLocation
                .getShoppingListDatabaseReference(list.getOwnerEmail())
                .child(reference.getKey())
                .setValue(list);

        if (list.getId() != null)
            listener.onInsertSuccess(list);
        else
            listener.onInsertError(list);
    }
}
