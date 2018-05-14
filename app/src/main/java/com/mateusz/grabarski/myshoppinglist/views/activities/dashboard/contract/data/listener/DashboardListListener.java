package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract.data.listener;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

import java.util.List;

/**
 * Created by MGrabarski on 25.02.2018.
 */

public interface DashboardListListener {
    void updateUserLists(List<ShoppingList> lists);
}
