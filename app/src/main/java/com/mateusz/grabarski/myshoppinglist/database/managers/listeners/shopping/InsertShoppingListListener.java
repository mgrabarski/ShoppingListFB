package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public interface InsertShoppingListListener {
    void onInsertSuccess(ShoppingList list);

    void onInsertError(ShoppingList list);
}
