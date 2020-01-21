package com.e.spectra.domain.model.services.impl;

import androidx.lifecycle.LiveData;


import com.e.spectra.domain.model.repositories.impl.UserRespositoryImpl;
import com.e.spectra.domain.model.services.UserService;

import javax.inject.Inject;

public class UserServiceImp implements UserService {

    @Inject
    UserServiceImp() {

    }

    @Inject
    UserRespositoryImpl user;

    @Override
    public LiveData<String> userLogin(String emailId, String password) {

        LiveData<String> data=user.userLogin(emailId, password);
        return data;
    }

    @Override
    public LiveData<String> userReset(String emailId) {
        return user.userReset(emailId);
    }

    @Override
    public LiveData<String> userRegister(String emailId, String password) {
        return user.userRegister(emailId, password);
    }
}
