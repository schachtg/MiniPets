package com.example.minipets.logic;

import android.content.Context;
import android.database.Cursor;

import com.example.minipets.data_layer.SQLdb;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDBLogic {

    SQLdb db;
    Context context;

    public InventoryDBLogic(Context con){
        context = con;
        db = new SQLdb(con);
    }

    public void insert_new_item(String name, int cost, int count, String type){
        System.out.println("hello: " + count);
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        db.insert_item(type + name, cost, count);
    }

    public ArrayList<String> init_inventory(){
        ArrayList<String> temp = new ArrayList<String>();
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.get_items().getCount()>0){
            Cursor cursor = db.get_items();
            cursor.moveToFirst();
            temp.add(cursor.getString(0));
            while (cursor.moveToNext()){
                temp.add(cursor.getString(0));
            }
        }
        else{
            temp.add("Inventory");
            temp.add("None");
            insert_new_item(temp.get(0), 0, 0, "");
            insert_new_item(temp.get(1), 0, 0, "Outfit: ");
        }
        return temp;
    }
}
