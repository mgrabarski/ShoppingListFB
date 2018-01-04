package com.mateusz.grabarski.myshoppinglist.database.managers;

import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.UserRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.dto.memory.UserRepoMemoryImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 30.12.2017.
 */

public class UserManager {

    private UserRepository mUserRepository;

    public UserManager() {
        mUserRepository = new UserRepoFirebaseImpl();
    }

    public void registerUser(User user, CreateNewAccountListener listener) {
        mUserRepository.insertUser(user, listener);
    }

    public User getUserByEmail(String email) {
        return mUserRepository.getUserByEmail(email);
    }

    public void loginUser(String email, String password, LoginListener loginListener) {
        mUserRepository.loginUser(email, password, loginListener);
    }

    public void sendResetPasswordEmail(String email, ResetPasswordListener listener) {
        mUserRepository.sendResetPasswordEmail(email, listener);
    }
}
