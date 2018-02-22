package com.mateusz.grabarski.myshoppinglist.database.dto.memory;

import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public class ShoppingRepoMemoryImpl implements ShoppingListRepository {

    public ShoppingRepoMemoryImpl() {
    }

    @Override
    public void insertShoppingList(ShoppingList list, InsertShoppingListListener listener) {

    }
}