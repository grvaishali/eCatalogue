package com.e.ecatalogue;

public class ListDataModel {
    String description;
    String code;
    String price;
    String image;
    String id_;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public ListDataModel(String description, String code, String price, String image, String id_) {
        this.description = description;
        this.code = code;
        this.price = price;
        this.image = image;
        this.id_ = id_;
    }
    public ListDataModel(){

    }
}
