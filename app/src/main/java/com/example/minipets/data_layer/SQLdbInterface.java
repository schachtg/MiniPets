package com.example.minipets.data_layer;

import android.database.Cursor;

import java.sql.SQLException;

public interface SQLdbInterface {

    public SQLdbInterface open() throws SQLException;
    public void close();
    public long insertShop(int tok);
    public Cursor get();
    public int update(long id, int tok);
    public void delete_shop(int id);
    public long insert_pet(String name, String type, String outfit, int happy);
    public Cursor get_pet();
    public int update_pet(long id, String name, String type, int happy, String outfit);
    public void delete_pet(int id);
    public long insert_misc(double timeAway, int bg);
    public Cursor get_misc();
    public int update_misc(long id, double timeAway, int bg);
    public long insert_item(String name, int cost, int count);
    public Cursor get_items();
    public int update_item(long id, String name, int cost, int count);
    public int delete_item(int id);
}
