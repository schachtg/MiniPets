package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class TestSQLdbPet implements SQLdbPetInterface {

    private Context context;
    private SQLiteDatabase db;
    private TestSQLdbHelper dbHelper;

    public TestSQLdbPet(Context c){
        context = c;

    }

    public SQLdbPetInterface open() throws SQLException {
        this.dbHelper = new TestSQLdbHelper(this.context);
        this.db = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.db = this.dbHelper.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }


    @Override
    public long insert_pet(String name, String type, String outfit, int happy) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.PET_NAME, name);
        cv.put(SQLiteHelper.PET_TYPE, type);
        cv.put(SQLiteHelper.PET_OUTFIT, outfit);
        cv.put(SQLiteHelper.PET_HAPPY, happy);
        return this.db.insert(SQLiteHelper.PET_TABLE_NAME, null, cv);
    }

    @Override
    public Cursor get_pet() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(SQLiteHelper.PET_TABLE_NAME, new String[]{SQLiteHelper.PET_NAME, SQLiteHelper.PET_TYPE, SQLiteHelper.PET_OUTFIT, SQLiteHelper.PET_HAPPY}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int update_pet(long id, String name, String type, String outfit, int happy) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.PET_NAME, name);
        cv.put(SQLiteHelper.PET_TYPE, type);
        cv.put(SQLiteHelper.PET_OUTFIT, outfit);
        cv.put(SQLiteHelper.PET_HAPPY, happy);
        return this.db.update(SQLiteHelper.PET_TABLE_NAME, cv, "_id=" + id, null);
    }

    @Override
    public void delete_pet() {
        this.db = this.dbHelper.getWritableDatabase();
        this.db.delete(SQLiteHelper.PET_TABLE_NAME, null, null);
    }
}
