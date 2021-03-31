package com.example.minipets.objects.IndividualTests;

import android.content.Context;

import com.example.minipets.objects.Pet;
import com.example.minipets.ui.shop.DashboardFragment;

import com.example.minipets.data_layer.TestSQLdbPet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

//Unsure how to get actual context to this. Tests most likely won't run since SQL requires context

public class TestPetData{
    private Pet thePet;
    private TestSQLdbPet db;
    private Context con;
    private DashboardFragment d;

    @Before
    public void setUp(){
        d = new DashboardFragment();
        db = new TestSQLdbPet(d.getActivity());
        thePet = new Pet("Bob", "Cat");
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testInsert(){
        System.out.println("Starting insert test: ");
        System.out.print(thePet.getName());
        long insert_pet = db.insert_pet(thePet.getName(), thePet.getType(), thePet.getOutfit(), thePet.getHappiness());
        System.out.println("Finished insert test: ");
    }

    @After
    public void tearDown(){
        db.delete_pet();
        db.close();
    }
}
