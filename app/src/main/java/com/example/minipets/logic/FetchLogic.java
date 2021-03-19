package com.example.minipets.logic;

import com.example.minipets.ui.fetch.FetchDirective;
import com.example.minipets.ui.fetch.UiFetchDirective;

public class FetchLogic implements FetchGameLogic {

    //Logic knows everything about the FetchDirective Object because it calls literally every method in FetchDirective

    protected FetchDirective prevDirective;    //stores the directive the UI has
    protected FetchDirective nextDirective;    //stores the next directive to pass to the UI
    protected int mapWidth;
    protected int mapHeight;

    public FetchLogic( int mapWidth, int mapHeight, int petWidth, int petHeight, int x_pos, int y_pos){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        //create the current state of the game
        this.prevDirective = new FetchDirective(petWidth, petHeight, x_pos, y_pos, 0);

        //create the next directive
        this.nextDirective = this.prevDirective.copy();
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);
    }


    //used incase the player just needs a new directive for some reason
    public UiFetchDirective getNewDirective(){
        //the next directive becomes the directive we will send
        this.prevDirective = this.nextDirective;

        //create a new directive for the next time this runs
        this.nextDirective = this.nextDirective.copy();
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);

        //pass the UI a coppy of the directive we wish to send
        return this.prevDirective.copy();
    }


    // called by ui
    // used by logic
    // determines if a click was on the pet
    // deter
    public boolean clickDetected(int x_pos, int y_pos){
        boolean petWasClicked = false;

        //determine if this click happened where the pet was
        int minX = this.prevDirective.getPetPositionX();
        int maxX = minX + this.prevDirective.getPetWidth();
        int minY = this.prevDirective.getPetPositionY();
        int maxY = minY + this.prevDirective.getPetHeight();

        //check if click location is on the pets area
        if(minX <= x_pos && x_pos <= maxX && minY <= y_pos && y_pos <= maxY){
            //click occurred on the pet

            // increase total number of points
            this.nextDirective.addPoints(5);

            //Inform UI cat was tapped
            petWasClicked = true;
        }

        //indicate to UI wether pet was clicked
        return petWasClicked;
    }


    // update currency in DB when window is closing
    public void gameIsClosing(){
        int tokensGained = this.nextDirective.getPoints()/5;
        //cleanup
            //just kidding, Java is chill like that
    }
}
