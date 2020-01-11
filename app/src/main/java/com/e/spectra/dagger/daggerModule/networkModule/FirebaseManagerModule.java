package com.e.spectra.dagger.daggerModule.networkModule;

import com.e.spectra.firebase.FirebaseManager;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseManagerModule {

    @Provides
    public FirebaseManager providesFirebaseManager() {
        return FirebaseManager.getInstance();
    }
}
