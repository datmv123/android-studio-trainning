package com.example.demo2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo2.R;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView totalRecords;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Home");

        findViewById(R.id.button).setOnClickListener(view -> {
            startActivity(new Intent(this.getApplicationContext(), AddActivity.class));
        });
    }
}
