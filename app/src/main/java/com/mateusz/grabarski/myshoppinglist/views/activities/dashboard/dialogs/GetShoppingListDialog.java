package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.dialogs;

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
import android.widget.EditText;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.utils.InputValidator;

/**
 * Created by Mateusz Grabarski on 09.01.2018.
 */

public class GetShoppingListDialog extends DialogFragment {

    private EditText mNameEt;
    private TextInputLayout mTextInputL;

    private GetShoppingListDialogInterface mListener;

    public static GetShoppingListDialog newInstance() {

        Bundle args = new Bundle();

        GetShoppingListDialog fragment = new GetShoppingListDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof GetShoppingListDialogInterface)
            mListener = (GetShoppingListDialogInterface) context;
        else
            throw new RuntimeException("Activity must implement GetShoppingListDialogInterface");
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog alertDialog = (AlertDialog) getDialog();

        if (alertDialog != null) {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputValidator inputValidator = new InputValidator();

                    if (!inputValidator.isShoppingListNameValid(mNameEt.getText().toString())) {
                        mTextInputL.setError(getString(R.string.empty_shopping_list_name_error));
                    } else {
                        dismiss();
                        mListener.onShoppingListNameTyped(mNameEt.getText().toString());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.shopping_list_name);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_shopping_list_name, null);

        mNameEt = view.findViewById(R.id.dialog_shopping_list_name_et);
        mTextInputL = view.findViewById(R.id.dialog_shopping_list_name_til);

        builder.setView(view);

        builder.setPositiveButton(R.string.ok, null);

        return builder.create();
    }

    public interface GetShoppingListDialogInterface {
        void onShoppingListNameTyped(String shoppingListName);
    }
}
