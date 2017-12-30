package com.mateusz.grabarski.myshoppinglist.database.dto.memory;

import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
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
    }

    @Override
    public void insertUser(final User user, final CreateNewAccountListener listener) {
        mUsers.add(user);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    listener.onCreateAccountSuccess(true, user);
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
}
