package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public interface CreateShoppingListContract {

    interface Model {

        void getCurrentUser();
    }

    interface View {

    }

    interface Presenter {

        void setListName(String listName);

        void setCurrentUser(User user);
    }
}
