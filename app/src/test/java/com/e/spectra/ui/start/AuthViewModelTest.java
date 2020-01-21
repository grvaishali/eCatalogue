package com.e.spectra.ui.start;


import android.view.View;

import com.e.spectra.domain.model.AuthViewModel;
import com.e.spectra.domain.model.services.impl.UserServiceImp;
import com.e.spectra.presentation.view.AuthListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthViewModelTest {

    @Mock
    UserServiceImp user;
    @Mock
    AuthListener authListener;
    @Mock
    View view;

    @InjectMocks
    private AuthViewModel auth = new AuthViewModel();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnLoginButtonClickEmptyEMail() {
        //Given
        auth.email = null;
        auth.password = "test";

        //When
        auth.onLoginButtonClick(view);

        //Then
        Mockito.verify(auth.authListener, Mockito.times(1)).onStarted();
        Mockito.verify(auth.authListener, Mockito.times(1)).onFailed(Mockito.anyString());
    }

    @Test
    public void testOnLoginButtonClickEmptyPassword() {
        //Given
        auth.email = "test";
        auth.password = null;

        //When
        auth.onLoginButtonClick(view);

        //Then
        Mockito.verify(auth.authListener, Mockito.times(1)).onStarted();
        Mockito.verify(auth.authListener, Mockito.times(1)).onFailed(Mockito.anyString());
    }

    @Test
    public void testOnLoginButtonClickVanilla() {
        //Given
        auth.email = "test";
        auth.password = "test";

        //When
        auth.onLoginButtonClick(view);

        //Then
        Mockito.verify(auth.authListener, Mockito.times(1)).onStarted();
        Mockito.verify(auth.authListener, Mockito.times(0)).onFailed(Mockito.anyString());
    }
}
