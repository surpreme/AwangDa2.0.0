package com.aite.a.utils;

import android.content.Context;
import com.google.android.material.snackbar.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String msg) {
        if (context == null && msg == null) return;
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);

        } else {
            toast.setText(msg);
        }
        toast.show();
    }


    public static void showSnakbar(View view, String msg, View.OnClickListener onClickListener) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("确定", onClickListener).show();
    }

}
