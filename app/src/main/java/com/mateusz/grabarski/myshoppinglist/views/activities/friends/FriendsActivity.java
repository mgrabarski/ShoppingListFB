package com.mateusz.grabarski.myshoppinglist.views.activities.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
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

        toolbar.setNavigationOnClickListener(v -> finish());

        FriendsViewPagerAdapter adapter = new FriendsViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(FriendsListFragment.newInstance(), getString(R.string.friends));
        adapter.addFragment(RequestFriendListFragment.newInstance(), getString(R.string.requests));

        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);
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
}
