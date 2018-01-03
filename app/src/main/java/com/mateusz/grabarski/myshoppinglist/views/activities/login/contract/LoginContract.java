package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public interface LoginContract {

    interface Model {

        void loginUser(String email, String password);

        void createUser(String name, String email, String password, String confirmPassword);
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
    }

    interface Presenter {
        void loginByEmail(String email, String password);

        void signUp(String name, String email, String password, String confirmPassword);

        void createAccountFailed(String errorMessage);

        void accountCreatedSuccessfully(User user);

        void loginByEmailSuccess(User user);

        void loginByEmailFailed(String errorMessage);
    }
}
