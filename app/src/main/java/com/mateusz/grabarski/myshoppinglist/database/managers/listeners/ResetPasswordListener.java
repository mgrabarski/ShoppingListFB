package com.mateusz.grabarski.myshoppinglist.database.managers.listeners;

/**
 * Created by Mateusz Grabarski on 04.01.2018.
 */

public interface ResetPasswordListener {
    void onSendSuccess();
    void onSendFailed(String errorMessage);
}
