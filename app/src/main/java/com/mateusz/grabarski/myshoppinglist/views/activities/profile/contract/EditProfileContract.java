package com.mateusz.grabarski.myshoppinglist.views.activities.profile.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 12.01.2018.
 */

public interface EditProfileContract {

    interface Model {

        void getCurrentLoginUser();

        void updateUserProfile(User user);
    }

    interface View {

        void loadUserProfile(User user);

        User getUserProfileFromView();

        void userProfileUpdated();

        void displayUpdateError(String message);
    }

    interface Presenter {

        void loadUserData();

        void readyUserProfile(User user);

        void updateProfile(User user);

        void updateProfileSuccess();

        void updateProfileFailed(String message);
    }
}
