package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import android.util.Log;

import com.mateusz.grabarski.myshoppinglist.database.dto.FriendsRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.SendFriendRequestListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

public class FriendsRepoFirebaseImpl implements FriendsRepository {

    @Override
    public void addNewRequest(User requesting, User receiver, SendFriendRequestListener listener) {
        Log.d(FriendsRepoFirebaseImpl.class.getSimpleName(), "addNewRequest: ");
    }
}
