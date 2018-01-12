package com.mateusz.grabarski.myshoppinglist.views.activities.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.profile.fragments.MainEditProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity implements
        MainEditProfileFragment.MainEditProfileFragmentListener {

    @BindView(R.id.activity_edit_profile_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            replaceAndAddToBackStackFragment(MainEditProfileFragment.newInstance());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
