package com.example.lab07;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
import java.util.UUID;

public class lab07a extends AppCompatActivity {
    ListView lv;
    List<User> users;
    CustomAdapter customAdapter;
    AppDatabase db;
    UserDao userDao;
    User userPosition = null;
    TextView tvSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7_a);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userdbtest")
                .allowMainThreadQueries()
                .build();
        userDao = db.userDao();
        users = userDao.getAll();
        customAdapter = new CustomAdapter(users,this);
        lv = findViewById(R.id.listview);
        lv.setAdapter(customAdapter);

        //add to list
        tvSearch = findViewById(R.id.txtSearch);
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text =  tvSearch.getText().toString();
                if(text.equalsIgnoreCase("") == false){
                    User u = new User(UUID.randomUUID().toString(),text);
                    userDao.insertAll(u);
                    users = userDao.getAll();
                    customAdapter.changeList(users);
                }

            }
        });

        //Display to search
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user= users.get(i);
                tvSearch.setText(user.getName());
                userPosition = user;
            }
        });

        //delete
        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.delete(userPosition);
                users = userDao.getAll();
                customAdapter.changeList(users);
                userPosition = null;
                tvSearch.setText("");
            }
        });
    }
}
