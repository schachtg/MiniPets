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

    public int initBackground() {
        int temp = 0;
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.getMisc().getCount() > 0) {
            Cursor cursor = db.getMisc();
            cursor.moveToFirst();
            temp = cursor.getInt(2);
        }
        else{
            db.insertMisc(100, temp);
        }
        return temp;
    }

    public void updateBackground(double time_away, int background){
        db.updateMisc(1, time_away, background);
    }
}
