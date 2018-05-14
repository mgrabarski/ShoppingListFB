package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.adapter;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

/**
 * Created by Mateusz Grabarski on 27.02.2018.
 */

public interface CurrentListAdapterListener {
    void onItemCheck(ShoppingItem item, int position);
    void onItemEditClick(ShoppingItem item, int position);
    void onItemDeleteClick(ShoppingItem item, int position);
}
