package com.example.demo1;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookViewAdapter extends BaseAdapter {

    private int layoutId;
    private Activity parentActivity;
    private List<Book> books = new ArrayList<>();

    public BookViewAdapter(int layoutId, Activity parentActivity, List<Book> books) {
        this.layoutId = layoutId;
        this.parentActivity = parentActivity;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = parentActivity.getLayoutInflater().inflate(layoutId, null);
        }
        Button btnEdit = convertView.findViewById(R.id.buttonEdit);
        TextView tvBookName = convertView.findViewById(R.id.textViewBookName);
        TextView tvAuthor = convertView.findViewById(R.id.textViewAuthor);
        TextView tvPublishYear = convertView.findViewById(R.id.textViewPublishYear);

        Book book = books.get(position);
        tvBookName.setText(book.getName());
        tvAuthor.setText(book.getAuthor());
        tvPublishYear.setText(book.getPublishYear()+"");

        Intent intent = new Intent(parentActivity.getApplicationContext(), EditBookActivity.class);
        intent.putExtra("book", book);

        btnEdit.setOnClickListener(t -> {
            parentActivity.startActivity(intent);
            parentActivity.finish();
        });

        return convertView;
    }
}
