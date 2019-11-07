package com.example.demo2;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Customer implements Serializable {

    private static int count = 0;
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    private int profileId = R.drawable.wolf_profile;

    public Customer() {
        this.id = ++count;
    }

    public Customer(String name, String phone, String email, String address) {
        this.id = ++count;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Customer(String name, String phone, String email, String address, int profileId) {
        this.id = ++count;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.profileId = profileId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Customer) {
            return this.id == ((Customer) obj).id;
        }
        return Boolean.FALSE;
    }
}
