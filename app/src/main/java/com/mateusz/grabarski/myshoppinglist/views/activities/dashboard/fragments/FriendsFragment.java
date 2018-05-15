package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.FriendsViewPagerAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments.friends.FriendsListFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments.friends.RequestFriendListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mateusz Grabarski on 09.01.2018.
 */

public class FriendsFragment extends Fragment {

    @BindView(R.id.fragment_friends_list_tab)
    TabLayout tabs;

    @BindView(R.id.fragment_friends_list_pager)
    ViewPager pager;

    @BindView(R.id.fragment_friends_list_ll)
    LinearLayout rootLl;

    Unbinder unbinder;

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

        unbinder = ButterKnife.bind(this, view);

        FriendsViewPagerAdapter adapter = new FriendsViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(FriendsListFragment.newInstance(), getString(R.string.friend));
        adapter.addFragment(RequestFriendListFragment.newInstance(), getString(R.string.request));

        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
