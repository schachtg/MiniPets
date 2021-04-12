package com.example.minipets.logic;

import android.content.Context;
import android.database.Cursor;

import com.example.minipets.data_layer.SQLdb;

import java.sql.SQLException;

public class BackgroundDBLogic {
    SQLdb db;
    Context context;

    public BackgroundDBLogic(Context con) {
        context = con;
        db = new SQLdb(con);
    }

    public int init_background() {
        int temp = 0;
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.get_misc().getCount() > 0) {
            Cursor cursor = db.get_misc();
            cursor.moveToFirst();
            temp = cursor.getInt(2);
        }
        else{
            db.insert_misc(100, temp);
        }
        return temp;
    }

    public void update_background(double time_away, int background){
        db.update_misc(1, time_away, background);
    }
}
