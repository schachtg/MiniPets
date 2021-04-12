package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class TestSQLdbItem implements SQLdbItemInterface {

    private Context context;
    private SQLiteDatabase db;
    private TestSQLdbHelper dbHelper;

    public TestSQLdbItem(Context c){
        context = c;

    }

    public SQLdbItemInterface open() throws SQLException {
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
    public long insert_item(String name, int cost, int count){
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TestSQLdbHelper.ITEM_NAME, name);
        cv.put(TestSQLdbHelper.ITEM_COST, cost);
        cv.put(TestSQLdbHelper.ITEM_COUNT, count);
        return this.db.insert(TestSQLdbHelper.INVENTORY_TABLE_NAME, null, cv);
    }
    @Override
    public Cursor get_items(){
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(TestSQLdbHelper.INVENTORY_TABLE_NAME, new String[]{TestSQLdbHelper.SHOP_ID, TestSQLdbHelper.ITEM_NAME, TestSQLdbHelper.ITEM_COST, TestSQLdbHelper.ITEM_COUNT}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    @Override
    public int update_item(long id, String name, int cost, int count){
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TestSQLdbHelper.ITEM_NAME, name);
        cv.put(TestSQLdbHelper.ITEM_COST, cost);
        cv.put(TestSQLdbHelper.ITEM_COUNT, count);
        return this.db.update(TestSQLdbHelper.INVENTORY_TABLE_NAME, cv, "id=" + id, null);
    }
    @Override
    public int delete_item(int id){
        this.db = this.dbHelper.getWritableDatabase();
        return db.delete(TestSQLdbHelper.INVENTORY_TABLE_NAME, "id=" + id, null);
    }
}
