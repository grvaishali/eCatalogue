package com.e.spectra.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Banner implements Serializable {
    @PrimaryKey(autoGenerate = true)
   public int banner_id;
    @ColumnInfo
   public String mainImageUrl;
    @ColumnInfo
   public String logoImageUrl;

    public Banner(int banner_id, String mainImageUrl, String logoImageUrl) {
        this.banner_id = banner_id;
        this.mainImageUrl = mainImageUrl;
        this.logoImageUrl = logoImageUrl;
    }
}
