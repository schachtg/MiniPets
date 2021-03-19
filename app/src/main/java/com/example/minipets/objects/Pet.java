package com.example.minipets.objects;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import java.util.Calendar;
import java.util.Date;

import com.example.minipets.R;

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
    private CountDownTimer timer;// timer for reaction img
    private Date lastLogin;//checks last time user logged in

    public final int MAX_FOODS = 3; // How many different types of food there is
    public final int MAX_HAPPINESS = 100;    // The max that the pet's happiness can go

    // Constructor
    // Initialize the values for the pet
    public Pet(String newName, String newType, ImageView newReactionImg, ImageView newPetImg, CountDownTimer newTimer)
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

        timer = newTimer;
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
            if (happiness>MAX_HAPPINESS)
                happiness=MAX_HAPPINESS;
        }
    }

    public void calcLastLogin()// gets last login and checks if over cap for happiness drop
    {
        Date curr= Calendar.getInstance().getTime();
        long diff = lastLogin.getTime() - curr.getTime();
        if(diff>172800)//if last login is over 48 hours
            happiness -= 10;
        else if (diff>86400)//if last login is over 24 hours
            happiness -= 20;

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
}