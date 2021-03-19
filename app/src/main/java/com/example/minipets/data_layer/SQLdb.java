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
        this.dbHelper.close();
    }

    @Override
    public long insert_shop(int tok) {
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.TOKENS, tok);
        return this.db.insert(SQLiteHelper.SHOP_TABLE_NAME, null, cv);
    }

    @Override
    public Cursor get() {
        Cursor cursor = this.db.query(SQLiteHelper.SHOP_TABLE_NAME, new String[]{SQLiteHelper.SHOP_ID, SQLiteHelper.TOKENS}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int update(long id, int tok) {
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.TOKENS, tok);
        return this.db.update(SQLiteHelper.SHOP_TABLE_NAME, cv, "id=" + id, null);
    }

    @Override
    public void delete() {
        this.db.delete(SQLiteHelper.SHOP_TABLE_NAME, null, null);
    }
}
