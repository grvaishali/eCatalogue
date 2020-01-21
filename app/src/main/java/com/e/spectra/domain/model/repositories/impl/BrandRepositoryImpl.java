package com.e.spectra.domain.model.repositories.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.e.spectra.data.dao.BrandDao;
import com.e.spectra.data.database.EcatalogueRoomDatabase;
import com.e.spectra.data.entity.Brand;
import com.e.spectra.domain.model.repositories.BrandRepository;
import javax.inject.Inject;

public class BrandRepositoryImpl implements BrandRepository {

    EcatalogueRoomDatabase database;
    BrandDao brandDao;
    String brandName;
    Context context;

    @Inject
    public BrandRepositoryImpl() {

    }


    Brand brand;

    @Override
    public void addBrand(String brandName, Context context) {
        this.brandName = brandName;
        this.context = context;
        insertTask(new Brand(12, brandName, ""));


    }

    public void insertTask(final Brand brand) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                BrandDao brandDao = EcatalogueRoomDatabase.getInstance(context).brandDao();

                //BrandDao dao = Room.databaseBuilder(context, EcatalogueRoomDatabase.class, "EcatalogueDatabase").build().brandDao();
                brandDao.insert(brand);
                return null;
            }
        }.execute();
    }

}
