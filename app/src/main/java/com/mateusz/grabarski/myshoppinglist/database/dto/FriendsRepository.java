package com.mateusz.grabarski.myshoppinglist.database.dto;

import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFriendRequestsListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.SendFriendRequestListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

public interface FriendsRepository {
    void addNewRequest(User requesting, User receiver, SendFriendRequestListener listener);

    void getUserFriendRequests(User user, GetUserFriendRequestsListener listener);
}
