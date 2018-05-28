package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends;

import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public interface GetUserFriendRequestsListener {
    void onNewFriendRequest(FriendRequest friendRequest);

    void onFriendRequestUpdate(FriendRequest friendRequest);

    void onFriendRequestDeleted(FriendRequest friendRequest);
}
