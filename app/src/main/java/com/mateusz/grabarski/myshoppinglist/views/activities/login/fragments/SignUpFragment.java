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
 * Created by Mateusz Grabarski on 27.12.2016.
 */

public class SignUpFragment extends Fragment {

    @BindView(R.id.fragment_sign_up_name_tv)
    EditText nameEt;

    @BindView(R.id.fragment_sign_up_email_tv)
    EditText emailEt;

    @BindView(R.id.fragment_sign_up_password_tv)
    EditText passwordEt;

    @BindView(R.id.fragment_sign_up_confirm_password_tv)
    EditText confirmPasswordEt;

    @BindView(R.id.fragment_sign_up_progress_bar)
    ProgressBar progressBar;

    private SignUpFragmentInterface mListener;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SignUpFragmentInterface)
            mListener = (SignUpFragmentInterface) context;
        else
            throw new RuntimeException("Activity must implement " + SignUpFragmentInterface.class.getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_sign_up_btn)
    protected void onSignUp() {
        mListener.onSignUp(nameEt.getText().toString(), emailEt.getText().toString(), passwordEt.getText().toString(), confirmPasswordEt.getText().toString());
    }

    public interface SignUpFragmentInterface {
        void onSignUp(String name, String email, String password, String confirmPassword);
    }
}
