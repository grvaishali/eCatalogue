package com.e.spectra.model;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.spectra.data.repositories.impl.UserRespositoryImpl;
import com.e.spectra.ui.view.AuthListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.crashlytics.android.beta.Beta.TAG;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class AuthViewModel extends ViewModel {
    public String email = null;
    public String password = null;
    public AuthListener authListener;

    private FirebaseAuth.AuthStateListener mAuthListener;
    UserRespositoryImpl user;

    @Inject
    public AuthViewModel(UserRespositoryImpl user) {
        this.user = user;

    }

    public void onLoginButtonClick(View view) {
        authListener.onStarted();
        if (null == email || email.isEmpty() || null == password || password.isEmpty()) {
            authListener.onFailed("Please enter valid emailId and Password");
            return;
        }

        LiveData response = user.userLogin(email, password);
        authListener.onSuccess(response);

    }

    public void onForgotPasswordButtonClick(View view) {


        if (null == email || email.isEmpty()) {
            authListener.onFailed("Please enter your emailId ");
            return;
        }
        LiveData response = user.userReset(email);
        authListener.onReset(response);


    }

    /*
         ----------------------------- Firebase setup ---------------------------------
      */
    public void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // UserData is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    public void onStart() {
        getInstance().addAuthStateListener(mAuthListener);
    }

    public void onStop() {
        if (mAuthListener != null) {
            getInstance().removeAuthStateListener(mAuthListener);

        }
    }
}
