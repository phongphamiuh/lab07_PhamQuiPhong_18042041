package com.example.lab07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends BaseAdapter {
    List<User> list;
    Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.item_listview1, null);
       // view  = LayoutInflater.from(context).inflate(layout,parent, false);
        TextView tvName = view.findViewById(R.id.txtName);
        tvName.setText(list.get(position).getName());
        return view;
    }

    public void changeList(List<User> lp){
        list = lp;
        notifyDataSetChanged();
    }
}
