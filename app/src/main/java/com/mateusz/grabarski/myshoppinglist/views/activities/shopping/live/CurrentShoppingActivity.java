package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.dialogs.SingleShoppingItemDialog;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.adapter.CurrentListAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.adapter.CurrentListAdapterListener;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.CurrentShoppingActivityContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.CurrentShoppingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentShoppingActivity extends AppCompatActivity implements
        CurrentShoppingActivityContract.View,
        CurrentListAdapterListener,
        SingleShoppingItemDialog.SingleShoppingItemDialogInterface {

    public static final String KEY_SHOPPING_LIST_ID = "SHOPPING_LIST_ID";
    public static final String KEY_SHOPPING_LIST_NAME = "SHOPPING_LIST_NAME";
    public static final String KEY_SHOPPING_LIST_OWNER = "SHOPPING_LIST_OWNER";

    @BindView(R.id.activity_current_shopping_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_current_shopping_rv)
    RecyclerView shoppingRv;

    private String mShoppingListId;

    private CurrentShoppingActivityContract.Presenter mPresenter;
    private CurrentListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_shopping);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.containsKey(KEY_SHOPPING_LIST_ID)) {
                mShoppingListId = extras.getString(KEY_SHOPPING_LIST_ID);
            } else {
                throw new RuntimeException("In extras are no shopping list id");
            }
        }

        if (extras != null && extras.getString(KEY_SHOPPING_LIST_OWNER) != null)
            mPresenter = new CurrentShoppingPresenter(this, extras.getString(KEY_SHOPPING_LIST_OWNER));
        else
            throw new RuntimeException("In extras are no shopping list owner email");

        setSupportActionBar(toolbar);

        String listName = getIntent().getExtras().getString(KEY_SHOPPING_LIST_NAME);

        if (listName == null)
            throw new RuntimeException("In extras are no shopping list name");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(listName);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        shoppingRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CurrentListAdapter(mPresenter.getShoppingItems(), this);

        shoppingRv.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadList(mShoppingListId);
    }

    @Override
    public void onItemCheck(ShoppingItem item, int position) {
        mPresenter.updateItem(item);
    }

    @Override
    public void onItemEditClick(ShoppingItem item, int position) {
        SingleShoppingItemDialog
                .newInstance(item)
                .show(getSupportFragmentManager(), SingleShoppingItemDialog.class.getSimpleName());
    }

    @Override
    public void onItemDeleteClick(ShoppingItem item, int position) {

    }

    @Override
    public void updateList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShoppingItemChange(ShoppingItem item, int flow) {
        if (flow == SingleShoppingItemDialog.EDIT_ITEM)
            mPresenter.itemChange(item);
    }
}
