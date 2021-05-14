package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class lab07b extends AppCompatActivity implements CustomAdapter2.CustomButtonListener{
    ListView lv2;
    List<Address> addresses;
    CustomAdapter2 customAdapter2;
    AppDatabase2 db2;
    AddressDao addressDao;
    TextView txtSearch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7_b);
        txtSearch2 = findViewById(R.id.txtSearch2);
        lv2 = findViewById(R.id.listview2);

        db2 = Room.databaseBuilder(getApplicationContext(), AppDatabase2.class, "addressdbtest")
                .allowMainThreadQueries()
                .build();
        addressDao = db2.addressDao();
        addresses = addressDao.getAll();
        customAdapter2 = new CustomAdapter2(addresses,this,this);
        lv2.setAdapter(customAdapter2);

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Address address= addresses.get(i);
                txtSearch2.setText(address.getName());
            }
        });


        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtSearch2.getText().toString();
                if(text.equalsIgnoreCase("")==false){
                    Address address = new Address( text);
                    addressDao.insertAll(address);
                    addresses = addressDao.getAll();
                    customAdapter2.changeList(addresses);
                }
            }
        });
    }

    @Override
    public void deleteAddress(int position) {
        Address addressPosition = addresses.get(position);
        addressDao.delete(addressPosition);
        addresses = addressDao.getAll();
        customAdapter2.changeList(addresses);
    }

    @Override
    public void editAddress(int position) {
        Address addressUpdate = addresses.get(position);
        addressUpdate.setName(txtSearch2.getText().toString());
        addressDao.update(addressUpdate);
        customAdapter2.changeList(addresses);
    }

}