package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mateusz.grabarski.myshoppinglist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateShoppingListActivity extends AppCompatActivity {

    public static final String KEY_SHOPPING_LIST_NAME = "SHOPPING_LIST_NAME";

    @BindView(R.id.activity_create_shopping_list_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString(KEY_SHOPPING_LIST_NAME));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: 20.02.2018 if user have items on list ask for save
    }
}
