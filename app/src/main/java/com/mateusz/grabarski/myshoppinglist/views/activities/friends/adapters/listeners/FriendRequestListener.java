package com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.listeners;

import com.mateusz.grabarski.myshoppinglist.views.activities.friends.models.FriendRequestUI;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public interface FriendRequestListener {
    void onAccept(FriendRequestUI friendRequest);
    void onRefused(FriendRequestUI friendRequest);
}
