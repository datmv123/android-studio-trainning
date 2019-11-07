package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.setTitle("Add Customer");

        Button btnAdd = findViewById(R.id.buttonAdd);
        Button btnCancel = findViewById(R.id.buttonCancel);

        etName = findViewById(R.id.editTextName);
        etAddress = findViewById(R.id.editTextAddress);
        etPhone = findViewById(R.id.editTextPhone);
        etEmail = findViewById(R.id.editTextEmail);

        btnCancel.setOnClickListener(t -> doCancel());
        btnAdd.setOnClickListener(t -> doAdd());
    }

    private void doAdd() {
        if (simpleValidate()) {
            String name = etName.getText() + "";
            String email = etEmail.getText() + "";
            String phone = etPhone.getText() + "";
            String address = etAddress.getText() + "";
            Storage.addCustomer(new Customer(name,phone,email,address));
            doCancel();
        }
    }

    private void doCancel() {
        Intent mainScreen = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(mainScreen);
        this.finish();
    }

    private boolean simpleValidate() {
        String name = etName.getText() + "";
        String email = etEmail.getText() + "";
        String phone = etPhone.getText() + "";
        String address = etAddress.getText() + "";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            openDialog("Input is empty.");
            return Boolean.FALSE;
        }
        if(phone.length() != 9){
            openDialog("Phone must be 9 digits.");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void openDialog(String message) {
        final String txtBody = message;
        final String title = "ERROR";
        final String labelBtnOK = "OK";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(txtBody).setPositiveButton(labelBtnOK, (dialog1, id) -> {
            //close dialog;
        });
        builder.create().show();
    }
}
