package com.mateusz.grabarski.myshoppinglist.database.dto.memory;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mateusz.grabarski.myshoppinglist.base.Constants;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginByGoogleListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGrabarski on 27.12.2017.
 */

public class UserRepoMemoryImpl implements UserRepository {

    private List<User> mUsers;

    public UserRepoMemoryImpl() {
        mUsers = new ArrayList<>();

        User user1 = new User("Mateusz", "mateusz.grabarski@gmail.com", "zaqwsx", Constants.FakeData.FAKE_DATE_1);
        User user2 = new User("Test", "test@gmail.com", "zaqwsx", Constants.FakeData.FAKE_DATE_2);

        mUsers.add(user1);
        mUsers.add(user2);
    }

    @Override
    public void insertUser(final User user, final CreateNewAccountListener listener) {
        mUsers.add(user);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    listener.onCreateAccountSuccess(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void updateUser(User user) {
        for (int i = 0; i < mUsers.size(); i++) {
            if (mUsers.get(i).equals(user)) {
                mUsers.set(i, user);
            }
        }
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : mUsers)
            if (user.getEmail().equals(email))
                return user;

        return null;
    }

    @Override
    public void loginUser(final String email, final String password, final LoginListener loginListener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    for (User user : mUsers) {
                        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                            loginListener.onLoginSuccess(user);
                            return;
                        }
                    }

                    loginListener.onLoginFailed("Failed login");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void sendResetPasswordEmail(final String email, final ResetPasswordListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    for (User user : mUsers)
                        if (user.getEmail().equals(email)) {
                            user.setPassword("reset");
                            listener.onSendSuccess();
                            return;
                        }

                    listener.onSendFailed("Filed password reset");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void loginUserByGoogle(GoogleSignInAccount account, LoginByGoogleListener listener) {

    }
}
