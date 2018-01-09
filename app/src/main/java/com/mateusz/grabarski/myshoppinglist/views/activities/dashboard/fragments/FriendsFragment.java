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

public class FriendsFragment extends Fragment {

    public static FriendsFragment newInstance() {

        Bundle args = new Bundle();

        FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);

        return view;
    }
}
