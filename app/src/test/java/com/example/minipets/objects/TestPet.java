package com.example.minipets.objects;

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPet {
    Context context;
    @Test
    public void testPet(){
        Pet pet;

        System.out.println("\nStarting testPet");

        pet = new Pet("Brian B", "Cat");
        assertNotNull(pet);
        assertNotNull(pet.getMood());

        assertEquals("Brian B", pet.getName());
        assertEquals("Cat", pet.getType());
        assertEquals("Average", pet.getMood());
        assertEquals("None", pet.getOutfit());


        System.out.println("Finished testPet");
    }

    @Test
    public void testLikedFoods(){
        Pet pet;

        System.out.println("\nStarting testLikedFoods");

        pet = new Pet("Brian", "Cat");
        boolean[] likes = {true, true, false};
        pet.setLikedFoods("Chicken", true);
        pet.setLikedFoods("Beef", true);
        pet.setLikedFoods("Fish", false);

        // Shouldn't be able to make pet like non existing food item
        pet.setLikedFoods("Rocks", true);

        assertEquals(pet.getLikedFood("Chicken"), true);
        assertEquals(pet.getLikedFood("Beef"), true);
        assertEquals(pet.getLikedFood("Fish"), false);
        assertEquals(pet.getLikedFood("Rocks"), false);

        System.out.println("Finished testSetLikedFoods");
    }

    @Test
    public void testFeed(){
        Pet pet;
        int initialHappiness;

        System.out.println("\nStarting testFeed");
        pet = new Pet("Brian", "Cat");
        initialHappiness = pet.getHappiness();
        pet.setLikedFoods("Chicken", true);

        //testing feeding pet a liked food
        pet.feed("Chicken");
        assertEquals(initialHappiness + 10, pet.getHappiness());
        initialHappiness = pet.getHappiness();

        //testing feeding pet a disliked food
        pet.feed("Fish");
        assertEquals(initialHappiness + 5, pet.getHappiness());
        initialHappiness = pet.getHappiness();

        //testing feeding pet a nonexistent food
        pet.feed("Rocks");
        assertEquals(initialHappiness, pet.getHappiness());
        initialHappiness = pet.getHappiness();

        //testing maxing out happiness
        for(int i = 0; i < 100; i++)
            pet.feed("Chicken");
        assertEquals(pet.MAX_HAPPINESS, pet.getHappiness());

        System.out.println("Finished testFeed");
    }
}
