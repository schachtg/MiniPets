package com.example.minipets.objects;

public class ShopItem {
    private String name; //name of shop item
    private String type;
    private int cost; //cost of item in tokens
    private int count; //count of items available

    //constructor
    public ShopItem(String newName, int newCost, String type){
        name = newName;
        cost = newCost;
        count = 1;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }

    public String getType(){return type;}

    //increment the item count by 1
    public void addCount() {
        this.count++;
    }

    //calculates total cost of item purchase
    public int getTotalCost(){
        return cost * count;
    }

    //returns a string of the name, cost and total price of an item
    @Override
    public String toString(){
        return name +" costs "+cost+" and total price "+this.getTotalCost();
    }
}
