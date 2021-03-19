package com.example.minipets.objects;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.example.minipets.R;

// Class which handles all information about the pet
public class Pet
{
    private String name;    // The name of the pet
    private int happiness;  // Represents how happy the pet is
    private String type;    // The type of pet (eg. cat or dog)
    private String outfit;     // Id representing the outfit the pet's wearing
    private boolean[] likedFoods;   // If the pet likes the food for some food id
    private ImageView reactionImg;  // ImageView which displays the pet's reaction
    private ImageView petImg;   // ImageView which displays the pet
    private ImageView outfitImg;
    private CountDownTimer timer;

    public final int MAX_FOODS = 3; // How many different types of food there is
    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    // Constructor
    // Initialize the values for the pet (Used when no pet has been made)
    public Pet(String newName, String newType)
    {
        name = newName;
        happiness = MAX_HAPPINESS/2;
        type = newType;
        outfit = "None";
        likedFoods = new boolean[MAX_FOODS];
    }

    // Constructor
    // Initialize the values for the pet (Used when creating a copy of a previous pet)
    public Pet(String newName, String newType, int newHappiness, String newOutfit)
    {
        name = newName;
        happiness = newHappiness;
        type = newType;
        outfit = newOutfit;
        likedFoods = new boolean[MAX_FOODS];
    }

    // Initializes the elements in the fragment
    public void setViews(ImageView newReactionImg, ImageView newPetImg, ImageView newOutfitImg, CountDownTimer newTimer)
    {

        reactionImg = newReactionImg;
        petImg = newPetImg;
        outfitImg = newOutfitImg;
        timer = newTimer;

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
        timer.start();
    }

    // Pass a foodItem id to have your pet eat the food
    public void feed(String foodItem)
    {
        int foodId = -1;
        if(foodItem.equals("Chicken"))
            foodId = 0;
        else if(foodItem.equals("Fish"))
            foodId = 1;
        else if(foodItem.equals("Beef"))
            foodId = 2;

        if(foodId >= 0 && foodId <= MAX_FOODS)
        {
            if(likedFoods[foodId])
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

    public void setOutfit(String newOutfit)
    {
        outfit = newOutfit;

        if(newOutfit.equals("None"))
        {
            outfitImg.setImageResource(R.drawable.empty);
        } else if (newOutfit.equals("Cowboy Hat"))
        {
            outfitImg.setImageResource(R.drawable.cowboy_hat);
        } else if (newOutfit.equals("Pirate Hat"))
        {
            outfitImg.setImageResource(R.drawable.pirate_hat);
        }
    }

    public void setLikedFoods(String foodItem, boolean likes)
    {
        int foodId = -1;
        if(foodItem.equals("Chicken"))
            foodId = 0;
        else if(foodItem.equals("Fish"))
            foodId = 1;
        else if(foodItem.equals("Beef"))
            foodId = 2;

        likedFoods[foodId] = likes;
    }

    public String getOutfit() {return outfit;}

    public String getName(){
        return name;
    }
}