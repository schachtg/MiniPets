package com.example.minipets.objects;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.example.minipets.R;
import com.example.minipets.data_layer.SQLiteHelper;

import java.util.Calendar;
import java.util.Date;

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
    private CountDownTimer timer;// timer for reaction img
    private Date lastLogin;//checks last time user logged in
    private double diff;

    public final int MAX_FOODS = 3; // How many different types of food there is
    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    private SQLiteHelper db;

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
            reactionImg.setImageResource(R.drawable.smile_emote);
        }
        else if (reaction.equals("Average"))
        {
            reactionImg.setImageResource(R.drawable.average);//need to get average image
        }
        else
        {
            reactionImg.setImageResource(R.drawable.sad);//need to get sad image
        }

        reactionImg.setVisibility(View.VISIBLE);
        timer.start();
    }

    // Pass a foodItem id to have your pet eat the food
    public void feed(String foodItem, boolean giveReaction)
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
                if(giveReaction)
                    react("Happy");
                happiness += 10;
            }
            else
            {
                if(giveReaction)
                    react("Gross");
                happiness += 5;
            }
            if (happiness>MAX_HAPPINESS)
                happiness=MAX_HAPPINESS;
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

        if(foodId >= 0 && foodId < likedFoods.length)
            likedFoods[foodId] = likes;
    }

    public boolean getLikedFood(String foodItem)
    {
        int foodId = -1;
        boolean result = false;
        if(foodItem.equals("Chicken"))
            foodId = 0;
        else if(foodItem.equals("Fish"))
            foodId = 1;
        else if(foodItem.equals("Beef"))
            foodId = 2;

        if(foodId >= 0 && foodId < likedFoods.length)
            result = likedFoods[foodId];

        return result;
    }

    public String getOutfit() {return outfit;}

    public void calcLastLogin()// gets last login and checks if over cap for happiness drop *ISN'T USED YET*
    {
        Date curr= Calendar.getInstance().getTime();
        diff = lastLogin.getTime() - curr.getTime();
        if(diff>172800)//if last login is over 48 hours
            happiness -= 20;
        else if (diff>86400)//if last login is over 24 hours
            happiness -= 10;

        if (happiness<0)//sets happiness to 0
            happiness = 0;
        //no penalty if less than 24 hours
    }

    public String getMood()
    {
        String reaction;
        if (getHappiness()>75)
            reaction="Happy";
        else if (getHappiness()>40)
            reaction="Average";
        else
            reaction="Sad";
        return reaction;
    }

    public String getName(){
        return name;
    }

    public int getHappiness()
    {
        return happiness;
    }

    public String getType(){return type;}

    public double getDiff(){return diff;}

    public void setName(String name){this.name = name;}

    public void setHappiness(int happiness){this.happiness = happiness;}

    public void setType(String type){this.type = type;}

    public void setLastLogin()
    {
        lastLogin = Calendar.getInstance().getTime();
    }
}