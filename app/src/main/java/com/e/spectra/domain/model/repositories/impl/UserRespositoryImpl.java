package com.e.spectra.domain.model.repositories.impl;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.spectra.domain.model.repositories.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

import static com.google.firebase.auth.FirebaseAuth.getInstance;
import static io.fabric.sdk.android.Fabric.TAG;

public class UserRespositoryImpl implements UserRepository {

    @Inject
    public UserRespositoryImpl() {

    }

    @Override
    public LiveData<String> userLogin(String emailId, String password) {

        final LiveData<String> response = new MutableLiveData<String>();

        getInstance().signInWithEmailAndPassword(emailId,
                password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (true) {
                            ((MutableLiveData<String>) response).setValue("login");
                        } else {
                            ((MutableLiveData<String>) response).setValue("login is not complete");
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ((MutableLiveData<String>) response).setValue("Login Failed");
            }
        });
        return response;
    }

    @Override
    public LiveData<String> userReset(String emailId) {

        final LiveData<String> response = new MutableLiveData<String>();
        getInstance().sendPasswordResetEmail(emailId)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Password Reset Email sent.");
                            ((MutableLiveData<String>) response).setValue("Sent Password Reset Link to Email");

                        } else {
                            Log.d(TAG, "onComplete: No user associated with that email.");
                            ((MutableLiveData<String>) response).setValue("No User Associated with that Email.");


                        }
                    }
                });

        return response;
    }

    public LiveData<String> userRegister(String email, String password) {
        final LiveData<String> response = new MutableLiveData<String>();
        getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: Password Register Email sent.");
                    ((MutableLiveData<String>) response).setValue("Sent Password Register Link to Email");

                } else {
                    Log.d(TAG, "onComplete: user already registered with that email.");
                    ((MutableLiveData<String>) response).setValue("user already registered with that email.");


                }
            }
        });
        return response;
    }

}
