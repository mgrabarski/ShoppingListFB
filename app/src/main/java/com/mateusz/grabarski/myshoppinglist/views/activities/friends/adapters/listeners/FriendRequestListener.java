package com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public interface FriendRequestListener {
    void onAccept(FriendRequest friendRequest);
    void onRefused(FriendRequest friendRequest);
}
