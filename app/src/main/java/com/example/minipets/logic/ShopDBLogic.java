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

    public void initShop(Shop newShop, int tokens){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(db.getShop().getCount()>0){
            Cursor cursor = db.getShop();
            cursor.moveToFirst();
            tokens = cursor.getInt(1);
            newShop.setTokens(tokens);
        }
        else {
            db.insertShop(tokens);
            newShop.setTokens(1000);
        }
    }

    public void updateShop(int tokens){
        db.updateShop(1, tokens);
        System.out.println(tokens);
    }

    public void gainTokens(int amount){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.getShop();
        cursor.moveToFirst();
        int temp = cursor.getInt(1);
        temp += amount;
        updateShop(temp);
    }

    public Boolean getPetForFetch(){
        Boolean isDog = false;
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.getPet();
        cursor.moveToFirst();
        System.out.println(cursor.getString(1));
        if (cursor.getString(1).equals("DOG")){
            isDog = true;
        }
        return isDog;
    }

    public Boolean doesShopExist(){
        Boolean exists = false;
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.getShop().getCount()>0){
            exists = true;
        }
        return exists;
    }
}
