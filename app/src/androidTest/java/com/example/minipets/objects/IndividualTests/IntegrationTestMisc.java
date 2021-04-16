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
public class IntegrationTestMisc {

    private TestSQLdb db;

    private Context app_context;

    @Before
    public void setUp(){
        app_context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new TestSQLdb(app_context);
    }

    @Test
    public void insertMisc() {
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insertMisc(100, 0);
        assertNotEquals(-1, test);
    }

    @Test
    public void getMisc(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insertMisc(100, 0);
        Cursor cursor = db.getMisc();
        cursor.moveToFirst();
    }

    @Test
    public void testUpdate(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        db.insertMisc(100, 0);
        Cursor cursor = db.getMisc();
        cursor.moveToFirst();
        int test = db.updateMisc(cursor.getInt(0), 50, 1);
    }

    @After
    public void cleanUp(){
        db.close();
    }

}