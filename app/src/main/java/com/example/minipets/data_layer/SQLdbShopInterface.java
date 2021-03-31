package com.example.minipets.data_layer;

import android.database.Cursor;

import java.sql.SQLException;

public interface SQLdbShopInterface {

    public SQLdbShopInterface open() throws SQLException;
    public void close();
    public long insert_shop(int tok);
    public Cursor get();
    public int update(long id, int tok);
    public void delete_all_shop();
}
