package com.mateusz.grabarski.myshoppinglist.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.mateusz.grabarski.myshoppinglist.R;

/**
 * Created by MGrabarski on 22.12.2017.
 */

public class DialogsGenerator {

    public static AlertDialog.Builder getMessageDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder;
    }

    public static AlertDialog.Builder getMessageDialog(Context context,
                                                       String title,
                                                       String message,
                                                       String positiveText,
                                                       DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = getMessageDialog(context, title, message);
        builder.setPositiveButton(positiveText, positiveListener);
        return builder;
    }
}
