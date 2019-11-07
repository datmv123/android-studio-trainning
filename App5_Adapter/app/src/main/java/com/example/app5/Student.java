package com.example.app5;

import androidx.annotation.NonNull;

public class Student {
    private String name;
    private String roll;
    private String phone;
    private int imageId;

    public Student(String name, String roll,String phone, int imageId) {
        this.name = name;
        this.roll = roll;
        this.phone = phone;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + " - " + this.roll;
    }
}
