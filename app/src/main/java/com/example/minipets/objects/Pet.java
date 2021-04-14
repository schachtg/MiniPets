package com.example.minipets.objects;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.example.minipets.R;
import com.example.minipets.data_layer.SQLiteHelper;
import com.example.minipets.enums.FoodItems;
import com.example.minipets.enums.Outfits;
import com.example.minipets.enums.PetType;
import com.example.minipets.enums.Reactions;

import java.util.Calendar;
import java.util.Date;

// Class which handles all information about the pet
public class Pet
{
    private String name;    // The name of the pet
    private int happiness;  // Represents how happy the pet is
    private PetType type;    // The type of pet (eg. cat or dog)
    private Outfits outfit;     // Id representing the outfit the pet's wearing
    private ImageView reactionImg;  // ImageView which displays the pet's reaction
    private ImageView petImg;   // ImageView which displays the pet
    private ImageView outfitImg;
    private CountDownTimer timer;// timer for reaction img
    private Date lastLogin;//checks last time user logged in
    private double diff;

    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    private SQLiteHelper db;

    // Constructor
    // Initialize the values for the pet (Used when no pet has been made)
    public Pet(String newName, PetType newType)
    {
        name = newName;
        happiness = MAX_HAPPINESS/2;
        type = newType;
        outfit = Outfits.NONE;
    }

    // Constructor
    // Initialize the values for the pet (Used when creating a copy of a previous pet)
    public Pet(String newName, PetType newType, int newHappiness, Outfits newOutfit)
    {
        name = newName;
        happiness = newHappiness;
        type = newType;
        outfit = newOutfit;
    }

    // Initializes the elements in the fragment
    public void setViews(ImageView newReactionImg, ImageView newPetImg, ImageView newOutfitImg)
    {

        reactionImg = newReactionImg;
        petImg = newPetImg;
        outfitImg = newOutfitImg;

        // Sets the image of the pet
        if(type == PetType.CAT)
            petImg.setImageResource(R.drawable.cat);
        else if(type == PetType.DOG)
            petImg.setImageResource(R.drawable.dog);
    }

    // Pass a string through to have the corresponding reaction displayed
    public void react(Reactions reaction)
    {

        // Retrieves the appropriate image
        if(reaction == Reactions.HAPPY)
        {
            reactionImg.setImageResource(R.drawable.smile_emote);
        }
        else if (reaction == Reactions.AVERAGE)
        {
            reactionImg.setImageResource(R.drawable.average);
        }
        else if (reaction == Reactions.SAD)
        {
            reactionImg.setImageResource(R.drawable.sad);
        }
        else if (reaction == Reactions.GROSS)
        {
            reactionImg.setImageResource(R.drawable.gross_emote);
        }
        else if (reaction == Reactions.LIKE)
        {
            reactionImg.setImageResource(R.drawable.heart);
        }

        reactionImg.setVisibility(View.VISIBLE);
        timer.start();
    }

    // Pass a foodItem id to have your pet eat the food
    public void feed(FoodItems foodItem, boolean giveReaction)
    {
        if(foodItem == FoodItems.CHICKEN) {
            if(giveReaction)
                react(Reactions.GROSS);
            happiness += 5;
        }
        else if(foodItem == FoodItems.FISH) {
            if(giveReaction)
                react(Reactions.LIKE);
            happiness += 10;
        }
        else if(foodItem == FoodItems.BEEF) {
            if(giveReaction)
                react(Reactions.LIKE);
            happiness += 15;
        }
    }



    public void setOutfit(Outfits newOutfit)
    {
        outfit = newOutfit;

        if(newOutfit == Outfits.NONE)
        {
            outfitImg.setImageResource(R.drawable.empty);
        } else if (newOutfit == Outfits.COWBOY_HAT)
        {
            outfitImg.setImageResource(R.drawable.cowboy_hat);
        } else if (newOutfit == Outfits.PIRATE_HAT)
        {
            outfitImg.setImageResource(R.drawable.pirate_hat);
        }
    }

    public Outfits getOutfit() {return outfit;}

    public void calcLastLogin()// gets last login and checks if over cap for happiness drop *ISN'T USED YET*
    {
        Date curr= Calendar.getInstance().getTime();
        if(lastLogin != null) {
            diff = lastLogin.getTime() - curr.getTime();
            if (diff > 172800)//if last login is over 48 hours
                happiness -= 20;
            else if (diff > 86400)//if last login is over 24 hours
                happiness -= 10;

            if (happiness < 0)//sets happiness to 0
                happiness = 0;
            //no penalty if less than 24 hours
        }
    }

    public Reactions getMood()
    {
        Reactions reaction;
        if (getHappiness()>75)
            reaction = Reactions.HAPPY;
        else if (getHappiness()>40)
            reaction = Reactions.AVERAGE;
        else
            reaction = Reactions.SAD;

        return reaction;
    }

    public String getName(){
        return name;
    }

    public int getHappiness()
    {
        return happiness;
    }

    public PetType getType(){return type;}

    public double getDiff(){return diff;}

    public void setName(String name){this.name = name;}

    public void setHappiness(int happiness){this.happiness = happiness;}

    public void setType(PetType type){this.type = type;}

    public void setCountDownTimer(CountDownTimer newTimer) {timer = newTimer;}

    public void setLastLogin()
    {
        lastLogin = Calendar.getInstance().getTime();
    }
}