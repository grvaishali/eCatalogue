package com.e.spectra.model;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.repositories.impl.UserRespositoryImpl;
import com.e.spectra.services.impl.UserServiceImp;
import com.e.spectra.ui.view.AuthListener;

public class RegisterViewModel extends ViewModel {

    public String email = null;
    public String password = null;
    public AuthListener authListener;

    UserServiceImp user = DaggerApplicationComponent.builder().build().userService();
    public void onRegisterButtonClick(View view) {
        authListener.onStarted();
        if (null==email || email.isEmpty() || null == password || password.isEmpty()) {
            authListener.onFailed("Please enter valid emailId and Password");
            return;
        }

        LiveData response = user.userRegister(email, password);
        authListener.onSuccess(response);

    }


}
