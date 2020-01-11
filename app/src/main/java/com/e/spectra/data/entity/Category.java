package com.e.spectra.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Category implements Serializable {
    @PrimaryKey(autoGenerate = true)
   public int category_id;
    @ColumnInfo
    public String name;
    @ColumnInfo
   public String brand;

    public Category(int category_id, String name, String brand) {
        this.category_id = category_id;
        this.name = name;
        this.brand = brand;
    }
}
