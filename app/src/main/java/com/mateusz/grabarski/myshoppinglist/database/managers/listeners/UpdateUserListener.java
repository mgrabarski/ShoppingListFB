package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

/**
 * Created by Mateusz Grabarski on 15.01.2018.
 */

public interface UpdateUserListener {
    void onUpdateSuccess();
    void onUpdateFailed(String message);
}
