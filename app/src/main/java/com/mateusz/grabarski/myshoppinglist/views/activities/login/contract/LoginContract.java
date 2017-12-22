package com.mateusz.grabarski.myshoppinglist.views.activities.login.contract;

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
    }

    interface Presenter {
        void validateCredentials(String email, String password);

        void signUp(String name, String email, String password, String confirmPassword);
    }
}
