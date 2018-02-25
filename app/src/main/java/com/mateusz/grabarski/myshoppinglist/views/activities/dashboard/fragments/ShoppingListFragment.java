package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.ShoppingListAdapter;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.adapters.listeners.ShoppingListClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mateusz Grabarski on 09.01.2018.
 */

public class ShoppingListFragment extends Fragment {

    @BindView(R.id.fragment_shopping_list_rv)
    RecyclerView listRv;

    private ShoppingListFragmentListener mListener;
    private List<ShoppingList> mLists;
    private ShoppingListAdapter mAdapter;

    public static ShoppingListFragment newInstance() {

        Bundle args = new Bundle();

        ShoppingListFragment fragment = new ShoppingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ShoppingListFragmentListener)
            mListener = (ShoppingListFragmentListener) context;
        else
            throw new RuntimeException("Activity must implement ShoppingListFragmentListener");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        ButterKnife.bind(this, view);

        mLists = new ArrayList<>();
        mAdapter = new ShoppingListAdapter(mLists, mListener);

        listRv.setLayoutManager(new LinearLayoutManager(getContext()));
        listRv.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.loadUserShoppingLists();
    }

    @OnClick(R.id.fragment_shopping_list_fab)
    public void onAddShoppingListClick() {
        mListener.onAddNewShoppingListClick();
    }

    public void updateList(List<ShoppingList> lists) {
        if (mLists != null && mAdapter != null) {
            mLists.clear();
            mLists.addAll(lists);
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface ShoppingListFragmentListener extends ShoppingListClickListener {
        void onAddNewShoppingListClick();

        void loadUserShoppingLists();
    }
}
