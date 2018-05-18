package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List; /**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public interface FindFriendContract {

    interface Model {

        void findUsersWithQuery(String query);

        void sendFriendRequestToUser(User user);
    }

    interface View {

        void refreshUserList(List<User> filteredUsers);
    }

    interface Presenter {

        void searchUsersByQuery(String query);

        void onFilteredUsersReady(List<User> filteredUsers);

        void onUserSelectFromList(User user);
    }
}
