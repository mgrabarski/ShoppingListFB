package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.dialogs.GetShoppingListDialog;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments.ShoppingListFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements
        ShoppingListFragment.ShoppingListFragmentListener,
        GetShoppingListDialog.GetShoppingListDialogInterface {

    @BindView(R.id.activity_dashboard_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_dashboard_navigation)
    NavigationView navigationView;

    @BindView(R.id.activity_dashboard_drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        drawerToggle = setupDrawerToggle();
        drawerToggle.syncState();

        setupDrawerContent(navigationView);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            selectDrawerItem(navigationView.getMenu().getItem(0));
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;
        AppCompatActivity activity = null;

        switch (menuItem.getItemId()) {
            case R.id.dashboard_drawer_shopping_lists:
                fragment = ShoppingListFragment.newInstance();
                break;
            case R.id.dashboard_drawer_your_friends:
                // TODO: 09.01.2018
                break;
            case R.id.dashboard_drawer_shared_lists:
                // TODO: 09.01.2018
                break;
            case R.id.dashboard_drawer_edit_profile:
                // TODO: 09.01.2018
                break;
            case R.id.dashboard_drawer_settings:
                // TODO: 09.01.2018
                break;
            case R.id.dashboard_drawer_help_feedback:
                // TODO: 09.01.2018  
                break;
        }

        if (fragment != null) {
            replaceAndAddToBackStackFragment(fragment);
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
            drawerLayout.closeDrawers();
        } else if (activity != null) {
            drawerLayout.closeDrawers();
            Intent intent = new Intent(this, activity.getClass());
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void replaceAndAddToBackStackFragment(Fragment fragment) {
        String tag = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(tag, 0);

        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.activity_dashboard_content_fl, fragment, tag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(tag);
            ft.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddNewShoppingListClick() {
        GetShoppingListDialog dialog = GetShoppingListDialog.newInstance();
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onShoppingListNameTyped(String shoppingListName) {
        Log.d(DashboardActivity.class.getSimpleName(), "onShoppingListNameTyped: " + shoppingListName);
    }
}
