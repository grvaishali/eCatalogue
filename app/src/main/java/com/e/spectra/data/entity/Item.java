package com.e.spectra.data.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Item implements Serializable {
    @NonNull
    @PrimaryKey
   public String code;
    @ColumnInfo
   public String name;
    @ColumnInfo
   public String description;
    @ColumnInfo
    public String brand;
    @ColumnInfo
    public String category;
    @ColumnInfo
    public String price;
    @ColumnInfo
    public String imageUrl;

    public Item(String code, String name, String description, String brand, String category, String price, String imageUrl) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
