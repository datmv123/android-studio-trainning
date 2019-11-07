package com.example.demo1;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;

    private String name;
    private String brand;
    private String price;

    public Product() {
    }

    public Product(int id,String name, String brand, String price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public int getId() {
        return id;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Product)) {
            return false;
        } else {
            return this.id == ((Product) obj).id;
        }
    }
}
