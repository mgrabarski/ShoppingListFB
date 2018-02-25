package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract;

import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.ShoppingRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.UserRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract.data.DashboardUserListManager;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract.data.listener.DashboardListListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class DashboardModelImpl implements
        DashboardContract.Model,
        DashboardListListener {

    private DashboardContract.Presenter mPresenter;
    private UserRepository mUserRepository;
    private ShoppingListRepository mRepository;
    private DashboardUserListManager mUserListManager;
    private List<ShoppingList> mUserLists;

    private boolean mDisplayLists;

    private User mCurrentLoginUser;

    public DashboardModelImpl(DashboardContract.Presenter presenter) {
        this.mPresenter = presenter;
        mUserRepository = new UserRepoFirebaseImpl();
        mRepository = new ShoppingRepoFirebaseImpl();

        mUserLists = new ArrayList<>();

        mDisplayLists = false;
    }

    @Override
    public void getUserData() {
        mUserRepository.getCurrentLoginUser(new CurrentLoginUserListener() {
            @Override
            public void onCurrentLoginUserLoaded(User user) {
                mUserListManager = new DashboardUserListManager(user.getEmail(),
                        mRepository,
                        DashboardModelImpl.this);

                mPresenter.refreshUserData(user);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });
    }

    @Override
    public void getUserShoppingLists() {
        mDisplayLists = true;

        mPresenter.displayUserLists(mUserLists);
    }

    @Override
    public void updateUserLists(List<ShoppingList> lists) {
        mUserLists.clear();
        mUserLists.addAll(lists);

        mPresenter.displayUserLists(lists);
    }
}
