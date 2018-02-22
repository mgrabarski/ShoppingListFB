package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import android.os.Bundle;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class CreateShoppingListPresenter implements CreateShoppingListContract.Presenter {

    public static final String KEY_SAVE_INSTANT_STATE = "SAVE_INSTANT_STATE";

    private CreateShoppingListContract.View mView;
    private CreateShoppingListContract.Model mModel;

    private ShoppingList mShoppingList;

    public CreateShoppingListPresenter(CreateShoppingListContract.View view, Bundle savedInstanceState) {
        this.mView = view;
        mModel = new CreateShoppingListModelImpl(this);
        mModel.getCurrentUser();

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_SAVE_INSTANT_STATE)) {
            mShoppingList = (ShoppingList) savedInstanceState.getSerializable(KEY_SAVE_INSTANT_STATE);
        } else {
            mShoppingList = ShoppingList.getNewShoppingList();
        }
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
    public void addNewShoppingItem(ShoppingItem item) {
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

    @Override
    public void saveInBundleList(Bundle outState) {
        outState.putSerializable(KEY_SAVE_INSTANT_STATE, mShoppingList);
    }

    @Override
    public boolean canCloseActivity() {
        if (mShoppingList.getShoppingItems().size() > 0) {
            mView.displayDialogForSaveShoppingList();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void saveList() {
        mModel.saveList(mShoppingList);
    }

    @Override
    public void listSaved(ShoppingList list) {
        mView.closeView();
    }

    @Override
    public void editItem(ShoppingItem item) {
        for (int i = 0; i < mShoppingList.getShoppingItems().size(); i++) {
            if (mShoppingList.getShoppingItems().get(i).getCreateDate() == item.getCreateDate()) {
                mShoppingList.getShoppingItems().set(i, item);
                break;
            }
        }

        mView.updateList(mShoppingList.getShoppingItems());
    }
}
