package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List; /**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public interface FindFriendContract {

    interface Model {

        void findUsersWithQuery(String query);
    }

    interface View {

    }

    interface Presenter {

        void searchUsersByQuery(String query);

        void onFilteredUsersReady(List<User> filteredUsers);
    }
}
