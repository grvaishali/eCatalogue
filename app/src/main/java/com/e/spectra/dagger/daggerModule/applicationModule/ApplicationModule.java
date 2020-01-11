package com.e.spectra.dagger.daggerModule.applicationModule;

import com.e.spectra.ui.start.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeAndroidInjector();
}
