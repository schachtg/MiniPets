package com.example.minipets.data_layer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String PET_TABLE = "create Table PetInformation(_id INTEGER primary key AUTOINCREMENT, name TEXT, type TEXT, happy INTEGER, outfit TEXT )";
    private static final String SHOP_TABLE = "create Table ShopInformation(id INTEGER primary key AUTOINCREMENT, tokens INTEGER )";
    private static final String OTHER_TABLE = "create Table MiscInfo(id_ INTEGER primary key AUTOINCREMENT, timeAway DOUBLE, bg INTEGER )";
    private static final String INVENTORY_TABLE = "create Table InventoryInfo(id INTEGER primary key AUTOINCREMENT, item_name TEXT, item_cost INTEGER, item_count INTEGER )";
    public static final String PET_TABLE_NAME = "PetInformation";
    public static final String SHOP_TABLE_NAME = "ShopInformation";
    public static final String OTHER_TABLE_NAME = "MiscInfo";
    public static final String INVENTORY_TABLE_NAME = "InventoryInfo";
    public static final String OTHER_ID = "id_";
    public static final String SHOP_ID = "id";
    public static final String TOKENS = "tokens";
    public static final String PET_NAME = "name";
    public static final String PET_TYPE = "type";
    public static final String PET_OUTFIT = "outfit";
    public static final String PET_HAPPY = "happy";
    public static final String MISC_TIME = "timeAway";
    public static final String MISC_BG = "bg";

    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_COST = "item_cost";
    public static final String ITEM_COUNT = "item_count";

    public SQLiteHelper(Context context) {
        super(context, "database.db", null, 22);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PET_TABLE);
        db.execSQL(SHOP_TABLE);
        db.execSQL(OTHER_TABLE);
        db.execSQL(INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists PetInformation");
        db.execSQL("drop table if exists ShopInformation");
        db.execSQL("drop table if exists MiscInfo");
        db.execSQL("drop table if exists InventoryInfo");
        onCreate(db);
    }


}
