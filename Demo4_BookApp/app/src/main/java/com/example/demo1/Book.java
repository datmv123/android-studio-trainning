package com.example.demo1;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;

    private String name;
    private String author;
    private int publishYear;

    public Book(int id, String name, String author, int publishYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Book)){
            return false;
        }else{
            return this.id == ((Book) obj).id;
        }
    }
}
