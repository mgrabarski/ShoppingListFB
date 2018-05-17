package com.mateusz.grabarski.myshoppinglist;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Bus;

/**
 * Created by MGrabarski on 20.12.2017.
 */

public class App extends Application {

    private Bus mBus;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = new Bus();
        FirebaseApp.initializeApp(this);

        // Enabling Offline Capabilities
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public Bus getBus() {
        return mBus;
    }
}
