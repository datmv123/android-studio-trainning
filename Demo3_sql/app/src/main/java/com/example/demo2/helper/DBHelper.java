package com.example.demo2.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String PRODUCT_TABLE = "create table product (" +
                "id integer primary key autoincrement," +
                "name text," +
                "description text," +
                "price float," +
                "categoryId integer" +
                ")";
        db.execSQL(PRODUCT_TABLE);
        final String CATEGORY_TABLE = "create table category (" +
                "id integer primary key autoincrement," +
                "name text unique" +
                ")";
        db.execSQL(CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
