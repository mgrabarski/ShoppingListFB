package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List;

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

    @Override
    public void addNewShoppingItem(String name, float number) {
        ShoppingItem item = ShoppingItem.getNewShoppingItem();
        item.setNumber(number);
        item.setName(name);

        mShoppingList.getShoppingItems().add(item);

        mView.updateList(mShoppingList.getShoppingItems());
    }

    @Override
    public List<ShoppingItem> getShoppingList() {
        return mShoppingList.getShoppingItems();
    }

    @Override
    public void removeShoppingItem(ShoppingItem item) {
        mShoppingList.getShoppingItems().remove(item);
        mView.updateList(mShoppingList.getShoppingItems());
    }
}
