package com.example.minipets.ui.fetch;

public class FetchDirective {   //TODO I should turn this into an interface
    protected int petWidth;
    protected int petHeight;
    protected int petPositionX;
    protected int petPositionY;
    protected int timer;
    protected int totalPoints;

    public FetchDirective(){
        this.petWidth = 0;
        this.petHeight = 0;
        this.petPositionX = 0;
        this.petPositionY = 0;
        this.timer = 0;
        this.totalPoints = 0;
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

    public int getTimer(){
        return this.timer;
    }

    public int getPoints(){
        return this.totalPoints;
    }
}
