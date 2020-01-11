package com.e.spectra.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Brand implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int brand_id;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String imageUrl;


    public Brand(int brand_id, String name, String imageUrl) {
        this.brand_id = brand_id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Brand(String brandName) {
        name = brandName;
    }
}
