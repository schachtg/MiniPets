package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table PetInformation(name TEXT primary key, type TEXT )");
        db.execSQL("create Table ShopInformation(tokens INTEGER primary key )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists PetInformation");
        db.execSQL("drop table if exists ShopInformation");
    }

    public Boolean insert_pet_data(String name, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name: ", name);
        cv.put("type: ", type);
        long result = db.insert("PetInformation", null, cv);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean insert_shop_data(int tok){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Tokens: ", tok);
        long result = db.insert("PetInformation", null, cv);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean update_shop_data(int tok){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("name: ", name);
        cv.put("Tokens: ", tok);
        Cursor cursor = db.rawQuery("Select * from PetInformation where name = ?", new String[] {"" + tok});
        if (cursor.getCount()>0) {
            long result = db.update("PetInformation", cv, "name=?", new String[]{"" + tok});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Boolean update_pet_data(String name, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("name: ", name);
        cv.put("type: ", type);
        Cursor cursor = db.rawQuery("Select * from PetInformation where name = ?", new String[]{name});
        if (cursor.getCount()>0) {
            long result = db.update("PetInformation", cv, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Boolean delete_pet_data(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from PetInformation where name = ?", new String[]{name});
        if (cursor.getCount()>0) {
            long result = db.delete("PetInformation", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Cursor view_pet_data(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from PetInformation", new String[]{name});
        return cursor;

    }
}
