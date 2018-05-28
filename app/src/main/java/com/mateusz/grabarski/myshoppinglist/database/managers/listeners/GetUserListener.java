package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 12.01.2018.
 */

public interface GetUserListener {
    void onUserLoaded(User user);
    void onErrorReceived(String message);
}
