package com.redcamel.heriansf.neardeal.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by heriansf on 4/9/16.
 */
public class Popup {
    private static Dialog dialog;
    // untuk menampilkan loading popup
    public static void showLoading(Context context) {
        dismiss();

        dialog = ProgressDialog.show(context, "",
                "Loading ...", true);
    }

    // untuk menghilangkan loading popup
    public static void dismiss() {
        if(dialog != null) {
            dialog.dismiss();
        }
    }
}