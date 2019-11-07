package com.example.demo2.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo2.R;
import com.example.demo2.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPrice;
    private EditText etDescription;
    private TextView tvCategory;
    private DBHelper productDB = new DBHelper(this, "product.db", null, 1);
    private DBHelper categoryDB = new DBHelper(this, "category.db", null, 1);
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.setTitle("Add Product");

        Button btnAdd = findViewById(R.id.buttonAdd);
        Button btnCancel = findViewById(R.id.buttonCancel);
        Button btnChoseCategory = findViewById(R.id.buttonChoseCategory);
        btnChoseCategory.setOnClickListener(t -> openDialog());

        etName = findViewById(R.id.editTextName);
        etPrice = findViewById(R.id.editTextPrice);
        etDescription = findViewById(R.id.editTextDescription);
        tvCategory = findViewById(R.id.textViewCategory);

        btnCancel.setOnClickListener(t -> doCancel());
        btnAdd.setOnClickListener(t -> doAdd());

        db = categoryDB.getWritableDatabase();
        db.execSQL("insert into category (name) values ('cat 1')");
        db.execSQL("insert into category (name) values ('cat 2')");
        db.execSQL("insert into category (name) values ('cat 3')");
        db.execSQL("insert into category (name) values ('cat 4')");
        db.execSQL("insert into category (name) values ('cat 5')");
    }

    private void doAdd() {
        String name = etName.getText() + "";
        String price = etPrice.getText() + "";
        String description = etDescription.getText() + "";
        String categoryName = tvCategory.getText() + "";
        int categoryId = -1;
        Cursor cursor = db.query("category", new String[]{"id", "name"}, null, null, null, null, null);
        List<String> categoryNames = new ArrayList<>();
        while (cursor.moveToNext()) {
            if (categoryName.equals(cursor.getString(cursor.getColumnIndex("name")))) {
                categoryId = cursor.getInt(cursor.getColumnIndex("id"));
                break;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("description", description);
        contentValues.put("categoryId", categoryId);
        db = productDB.getWritableDatabase();
        db.insert("product", null, contentValues);

        db = productDB.getReadableDatabase();
        Cursor aa = db.query("product", new String[]{"id", "name"}, null, null, null, null, null);
        while (aa.moveToNext()) {
            String sadfsd = aa.getString(aa.getColumnIndex("name"));
            System.err.println(sadfsd);
        }
        doCancel();
    }

    private void doCancel() {
        Intent mainScreen = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(mainScreen);
        this.finish();
    }

    private void openDialog() {
        final String title = "Chose Category";
        db = categoryDB.getReadableDatabase();
        Cursor cursor = db.query("category", new String[]{"id", "name"}, null, null, null, null, null);
        List<String> categoryNames = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            categoryNames.add(name);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setItems(categoryNames.toArray(new String[0]), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String category = categoryNames.get(which);
                tvCategory.setText(category);
            }
        });
        builder.create().show();
    }
}
