package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class LoginModelImpl implements LoginContract.Model {

    private LoginContract.Presenter mPresenter;

    public LoginModelImpl(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loginUser(String email, String password) {

    }

    @Override
    public void createUser(String name, String email, String password, String confirmPassword) {

    }
}
