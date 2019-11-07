package com.example.demo2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    Customer customer;
    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.setTitle("Edit Customer");

        etName = findViewById(R.id.editTextName);
        etAddress = findViewById(R.id.editTextAddress);
        etPhone = findViewById(R.id.editTextPhone);
        etEmail = findViewById(R.id.editTextEmail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        customer = (Customer) bundle.get("customer");
        String name = customer.getName();
        String address = customer.getAddress();
        String email = customer.getEmail();
        String phone = customer.getPhone();

        etName.setText(name);
        etAddress.setText(address);
        etEmail.setText(email);
        etPhone.setText(phone);

        Button btnUpdate = findViewById(R.id.buttonUpdate);
        Button btnCancel = findViewById(R.id.buttonCancel);

        btnUpdate.setOnClickListener(t -> doUpdate());
        btnCancel.setOnClickListener(t -> doCancel());
    }

    private void doUpdate() {
        if (simpleValidate()) {
            String name = etName.getText() + "";
            String email = etEmail.getText() + "";
            String phone = etPhone.getText() + "";
            String address = etAddress.getText() + "";
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setAddress(address);

            Storage.updateCustomer(customer);
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
        if (phone.length() != 9) {
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
