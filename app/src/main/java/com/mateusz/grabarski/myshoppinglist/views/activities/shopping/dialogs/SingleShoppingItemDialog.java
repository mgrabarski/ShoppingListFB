package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.dialogs;

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_single_shopping_item, null);

        mItemNameEt = view.findViewById(R.id.dialog_single_shopping_item_name_et);
        mItemNumberEt = view.findViewById(R.id.dialog_single_shopping_item_value_et);
        mItemNameTil = view.findViewById(R.id.dialog_single_shopping_item_name_til);
        mItemNumberTil = view.findViewById(R.id.dialog_single_shopping_item_value_til);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkValues();
            }
        });

        builder.setNegativeButton(R.string.cancel, null);

        return builder.create();
    }

    private void checkValues() {
        
    }

    public interface SingleShoppingItemDialogInterface {
        void onAddNewShoppingItem(String name, float number);
    }
}
