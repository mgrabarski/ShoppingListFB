package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mateusz.grabarski.myshoppinglist.R;

/**
 * Created by Mateusz Grabarski on 21.02.2018.
 */

public class SingleShoppingItemDialog extends DialogFragment {

    private EditText mItemNameEt, mItemNumberEt;
    private TextInputLayout mItemNameTil, mItemNumberTil;

    private SingleShoppingItemDialogInterface mListener;

    public static SingleShoppingItemDialog newInstance() {

        Bundle args = new Bundle();

        SingleShoppingItemDialog fragment = new SingleShoppingItemDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SingleShoppingItemDialogInterface)
            mListener = (SingleShoppingItemDialogInterface) context;
        else
            throw new RuntimeException("Activity must implement SingleShoppingItemDialogInterface");
    }

    @Override
    public void onResume() {
        super.onResume();

        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkValues())
                        dismiss();
                }
            });
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.add_new_item);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_single_shopping_item, null);

        mItemNameEt = view.findViewById(R.id.dialog_single_shopping_item_name_et);
        mItemNumberEt = view.findViewById(R.id.dialog_single_shopping_item_value_et);
        mItemNameTil = view.findViewById(R.id.dialog_single_shopping_item_name_til);
        mItemNumberTil = view.findViewById(R.id.dialog_single_shopping_item_value_til);

        builder.setView(view);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton(R.string.cancel, null);

        return builder.create();
    }

    private boolean checkValues() {
        if (TextUtils.isEmpty(mItemNameEt.getText().toString())) {
            mItemNameTil.setError(getString(R.string.name_can_not_be_empty));
            return false;
        } else if (TextUtils.isEmpty(mItemNumberEt.getText().toString())) {
            mItemNumberTil.setError(getString(R.string.number_can_not_be_empty));
            return false;
        }

        float number = Float.parseFloat(mItemNumberEt.getText().toString());

        if (number <= 0) {
            mItemNumberTil.setError(getString(R.string.number_must_be_higher_then_zero));
            return false;
        }

        mListener.onAddNewShoppingItem(mItemNameEt.getText().toString(), number);
        return true;
    }

    public interface SingleShoppingItemDialogInterface {
        void onAddNewShoppingItem(String name, float number);
    }
}
