package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by MGrabarski on 28.02.2018.
 */

public interface UpdateShoppingListListener {
    void onUpdate(boolean success, ShoppingList list);
}
