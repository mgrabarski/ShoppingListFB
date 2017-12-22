package com.mateusz.grabarski.myshoppinglist.views.activities.login.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.mateusz.grabarski.myshoppinglist.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mateusz Grabarski on 26.12.2016.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.fragment_login_email_tv)
    EditText emailTv;

    @BindView(R.id.fragment_login_password_tv)
    EditText passwordTv;

    @BindView(R.id.fragment_login_progress_bar)
    ProgressBar progressBar;

    private LoginFragmentInterface mListener;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof LoginFragmentInterface)
            mListener = (LoginFragmentInterface) context;
        else
            throw new RuntimeException("Activity must implement " + LoginFragmentInterface.class.getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_login_login_btn)
    protected void onLogin() {
        mListener.onLogin(emailTv.getText().toString(), passwordTv.getText().toString());
    }

    @OnClick(R.id.fragment_login_forgot_password_ll)
    protected void onPasswordForget() {
        mListener.onPasswordForget();
    }

    @OnClick(R.id.fragment_login_forgot_sign_up_ll)
    protected void onSignUp() {
        mListener.onSignUp();
    }

    @OnClick(R.id.fragment_login_facebook_btn)
    protected void onFacebookLogin() {
        mListener.onFacebookLogin();
    }

    @OnClick(R.id.fragment_login_google_btn)
    protected void onGoogleLogin() {
        mListener.onGoogleLogin();
    }

    public interface LoginFragmentInterface {
        void onLogin(String email, String password);

        void onSignUp();

        void onPasswordForget();

        void onFacebookLogin();

        void onGoogleLogin();
    }
}
