package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 03.01.2018.
 */

public interface LoginListener {
    void onLoginSuccess(User user);
    void onLoginFailed(String errorMessage);
}
