package com.example.avengersassemble.model;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class ListItem {
    @ColumnInfo(name = "item_title")
    public String head;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "superpower")
    public String superpower;

    @ColumnInfo(name = "simage")
    public String simage;

    @PrimaryKey(autoGenerate = true)
    public int localid;


    public ListItem(String head, String description, String superpower,String simage) {
        this.head = head;
        this.description = description;
        this.superpower = superpower;
        this.simage = simage;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }

    public String getPower() {
        return superpower;
    }

    public Bitmap getSimage(){
        return RoomConverters.StringToBitMap(simage);
    }
}
