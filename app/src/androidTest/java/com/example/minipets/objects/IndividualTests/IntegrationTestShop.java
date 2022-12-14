package com.example.minipets.objects.IndividualTests;

import android.content.Context;
import android.database.Cursor;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.minipets.data_layer.TestSQLdb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

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
    public void insertShop() {
        // Context of the app under test.
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insertShop(100);
        assertNotEquals(-1, test);
    }

    @Test
    public void testUpdate(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insertShop(100);
        Cursor cursor = db.getShop();
        cursor.moveToFirst();
        int test2 = db.updateShop(cursor.getInt(0), 50);
    }



    @Test
    public void getShop(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insertShop(100);
        Cursor cursor = db.getShop();
        cursor.moveToFirst();
    }

    @Test
    public void testDelete(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.getShop();
        cursor.moveToFirst();
        db.deleteShop(cursor.getInt(0));
    }

    @After
    public void cleanUp(){
        db.close();
    }
}