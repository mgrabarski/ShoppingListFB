package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 21.02.2018.
 */

public class SingleShoppingItemDialog extends DialogFragment {

    public static final int ADD_NEW = 1;
    public static final int EDIT_ITEM = 2;

    private static final String KEY_FLOW = "FLOW";
    private static final String KEY_ITEM = "ITEM";

    @BindView(R.id.dialog_single_shopping_item_name_et)
    EditText mItemNameEt;

    @BindView(R.id.dialog_single_shopping_item_name_til)
    TextInputLayout mItemNameTil;

    @BindView(R.id.dialog_single_shopping_item_value_et)
    EditText mItemNumberEt;

    @BindView(R.id.dialog_single_shopping_item_value_til)
    TextInputLayout mItemNumberTil;

    private int flow;
    private ShoppingItem item;

    private SingleShoppingItemDialogInterface mListener;

    public static SingleShoppingItemDialog newInstance() {

        Bundle args = new Bundle();
        args.putInt(KEY_FLOW, ADD_NEW);

        SingleShoppingItemDialog fragment = new SingleShoppingItemDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static SingleShoppingItemDialog newInstance(ShoppingItem item) {

        Bundle args = new Bundle();
        args.putInt(KEY_FLOW, EDIT_ITEM);
        args.putSerializable(KEY_ITEM, item);

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args == null)
            return;

        flow = args.getInt(KEY_FLOW);

        if (args.containsKey(KEY_ITEM))
            item = (ShoppingItem) args.getSerializable(KEY_ITEM);
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

        ButterKnife.bind(this, view);

        if (flow == EDIT_ITEM) {
            mItemNameEt.setText(item.getName());
            mItemNumberEt.setText(String.valueOf(item.getNumber()));
        }

        builder.setView(view);

        builder.setPositiveButton(flow == ADD_NEW ? R.string.add : R.string.update, new DialogInterface.OnClickListener() {
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

        if (item == null)
            item = ShoppingItem.getNewShoppingItem();

        item.setNumber(number);
        item.setName(mItemNameEt.getText().toString());

        mListener.onAddNewShoppingItem(item, flow);
        return true;
    }

    public interface SingleShoppingItemDialogInterface {
        void onAddNewShoppingItem(ShoppingItem item, int flow);
    }
}
