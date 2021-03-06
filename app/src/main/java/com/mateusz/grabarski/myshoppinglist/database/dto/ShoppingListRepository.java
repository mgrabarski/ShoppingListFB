package com.mateusz.grabarski.myshoppinglist.database.dto;

import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.DeleteShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.SLDatabaseReferenceListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.UpdateListNameListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.UpdateShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public interface ShoppingListRepository {
    void insertShoppingList(ShoppingList list, InsertShoppingListListener listener);

    void getDatabaseReference(String owner, SLDatabaseReferenceListener listener);

    void deleteShoppingList(ShoppingList list, DeleteShoppingListListener listener);

    void updateListName(ShoppingList list, UpdateListNameListener listener);

    void updateList(ShoppingList list, UpdateShoppingListListener listener);
}
