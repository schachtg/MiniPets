package com.example.minipets.logic;

import com.example.minipets.ui.fetch.IFetchDirective;

import java.util.Random;

public class FetchDirective implements IFetchDirective {
    protected int petWidth;
    protected int petHeight;
    protected int petPositionX;
    protected int petPositionY;

    public FetchDirective(int width, int height, int x, int y){
        this.petWidth=width;
        this.petHeight=height;
        this.petPositionX=x;
        this.petPositionY=y;
    }

    public void generateLocation(int maxX, int maxY){
        Random rand = new Random();
        this.petPositionX = rand.nextInt(maxX - this.petWidth);
        this.petPositionY = rand.nextInt(maxY - this.petHeight);
    }

    public FetchDirective copy() {      //just for simplicity
        FetchDirective dup;
        dup = new FetchDirective(this.petWidth, this.petHeight, this.petPositionX, this.petPositionY);
        return dup;
    }

    public int getPetWidth() {
        return this.petWidth;
    }

    public int getPetHeight(){
        return this.petHeight;
    }

    public  int getPetPositionX(){
        return this.petPositionX;
    }

    public int getPetPositionY(){
        return  this.petPositionY;
    }
}
