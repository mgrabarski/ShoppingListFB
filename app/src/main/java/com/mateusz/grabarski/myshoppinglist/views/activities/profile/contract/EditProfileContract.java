package com.mateusz.grabarski.myshoppinglist.views.activities.profile.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 12.01.2018.
 */

public interface EditProfileContract {

    interface Model {

        void getCurrentLoginUser();
    }

    interface View {

        void loadUserProfile(User user);
    }

    interface Presenter {

        void loadUserData();

        void readyUserProfile(User user);
    }
}
