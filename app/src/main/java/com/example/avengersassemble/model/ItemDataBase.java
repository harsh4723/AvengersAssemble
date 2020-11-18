package com.example.avengersassemble.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ListItem.class}, version = 1, exportSchema = false)
@TypeConverters(RoomConverters.class)
public abstract class ItemDataBase extends RoomDatabase {
    private static ItemDataBase instance;

    public static ItemDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ItemDataBase.class,
                    "listitemdatabase").build();
        }
        return instance;
    }

    public abstract ItemsDao itemsDao();

}
