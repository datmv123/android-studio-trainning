package com.example.app6_sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextScore;
    TextView textViewShow;
    private DBHelper dbHelper = new DBHelper(this, "student.db", null, 1);
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btInsert = findViewById(R.id.buttonInsert);
        Button btSelect = findViewById(R.id.buttonSelect);
        Button btClear = findViewById(R.id.buttonClear);

        btClear.setOnClickListener(t -> {
            db = dbHelper.getWritableDatabase();
//            db.execSQL("delete from student");
            db.delete("student", null, null);
        });

        editTextName = findViewById(R.id.editTextName);
        editTextScore = findViewById(R.id.editTextScore);
        textViewShow = findViewById(R.id.textViewShow);


        btInsert.setOnClickListener(t -> {
            final String insertSQL = "insert into student(name, score) values (?,?)";

            String name = editTextName.getText() + "";
            float score = Float.parseFloat(editTextScore.getText() + "");
            db = dbHelper.getWritableDatabase();
//            db.execSQL(insertSQL, new Object[]{name, score});

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("score", score);
            db.insert("student", null, contentValues);
        });

        btSelect.setOnClickListener(t -> select(t));
    }

    void select(View view) {
        final String selectSQL = "select id, name, score from student";
        db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectSQL, null);
        Cursor cursor = db.query("student",
                new String[]{"name", "score"},
                null, null, null, null, null);
        StringJoiner joiner = new StringJoiner(";");
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float score = cursor.getFloat(cursor.getColumnIndex("score"));
            joiner.add(name + " - " + score);

        }
        textViewShow.setText(joiner.toString());
    }
}
