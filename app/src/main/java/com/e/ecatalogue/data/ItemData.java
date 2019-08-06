package com.e.ecatalogue.data;

import java.io.Serializable;

public class ItemData implements Serializable {
    private String code;
    private String description;
    private String brand;
    private String category;
    private String price;
    private String imageUrl;

    public ItemData() {

    }

    public ItemData(String code, String description, String brand, String category, String price, String imageUrl) {
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
