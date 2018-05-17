package com.mateusz.grabarski.myshoppinglist.views.activities.friends;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.FriendsViewPagerAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.fragments.FriendsListFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.fragments.RequestFriendListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity {

    @BindView(R.id.activity_friend_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_friends_tab)
    TabLayout tabs;

    @BindView(R.id.activity_friends_pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FriendsViewPagerAdapter adapter = new FriendsViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(FriendsListFragment.newInstance(), getString(R.string.friends));
        adapter.addFragment(RequestFriendListFragment.newInstance(), getString(R.string.requests));

        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);
    }
}
