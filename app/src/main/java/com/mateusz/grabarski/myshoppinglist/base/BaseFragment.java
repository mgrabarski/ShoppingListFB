package com.mateusz.grabarski.myshoppinglist.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mateusz.grabarski.myshoppinglist.App;
import com.squareup.otto.Bus;

/**
 * Created by MGrabarski on 20.12.2017.
 */

public class BaseFragment extends Fragment {

    protected Bus mBus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBus = ((App) getActivity().getApplication()).getBus();
        mBus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBus.unregister(this);
    }
}
