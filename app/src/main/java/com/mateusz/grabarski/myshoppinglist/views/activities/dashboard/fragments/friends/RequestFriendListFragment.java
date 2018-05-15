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
public class RequestFriendListFragment extends Fragment {

    public static RequestFriendListFragment newInstance() {

        Bundle args = new Bundle();

        RequestFriendListFragment fragment = new RequestFriendListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_friend, container, false);

        return view;
    }
}
