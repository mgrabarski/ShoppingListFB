package com.mateusz.grabarski.myshoppinglist.views.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.DialogsGenerator;
import com.mateusz.grabarski.myshoppinglist.views.activities.profile.contract.EditProfileContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.profile.contract.EditProfilePresenterImpl;
import com.mateusz.grabarski.myshoppinglist.views.activities.profile.fragments.MainEditProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity implements
        EditProfileContract.View,
        MainEditProfileFragment.MainEditProfileFragmentListener {

    @BindView(R.id.activity_edit_profile_toolbar)
    Toolbar toolbar;

    private MainEditProfileFragment mEditProfileFragment;
    private EditProfilePresenterImpl mPresenter;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            mEditProfileFragment = MainEditProfileFragment.newInstance();
            replaceAndAddToBackStackFragment(mEditProfileFragment);
        } else {
            mEditProfileFragment = (MainEditProfileFragment) getSupportFragmentManager()
                    .getFragments()
                    .get(getSupportFragmentManager().getBackStackEntryCount() - 1);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPresenter = new EditProfilePresenterImpl(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
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
            ft.replace(R.id.activity_edit_profile_fl, fragment, tag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(tag);
            ft.commit();
        }
    }

    @Override
    public void onChangeAvatarClick() {

    }

    @Override
    public void loadUserData() {
        mPresenter.loadUserData();
    }

    @Override
    public void loadUserProfile(User user) {
        mUser = user;
        mEditProfileFragment.setUser(user);
    }

    @Override
    public User getUserProfileFromView() {
        User user = mUser;

        user.setName(mEditProfileFragment.getUserName() == null ? "" : mEditProfileFragment.getUserName());

        return user;
    }

    @Override
    public void userProfileUpdated() {
        finish();
    }

    @Override
    public void displayUpdateError(String message) {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_edit_profile_save:
                mPresenter.updateProfile(getUserProfileFromView());
                break;
            case R.id.menu_edit_profile_logout:
                FirebaseAuth.getInstance().signOut();

                finish();
                Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                break;
        }

        return true;
    }
}
