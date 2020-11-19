package com.example.avengersassemble.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemsDao {
    @Insert
    void insertAll(ListItem items);

    @Delete
    void delete(ListItem item);

    @Query("SELECT * FROM listitem")
    List<ListItem> getAllItems();

    @Query("SELECT * FROM listitem WHERE localid = :itemId")
    ListItem getItem(int itemId);

    @Query("DELETE FROM listitem")
    void deleteAllItems();
}