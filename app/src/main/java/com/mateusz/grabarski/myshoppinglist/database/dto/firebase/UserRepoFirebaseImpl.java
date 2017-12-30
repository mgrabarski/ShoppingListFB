package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 30.12.2017.
 */

public class UserRepoFirebaseImpl implements UserRepository {
    @Override
    public void insertUser(User user, CreateNewAccountListener listener) {
    
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
