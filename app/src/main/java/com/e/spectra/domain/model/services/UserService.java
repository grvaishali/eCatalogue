package com.e.spectra.services;

import androidx.lifecycle.LiveData;

public interface UserService {

    LiveData<String> userLogin(String emailId, String password);

    LiveData<String> userReset(String emailId);

    LiveData<String> userRegister(String emailId,String password);

}

