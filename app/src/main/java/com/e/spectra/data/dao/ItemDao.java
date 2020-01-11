package com.e.spectra.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.e.spectra.data.entity.Item;

@Dao
public interface ItemDao {

    @Insert
    public void insert(Item item);


}
