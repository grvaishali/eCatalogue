package com.e.spectra.ui.start;

import android.view.View;

import com.e.spectra.domain.model.RegisterViewModel;
import com.e.spectra.domain.model.services.impl.UserServiceImp;
import com.e.spectra.presentation.view.AuthListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class RegisterViewModelTest {

    @Mock
    AuthListener auth;

    @Mock
    UserServiceImp user;

    @Mock
    View view;

    @InjectMocks
    private RegisterViewModel register=new RegisterViewModel();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }



    @Test
    public void testOnRegisterButtonClickEmptyEMail() {
        //Given
        register.email = null;
        register.password = "test";

        //When
       register.onRegisterButtonClick(view);

        //Then
        Mockito.verify(register.authListener, Mockito.times(1)).onStarted();
        Mockito.verify(register.authListener, Mockito.times(1)).onFailed(Mockito.anyString());
    }

    @Test
    public void testOnRegisterButtonClickEmptyPassword() {
        //Given
        register.email = "test";
        register.password = null;

        //When
    register.onRegisterButtonClick(view);

        //Then
        Mockito.verify(register.authListener, Mockito.times(1)).onStarted();
        Mockito.verify(register.authListener, Mockito.times(1)).onFailed(Mockito.anyString());
    }

    @Test
    public void testOnRegisterButtonClickVanilla() {
        //Given
        register.email = "test";
        register.password = "test";

        //When
  register.onRegisterButtonClick(view);

        //Then
        Mockito.verify(register.authListener, Mockito.times(1)).onStarted();
        Mockito.verify(register.authListener, Mockito.times(0)).onFailed(Mockito.anyString());
    }

}
