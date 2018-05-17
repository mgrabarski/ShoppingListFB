package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public interface AllUsersListener {
    void onAllUsersReady(List<User> users);
}
