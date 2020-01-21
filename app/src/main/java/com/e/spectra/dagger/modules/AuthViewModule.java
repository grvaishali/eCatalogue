package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.domain.model.repositories.impl.UserRespositoryImpl;
import com.e.spectra.domain.model.AuthViewModel;
import com.e.spectra.util.factory.ViewModelProviderFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthViewModule {

    @Provides
    @Named("AuthViewModel")
    ViewModelProvider.Factory provideAuthViewModelProvider(AuthViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    AuthViewModel providesLoginActivityViewModel(UserRespositoryImpl user) {
        return new AuthViewModel(user);
    }
}
