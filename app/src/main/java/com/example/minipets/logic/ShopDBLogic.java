package com.example.minipets.logic;

import android.content.Context;
import android.database.Cursor;

import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.objects.Shop;

import java.sql.SQLException;

public class ShopDBLogic {
    SQLdb db;
    Context context;

    public ShopDBLogic(Context con){ //make the db
        context = con;
        db = new SQLdb(con);
    }

    public void init_shop(Shop newShop, int tokens){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(db.get().getCount()>0){
            Cursor cursor = db.get();
            cursor.moveToFirst();
            tokens = cursor.getInt(1);
            newShop.set_tokens(tokens);
        }
        else {
            db.insert_shop(tokens);
            newShop.set_tokens(1000);
        }
    }

    public void update_shop(int tokens){
        db.update(1, tokens);
        System.out.println(tokens);
    }
}