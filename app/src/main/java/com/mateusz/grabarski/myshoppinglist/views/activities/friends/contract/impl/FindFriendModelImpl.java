package com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.mateusz.grabarski.myshoppinglist.database.managers.UserManager;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.AllUsersListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FindFriendContract;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class FindFriendModelImpl implements FindFriendContract.Model {

    private FindFriendContract.Presenter mPresenter;
    private List<User> mUsers;
    private UserManager mUserManager;

    public FindFriendModelImpl(FindFriendContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mUsers = new ArrayList<>();
        this.mUserManager = new UserManager();

        mUserManager.getAllUsers(users -> mUsers.addAll(users));
    }

    @Override
    public void findUsersWithQuery(String query) {
        List<User> filteredUsers = new ArrayList<>();

        mUserManager.getUserByEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                new CurrentLoginUserListener() {
                    @Override
                    public void onCurrentLoginUserLoaded(User user) {
                        for (User userFromDatabase : mUsers) {
                            if (StringUtils.containsIgnoreCase(userFromDatabase.getName(), query) &&
                                    !user.getEmail().equals(userFromDatabase.getEmail())) {
                                filteredUsers.add(userFromDatabase);
                            }
                        }

                        mPresenter.onFilteredUsersReady(filteredUsers);
                    }

                    @Override
                    public void onErrorReceived(String message) {

                    }
                });
    }
}
