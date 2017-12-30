package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import android.util.Log;

import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.InputValidator;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class LoginPresenterImpl implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenterImpl";

    private LoginContract.View mView;
    private LoginContract.Model mModel;

    public LoginPresenterImpl(LoginContract.View view) {
        this.mView = view;
        mModel = new LoginModelImpl(this);
    }

    @Override
    public void validateCredentials(String email, String password) {
        InputValidator inputValidator = new InputValidator();

        if (!inputValidator.isEmailValid(email))
            mView.displayEmailError();
        else if (!inputValidator.isPasswordLengthValid(password))
            mView.displayPasswordError();
        else
            mModel.loginUser(email, password);
    }

    @Override
    public void signUp(String name, String email, String password, String confirmPassword) {
        InputValidator inputValidator = new InputValidator();

        if (!inputValidator.isEmailValid(email))
            mView.displayEmailError();
        else if (!inputValidator.isPasswordLengthValid(password))
            mView.displayPasswordError();
        else if (inputValidator.isUserNameValid(name))
            mView.displayUserNameError();
        else if (!inputValidator.isPasswordAndConfirmPasswordValid(password, confirmPassword))
            mView.displayPasswordMatchError();
        else {
            mView.showProgressDialog();
            mModel.createUser(name, email, password, confirmPassword);
        }
    }

    @Override
    public void createAccountFailed(String errorMessage) {
        mView.hideProgressDialog();
        mView.displayRegistrationError(errorMessage);
    }

    @Override
    public void accountCreatedSuccessfully(boolean success, User user) {
        Log.d(TAG, "accountCreatedSuccessfully: " + success);
        mView.hideProgressDialog();
    }
}
