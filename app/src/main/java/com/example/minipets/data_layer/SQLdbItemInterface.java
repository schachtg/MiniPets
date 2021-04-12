package com.example.minipets.data_layer;

import android.database.Cursor;

public interface SQLdbItemInterface {
    public long insert_item(String name, int cost, int count);
    public Cursor get_items();
    public int update_item(long id, String name, int cost, int count);
    public int delete_item(int id);
}
