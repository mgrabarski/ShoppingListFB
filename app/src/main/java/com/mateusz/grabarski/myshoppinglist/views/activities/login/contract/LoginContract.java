package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public interface LoginContract {

    interface Model {

        void loginUser(String email, String password);

        void createUser(String name, String email, String password, String confirmPassword);

        void sendResetPasswordEmail(String email);

        void loginByGoogle(GoogleSignInAccount account);
    }

    interface View {
        void showProgressDialog();

        void hideProgressDialog();

        void displayEmailError();

        void displayPasswordError();

        void displayDashboard();

        void displayPasswordMatchError();

        void displayUserNameError();

        void displayRegistrationError(String errorMessage);

        void displayRegistrationSuccess(User user);

        void displayLoginError(String errorMessage);

        void displaySendResetPasswordEmailSuccess();

        void displaySendResetPasswordEmailFailed(String errorMessage);

        void displayMessage(String message);
    }

    interface Presenter {
        void loginByEmail(String email, String password);

        void signUp(String name, String email, String password, String confirmPassword);

        void createAccountFailed(String errorMessage);

        void accountCreatedSuccessfully(User user);

        void loginByEmailSuccess(User user);

        void loginByEmailFailed(String errorMessage);

        void resetPassword(String emailAddress);

        void sendResetPasswordEmailSuccess();

        void sendResetPasswordEmailFailed(String errorMessage);

        void loginByGoogle(GoogleSignInAccount account);

        void loginByGoogleSuccess();

        void loginByGoogleFailed(String errorMessage);
    }
}
