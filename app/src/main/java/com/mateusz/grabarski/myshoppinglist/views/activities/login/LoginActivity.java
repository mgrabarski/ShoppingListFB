package com.mateusz.grabarski.myshoppinglist.views.activities.login;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.utils.DialogsGenerator;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.contract.LoginContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.contract.LoginPresenterImpl;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.fragments.LoginFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.fragments.SignUpFragment;

public class LoginActivity extends AppCompatActivity implements
        LoginFragment.LoginFragmentInterface,
        SignUpFragment.SignUpFragmentInterface,
        LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentFragment = LoginFragment.newInstance();
        replaceAndAddToBackStackFragment(currentFragment);

        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void replaceAndAddToBackStackFragment(Fragment fragment) {
        String tag = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(tag, 0);

        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.activity_login_fl, fragment, tag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(tag);
            ft.commit();
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void displayEmailError() {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                getString(R.string.email_valid_error));
        builder.show();
    }

    @Override
    public void displayPasswordError() {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                getString(R.string.password_valid_error));
        builder.show();
    }

    @Override
    public void displayDashboard() {

    }

    @Override
    public void displayPasswordMatchError() {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                getString(R.string.password_confirm_password_not_same));
        builder.show();
    }

    @Override
    public void displayUserNameError() {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                getString(R.string.user_name_empty_error));
        builder.show();
    }

    @Override
    public void onSignUp(String name, String email, String password, String confirmPassword) {
        mPresenter.signUp(name, email, password, confirmPassword);
    }

    @Override
    public void onLogin(String email, String password) {
        mPresenter.validateCredentials(email, password);
    }

    @Override
    public void onSignUp() {
        currentFragment = SignUpFragment.newInstance();
        replaceAndAddToBackStackFragment(currentFragment);
    }

    @Override
    public void onPasswordForget() {

    }

    @Override
    public void onFacebookLogin() {
        // TODO: 22.12.2017
    }

    @Override
    public void onGoogleLogin() {
        // TODO: 22.12.2017
    }
}
