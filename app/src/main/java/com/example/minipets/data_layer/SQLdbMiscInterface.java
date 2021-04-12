package com.example.minipets.data_layer;

import android.database.Cursor;

public interface SQLdbMiscInterface {
    public long insert_misc(double timeAway, int bg);
    public Cursor get_misc();
    public int update_misc(long id, double timeAway, int bg);
}
