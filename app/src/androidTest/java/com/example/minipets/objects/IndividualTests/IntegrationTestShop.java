package com.example.minipets.objects.IndividualTests;

import android.content.Context;
import android.database.Cursor;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.minipets.data_layer.TestSQLdb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IntegrationTestShop {

    private TestSQLdb db;

    private Context app_context;

    @Before
    public void setUp(){
        app_context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new TestSQLdb(app_context);
    }

    @Test
    public void insert_shop() {
        // Context of the app under test.
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insert_shop(100);
        assertNotEquals(-1, test);
    }



    @Test
    public void test_update(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get();
        cursor.moveToFirst();
        int test = db.update(cursor.getInt(0), 50);
        assertEquals(1, test);
        assertEquals(100, cursor.getInt(1));
    }



    @Test
    public void get_shop(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get();
        cursor.moveToFirst();
        assertEquals(50, cursor.getInt(1));
    }

    @Test
    public void test_delete(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get();
        cursor.moveToFirst();
        db.delete_shop(cursor.getInt(0));
    }
}