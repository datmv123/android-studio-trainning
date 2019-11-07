package com.example.app5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Student> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        list.add(new Student("Nguyen van a", "SE01", "012345678",R.drawable.cat));
        list.add(new Student("Nguyen van b", "SE02", "876543210",R.drawable.cat1));
        list.add(new Student("Nguyen van c", "SE03", "888888888",R.drawable.cat2));

        CustomAdapter adapter = new CustomAdapter(this, R.layout.student_layout, list);

        listView.setAdapter(adapter);
    }
}
