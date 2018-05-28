package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends;

import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;

public interface FriendRequestDenied {
    void onRequestDenied(FriendRequest request);
}
