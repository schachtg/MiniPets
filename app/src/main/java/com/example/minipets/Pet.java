package com.example.minipets;

import android.view.View;
import android.widget.ImageView;

// Class which handles all information about the pet
public class Pet
{
    private String name;    // The name of the pet
    private int happiness;  // Represents how happy the pet is
    private String type;    // The type of pet (eg. cat or dog)
    private int outfit;     // Id representing the outfit the pet's wearing
    private boolean[] likedFoods;   // If the pet likes the food for some food id
    private ImageView reactionImg;  // ImageView which displays the pet's reaction
    private ImageView petImg;   // ImageView which displays the pet

    public final int MAX_FOODS = 3; // How many different types of food there is
    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    // Constructor
    // Initialize the values for the pet
    public Pet(String newName, String newType, ImageView newReactionImg, ImageView newPetImg)
    {
        name = newName;
        happiness = MAX_HAPPINESS/2;
        type = newType;
        outfit = 0;
        likedFoods = new boolean[MAX_FOODS];
        reactionImg = newReactionImg;
        petImg = newPetImg;

        // Sets the image of the pet
        if(type.equals("Cat"))
            petImg.setImageResource(R.drawable.cat);
    }

    // Pass a string through to have the corresponding reaction displayed
    public void react(String reaction)
    {
        // Retrieves the appropriate image
        if(reaction.equals("Happy"))
        {
            reactionImg.setImageResource(R.drawable.heart);
        }

        reactionImg.setVisibility(View.VISIBLE);
    }

    // Pass a foodItem id to have your pet eat the food
    public void feed(int foodItem)
    {
        if(foodItem >= 0 && foodItem <= MAX_FOODS)
        {
            if(likedFoods[foodItem])
            {
                react("Happy");
                happiness += 10;
            }
            else
            {
                react("Gross");
                happiness += 5;
            }
        }
    }
}