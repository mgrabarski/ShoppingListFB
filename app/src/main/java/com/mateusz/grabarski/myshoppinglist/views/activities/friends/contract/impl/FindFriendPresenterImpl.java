package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl;

import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FindFriendContract;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class FindFriendPresenterImpl implements FindFriendContract.Presenter {

    private FindFriendContract.View mView;

    public FindFriendPresenterImpl(FindFriendContract.View view) {
        this.mView = view;
    }
}
