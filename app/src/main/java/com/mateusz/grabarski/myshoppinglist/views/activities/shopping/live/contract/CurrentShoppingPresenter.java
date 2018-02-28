package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.List;

/**
 * Created by MGrabarski on 27.02.2018.
 */

public class CurrentShoppingPresenter implements CurrentShoppingActivityContract.Presenter {

    private CurrentShoppingActivityContract.View mView;
    private CurrentShoppingActivityContract.Model mModel;

    public CurrentShoppingPresenter(CurrentShoppingActivityContract.View view, String owner) {
        this.mView = view;
        this.mModel = new CurrentShoppingModelImpl(this, owner);
    }

    @Override
    public void loadList(String shoppingListId) {
        mModel.setShoppingListId(shoppingListId);
    }

    @Override
    public List<ShoppingItem> getShoppingItems() {
        return mModel.getItems();
    }

    @Override
    public void updateList(List<ShoppingItem> items) {
        mView.updateList();
    }

    @Override
    public void updateItem(ShoppingItem item) {
        mModel.updateItemInDb(item);
    }
}
