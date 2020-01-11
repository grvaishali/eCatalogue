package com.e.spectra.services;

import android.app.Application;
import android.content.Context;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.repositories.impl.UserRespositoryImpl;
import com.e.spectra.firebase.FirebaseManager;
import com.e.spectra.services.impl.UserServiceImp;
import com.google.firebase.FirebaseApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserRespositoryImpl user;

    @InjectMocks
    private UserServiceImp userService = DaggerApplicationComponent.create().userService();

    @Mock
    Context context;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
      FirebaseManager.getInstance();

    }

    @Test
    public void testOnUserLogin() {
        //Given
        String email = "test";
        String password = "test";

        //When
        userService.userLogin(email, password);
        //Then
        Mockito.verify(user.userLogin(email, password), Mockito.times(1));

    }


}
