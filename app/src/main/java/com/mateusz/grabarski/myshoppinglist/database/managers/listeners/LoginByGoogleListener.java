package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 04.01.2018.
 */

public interface LoginByGoogleListener {
    void onLoginSuccess(User user);
    void onLoginFailed(String errorMessage);
}
