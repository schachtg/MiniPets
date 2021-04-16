package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class TestSQLdb implements SQLdbInterface{

    private Context context;
    private SQLiteDatabase db;
    private TestSQLdbHelper dbHelper;

    public TestSQLdb(Context con){
        context = con;
    }

    @Override
    public SQLdbInterface open() throws SQLException {
        this.dbHelper = new TestSQLdbHelper(this.context);
        this.db = this.dbHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        this.db = this.dbHelper.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }

    @Override
    public long insertShop(int tok) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TestSQLdbHelper.TOKENS, tok);
        return this.db.insert(TestSQLdbHelper.SHOP_TABLE_NAME, null, cv);
    }

    @Override
    public Cursor get() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(TestSQLdbHelper.SHOP_TABLE_NAME, new String[]{TestSQLdbHelper.SHOP_ID, TestSQLdbHelper.TOKENS}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int update(long id, int tok) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TestSQLdbHelper.TOKENS, tok);
        return this.db.update(TestSQLdbHelper.SHOP_TABLE_NAME, cv, "id=" + id, null);
    }

    @Override
    public void delete_shop(int id) {
        this.db = this.dbHelper.getWritableDatabase();
        this.db.delete(TestSQLdbHelper.SHOP_TABLE_NAME, "id=" + id, null);
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
    @Override
    public long insert_misc(double timeAway, int bg) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TestSQLdbHelper.MISC_TIME, timeAway);
        cv.put(TestSQLdbHelper.MISC_BG, bg);
        return this.db.insert(TestSQLdbHelper.OTHER_TABLE_NAME, null, cv);
    }

    @Override
    public Cursor get_misc() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(TestSQLdbHelper.OTHER_TABLE_NAME, new String[]{TestSQLdbHelper.OTHER_ID, TestSQLdbHelper.MISC_TIME, TestSQLdbHelper.MISC_BG}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int update_misc(long id, double timeAway, int bg) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TestSQLdbHelper.MISC_TIME, timeAway);
        cv.put(TestSQLdbHelper.MISC_BG, bg);
        return db.update(TestSQLdbHelper.OTHER_TABLE_NAME, cv, "id_=" + id, null);
    }
}
