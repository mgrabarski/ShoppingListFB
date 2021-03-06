package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.InputValidator;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class LoginPresenterImpl implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginContract.Model mModel;

    public LoginPresenterImpl(LoginContract.View view) {
        this.mView = view;
        mModel = new LoginModelImpl(this);
    }

    @Override
    public void loginByEmail(String email, String password) {
        InputValidator inputValidator = new InputValidator();

        if (!inputValidator.isEmailValid(email))
            mView.displayEmailError();
        else if (!inputValidator.isPasswordLengthValid(password))
            mView.displayPasswordError();
        else {
            mView.showProgressDialog();
            mModel.loginUser(email, password);
        }
    }

    @Override
    public void signUp(String name, String email, String password, String confirmPassword) {
        InputValidator inputValidator = new InputValidator();

        if (!inputValidator.isEmailValid(email))
            mView.displayEmailError();
        else if (!inputValidator.isPasswordLengthValid(password))
            mView.displayPasswordError();
        else if (!inputValidator.isPasswordAndConfirmPasswordValid(password, confirmPassword))
            mView.displayPasswordMatchError();
        else if (!inputValidator.isUserNameValid(name))
            mView.displayUserNameError();
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
    public void accountCreatedSuccessfully(User user) {
        mView.hideProgressDialog();
        mView.displayRegistrationSuccess(user);
    }

    @Override
    public void loginByEmailSuccess(User user) {
        mView.hideProgressDialog();
        mView.displayDashboard();
    }

    @Override
    public void loginByEmailFailed(String errorMessage) {
        mView.hideProgressDialog();
        mView.displayLoginError(errorMessage);
    }

    @Override
    public void resetPassword(String emailAddress) {
        InputValidator inputValidator = new InputValidator();

        if (!inputValidator.isEmailValid(emailAddress))
            mView.displayEmailError();
        else {
            mView.showProgressDialog();
            mModel.sendResetPasswordEmail(emailAddress);
        }
    }

    @Override
    public void sendResetPasswordEmailSuccess() {
        mView.hideProgressDialog();
        mView.displaySendResetPasswordEmailSuccess();
    }

    @Override
    public void sendResetPasswordEmailFailed(String errorMessage) {
        mView.hideProgressDialog();
        mView.displaySendResetPasswordEmailFailed(errorMessage);
    }

    @Override
    public void loginByGoogle(GoogleSignInAccount account) {
        mModel.loginByGoogle(account);
    }

    @Override
    public void loginByGoogleSuccess() {
        mView.hideProgressDialog();
        mView.displayDashboard();
    }

    @Override
    public void loginByGoogleFailed(String errorMessage) {
        mView.hideProgressDialog();
        mView.displayMessage(errorMessage);
    }
}
