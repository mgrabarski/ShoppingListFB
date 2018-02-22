package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.adapter;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public interface CreateShoppingListListener {
    void onEditClick(ShoppingItem item);
    void onDeleteClick(ShoppingItem item);
}
