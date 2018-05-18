package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl;

import android.text.TextUtils;
import android.util.Log;

import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FindFriendContract;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class FindFriendPresenterImpl implements FindFriendContract.Presenter {

    private FindFriendContract.View mView;
    private FindFriendContract.Model mModel;

    public FindFriendPresenterImpl(FindFriendContract.View view) {
        this.mView = view;
        this.mModel = new FindFriendModelImpl(this);
    }

    @Override
    public void searchUsersByQuery(String query) {
        if (!TextUtils.isEmpty(query))
            mModel.findUsersWithQuery(query);
    }

    @Override
    public void onFilteredUsersReady(List<User> filteredUsers) {
        mView.refreshUserList(filteredUsers);
    }
}
