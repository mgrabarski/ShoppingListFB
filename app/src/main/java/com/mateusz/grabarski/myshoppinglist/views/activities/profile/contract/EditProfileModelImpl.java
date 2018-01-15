package com.mateusz.grabarski.myshoppinglist.views.activities.profile.contract;

import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.UserRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 12.01.2018.
 */

public class EditProfileModelImpl implements EditProfileContract.Model {

    private EditProfileContract.Presenter mPresenter;
    private UserRepository mUserRepository;

    public EditProfileModelImpl(EditProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
        mUserRepository = new UserRepoFirebaseImpl();
    }

    @Override
    public void getCurrentLoginUser() {
        mUserRepository.getCurrentLoginUser(new CurrentLoginUserListener() {
            @Override
            public void onCurrentLoginUserLoaded(User user) {
                mPresenter.readyUserProfile(user);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });
    }
}
