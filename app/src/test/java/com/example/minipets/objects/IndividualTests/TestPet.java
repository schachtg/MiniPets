package com.example.minipets.objects.IndividualTests;

import android.content.Context;

import com.example.minipets.enums.FoodItems;
import com.example.minipets.enums.Outfits;
import com.example.minipets.enums.PetType;
import com.example.minipets.enums.Reactions;
import com.example.minipets.objects.Pet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;

public class TestPet {
    Context context;
    Pet pet;


    @Before
    public void setUp(){
        pet = new Pet("Brian B", PetType.CAT);
    }

    @Test
    public void testPet(){
        System.out.println("\nStarting testPet");


        assertNotNull(pet);
        assertNotNull(pet.getMood());

        assertEquals("the pet's name should be Brian B", "Brian B", pet.getName());
        assertEquals("the cat should be a cat", PetType.CAT, pet.getType());
        assertEquals("the cat should be fine, not great, just fine", Reactions.AVERAGE, pet.getMood());
        assertEquals("the pet should initially be naked", Outfits.NONE, pet.getOutfit());


        System.out.println("Finished testPet");
    }

    //test feeding pet increases happyness
    @Test
    public void testFeedPet(){
        int happy;

        happy = pet.getHappiness();
        pet.feed(FoodItems.CHICKEN, false);

        assertNotEquals("the pet should still be happyer having eaten chicken", happy, pet.getHappiness());
        assertTrue("the pet's happyness should have increased", pet.getHappiness() > happy);
    }
}
