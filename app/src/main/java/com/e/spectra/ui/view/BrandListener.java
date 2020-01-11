package com.e.spectra.ui.view;

import android.content.Context;

import androidx.lifecycle.LiveData;

public interface BrandListener {

    Context onStarted();

    void onInsert(String response);

    void onFailed(String message);
}
