package com.example.minipets.logic;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.minipets.R;
import com.example.minipets.enums.FoodItems;
import com.example.minipets.enums.Outfits;
import com.example.minipets.objects.Pet;

public class HomeLogic implements HomeInterface {

    protected ImageView reactionImg;  // Shows the pet's reaction image
    protected ImageView petImg;   // Shows the pet's image
    protected ImageView outfitImg;    // Shows the pet's outfit
    Spinner inventoryList;  // Displays the user's inventory
    protected CountDownTimer countDownTimer;
    protected String[] inventory;


    public void refreshPet(Pet thePet, View newView, Activity newActivity, View.OnClickListener newListener)
    {
        reactionImg = (ImageView) newView.findViewById(R.id.reactionImage);
        petImg = (ImageView) newView.findViewById(R.id.petImage);
        petImg.setOnClickListener(newListener);    // Sets function for when the pet is clicked
        outfitImg = (ImageView) newView.findViewById(R.id.outfitImage);

        countDownTimer = new CountDownTimer(1000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() { reactionImg.setVisibility(View.GONE); }
        };

        reactionImg.setVisibility(View.GONE);

        thePet.setViews(reactionImg, petImg, outfitImg);
        thePet.setOutfit(thePet.getOutfit());   // Re displays the outfit
        thePet.setCountDownTimer(countDownTimer);
    }

    public void refreshInventory(View newView, Activity newActivity, View.OnClickListener newListener)
    {
        inventory = new String[]{"Inventory", "Feed: Chicken", "Feed: Fish", "Feed: Beef", "Outfit: None", "Outfit: Cowboy Hat", "Outfit: Pirate Hat", "Background: Light", "Background: Dark", "Background: Purple"};

        inventoryList = newView.findViewById(R.id.inv_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(newActivity, android.R.layout.simple_spinner_item, inventory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inventoryList.setAdapter(adapter);
        inventoryList.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) newListener);
    }

    public void resetInventorySelection()
    {
        inventoryList.setSelection(0);
    }

    public void updateBackground(int bg, View newView)
    {
        // Updates background
        if (bg == 0)
            newView.setBackgroundColor(Color.WHITE);
        else if (bg == 1)
            newView.setBackgroundColor(Color.rgb(47, 60, 79));
        else if (bg == 2)
            newView.setBackgroundColor(Color.rgb(102, 34, 212));
    }

    public int selectItem(String item, Pet thePet, int bg)
    {
        if(!item.equals("Inventory"))
        {
            String[] splitText = item.split(": ", 2);

            if (splitText[0].equals("Outfit")) {
                switch(splitText[1])
                {
                    case "None" : thePet.setOutfit(Outfits.NONE);
                        break;
                    case "Cowboy Hat" : thePet.setOutfit(Outfits.COWBOY_HAT);
                        break;
                    case "Pirate Hat" : thePet.setOutfit(Outfits.PIRATE_HAT);
                        break;
                    case "Sunglasses" : thePet.setOutfit(Outfits.SUNGLASSES);
                        break;
                }
            } else if (splitText[0].equals("Feed")) {
                switch(splitText[1])
                {
                    case "Chicken" : thePet.feed(FoodItems.CHICKEN, true);
                        break;
                    case "Fish" : thePet.feed(FoodItems.FISH, true);
                        break;
                    case "Beef" : thePet.feed(FoodItems.BEEF, true);
                        break;
                }
            } else if (splitText[0].equals("Background")) {
                if(splitText[1].equals("Light")) {
                    bg = 0;
                }
                else if(splitText[1].equals("Dark")) {
                    bg = 1;
                }
                else if(splitText[1].equals("Purple")) {
                    bg = 2;
                }
            }
        }

        return bg;
    }
}
