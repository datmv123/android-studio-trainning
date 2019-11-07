package com.example.app5;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Student> list = new ArrayList<>();

    public CustomAdapter(Context context, int layout, List<Student> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvName;
        TextView tvPhone;
        Button btCall;
        ImageView imProfile;
        if (convertView == null) {
            convertView = ((Activity)context).getLayoutInflater().inflate(layout, null);
        }
        tvName = convertView.findViewById(R.id.tvName);
        tvPhone = convertView.findViewById(R.id.tvPhone);
        btCall = convertView.findViewById(R.id.btCall);
        imProfile = convertView.findViewById(R.id.imProfile);

        Student st = list.get(position);
        tvName.setText(st.getName() +" - "+st.getRoll());
        tvPhone.setText(st.getPhone());
        imProfile.setImageResource(st.getImageId());

        return convertView;
    }
}
