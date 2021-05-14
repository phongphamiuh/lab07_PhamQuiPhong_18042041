package com.example.lab07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter2 extends BaseAdapter {
    List<Address> list;
    Context context;
    String text;
    EditText txtDD;
    private LayoutInflater layoutInflater;
    CustomButtonListener customButtonListener;
    public CustomAdapter2(List<Address> list, Context context, CustomButtonListener customButtonListener) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.customButtonListener=customButtonListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ImageView imgDelete,imgUpdate;
        view = layoutInflater.inflate(R.layout.item_listview2, null);

        TextView tvSTT = view.findViewById(R.id.tvSTT);
        txtDD = view.findViewById(R.id.txtDD);

        tvSTT.setText(String.valueOf(list.get(position).getId()));
        txtDD.setText(list.get(position).getName());
        txtDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
            }
        });

        imgDelete = view.findViewById(R.id.imgDelete);
        imgUpdate = view.findViewById(R.id.imgUpdate);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customButtonListener.deleteAddress(position);
            }
        });

        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customButtonListener.editAddress(position);
            }
        });


        return view;
    }

    public void changeList(List<Address> listDD){
        list = listDD;
        notifyDataSetChanged();
    }

    public interface CustomButtonListener {
        public void editAddress(int position);
        public void deleteAddress(int position);
    }
}
