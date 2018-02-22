package com.mateusz.grabarski.myshoppinglist.database.dto;

import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public interface ShoppingListRepository {
    void insertShoppingList(ShoppingList list, InsertShoppingListListener listener);
}
