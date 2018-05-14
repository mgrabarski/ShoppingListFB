package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.mateusz.grabarski.myshoppinglist.R;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 26.02.2018.
 */

public class DeleteShoppingListDialog extends DialogFragment {

    private static final String KEY_SERIALIZED_LIST = "SERIALIZED_LIST";

    private ShoppingList mList;
    private DeleteShoppingListDialogInterface mListener;

    public static DeleteShoppingListDialog newInstance(ShoppingList list) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_SERIALIZED_LIST, list);

        DeleteShoppingListDialog fragment = new DeleteShoppingListDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DeleteShoppingListDialogInterface)
            mListener = (DeleteShoppingListDialogInterface) context;
        else
            throw new RuntimeException("Activity must implement DeleteShoppingListDialogInterface");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments == null)
            return;

        mList = (ShoppingList) arguments.getSerializable(KEY_SERIALIZED_LIST);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.delete_list);
        builder.setMessage(getString(R.string.are_you_sure_you_want_to_delete_the_list) +
                " " +
                mList.getListName() + "?");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDeleteList(mList);
            }
        });
        builder.setNegativeButton(R.string.no, null);

        return builder.create();
    }

    public interface DeleteShoppingListDialogInterface {
        void onDeleteList(ShoppingList list);
    }
}
