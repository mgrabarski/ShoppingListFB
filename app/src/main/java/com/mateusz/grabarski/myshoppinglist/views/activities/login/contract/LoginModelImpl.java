package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import com.mateusz.grabarski.myshoppinglist.database.managers.UserManager;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class LoginModelImpl implements LoginContract.Model {

    private LoginContract.Presenter mPresenter;
    private UserManager mUserManager;

    public LoginModelImpl(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
        mUserManager = new UserManager();
    }

    @Override
    public void loginUser(String email, String password) {

    }

    @Override
    public void createUser(String name, String email, String password, String confirmPassword) {
        User user = new User(name, email, password, System.currentTimeMillis());
        mUserManager.registerUser(user, new CreateNewAccountListener() {
            @Override
            public void onCreateAccountSuccess(boolean success, User user) {
                mPresenter.accountCreatedSuccessfully(success, user);
            }

            @Override
            public void onCreateAccountFailed(String errorMessage) {
                mPresenter.createAccountFailed(errorMessage);
            }
        });
    }
}
