package com.mateusz.grabarski.myshoppinglist.views.activities.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.FriendsViewPagerAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.FriendContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.contract.impl.FriendPresenterImpl;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.fragments.FriendsListFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.fragments.RequestFriendListFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.models.FriendRequestUI;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity implements
        FriendContract.View,
        RequestFriendListFragment.RequestFriendListFragmentInterface {

    private static final String TAG = "FriendsActivity";

    @BindView(R.id.activity_friend_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_friends_tab)
    TabLayout tabs;

    @BindView(R.id.activity_friends_pager)
    ViewPager pager;

    private FriendContract.Presenter mPresenter;
    private FriendsViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());

        mViewPagerAdapter = new FriendsViewPagerAdapter(getSupportFragmentManager());

        mViewPagerAdapter.addFragment(FriendsListFragment.newInstance(), getString(R.string.friends));
        mViewPagerAdapter.addFragment(RequestFriendListFragment.newInstance(), getString(R.string.requests));

        pager.setAdapter(mViewPagerAdapter);

        tabs.setupWithViewPager(pager);

        mPresenter = new FriendPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_friends_add_friend:
                Intent intent = new Intent(this, FindFriendActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onAccept(FriendRequestUI friendRequest) {
        mPresenter.acceptedRequest(friendRequest);
    }

    @Override
    public void onRefused(FriendRequestUI friendRequest) {
        mPresenter.refusedRequest(friendRequest);
    }

    @Override
    public void loadCurrentLoginUserFriendRequests() {
        mPresenter.loadCurrentLoginUserFriendRequests();
    }

    @Override
    public void displayFriendRequests(List<FriendRequestUI> friendRequests) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((RequestFriendListFragment) mViewPagerAdapter.getFragment(RequestFriendListFragment.class))
                        .updateFriendRequests(friendRequests);
            }
        });
    }
}
