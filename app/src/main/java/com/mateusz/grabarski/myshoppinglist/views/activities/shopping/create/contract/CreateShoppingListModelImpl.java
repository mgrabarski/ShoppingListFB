package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.UserRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.ShoppingListManager;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class CreateShoppingListModelImpl implements CreateShoppingListContract.Model {

    private CreateShoppingListContract.Presenter mPresenter;
    private UserRepository mUserRepository;
    private ShoppingListManager mShoppingListManager;

    public CreateShoppingListModelImpl(CreateShoppingListContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mUserRepository = new UserRepoFirebaseImpl();
        this.mShoppingListManager = new ShoppingListManager();
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

    @Override
    public void saveList(ShoppingList shoppingList) {
        mShoppingListManager.insertShoppingList(shoppingList, new InsertShoppingListListener() {
            @Override
            public void onInsertSuccess(ShoppingList list) {
                mPresenter.listSaved(list);
            }

            @Override
            public void onInsertError(ShoppingList list) {

            }
        });
    }
}
