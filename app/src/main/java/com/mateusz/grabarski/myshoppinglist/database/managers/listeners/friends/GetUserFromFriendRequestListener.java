package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends;

import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public interface GetUserFromFriendRequestListener {
    void onUser(FriendRequest friendRequest, User user);
}
