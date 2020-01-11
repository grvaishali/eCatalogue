package com.e.spectra.application;

import android.app.Activity;
import android.app.Application;


import com.e.spectra.dagger.component.ApplicationComponent;
import com.e.spectra.dagger.component.DaggerApplicationComponent;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class EcatalogueApplication extends Application implements HasActivityInjector {
private ApplicationComponent component;

@Inject
    DispatchingAndroidInjector dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();

      //  component= DaggerApplicationComponent.builder().build();


//               DaggerApplicationComponent.builder()
//                .application(this)
//                .build()
//                .inject(this);

        // component= DaggerApplicationComponent.create();

    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
