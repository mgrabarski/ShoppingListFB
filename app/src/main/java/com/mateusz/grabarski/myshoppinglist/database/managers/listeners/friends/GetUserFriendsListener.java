package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

public interface GetUserFriendsListener {
    void onNewFriend(User user);

    void onFriendUpdate(User user);

    void onFriendDeleted(User user);
}
