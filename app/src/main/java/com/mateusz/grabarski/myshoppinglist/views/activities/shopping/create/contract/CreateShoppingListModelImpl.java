package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.UserRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class CreateShoppingListModelImpl implements CreateShoppingListContract.Model {

    private CreateShoppingListContract.Presenter mPresenter;
    private UserRepository mUserRepository;

    public CreateShoppingListModelImpl(CreateShoppingListContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mUserRepository = new UserRepoFirebaseImpl();
    }

    @Override
    public void getCurrentUser() {
        mUserRepository.getCurrentLoginUser(new CurrentLoginUserListener() {
            @Override
            public void onCurrentLoginUserLoaded(User user) {
                mPresenter.setCurrentUser(user);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });
    }
}
