package com.example.minipets.data_layer;

import android.widget.ImageView;

import com.example.minipets.objects.Stock;
import com.example.minipets.objects.Shop;

public class ShopFakeDatabase {
    private Stock[] availableItems; //list of available shop items
    private Stock[] boughtItems; //list of purchased shop items
    private ImageView itemImg;   // ImageView which displays the item
    private int tokens; //token value used for purchase
    private Shop shop;
    public final int MAX_ITEMS = 3; //maximum number of shop items

    //constructor
    public ShopFakeDatabase(Shop newShop){
        availableItems = new Stock[MAX_ITEMS];
        boughtItems = new Stock[MAX_ITEMS];
        tokens = 1000;
        shop = newShop;
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
    public void setBoughtItems(Stock[] boughtItems) {
        this.boughtItems = boughtItems;
    }

    public void addItem(String itemName, int itemCost, int itemSpot, int available )//replace an item with a new one
    {
        Stock newItem = new Stock(itemName, itemCost);
        availableItems[itemSpot]=null;
        availableItems[itemSpot]=newItem;
    }

    public void deleteItem(int itemSpot)//delete an item from the shop
    {
        availableItems[itemSpot]=null;
    }

    public int getCurrentTokens(){
        return tokens;
    }

    public void updateTokens(){
        tokens = shop.remTokens();
    }
}