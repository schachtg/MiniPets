package com.example.minipets;

public class ShopItem {
    private String name; //name of shop item
    private int cost; //cost of item in tokens
    private int count; //count of items available

    //constructor
    public ShopItem(String newName, int newCost){
        name = newName;
        cost = newCost;

    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setCount(int newCount) {
        this.count = newCount;
    }

    public int getTotalCost(){
        return cost * count;
    }
}
