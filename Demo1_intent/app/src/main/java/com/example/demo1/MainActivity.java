package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Product> products = new ArrayList<>();
    ListView listViewAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAllProducts = findViewById(R.id.listViewAllProducts);
        Button btnDelete = findViewById(R.id.buttonDelete);
        Button btnFakeData = findViewById(R.id.buttonFakeData);
        btnFakeData.setOnClickListener(t->fakeData());
        btnDelete.setOnClickListener(t -> doDelete());
        ProductViewAdapter adapter = new ProductViewAdapter(R.layout.product_item_view, this, products);
        listViewAllProducts.setAdapter(adapter);
    }

    public static void updateProduct(Product product) {
        int index = products.indexOf(product);
        products.set(index, product);
    }

    private void doDelete() {
        SharedData.getAllSelectedItems().forEach(t -> {
            products.remove(new Product(t, "", "", ""));
        });
        ProductViewAdapter adapter = new ProductViewAdapter(R.layout.product_item_view, this, products);
        listViewAllProducts.setAdapter(adapter);
    }

    private void fakeData() {
        products = new ArrayList<>();
        products.add(new Product(1, "Dầu gội", "Sunsilk", "10k"));
        products.add(new Product(2, "Xúc xich", "An Việt", "20k"));
        products.add(new Product(3, "Bóng đèn", "Rạng Đông", "30k"));
        products.add(new Product(4, "Bánh mỳ", "Việt Cường", "40k"));
        products.add(new Product(5, "Sữa", "Vinamilk", "150k"));
        Collections.shuffle(products);
        ProductViewAdapter adapter = new ProductViewAdapter(R.layout.product_item_view, this, products);
        listViewAllProducts.setAdapter(adapter);
        SharedData.init();
    }
}
