package com.e.spectra.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.e.spectra.data.entity.Category;

@Dao
public interface CategoryDao {

    @Insert
    public void insert(Category category);
}
