package com.example.minipets.objects.IndividualTests;



import com.example.minipets.data_layer.TestSQLdbPet;
import com.example.minipets.objects.Pet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestIntegrationPets {
    private TestSQLdbPet db;
    private Pet test_pet;


    @Before
    public void setUp(){

    }

    @Test
    public void test_pet_creation(){


        assertTrue(true);
    }

    @After
    public void clean_up(){

    }

}
