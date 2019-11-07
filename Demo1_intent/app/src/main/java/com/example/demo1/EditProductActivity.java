package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProductActivity extends AppCompatActivity {
    EditText etProductName;
    EditText etProductBrand;
    EditText etProductPrice;
    TextView tvMessage;

    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Product product = (Product) bundle.get("product");
        productId = product.getId();
        tvMessage = findViewById(R.id.textViewMessage);

        etProductName = findViewById(R.id.editTextProductName);
        etProductBrand = findViewById(R.id.editTextProductBrand);
        etProductPrice = findViewById(R.id.editTextProductPrice);

        etProductName.setText(product.getName());
        etProductBrand.setText(product.getBrand());
        etProductPrice.setText(product.getPrice() + "");

        Button btnSave = findViewById(R.id.buttonSave);
        Button btnCancel = findViewById(R.id.buttonCancel);

        btnSave.setOnClickListener(t -> doSave());
        btnCancel.setOnClickListener(t -> doCancel());
    }

    private void doSave() {
        String productName = etProductName.getText() + "";
        String productBrand = etProductName.getText() + "";
        String productPrice = etProductName.getText() + "";
        if (productName.isEmpty() || productBrand.isEmpty() || productPrice.isEmpty()) {
            tvMessage.setText("Check input again!!!");
            return;
        }
        MainActivity.updateProduct(new Product(productId, productName, productBrand, productPrice));
        doCancel();
    }

    private void doCancel() {
        Intent mainScreen = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(mainScreen);
        this.finish();
    }
}
