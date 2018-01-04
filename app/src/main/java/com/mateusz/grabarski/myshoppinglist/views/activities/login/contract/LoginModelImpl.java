package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mateusz.grabarski.myshoppinglist.database.managers.UserManager;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginByGoogleListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
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
        mUserManager.loginUser(email, password, new LoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                mPresenter.loginByEmailSuccess(user);
                // TODO: 03.01.2018 save user email in shared preferences
            }

            @Override
            public void onLoginFailed(String errorMessage) {
                mPresenter.loginByEmailFailed(errorMessage);
            }
        });
    }

    @Override
    public void createUser(String name, String email, String password, String confirmPassword) {
        User user = new User(name, email, password, System.currentTimeMillis());
        mUserManager.registerUser(user, new CreateNewAccountListener() {
            @Override
            public void onCreateAccountSuccess(User user) {
                mPresenter.accountCreatedSuccessfully(user);
            }

            @Override
            public void onCreateAccountFailed(String errorMessage) {
                mPresenter.createAccountFailed(errorMessage);
            }
        });
    }

    @Override
    public void sendResetPasswordEmail(String email) {
        mUserManager.sendResetPasswordEmail(email, new ResetPasswordListener() {
            @Override
            public void onSendSuccess() {
                mPresenter.sendResetPasswordEmailSuccess();
            }

            @Override
            public void onSendFailed(String errorMessage) {
                mPresenter.sendResetPasswordEmailFailed(errorMessage);
            }
        });
    }

    @Override
    public void loginByGoogle(GoogleSignInAccount account) {
        mUserManager.loginByGoogle(account, new LoginByGoogleListener(){
            @Override
            public void onLoginSuccess(User user) {

            }

            @Override
            public void onLoginFailed(String errorMessage) {

            }
        });
    }
}
