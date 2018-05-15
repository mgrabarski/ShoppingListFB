package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments.friends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateusz.grabarski.myshoppinglist.R;

/**
 * Created by Mateusz Grabarski on 15.05.2018.
 */
public class FriendsListFragment extends Fragment {

    public static FriendsListFragment newInstance() {

        Bundle args = new Bundle();

        FriendsListFragment fragment = new FriendsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accepted_friends, container, false);

        return view;
    }
}
