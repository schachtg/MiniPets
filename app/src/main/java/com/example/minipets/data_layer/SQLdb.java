package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class SQLdb implements SQLdbInterface{

    private Context context;
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public SQLdb(Context cont){
        this.context = cont;
    }

    @Override
    public SQLdbInterface open() throws SQLException {
        this.dbHelper = new SQLiteHelper(this.context);
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
        cv.put(SQLiteHelper.TOKENS, tok);
        return this.db.insert(SQLiteHelper.SHOP_TABLE_NAME, null, cv);
    }

    @Override
    public Cursor getShop() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(SQLiteHelper.SHOP_TABLE_NAME, new String[]{SQLiteHelper.SHOP_ID, SQLiteHelper.TOKENS}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int updateShop(long id, int tok) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.TOKENS, tok);
        return this.db.update(SQLiteHelper.SHOP_TABLE_NAME, cv, "id=" + id, null);
    }

    @Override
    public void deleteShop(int id) {
        this.db = this.dbHelper.getWritableDatabase();
        this.db.delete(SQLiteHelper.SHOP_TABLE_NAME, "id=" +id, null);
    }
    @Override
    public long insertPet(String name, String type, String outfit, int happy) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.PET_NAME, name);
        cv.put(SQLiteHelper.PET_TYPE, type);
        cv.put(SQLiteHelper.PET_OUTFIT, outfit);
        cv.put(SQLiteHelper.PET_HAPPY, happy);
        return this.db.insert(SQLiteHelper.PET_TABLE_NAME, null, cv);
    }
    @Override
    public Cursor getPet() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(SQLiteHelper.PET_TABLE_NAME, new String[]{SQLiteHelper.PET_NAME, SQLiteHelper.PET_TYPE,  SQLiteHelper.PET_HAPPY, SQLiteHelper.PET_OUTFIT}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    @Override
    public int updatePet(long id, String name, String type, int happy, String outfit) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.PET_NAME, name);
        cv.put(SQLiteHelper.PET_TYPE, type);
        cv.put(SQLiteHelper.PET_HAPPY, happy);
        cv.put(SQLiteHelper.PET_OUTFIT, outfit);
        return this.db.update(SQLiteHelper.PET_TABLE_NAME, cv, "_id=" + id, null);
    }
    @Override
    public void deletePet(int id) {
        this.db = this.dbHelper.getWritableDatabase();
        this.db.delete(SQLiteHelper.PET_TABLE_NAME, "id=" + id, null);
    }
    @Override
    public long insertMisc(double timeAway, int bg) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.MISC_TIME, timeAway);
        cv.put(SQLiteHelper.MISC_BG, bg);
        return this.db.insert(SQLiteHelper.OTHER_TABLE_NAME, null, cv);
    }
    @Override
    public Cursor getMisc() {
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(SQLiteHelper.OTHER_TABLE_NAME, new String[]{SQLiteHelper.OTHER_ID, SQLiteHelper.MISC_TIME, SQLiteHelper.MISC_BG}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    @Override
    public int updateMisc(long id, double timeAway, int bg) {
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.MISC_TIME, timeAway);
        cv.put(SQLiteHelper.MISC_BG, bg);
        return this.db.update(SQLiteHelper.OTHER_TABLE_NAME, cv, "id_=" + id, null);
    }
    @Override
    public long insertItem(String name, int cost, int count){
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.ITEM_NAME, name);
        cv.put(SQLiteHelper.ITEM_COST, cost);
        cv.put(SQLiteHelper.ITEM_COUNT, count);
        return this.db.insert(SQLiteHelper.INVENTORY_TABLE_NAME, null, cv);
    }
    @Override
    public Cursor getItems(){
        this.db = this.dbHelper.getWritableDatabase();
        Cursor cursor = this.db.query(SQLiteHelper.INVENTORY_TABLE_NAME, new String[]{SQLiteHelper.SHOP_ID, SQLiteHelper.ITEM_NAME, SQLiteHelper.ITEM_COST, SQLiteHelper.ITEM_COUNT}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    @Override
    public int updateItem(long id, String name, int cost, int count){
        this.db = this.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.ITEM_NAME, name);
        cv.put(SQLiteHelper.ITEM_COST, cost);
        cv.put(SQLiteHelper.ITEM_COUNT, count);
        return this.db.update(SQLiteHelper.INVENTORY_TABLE_NAME, cv, "id=" + id, null);
    }
    @Override
    public int deleteItem(int id){
        this.db = this.dbHelper.getWritableDatabase();
        return db.delete(SQLiteHelper.INVENTORY_TABLE_NAME, "id=" + id, null);
    }

}
