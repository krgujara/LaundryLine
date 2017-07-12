package com.example.android.retrofitpostexample.AlertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.android.retrofitpostexample.LoginActivity;
import com.example.android.retrofitpostexample.R;

/**
 * Created by Gujarathi on 18/08/2016.
 */
public class AlertDialogManager {
    public void showAlertDialog(final Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.ic_check_circle_black_24dp : R.drawable.ic_error_black_24dp);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               /* Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);*/
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}
