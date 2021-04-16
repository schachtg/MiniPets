package com.example.minipets.data_layer;

import android.database.Cursor;

import java.sql.SQLException;

public interface SQLdbInterface {

    public SQLdbInterface open() throws SQLException;
    public void close();
    public long insertShop(int tok);
    public Cursor getShop();
    public int updateShop(long id, int tok);
    public void deleteShop(int id);
    public long insertPet(String name, String type, String outfit, int happy);
    public Cursor getPet();
    public int updatePet(long id, String name, String type, int happy, String outfit);
    public void deletePet(int id);
    public long insertMisc(double timeAway, int bg);
    public Cursor getMisc();
    public int updateMisc(long id, double timeAway, int bg);
    public long insertItem(String name, int cost, int count);
    public Cursor getItems();
    public int updateItem(long id, String name, int cost, int count);
    public int deleteItem(int id);
}
