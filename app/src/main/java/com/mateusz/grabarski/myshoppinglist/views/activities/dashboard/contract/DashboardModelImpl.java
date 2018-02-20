package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract;

import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.UserRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class DashboardModelImpl implements DashboardContract.Model {

    private DashboardContract.Presenter mPresenter;
    private UserRepository mUserRepository;

    public DashboardModelImpl(DashboardContract.Presenter presenter) {
        this.mPresenter = presenter;
        mUserRepository = new UserRepoFirebaseImpl();
    }

    @Override
    public void getUserData() {
        mUserRepository.getCurrentLoginUser(new CurrentLoginUserListener() {
            @Override
            public void onCurrentLoginUserLoaded(User user) {
                mPresenter.refreshUserData(user);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });
    }
}
