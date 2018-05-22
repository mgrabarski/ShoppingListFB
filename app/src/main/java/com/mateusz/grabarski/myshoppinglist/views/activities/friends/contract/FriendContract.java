package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract;

import com.mateusz.grabarski.myshoppinglist.views.activities.friends.models.FriendRequestUI;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public interface FriendContract {

    interface Model {

        void loadCurrentLoginUserFriendRequests();
    }

    interface View {

        void displayFriendRequests(List<FriendRequestUI> friendRequests);
    }

    interface Presenter {

        void loadCurrentLoginUserFriendRequests();

        void readyUserFriendRequests(List<FriendRequestUI> friendRequests);
    }
}
