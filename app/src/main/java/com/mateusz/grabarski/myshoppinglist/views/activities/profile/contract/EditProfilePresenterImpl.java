package com.mateusz.grabarski.myshoppinglist.views.activities.profile.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by MGrabarski on 12.01.2018.
 */

public class EditProfilePresenterImpl implements EditProfileContract.Presenter {

    private EditProfileContract.View mView;
    private EditProfileContract.Model mModel;

    public EditProfilePresenterImpl(EditProfileContract.View view) {
        this.mView = view;
        mModel = new EditProfileModelImpl(this);
    }

    @Override
    public void loadUserData() {
        mModel.getCurrentLoginUser();
    }

    @Override
    public void readyUserProfile(User user) {
        mView.loadUserProfile(user);
    }

    @Override
    public void updateProfile(User user) {
        mModel.updateUserProfile(user);
    }

    @Override
    public void updateProfileSuccess() {
        mView.userProfileUpdated();
    }

    @Override
    public void updateProfileFailed(String message) {
        mView.displayUpdateError(message);
    }
}
