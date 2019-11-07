package com.example.demo2;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {

    private List<Customer> customers = new ArrayList<>();
    private Activity activity;
    private int layoutID;

    public CustomListViewAdapter(List<Customer> customers, Activity activity, int layoutID) {
        this.customers = customers;
        this.activity = activity;
        this.layoutID = layoutID;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(layoutID, null);
        }
        TextView tvName = convertView.findViewById(R.id.textViewName);
        TextView tvPhone = convertView.findViewById(R.id.textViewPhone);
        TextView tvAddress = convertView.findViewById(R.id.textViewAddress);
        ImageView imProfile = convertView.findViewById(R.id.imageViewProfile);

        Customer customer = customers.get(position);

        tvName.setTextColor(Color.RED);

        tvName.setText(customer.getName());
        tvPhone.setText(customer.getPhone());
        tvAddress.setText(customer.getAddress());
        imProfile.setImageResource(customer.getProfileId());

        return convertView;
    }
}
