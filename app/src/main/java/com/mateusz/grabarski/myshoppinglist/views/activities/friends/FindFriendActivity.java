package com.mateusz.grabarski.myshoppinglist.views.activities.friends;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FindFriendContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl.FindFriendPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindFriendActivity extends AppCompatActivity implements
        FindFriendContract.View {

    @BindView(R.id.activity_find_friend_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_find_friend_rv)
    RecyclerView friendRv;

    private FindFriendContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());

        mPresenter = new FindFriendPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_find_friend, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_find_friend_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.d(FindFriendActivity.class.getSimpleName(), "onQueryTextSubmit = " + query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d(FindFriendActivity.class.getSimpleName(), "onQueryTextChange = " + newText);

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
