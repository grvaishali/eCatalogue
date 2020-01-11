package com.e.spectra.ui.data;

public class CategoryData {
    private String name;
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (null == this.getBrand() || null == this.getName()) {
            return super.equals(o);
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof CategoryData)) {
            return false;
        }
        CategoryData c = (CategoryData) o;
        return c.getName().equals(this.getName()) && c.getBrand().equals(this.getBrand());
    }

    @Override
    public int hashCode() {
        if (null == this.getBrand() || null == this.getName()) {
            return super.hashCode();
        }
        return (name + brand).hashCode();
    }
}