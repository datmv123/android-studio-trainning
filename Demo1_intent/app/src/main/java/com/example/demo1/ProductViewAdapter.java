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

public class ProductViewAdapter extends BaseAdapter {

    private int layoutId;
    private Activity parentActivity;
    private List<Product> products = new ArrayList<>();

    public ProductViewAdapter(int layoutId, Activity parentActivity, List<Product> products) {
        this.layoutId = layoutId;
        this.parentActivity = parentActivity;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
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
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView tvProductName = convertView.findViewById(R.id.textViewProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.textViewProductPrice);
        TextView tvProductBrand = convertView.findViewById(R.id.textViewProductBrand);

        Product product = products.get(position);
        tvProductName.setText(product.getName());
        tvProductPrice.setText(product.getPrice() + "");
        tvProductBrand.setText(product.getBrand());

        Intent intent = new Intent(parentActivity.getApplicationContext(), EditProductActivity.class);
        intent.putExtra("product", product);

        btnEdit.setOnClickListener(t -> {
            parentActivity.startActivity(intent);
            parentActivity.finish();
        });

        return convertView;
    }
}
