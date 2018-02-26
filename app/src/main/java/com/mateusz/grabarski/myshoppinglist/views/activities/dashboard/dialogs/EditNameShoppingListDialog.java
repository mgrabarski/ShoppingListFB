package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.utils.InputValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mateusz Grabarski on 26.02.2018.
 */

public class EditNameShoppingListDialog extends DialogFragment {

    private static final String KEY_SERIALIZABLE_LIST = "SERIALIZABLE_LIST";

    @BindView(R.id.dialog_shopping_list_name_et)
    EditText mNameEt;

    @BindView(R.id.dialog_shopping_list_name_til)
    TextInputLayout mTextInputL;

    private ShoppingList mList;
    private EditNameShoppingListDialogInterface mListener;

    public static EditNameShoppingListDialog newInstance(ShoppingList list) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_SERIALIZABLE_LIST, list);

        EditNameShoppingListDialog fragment = new EditNameShoppingListDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof EditNameShoppingListDialogInterface)
            mListener = (EditNameShoppingListDialogInterface) context;
        else
            throw new RuntimeException("Activity must implement EditNameShoppingListDialogInterface");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments == null)
            return;

        mList = (ShoppingList) arguments.getSerializable(KEY_SERIALIZABLE_LIST);
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
                        mList.setListName(mNameEt.getText().toString());
                        mListener.onEditShoppingList(mList);
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.edit_list_name);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_shopping_list_name, null);

        ButterKnife.bind(this, view);

        mNameEt.setText(mList.getListName());

        builder.setView(view);

        builder.setPositiveButton(R.string.save, null);
        builder.setNegativeButton(R.string.cancel, null);

        return builder.create();
    }

    public interface EditNameShoppingListDialogInterface {
        void onEditShoppingList(ShoppingList list);
    }
}
