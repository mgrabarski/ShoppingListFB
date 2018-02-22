package com.mateusz.grabarski.myshoppinglist.database.dto.memory;

import android.os.Handler;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mateusz.grabarski.myshoppinglist.base.Constants;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginByGoogleListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.UpdateUserListener;
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

        User user1 = new User("Mateusz", "mateusz.grabarski@gmail.com", "zaqwsx", Constants.FakeData.FAKE_DATE_1, "https://t3.ftcdn.net/jpg/01/04/10/10/240_F_104101070_wbEDt3CmlzqnPbdmOlVCL7Q7yu9mCduz.jpg");
        User user2 = new User("Test", "test@gmail.com", "zaqwsx", Constants.FakeData.FAKE_DATE_2, "https://t3.ftcdn.net/jpg/01/04/10/10/240_F_104101070_wbEDt3CmlzqnPbdmOlVCL7Q7yu9mCduz.jpg");

        mUsers.add(user1);
        mUsers.add(user2);
    }

    @Override
    public void insertUser(final User user, final CreateNewAccountListener listener) {
        mUsers.add(user);

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onCreateAccountSuccess(user);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void updateUser(User user, UpdateUserListener listener) {
        for (int i = 0; i < mUsers.size(); i++) {
            if (mUsers.get(i).equals(user)) {
                mUsers.set(i, user);
            }
        }
    }

    @Override
    public void getUserByEmail(String email, CurrentLoginUserListener listener) {
        for (User user : mUsers)
            if (user.getEmail().equals(email)) {
                listener.onCurrentLoginUserLoaded(user);
            }

        listener.onCurrentLoginUserLoaded(null);
    }

    @Override
    public void loginUser(final String email, final String password, final LoginListener loginListener) {
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (User user : mUsers) {
                                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                                    loginListener.onLoginSuccess(user);
                                    return;
                                }
                            }

                            loginListener.onLoginFailed("Failed login");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void sendResetPasswordEmail(final String email, final ResetPasswordListener listener) {
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (User user : mUsers)
                                if (user.getEmail().equals(email)) {
                                    user.setPassword("reset");
                                    listener.onSendSuccess();
                                    return;
                                }

                            listener.onSendFailed("Filed password reset");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void loginUserByGoogle(GoogleSignInAccount account, LoginByGoogleListener listener) {

    }

    @Override
    public void getCurrentLoginUser(CurrentLoginUserListener listener) {
        listener.onCurrentLoginUserLoaded(mUsers.get(0));
    }
}
