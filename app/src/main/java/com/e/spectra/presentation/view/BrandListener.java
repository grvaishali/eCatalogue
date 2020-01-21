package com.e.spectra.presentation.view;

import android.content.Context;

import androidx.lifecycle.LiveData;

public interface BrandListener {

    Context onStarted();

    void onInsert(String response);

    void onFailed(String message);
}
