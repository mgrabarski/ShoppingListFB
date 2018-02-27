package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract;

/**
 * Created by MGrabarski on 27.02.2018.
 */

public class CurrentShoppingModelImpl implements CurrentShoppingActivityContract.Model {

    private CurrentShoppingActivityContract.Presenter mPresenter;

    public CurrentShoppingModelImpl(CurrentShoppingActivityContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
