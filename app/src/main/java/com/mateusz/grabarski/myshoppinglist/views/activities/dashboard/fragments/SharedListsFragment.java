package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateusz.grabarski.myshoppinglist.R;

/**
 * Created by Mateusz Grabarski on 09.01.2018.
 */

public class SharedListsFragment extends Fragment {

    public static SharedListsFragment newInstance() {

        Bundle args = new Bundle();

        SharedListsFragment fragment = new SharedListsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_lists, container, false);

        return view;
    }
}
