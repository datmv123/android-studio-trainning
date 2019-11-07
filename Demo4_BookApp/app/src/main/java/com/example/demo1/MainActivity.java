package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static List<Book> books = new ArrayList<>();
    ListView listViewAllBooks;
    EditText etSearch;

    static {
        fakeData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAllBooks = findViewById(R.id.listViewAllBooks);
        Button btnSearch = findViewById(R.id.buttonSearch);
        etSearch = findViewById(R.id.editTextSearch);
        etSearch.setFocusableInTouchMode(true);
        btnSearch.setOnClickListener(t -> {
            doSearch();
            View view = this.getCurrentFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
        BookViewAdapter adapter = new BookViewAdapter(R.layout.book_view, this, books);
        listViewAllBooks.setAdapter(adapter);
    }

    public static void updateBook(Book book) {
        int index = books.indexOf(book);
        books.set(index, book);
    }

    private void doSearch() {
        String textSearch = etSearch.getText() + "";

        List<Book> afterSearch = books.stream()
                .filter(t -> t.getName().toUpperCase().contains(textSearch.toUpperCase()))
                .collect(Collectors.toList());

        BookViewAdapter adapter = new BookViewAdapter(R.layout.book_view, this, afterSearch);
        listViewAllBooks.setAdapter(adapter);
    }

    private static void fakeData(){
        books.add(new Book(1, "book 1", "nguyen van a", 1999));
        books.add(new Book(2, "book 2", "nguyen van b", 2000));
        books.add(new Book(3, "book 3", "nguyen van c", 2001));
        books.add(new Book(4, "book 4", "nguyen van d", 1998));
        books.add(new Book(5, "book 5", "nguyen van e", 2002));
        books.add(new Book(6, "book 6", "nguyen van f", 1997));
        books.add(new Book(7, "book 7", "nguyen van g", 1996));
    }
}
