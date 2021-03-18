package com.example.minipets.ui.fetch;

import java.util.Random;

public class LogicFetchDirective extends FetchDirective{
    public LogicFetchDirective(int width, int height, int x, int y, int time, int startingPoints){
        this.petWidth=width;
        this.petHeight=height;
        this.petPositionX=x;
        this.petPositionY=y;
        this.timer=time;
        this.totalPoints=startingPoints;
    }

    public void increaseTime(int boost){
        if(this.timer + boost > 0){
            this.timer += boost;
        }
    }

    public void multiplySize(float boost){
        this.petWidth  *= boost;
        this.petHeight *= boost;
        if(this.petWidth < 5){
            this.petWidth = 5;
        }
        if(this.petHeight < 5){
            this.petHeight = 5;
        }
    }

    public void generateLocation(int maxX, int maxY){
        Random rand = new Random();
        this.petPositionX = rand.nextInt(maxX - this.petWidth);
        this.petPositionY = rand.nextInt(maxY - this.petHeight);
    }

    public LogicFetchDirective copy() {      //just for simplicity
        LogicFetchDirective dup = new LogicFetchDirective(this.petWidth, this.petHeight, this.petPositionX, this.petPositionY, this.timer, this.totalPoints);
        return dup;
    }


    public void addPoints(int pts){
        this.totalPoints += pts;
    }
}
