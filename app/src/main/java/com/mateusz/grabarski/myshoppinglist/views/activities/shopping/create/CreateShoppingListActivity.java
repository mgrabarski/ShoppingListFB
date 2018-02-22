package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.adapter.CreateShoppingListAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.adapter.CreateShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract.CreateShoppingListContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract.CreateShoppingListPresenter;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.dialogs.SingleShoppingItemDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateShoppingListActivity extends AppCompatActivity implements
        CreateShoppingListContract.View,
        SingleShoppingItemDialog.SingleShoppingItemDialogInterface,
        CreateShoppingListListener {

    public static final String KEY_SHOPPING_LIST_NAME = "SHOPPING_LIST_NAME";

    @BindView(R.id.activity_create_shopping_list_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_create_shopping_list_rv)
    RecyclerView shoppingListRv;

    private CreateShoppingListContract.Presenter mPresenter;
    private CreateShoppingListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String listName = getIntent().getExtras().getString(KEY_SHOPPING_LIST_NAME);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(listName);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        shoppingListRv.setLayoutManager(new LinearLayoutManager(this));

        mPresenter = new CreateShoppingListPresenter(this);
        mPresenter.setListName(listName);

        mAdapter = new CreateShoppingListAdapter(mPresenter.getShoppingList(), this);

        shoppingListRv.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: 20.02.2018 if user have items on list ask for save
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_shopping_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_create_shopping_list_add:
                SingleShoppingItemDialog.newInstance().show(getSupportFragmentManager(), null);
                break;
        }

        return true;
    }

    @Override
    public void onAddNewShoppingItem(ShoppingItem item, int flow) {
        if (flow == SingleShoppingItemDialog.ADD_NEW)
            mPresenter.addNewShoppingItem(item);
        else if (flow == SingleShoppingItemDialog.EDIT_ITEM)
            mPresenter.editItem(item);
    }

    @Override
    public void updateList(List<ShoppingItem> shoppingItems) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditClick(ShoppingItem item) {
        SingleShoppingItemDialog.newInstance(item).show(getSupportFragmentManager(), null);
    }

    @Override
    public void onDeleteClick(ShoppingItem item) {
        mPresenter.removeShoppingItem(item);
    }
}
