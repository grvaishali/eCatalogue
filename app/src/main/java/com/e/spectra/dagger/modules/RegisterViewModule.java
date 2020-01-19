package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.data.repositories.impl.UserRespositoryImpl;
import com.e.spectra.model.RegisterViewModel;
import com.e.spectra.util.ViewModelProviderFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterViewModule {
    @Provides

    @Named("RegisterViewModel")
    ViewModelProvider.Factory provideRegisterViewModelProvider(RegisterViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    RegisterViewModel providesRegisterActivityViewModel(UserRespositoryImpl userRespository) {
        return new RegisterViewModel(userRespository);
    }

}
