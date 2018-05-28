package com.mateusz.grabarski.myshoppinglist.views.activities.friends.models;

import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public class FriendRequestUI {

    private FriendRequest friendRequest;
    private User user;

    public FriendRequestUI(FriendRequest friendRequest, User user) {
        this.friendRequest = friendRequest;
        this.user = user;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FriendRequestUI{" +
                "friendRequest=" + friendRequest +
                ", user=" + user +
                '}';
    }
}
