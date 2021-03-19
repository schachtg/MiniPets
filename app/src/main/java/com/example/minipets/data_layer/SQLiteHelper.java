package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.minipets.ui.shop.DashboardFragment;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String PET_TABLE = "create Table PetInformation(name TEXT primary key, type TEXT )";
    private static final String SHOP_TABLE = "create Table ShopInformation(id INTEGER primary key AUTOINCREMENT, tokens INTEGER )";
    public static final String PET_TABLE_NAME = "PetInformation";
    public static final String SHOP_TABLE_NAME = "ShopInformation";
    public static final String SHOP_ID = "id";
    public static final String TOKENS = "tokens";
    public static final String PET_NAME = "name";
    public static final String PET_TYPE = "type";

    public SQLiteHelper(Context context) {
        super(context, "database.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PET_TABLE);
        db.execSQL(SHOP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists PetInformation");
        db.execSQL("drop table if exists ShopInformation");
        onCreate(db);
    }


}
