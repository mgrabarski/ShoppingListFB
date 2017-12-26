package com.mateusz.grabarski.myshoppinglist.views.activities.login.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.utils.InputValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MGrabarski on 23.12.2017.
 */

public class ForgotPasswordDialog extends DialogFragment {

    private EditText emailEt;
    private TextInputLayout emailTil;

    private ForgotPasswordDialogListener mListener;

    public static ForgotPasswordDialog newInstance() {

        Bundle args = new Bundle();

        ForgotPasswordDialog fragment = new ForgotPasswordDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ForgotPasswordDialogListener)
            mListener = (ForgotPasswordDialogListener) context;
        else
            throw new RuntimeException("Activity must implement ForgotPasswordDialogListener");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_forgor_password, null);

        emailEt = view.findViewById(R.id.dialog_forgot_password_email_et);
        emailTil = view.findViewById(R.id.dialog_forgot_password_til);

        builder.setView(view);

        builder.setTitle(R.string.reset_password)
                .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onResetPasswordClick(emailEt.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        return builder.create();
    }

    public interface ForgotPasswordDialogListener {
        void onResetPasswordClick(String emailAddress);
    }
}
