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
        return this.db.insert(TestSQLdbHelper.PET_TABLE_NAME, null, cv);
    }

    @Override
    public Cursor get_pet() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(TestSQLdbHelper.PET_TABLE_NAME, new String[]{TestSQLdbHelper.PET_ID, TestSQLdbHelper.PET_NAME, TestSQLdbHelper.PET_TYPE, TestSQLdbHelper.PET_OUTFIT, TestSQLdbHelper.PET_HAPPY}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int update_pet(long id, String name, String type, int happy, String outfit) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.PET_NAME, name);
        cv.put(SQLiteHelper.PET_TYPE, type);
        cv.put(SQLiteHelper.PET_OUTFIT, outfit);
        cv.put(SQLiteHelper.PET_HAPPY, happy);
        return this.db.update(TestSQLdbHelper.PET_TABLE_NAME, cv, "_id=" + id, null);
    }

    @Override
    public void delete_pet(int id) {
        this.db = this.dbHelper.getWritableDatabase();
        this.db.delete(TestSQLdbHelper.PET_TABLE_NAME, "_id=" + id, null);
    }
}
