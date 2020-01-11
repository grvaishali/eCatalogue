package com.e.spectra.util;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ViewUtil {
    private static ViewUtil viewUtil;

    private ViewUtil() {

    }

    public static void getInstance() {
        if (viewUtil != null)
            viewUtil = new ViewUtil();


    }

    public static void toast(Context context, String message) {
        getInstance();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    public static void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }


    public static void hideProgressBar(ProgressBar progressBar) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.INVISIBLE);
        }

    }


}