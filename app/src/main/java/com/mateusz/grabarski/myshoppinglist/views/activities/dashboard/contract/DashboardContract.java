package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public interface DashboardContract {

    interface Model {
        void getUserData();

        void getUserShoppingLists();

        void deleteShoppingList(ShoppingList list);

        void updateListName(ShoppingList list);
    }

    interface View {
        void setUserName(String name);

        void setUserEmail(String email);

        void setUserShoppingList(List<ShoppingList> lists);
    }

    interface Presenter {
        void loadUserInformation();

        void refreshUserData(User user);

        void loadUserShoppingLists();

        void displayUserLists(List<ShoppingList> lists);

        void deleteShoppingList(ShoppingList list);

        void updateListName(ShoppingList list);
    }
}
