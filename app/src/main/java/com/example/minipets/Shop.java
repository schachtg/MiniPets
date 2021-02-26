package com.example.minipets;

import android.widget.ImageView;

import java.util.ArrayList;

public class Shop {
    private ShopItem[] availableItems; //list of available shop items
    private ArrayList<ShopItem>  boughtItems; //list of purchased shop items
    private ImageView itemImg;   // ImageView which displays the item
    private int tokens; //token value used for purchase

    public final int MAX_ITEMS = 5; //maximum number of shop items

    //constructor
    public Shop(int newTokens){
        availableItems = new ShopItem[MAX_ITEMS];
        boughtItems = new ArrayList(MAX_ITEMS);
        tokens = newTokens;

        //initializing list of available items
        availableItems[0] = new ShopItem("Chicken", 3);
        availableItems[1] =  new ShopItem("Fish", 4);
        availableItems[2] = new ShopItem("Beef", 3);
        availableItems[3] = new ShopItem("Frisbee", 5);
        availableItems[4] = new ShopItem("Ball", 3);
    }

    //returns an array of the current available shop items
    public ShopItem[] getAvailableItems() {
        return availableItems;
    }

    //returns a string array of the current available shop items
    public String[] itemsList(){
        String[] items = new String[MAX_ITEMS];

        for(int i = 0; i < MAX_ITEMS; i++){
            items[i] = availableItems[i].getName();
        }
        return items;
    }

    //adds a bought items into the purchases shop items list
    public void addBoughtItems(ShopItem newItem) {
        if(boughtItems.isEmpty()){
            boughtItems.add(newItem);
        }else{
            if(boughtItems.contains(newItem))
                boughtItems.get(boughtItems.indexOf(newItem)).addCount();
            else
                boughtItems.add(newItem);
        }

        //updates tokens after purchase
        tokens = tokens - newItem.getCost();
    }

    //returns tokens left
    public int remTokens(){
        return this.tokens;
    }
}
