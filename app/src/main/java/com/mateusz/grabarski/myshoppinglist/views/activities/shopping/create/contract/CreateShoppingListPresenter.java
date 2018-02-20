package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class CreateShoppingListPresenter implements CreateShoppingListContract.Presenter {

    private CreateShoppingListContract.View mView;
    private CreateShoppingListContract.Model mModel;

    private ShoppingList mShoppingList;

    public CreateShoppingListPresenter(CreateShoppingListContract.View view) {
        this.mView = view;
        mModel = new CreateShoppingListModelImpl(this);
        mModel.getCurrentUser();
        mShoppingList = ShoppingList.getNewShoppingList();
    }

    @Override
    public void setListName(String listName) {
        mShoppingList.setListName(listName);
    }

    @Override
    public void setCurrentUser(User user) {
        mShoppingList.setOwnerEmail(user.getEmail());
    }
}
