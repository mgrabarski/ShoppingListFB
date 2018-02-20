package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract.CreateShoppingListContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract.CreateShoppingListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateShoppingListActivity extends AppCompatActivity implements
        CreateShoppingListContract.View {

    public static final String KEY_SHOPPING_LIST_NAME = "SHOPPING_LIST_NAME";

    @BindView(R.id.activity_create_shopping_list_toolbar)
    Toolbar toolbar;

    private CreateShoppingListContract.Presenter mPresenter;

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

        mPresenter = new CreateShoppingListPresenter(this);
        mPresenter.setListName(listName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: 20.02.2018 if user have items on list ask for save
    }
}
