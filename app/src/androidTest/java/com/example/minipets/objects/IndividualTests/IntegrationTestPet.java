package com.example.minipets.objects.IndividualTests;

import android.content.Context;
import android.database.Cursor;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.minipets.data_layer.TestSQLdbPet;
import com.example.minipets.enums.PetType;
import com.example.minipets.objects.Pet;

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
public class IntegrationTestPet {

    private TestSQLdbPet db;
    private Pet test_pet;
    private Context app_context;

    @Before
    public void setUp(){
        app_context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new TestSQLdbPet(app_context);
        test_pet = new Pet("test", PetType.CAT);
    }

    @Test
    public void test_delete(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get_pet();
        cursor.moveToFirst();
        db.delete_pet(cursor.getInt(0));
    }

    @Test
    public void insert_pet() {
        // Context of the app under test.
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        long test = db.insert_pet(test_pet.getName(), test_pet.getType().toString(), test_pet.getOutfit().toString(), test_pet.getHappiness());
        assertNotEquals(-1, test);
    }

    @Test
    public void get_pet(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Cursor cursor = db.get_pet();
        cursor.moveToFirst();
        assertEquals("test", cursor.getString(1));
    }
    
}