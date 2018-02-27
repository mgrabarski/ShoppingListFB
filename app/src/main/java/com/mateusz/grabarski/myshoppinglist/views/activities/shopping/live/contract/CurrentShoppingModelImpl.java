package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract;

import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.ShoppingRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.data.CurrentListListener;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.data.CurrentListManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGrabarski on 27.02.2018.
 */

public class CurrentShoppingModelImpl implements CurrentShoppingActivityContract.Model, CurrentListListener {

    private CurrentShoppingActivityContract.Presenter mPresenter;
    private ShoppingListRepository mRepository;
    private List<ShoppingItem> mItems;
    private CurrentListManager mCurrentListManager;

    public CurrentShoppingModelImpl(CurrentShoppingActivityContract.Presenter presenter, String owner) {
        this.mPresenter = presenter;
        this.mRepository = new ShoppingRepoFirebaseImpl();
        mItems = new ArrayList<>();
        mCurrentListManager = new CurrentListManager(owner, mRepository, this);
    }

    @Override
    public List<ShoppingItem> getItems() {
        return mItems;
    }

    @Override
    public void setShoppingListId(String shoppingListId) {
        mCurrentListManager.setShoppingListId(shoppingListId);
    }

    @Override
    public void updateList(List<ShoppingItem> items) {
        mItems.clear();
        mItems.addAll(items);
        mPresenter.updateList(mItems);
    }
}
