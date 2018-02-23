package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 23.02.2018.
 */

public interface ShoppingListClickListener {
    void onListSelected(ShoppingList list);

    void onEditListClick(ShoppingList list);

    void onDeleteListClick(ShoppingList list);
}
