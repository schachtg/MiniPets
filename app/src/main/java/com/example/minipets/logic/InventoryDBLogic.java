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
            temp.add(cursor.getString(1));
            while (cursor.moveToNext()){
                temp.add(cursor.getString(1));
            }
        }
        else{
            temp.add("Inventory");
            temp.add("None");
            temp.add("Field");
            insert_new_item(temp.get(0), 0, 0, "");
            insert_new_item(temp.get(1), 0, 0, "Outfit: ");
            insert_new_item(temp.get(2), 0, 0, "Background: ");
        }
        return temp;
    }

    public void insert_new_item(String name, int cost, int count, String type){
        Boolean found = false;
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get_items();
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            if (cursor.getString(1).equals(type+name)){
                found = true;
                break;
            }
        }
        if (!found) {
            db.insert_item(type + name , cost, count);
        }
        else {
            int increase_count = cursor.getInt(3) + 1;
            System.out.println("Value before updating!: " + cursor.getInt(3));
            int test =db.update_item(cursor.getInt(0), type+name, cost, increase_count);
            System.out.println("Value after updating!: " + cursor.getInt(3));
        }
    }


    public int get_refresh_count(int refresh_count){return refresh_count;}

    public void decrease_count(String item){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get_items();
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            System.out.println("This is the item: " + item + " this is what we are at: " + cursor.getString(1));
            if (cursor.getString(1).equals(item)){
                System.out.println("FOUND IT");
                break;
            }
        }
        int new_count = cursor.getInt(3) - 1;
        if (new_count > 0){
            System.out.println("this is the new count: " + new_count);
            db.update_item(cursor.getInt(0), item, cursor.getInt(2), new_count);
        }
        else{
            db.delete_item(cursor.getInt(0));
        }
    }

}
