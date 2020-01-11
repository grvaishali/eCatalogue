package com.e.spectra.ui.view;

import androidx.lifecycle.LiveData;

public interface AuthListener {
    void onStarted();

    void onSuccess(LiveData response);

    void onFailed(String message);

    void onReset(LiveData response);


}
