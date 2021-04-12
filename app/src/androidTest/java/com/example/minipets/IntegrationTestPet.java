package com.example.minipets;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.minipets.data_layer.TestSQLdbPet;
import com.example.minipets.enums.PetType;
import com.example.minipets.objects.Pet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

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
    public void insert_pet() {
        // Context of the app under test.

        assertEquals("com.example.minipets", app_context.getPackageName());
    }

    @After
    public void clean_up(){

    }
}