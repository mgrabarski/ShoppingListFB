package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.CurrentShoppingActivityContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.CurrentShoppingPresenter;

public class CurrentShoppingActivity extends AppCompatActivity
        implements CurrentShoppingActivityContract.View {

    public static final String KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID";

    private String mShoppingListId;

    private CurrentShoppingActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_shopping);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.containsKey(KEY_SHOPPING_LIST_ID)) {
                mShoppingListId = extras.getString(KEY_SHOPPING_LIST_ID);
            } else {
                finish();
            }
        }

        mPresenter = new CurrentShoppingPresenter(this);
    }
}
