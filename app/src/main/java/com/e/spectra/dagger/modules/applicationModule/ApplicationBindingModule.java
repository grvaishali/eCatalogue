package com.e.spectra.dagger.modules.applicationModule;

import com.e.spectra.ui.start.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ApplicationBindingModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();
}
