package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

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
        listener.onInsertSuccess(list); // TODO: 22.02.2018 save in database
    }
}
