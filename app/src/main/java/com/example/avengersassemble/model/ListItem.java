package com.example.avengersassemble.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class ListItem {
    @ColumnInfo(name="item_title")
    public String head;

    @ColumnInfo(name = "society")
    public String location;

    @ColumnInfo(name = "price")

    public String price;

    @PrimaryKey(autoGenerate = true)
    public int localid;

    public ListItem(String head, String location, String price) {
        this.head = head;
        this.location = location;
        this.price = price;
    }

    public String getHead() {
        return head;
    }

    public String getLocation() { return location; }

    public String getPrice(){return price;};
}
