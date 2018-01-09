package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateusz.grabarski.myshoppinglist.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mateusz Grabarski on 09.01.2018.
 */

public class ShoppingListFragment extends Fragment {

    private ShoppingListFragmentListener mListener;

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

        return view;
    }

    @OnClick(R.id.fragment_shopping_list_fab)
    public void onAddShoppingListClick() {
        mListener.onAddNewShoppingListClick();
    }

    public interface ShoppingListFragmentListener {
        void onAddNewShoppingListClick();
    }
}
