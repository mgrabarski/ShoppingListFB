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
import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.WaitingRequestsAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.friends.adapters.listeners.FriendRequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mateusz Grabarski on 15.05.2018.
 */
public class RequestFriendListFragment extends Fragment {

    @BindView(R.id.fragment_request_friend_rv)
    RecyclerView friendRv;

    private WaitingRequestsAdapter mAdapter;
    private List<FriendRequest> requests;
    private RequestFriendListFragmentInterface mListener;

    Unbinder unbinder;

    public static RequestFriendListFragment newInstance() {

        Bundle args = new Bundle();

        RequestFriendListFragment fragment = new RequestFriendListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RequestFriendListFragmentInterface) {
            mListener = (RequestFriendListFragmentInterface) context;
        } else {
            throw new RuntimeException("Activity must implement RequestFriendListFragmentInterface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_friend, container, false);

        unbinder = ButterKnife.bind(this, view);

        friendRv.setLayoutManager(new LinearLayoutManager(getContext()));

        requests = new ArrayList<>();

        mAdapter = new WaitingRequestsAdapter(requests, mListener);

        friendRv.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.loadCurrentLoginUserFriendRequests();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface RequestFriendListFragmentInterface extends FriendRequestListener {

        void loadCurrentLoginUserFriendRequests();
    }
}
