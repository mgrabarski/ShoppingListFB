package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.models.FriendRequestUI;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public interface FriendContract {

    interface Model {

        void loadCurrentLoginUserFriendRequests();

        void addFriend(FriendRequestUI friendRequest);

        void friendRequestRefused(FriendRequestUI friendRequest);
    }

    interface View {

        void displayFriendRequests(List<FriendRequestUI> friendRequests);

        void loadUserFriends(List<User> friends);
    }

    interface Presenter {

        void loadCurrentLoginUserFriendRequests();

        void readyUserFriendRequests(List<FriendRequestUI> friendRequests);

        void acceptedRequest(FriendRequestUI friendRequest);

        void refusedRequest(FriendRequestUI friendRequest);

        void readyUserFriends(List<User> friends);
    }
}
