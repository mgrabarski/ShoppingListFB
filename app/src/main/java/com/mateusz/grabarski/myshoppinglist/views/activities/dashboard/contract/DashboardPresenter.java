package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View mView;
    private DashboardContract.Model mModel;

    public DashboardPresenter(DashboardContract.View view) {
        this.mView = view;
        mModel = new DashboardModelImpl(this);
    }

    @Override
    public void loadUserInformation() {
        mModel.getUserData();
    }

    @Override
    public void refreshUserData(User user) {
        mView.setUserName(user.getName());
        mView.setUserEmail(user.getEmail());
    }

    @Override
    public void loadUserShoppingLists() {
        mModel.getUserShoppingLists();
    }

    @Override
    public void displayUserLists(List<ShoppingList> lists) {
        mView.setUserShoppingList(lists);
    }
}
