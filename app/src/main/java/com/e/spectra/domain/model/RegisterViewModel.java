package com.e.spectra.domain.model;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.spectra.domain.model.repositories.impl.UserRespositoryImpl;
import com.e.spectra.presentation.view.AuthListener;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    public String email = null;
    public String password = null;
    public AuthListener authListener;
    UserRespositoryImpl userRespository;

   // UserServiceImp user = DaggerApplicationComponent.builder().build().userService();

    @Inject
    public RegisterViewModel(UserRespositoryImpl userRespository) {
        this.userRespository=userRespository;
    }

    public void onRegisterButtonClick(View view) {
        authListener.onStarted();
        if (null==email || email.isEmpty() || null == password || password.isEmpty()) {
            authListener.onFailed("Please enter valid emailId and Password");
            return;
        }

        LiveData response = userRespository.userRegister(email, password);
        authListener.onSuccess(response);

    }


}
