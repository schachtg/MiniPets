package com.example.minipets;

import android.widget.ImageView;

public class Shop {
    private ShopItem[] availableItems; //list of available shop items
    private ShopItem[] boughtItems; //list of purchased shop items
    private ImageView itemImg;   // ImageView which displays the item
    private int tokens; //token value used for purchase

    public final int MAX_ITEMS = 3; //maximum number of shop items

    //constructor
    public Shop(int newTokens){
        availableItems = new ShopItem[MAX_ITEMS];
        boughtItems = new ShopItem[MAX_ITEMS];
        tokens = newTokens;

        //initializing list of available items
        availableItems[0] = new ShopItem("Chicken", 3);
        availableItems[1] =  new ShopItem("Fish", 4);
        availableItems[2] = new ShopItem("Beef", 3);
    }

    //returns a string array of the current available items
    public String[] getAvailableItems() {
        String[] items = new String[MAX_ITEMS];

        for(int i = 0; i < MAX_ITEMS; i++){
            items[i] = availableItems[i].getName();
        }
        return items;
    }

    //setter for list of bought items
    public void setBoughtItems(ShopItem[] boughtItems) {
        this.boughtItems = boughtItems;
    }
}
