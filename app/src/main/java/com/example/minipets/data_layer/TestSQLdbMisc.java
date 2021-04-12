package com.example.minipets.data_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class TestSQLdbMisc implements SQLdbMiscInterface{
    private Context context;
    private SQLiteDatabase db;
    private TestSQLdbHelper dbHelper;

    public TestSQLdbMisc(Context c){
        context = c;

    }

    public SQLdbMiscInterface open() throws SQLException {
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
