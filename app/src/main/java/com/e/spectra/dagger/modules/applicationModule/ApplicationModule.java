package com.e.spectra.dagger.modules.applicationModule;

import android.content.Context;

import com.e.spectra.application.EcatalogueApplication;
import com.e.spectra.firebase.FirebaseManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    Context provideContext(EcatalogueApplication application) {
        return application;
    }

    @Provides
    public FirebaseManager providesFirebaseManager() {
        return FirebaseManager.getInstance();
    }

}
