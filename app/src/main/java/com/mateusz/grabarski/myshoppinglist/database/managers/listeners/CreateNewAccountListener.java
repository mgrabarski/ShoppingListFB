package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 30.12.2017.
 */

public interface CreateNewAccountListener {
    void onCreateAccountSuccess(boolean success, User user);

    void onCreateAccountFailed(String errorMessage);
}
