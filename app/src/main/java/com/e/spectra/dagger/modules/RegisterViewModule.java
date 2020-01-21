package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.domain.model.repositories.impl.UserRespositoryImpl;
import com.e.spectra.domain.model.RegisterViewModel;
import com.e.spectra.util.factory.ViewModelProviderFactory;

import javax.inject.Named;

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
