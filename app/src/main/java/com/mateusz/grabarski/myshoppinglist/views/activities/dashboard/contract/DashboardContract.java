package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public interface DashboardContract {

    interface Model {
        void getUserData();
    }

    interface View {
        void setUserName(String name);
        void setUserEmail(String email);
        void setUserShoppingList();
    }

    interface Presenter {
        void loadUserInformation();
        void refreshUserData(User user);
    }
}
