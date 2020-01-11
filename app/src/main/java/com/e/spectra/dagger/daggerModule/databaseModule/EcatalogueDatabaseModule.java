package com.e.spectra.dagger.daggerModule.databaseModule;

import android.content.Context;

import androidx.room.Room;

import com.e.spectra.data.dao.BrandDao;
import com.e.spectra.data.database.EcatalogueRoomDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EcatalogueDatabaseModule {


    private EcatalogueRoomDatabase ecatalogueRoomDatabase;
    Context context;

    @Provides
    @Singleton
    @Inject
    BrandDao providesBrandDao(Context context) {

        return EcatalogueRoomDatabase.getInstance(context).brandDao();

    }
}
