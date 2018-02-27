package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract;

/**
 * Created by MGrabarski on 27.02.2018.
 */

public class CurrentShoppingPresenter implements CurrentShoppingActivityContract.Presenter {

    private CurrentShoppingActivityContract.View mView;
    private CurrentShoppingActivityContract.Model mModel;

    public CurrentShoppingPresenter(CurrentShoppingActivityContract.View view) {
        this.mView = view;
    }
}
