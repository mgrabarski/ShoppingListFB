package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 26.02.2018.
 */

public interface DeleteShoppingListListener {
    void onDeleteSuccess(boolean success, ShoppingList list);
}
