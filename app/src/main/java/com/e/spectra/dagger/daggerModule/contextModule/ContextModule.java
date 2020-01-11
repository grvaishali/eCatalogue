package com.e.spectra.dagger.daggerModule.contextModule;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

//    ContextModule(Context context) {
//        this.context = context;
//    }

    @Provides
    public Context providesContext() {


        return context;

    }

}
