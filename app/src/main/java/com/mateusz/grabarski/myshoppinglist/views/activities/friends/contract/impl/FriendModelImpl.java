package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.mateusz.grabarski.myshoppinglist.database.managers.FriendsManager;
import com.mateusz.grabarski.myshoppinglist.database.managers.UserManager;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.GetUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFriendRequestsListener;
import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FriendContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.models.FriendRequestUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 22.05.2018.
 */
public class FriendModelImpl implements FriendContract.Model, GetUserFriendRequestsListener {

    private static final String TAG = "FriendModelImpl";

    private FriendContract.Presenter mPresenter;
    private FriendsManager mFriendsManager;
    private UserManager mUserManager;
    private List<FriendRequest> mFriendRequests;
    private List<FriendRequestUI> mFriendRequestUI;

    private boolean startLoadingUserFriendRequests;

    public FriendModelImpl(FriendContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mFriendsManager = new FriendsManager();
        this.mUserManager = new UserManager();
        this.mFriendRequests = new ArrayList<>();
        this.mFriendRequestUI = new ArrayList<>();
        this.startLoadingUserFriendRequests = false;

        mUserManager.getUserByEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                new GetUserListener() {
                    @Override
                    public void onUserLoaded(User currentLoginUser) {
                        mFriendsManager.getAllUserFriendRequests(currentLoginUser, FriendModelImpl.this);
                    }

                    @Override
                    public void onErrorReceived(String message) {

                    }
                });
    }

    @Override
    public void loadCurrentLoginUserFriendRequests() {
        startLoadingUserFriendRequests = true;
        updateUserFriendRequests();
    }

    @Override
    public void onNewFriendRequest(FriendRequest friendRequest) {
        mFriendRequests.add(friendRequest);

        if (!startLoadingUserFriendRequests) {
            return;
        }

        updateUserFriendRequests();
    }

    @Override
    public void onFriendRequestUpdate(FriendRequest friendRequest) {

        for (int i = 0; i < mFriendRequests.size(); i++) {
            if (mFriendRequests.get(i).getKey().equals(friendRequest.getKey())) {
                mFriendRequests.set(i, friendRequest);
                break;
            }
        }

        if (!startLoadingUserFriendRequests) {
            return;
        }

        updateUserFriendRequests();
    }

    @Override
    public void onFriendRequestDeleted(FriendRequest friendRequest) {

        for (int i = 0; i < mFriendRequests.size(); i++) {
            if (mFriendRequests.get(i).getKey().equals(friendRequest.getKey())) {
                mFriendRequests.remove(i);
                break;
            }
        }

        if (!startLoadingUserFriendRequests) {
            return;
        }

        updateUserFriendRequests();
    }

    private void updateUserFriendRequests() {

        mFriendRequestUI.clear();

        for (FriendRequest request : mFriendRequests) {

            if (request.getTimestampOfAcceptRequest() == 0 && request.getTimestampOfDenyRequest() == 0) {

                mFriendsManager.getUserFromFriendRequest(request, (friendRequest, user) -> {

                    // is is then change and update ui
                    for (int i = 0; i < mFriendRequestUI.size(); i++) {
                        FriendRequestUI checkingRequest = mFriendRequestUI.get(i);

                        if (checkingRequest.getFriendRequest().getKey().equals(friendRequest.getKey())) {
                            mFriendRequestUI.set(i, new FriendRequestUI(request, user));
                            mPresenter.readyUserFriendRequests(mFriendRequestUI);
                            return;
                        }
                    }

                    // if there was no ui request then add new and notify ui
                    mFriendRequestUI.add(new FriendRequestUI(friendRequest, user));
                    mPresenter.readyUserFriendRequests(mFriendRequestUI);
                });
            } else {
                mPresenter.readyUserFriendRequests(mFriendRequestUI);
            }
        }
    }

    @Override
    public void addFriend(FriendRequestUI friendRequest) {

        FriendRequest request = friendRequest.getFriendRequest();
        request.setTimestampOfAcceptRequest(System.currentTimeMillis());

        mFriendsManager.requestAccepted(request, () -> {
            // TODO: 25.05.2018 maybe send push notification about accepted request
        });
    }

    @Override
    public void friendRequestRefused(FriendRequestUI friendRequest) {
        FriendRequest request = friendRequest.getFriendRequest();
        request.setTimestampOfDenyRequest(System.currentTimeMillis());

        mFriendsManager.requestDenied(request, request1 -> {
            // TODO: 25.05.2018 maybe send push notification about denied request
        });
    }
}
