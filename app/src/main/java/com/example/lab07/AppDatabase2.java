package com.example.lab07;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Address.class}, version = 1)
public abstract class AppDatabase2 extends RoomDatabase {
    public abstract AddressDao addressDao();
}
