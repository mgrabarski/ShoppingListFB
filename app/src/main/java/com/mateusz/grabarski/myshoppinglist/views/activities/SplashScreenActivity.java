package com.mateusz.grabarski.myshoppinglist.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mateusz.grabarski.myshoppinglist.base.BaseActivity;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.DashboardActivity;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.LoginActivity;

/**
 * Created by Mateusz Grabarski on 29.12.2016.
 */

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
