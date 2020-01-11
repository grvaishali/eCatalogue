package com.e.spectra.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import com.e.spectra.data.entity.Banner;


@Dao
public interface  BannerDao {

    @Insert
    public void insert(Banner banner);
}
