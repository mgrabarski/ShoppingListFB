package com.mateusz.grabarski.myshoppinglist.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mateusz.grabarski.myshoppinglist.App;
import com.squareup.otto.Bus;

/**
 * Created by MGrabarski on 20.12.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Bus mBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBus = ((App) getApplication()).getBus();
        mBus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBus.unregister(this);
    }
}
