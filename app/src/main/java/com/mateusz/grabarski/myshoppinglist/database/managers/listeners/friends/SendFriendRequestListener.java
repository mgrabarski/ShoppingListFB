package com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends;

public interface SendFriendRequestListener {
    void successRequestSend();

    void requestWasSend();

    void failedRequestSend(String message);
}
