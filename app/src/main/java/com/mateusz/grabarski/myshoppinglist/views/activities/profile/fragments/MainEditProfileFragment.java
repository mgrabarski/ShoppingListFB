package com.mateusz.grabarski.myshoppinglist.views.activities.profile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by MGrabarski on 12.01.2018.
 */

public class MainEditProfileFragment extends Fragment {

    @BindView(R.id.fragment_main_edit_profile_name_et)
    TextInputEditText nameEt;

    @BindView(R.id.fragment_main_edit_profile_name_til)
    TextInputLayout nameTil;

    @BindView(R.id.fragment_main_edit_profile_email_et)
    TextInputEditText emailEt;

    @BindView(R.id.fragment_main_edit_profile_email_til)
    TextInputLayout emailTil;

    @BindView(R.id.fragment_main_edit_profile_avatar_iv)
    ImageView avatarIv;

    Unbinder unbinder;

    private MainEditProfileFragmentListener mListener;

    public static MainEditProfileFragment newInstance() {

        Bundle args = new Bundle();

        MainEditProfileFragment fragment = new MainEditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainEditProfileFragmentListener)
            mListener = (MainEditProfileFragmentListener) context;
        else
            throw new RuntimeException("Activity must implement MainEditProfileFragmentListener");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_edit_profile, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.loadUserData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_main_edit_profile_avatar_iv)
    public void onAvatarClick() {
        mListener.onChangeAvatarClick();
    }

    public void setUser(User user) {
        nameEt.setText(user.getName());
        emailEt.setText(user.getEmail());

        Picasso.with(getContext()).load(user.getPictureUrl()).fit().centerCrop().into(avatarIv, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }

    public String getUserName() {
        return nameEt != null ? nameEt.getText().toString() : null;
    }

    public interface MainEditProfileFragmentListener {
        void onChangeAvatarClick();

        void loadUserData();
    }
}
