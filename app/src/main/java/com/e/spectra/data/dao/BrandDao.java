package com.e.spectra.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.e.spectra.data.entity.Brand;


@Dao

public interface BrandDao {


    @Insert
    public void insert(Brand brand);
}
