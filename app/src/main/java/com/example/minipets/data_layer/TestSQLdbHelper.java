package com.example.minipets.data_layer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class TestSQLdbHelper extends SQLiteOpenHelper {

    private static final String PET_TABLE = "create Table TestPet(_id INTEGER primary key AUTOINCREMENT, name TEXT, type TEXT, outfit TEXT, happy INTEGER )";
    public static final String PET_TABLE_NAME = "TestPet";
    public static final String PET_ID = "_id";
    public static final String PET_NAME = "name";
    public static final String PET_TYPE = "type";
    public static final String PET_OUTFIT = "outfit";
    public static final String PET_HAPPY = "happy";



    public TestSQLdbHelper(@Nullable Context context) {
        super(context, "test.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TestPet");
        onCreate(db);
    }
}
