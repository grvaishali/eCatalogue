package com.e.spectra.data.database;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.e.spectra.constants.DatabaseConstants;
import com.e.spectra.data.dao.BannerDao;
import com.e.spectra.data.dao.BrandDao;
import com.e.spectra.data.dao.CategoryDao;
import com.e.spectra.data.dao.ItemDao;
import com.e.spectra.data.entity.Banner;
import com.e.spectra.data.entity.Brand;
import com.e.spectra.data.entity.Category;
import com.e.spectra.data.entity.Item;

import javax.inject.Singleton;

@Singleton
@Database(entities = {Category.class, Item.class, Banner.class, Brand.class}, version = 1, exportSchema = false)
public abstract class EcatalogueRoomDatabase extends RoomDatabase {
    private static EcatalogueRoomDatabase instance;

    public static synchronized EcatalogueRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, EcatalogueRoomDatabase.class, DatabaseConstants.DB_NAME)
                    .build();
        }
        return instance;
    }


    public abstract CategoryDao categoryDao();

    public abstract ItemDao itemDao();

    public abstract BannerDao bannerDao();

    public abstract BrandDao brandDao();


}

//
//    public EcatalogueRoomDatabase getEcatalogueDatabase(Context context) {
//        ecatalogueRoomDatabase =
//        return ecatalogueRoomDatabase;
//    }