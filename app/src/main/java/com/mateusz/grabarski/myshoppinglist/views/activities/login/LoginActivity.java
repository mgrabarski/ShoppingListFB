package com.mateusz.grabarski.myshoppinglist.views.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.DialogsGenerator;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.contract.LoginContract;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.contract.LoginPresenterImpl;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.dialogs.ForgotPasswordDialog;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.fragments.LoginFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.fragments.SignUpFragment;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.DashboardActivity;

public class LoginActivity extends AppCompatActivity implements
        LoginFragment.LoginFragmentInterface,
        SignUpFragment.SignUpFragmentInterface,
        LoginContract.View,
        ForgotPasswordDialog.ForgotPasswordDialogListener {

    private static final int GOOGLE_SIGN_IN = 9001;

    private LoginContract.Presenter mPresenter;
    private Fragment currentFragment;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentFragment = LoginFragment.newInstance();

        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            replaceAndAddToBackStackFragment(currentFragment);

        mPresenter = new LoginPresenterImpl(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                mPresenter.loginByGoogle(account);
            } catch (ApiException e) {
                // TODO: 04.01.2018 log exception
                Log.d(LoginActivity.class.getSimpleName(), "onActivityResult: " + e);
            }
        }
    }

    @Override
    public void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (currentFragment instanceof LoginFragment)
                    ((LoginFragment) currentFragment).showProgressIndicator();
                else if (currentFragment instanceof SignUpFragment)
                    ((SignUpFragment) currentFragment).showProgressIndicator();

            }
        });
    }

    @Override
    public void hideProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (currentFragment instanceof LoginFragment)
                    ((LoginFragment) currentFragment).dismissProgressIndicator();
                else if (currentFragment instanceof SignUpFragment)
                    ((SignUpFragment) currentFragment).dismissProgressIndicator();
            }
        });
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
        finish();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
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
    public void displayRegistrationError(String errorMessage) {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                errorMessage);
        builder.show();
    }

    @Override
    public void displayRegistrationSuccess(User user) {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                getString(R.string.registration_success),
                getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
        builder.show();
    }

    @Override
    public void displayLoginError(String errorMessage) {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                errorMessage);
        builder.show();
    }

    @Override
    public void displaySendResetPasswordEmailSuccess() {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                getString(R.string.check_email_for_reset_password_message));
        builder.show();
    }

    @Override
    public void displaySendResetPasswordEmailFailed(String errorMessage) {
        AlertDialog.Builder builder = DialogsGenerator.getMessageDialog(this,
                getString(R.string.information),
                errorMessage);
        builder.show();
    }

    @Override
    public void onSignUp(String name, String email, String password, String confirmPassword) {
        mPresenter.signUp(name, email, password, confirmPassword);
    }

    @Override
    public void onLogin(String email, String password) {
        mPresenter.loginByEmail(email, password);
    }

    @Override
    public void onSignUp() {
        currentFragment = SignUpFragment.newInstance();
        replaceAndAddToBackStackFragment(currentFragment);
    }

    @Override
    public void onPasswordForget() {
        ForgotPasswordDialog dialog = ForgotPasswordDialog.newInstance();
        dialog.show(getSupportFragmentManager(), ForgotPasswordDialog.class.getSimpleName());
    }

    @Override
    public void onFacebookLogin() {
        // TODO: 22.12.2017
    }

    @Override
    public void onGoogleLogin() {
        showProgressDialog();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    public void onResetPasswordClick(String emailAddress) {
        mPresenter.resetPassword(emailAddress);
    }
}
