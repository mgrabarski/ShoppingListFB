package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.data;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 27.02.2018.
 */

public interface CurrentListListener {
    void updateList(List<ShoppingItem> items);
}
