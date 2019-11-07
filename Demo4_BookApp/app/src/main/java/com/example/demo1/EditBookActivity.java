package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditBookActivity extends AppCompatActivity {
    EditText etBookName;
    EditText etAuthor;
    EditText etPublishYear;
    TextView tvMessage;

    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Book book = (Book) bundle.get("book");
        bookId = book.getId();

        tvMessage = findViewById(R.id.textViewMessage);

        etBookName = findViewById(R.id.editTextBookName);
        etAuthor = findViewById(R.id.editTextAuthor);
        etPublishYear = findViewById(R.id.editTextPublishYear);

        etBookName.setText(book.getName());
        etAuthor.setText(book.getAuthor());
        etPublishYear.setText(book.getPublishYear() + "");

        Button btnSave = findViewById(R.id.buttonSave);
        Button btnCancel = findViewById(R.id.buttonCancel);

        btnSave.setOnClickListener(t -> doSave());
        btnCancel.setOnClickListener(t -> doCancel());
    }

    private void doSave() {
        String bookName = etBookName.getText() + "";
        String author = etAuthor.getText() + "";
        String publishYear = etPublishYear.getText() + "";
        if (bookName.isEmpty()
                || author.isEmpty()
                || publishYear.isEmpty()        ) {
            tvMessage.setText("Empty input !!!");
            return;
        }
        if( publishYear.length()!=4){
            tvMessage.setText("Year must 4 digit !!!");
            return;
        }
        MainActivity.updateBook(new Book(bookId, bookName, author, Integer.parseInt(publishYear)));
        doCancel();
    }

    private void doCancel() {
        Intent mainScreen = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(mainScreen);
        this.finish();
    }
}
