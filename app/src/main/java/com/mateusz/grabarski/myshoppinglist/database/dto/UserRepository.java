package com.mateusz.grabarski.myshoppinglist.database.dto;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.AllUsersListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.GetUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginByGoogleListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.UpdateUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 27.12.2017.
 */

public interface UserRepository {

    void insertUser(User user, CreateNewAccountListener listener);

    void updateUser(User user, UpdateUserListener listener);

    void getUserByEmail(String email, GetUserListener listener);

    void loginUser(String email, String password, LoginListener loginListener);

    void sendResetPasswordEmail(String email, ResetPasswordListener listener);

    void loginUserByGoogle(GoogleSignInAccount account, LoginByGoogleListener listener);

    void getCurrentLoginUser(GetUserListener listener);

    void getAllUsers(AllUsersListener listener);
}
