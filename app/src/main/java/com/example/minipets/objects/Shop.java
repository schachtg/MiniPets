package com.example.minipets.objects;

import android.widget.ImageView;

import com.example.minipets.data_layer.ShopFakeDatabase;

import java.util.ArrayList;

public class Shop {
    private ShopItem[] availableItems; //list of available shop items
    private ArrayList<ShopItem>  boughtItems; //list of purchased shop items
    private ImageView itemImg;   // ImageView which displays the item
    private int tokens; //token value used for purchase
    private ShopFakeDatabase DB;
    public final int MAX_ITEMS = 6; //maximum number of shop items
    private Boolean wasBought = true;

    //constructor
    public Shop(){
        availableItems = new ShopItem[MAX_ITEMS];
        boughtItems = new ArrayList(MAX_ITEMS);
        DB = new ShopFakeDatabase(this);
        //initializing list of available items
        availableItems[0] = new ShopItem("Chicken", 5, "Feed: ");
        availableItems[1] =  new ShopItem("Fish", 4, "Feed: ");
        availableItems[2] = new ShopItem("Beef", 3, "Feed: ");
        availableItems[3] = new ShopItem("Cowboy Hat", 500, "Outfit: ");
        availableItems[4] = new ShopItem("Pirate Hat", 500, "Outfit: ");
        availableItems[5] = new ShopItem("Beach", 300, "Background: ");
    }

    //returns an array of the current available shop items
    public ShopItem[] getAvailableItems() {
        return availableItems;
    }

    //return the Array list of bought items
    public ArrayList<ShopItem> getBoughtItems(){ return boughtItems;}

    //returns a string array of the current available shop items
    public String[] itemsList(){
        String[] items = new String[MAX_ITEMS];

        for(int i = 0; i < MAX_ITEMS; i++){
            items[i] = availableItems[i].getName();
        }
        return items;
    }

    public void itemsListBought(){

        for(int i = 0; i < boughtItems.size(); i++){
            System.out.println(boughtItems.get(i).getName());
        }

    }

    //adds a bought items into the purchases shop items list
    public void addBoughtItems(ShopItem newItem) {
        if (tokens - newItem.getCost() >= 0) {
            if (boughtItems.isEmpty()) {
                boughtItems.add(newItem);
            } else {
                if (boughtItems.contains(newItem))
                    boughtItems.get(boughtItems.indexOf(newItem)).addCount();
                else
                    boughtItems.add(newItem);
            }
            //updates tokens after purchase
            tokens = tokens - newItem.getCost();
            wasBought = true;
        }
        else{
            wasBought = false;
        }
    }

    //returns tokens left
    public int remTokens(){
        return this.tokens;
    }

    public void set_tokens(int tok){
        tokens = tok;
    }

    public Boolean getWasBought(){return wasBought;}

}
