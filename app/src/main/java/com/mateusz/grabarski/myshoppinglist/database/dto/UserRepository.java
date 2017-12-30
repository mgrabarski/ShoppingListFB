package com.mateusz.grabarski.myshoppinglist.database.dto;

import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 27.12.2017.
 */

public interface UserRepository {

    void insertUser(User user, CreateNewAccountListener listener);
    void updateUser(User user);
    User getUserByEmail(String email);
}
