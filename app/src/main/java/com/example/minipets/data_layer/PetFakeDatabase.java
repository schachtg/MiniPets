package com.example.minipets.data_layer;

import android.widget.ImageView;

import com.example.minipets.R;
import com.example.minipets.objects.Pet;

public class PetFakeDatabase//creates fake database
{

    private Pet myPet;
    private String name;    // The name of the pet
    private int happiness;  // Represents how happy the pet is
    private String type;    // The type of pet (eg. cat or dog)
    private int outfit;     // Id representing the outfit the pet's wearing
    private String likedFood;   // If the pet likes the food for some food id
    private ImageView reactionImg;  // ImageView which displays the pet's reaction
    private ImageView petImg;   // ImageView which displays the pet
    private int coins;

    public final int MAX_FOODS = 3; // How many different types of food there is
    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    public PetFakeDatabase(){}

    public void newPet(ImageView newReactionImg, ImageView newPetImg)//String newName, String newType, ImageView newReactionImg, ImageView newPetImg
    {
        this.name = "Chester";
        this.happiness = MAX_HAPPINESS/2;
        this.type = "cat";
        this.outfit = 0;
        this.reactionImg = newReactionImg;

        //Set new pet image to cat for testing
        this.petImg = newPetImg;
        petImg.setImageResource(R.drawable.cat);

        //Set favourite food to salmon
        this.likedFood="Fish";

        this.coins=10000000;
    }

    public void deletePet() //delete you pet :(
    {
        myPet=null;
    }
}
