package com.mateusz.grabarski.myshoppinglist.database.managers;

import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.ShoppingRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public class ShoppingListManager {

    private ShoppingListRepository mRepository;

    public ShoppingListManager() {
        mRepository = new ShoppingRepoFirebaseImpl();
    }

    public void insertShoppingList(ShoppingList list, InsertShoppingListListener listener) {
        mRepository.insertShoppingList(list, listener);
    }
}
