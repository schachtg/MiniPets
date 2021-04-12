package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class TestSQLdbShop implements SQLdbShopInterface{

    private Context context;
    private SQLiteDatabase db;
    private TestSQLdbHelper dbHelper;

    public TestSQLdbShop(Context con){
        context = con;
    }

    @Override
    public SQLdbShopInterface open() throws SQLException {
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
    public long insert_shop(int tok) {
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
}
