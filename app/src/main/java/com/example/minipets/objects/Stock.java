package com.example.minipets.objects;

public class Stock {
    private String name; //name of shop item
    private int cost; //cost of item in tokens
    private int count; //count of items available

    //constructor
    public Stock(String newName, int newCost){
        name = newName;
        cost = newCost;
        count = 1;
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
