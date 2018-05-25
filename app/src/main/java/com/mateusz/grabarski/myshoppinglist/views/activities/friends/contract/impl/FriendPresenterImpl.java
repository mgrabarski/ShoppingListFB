package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl;

import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FriendContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.models.FriendRequestUI;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public class FriendPresenterImpl implements FriendContract.Presenter {

    private FriendContract.View mView;
    private FriendContract.Model mModel;

    public FriendPresenterImpl(FriendContract.View view) {
        this.mView = view;
        this.mModel = new FriendModelImpl(this);
    }

    @Override
    public void loadCurrentLoginUserFriendRequests() {
        mModel.loadCurrentLoginUserFriendRequests();
    }

    @Override
    public void readyUserFriendRequests(List<FriendRequestUI> friendRequests) {
        mView.displayFriendRequests(friendRequests);
    }

    @Override
    public void acceptedRequest(FriendRequestUI friendRequest) {
        mModel.addFriend(friendRequest);
    }

    @Override
    public void refusedRequest(FriendRequestUI friendRequest) {
        mModel.friendRequestRefused(friendRequest);
    }
}
