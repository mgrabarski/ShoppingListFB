package com.mateusz.grabarski.myshoppinglist.views.activities.friends.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.FriendAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.listeners.FriendListListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mateusz Grabarski on 15.05.2018.
 */
public class FriendsListFragment extends Fragment {

    @BindView(R.id.fragment_accepted_friends_list)
    RecyclerView friendsList;

    Unbinder unbinder;

    private List<User> mFriends;
    private FriendAdapter mAdapter;

    private FriendsListFragmentInterface mListener;

    public static FriendsListFragment newInstance() {

        Bundle args = new Bundle();

        FriendsListFragment fragment = new FriendsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FriendsListFragmentInterface) {
            mListener = (FriendsListFragmentInterface) context;
        } else {
            throw new RuntimeException("Activity must implement FriendsListFragmentInterface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accepted_friends, container, false);

        unbinder = ButterKnife.bind(this, view);

        mFriends = new ArrayList<>();
        mAdapter = new FriendAdapter(mFriends, mListener);

        friendsList.setLayoutManager(new LinearLayoutManager(getContext()));
        friendsList.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void updateFriendList(List<User> friends) {
        mFriends.clear();
        mFriends.addAll(friends);
        mAdapter.notifyDataSetChanged();
    }

    public interface FriendsListFragmentInterface extends FriendListListener {

    }
}
