package com.example.minipets.logic;

import android.util.Log;

import com.example.minipets.ui.fetch.FetchDirective;
import com.example.minipets.ui.fetch.UiFetchDirective;

public class FetchLogic implements IFetchGameLogic {

    //Logic knows everything about the FetchDirective Object because it calls literally every method in FetchDirective

    protected FetchDirective prevDirective;    //stores the directive the UI has
    protected FetchDirective nextDirective;    //stores the next directive to pass to the UI
    protected int mapWidth;
    protected int mapHeight;

    protected boolean petStateInitialised = false;  //checks wether declairPetStats has been declaired at all

    public FetchLogic( int mapWidth, int mapHeight){

        Log.d("FetchLogic", "fetch logic created");
        Log.d("FetchLogic", String.format("Constructer recieved screen dimensions width = %d, height = %d", mapWidth, mapHeight));


        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        //create blank directives for prevDirective and nextDirective
        this.prevDirective = new FetchDirective(0,0,0,0,0);
        this.nextDirective = this.prevDirective.copy();

        //make sure game logic knows that it has not had petState initialised yet (we don't have size or dimensions of the pet image)
        this.petStateInitialised = false;
    }

    // this only needs to be called once, but could easily be modified to allow the
    // resizing of the pet image by the UI layer
    //TODO throw some exception if the pet's initial state is invalid
    //TODO throw an exception if the pet's width is invalid
    //------------------------------------------------------------------------------------------------------
    public void definePetState(int petWidth, int petHeight, int x_pos, int y_pos){
        Log.d("FetchLogic", String.format("Constructer recieved pet dimensions width = %d, height = %d", petWidth, petHeight));
        Log.d("FetchLogic", String.format("Constructer recieved pet position x = %d, y = %d", x_pos, y_pos));
        if(petWidth > 0 && petHeight > 0 && 0 <= x_pos && x_pos <= this.mapHeight && 0 <= y_pos && y_pos <= this.mapHeight){
            if(!petStateInitialised && petWidth != prevDirective.getPetWidth() && petHeight != prevDirective.getPetHeight() && x_pos != prevDirective.getPetPositionX() && y_pos != prevDirective.getPetPositionY()) {
                this.prevDirective = new FetchDirective(petWidth, petHeight, x_pos, y_pos, this.prevDirective.getPoints());
                this.nextDirective = this.prevDirective.copy();
                this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);
                this.petStateInitialised = true;
            }
        }
    }


    //used incase the player just needs a new directive for some reason
    public UiFetchDirective getNewDirective(){

        UiFetchDirective toUI = null;  //the directive to send to the UI

        if(petStateInitialised) {
            Log.d("FetchLogic", "new directive requested");

            //the next directive becomes the directive we will send
            this.prevDirective = this.nextDirective;

            //create a new directive for the next time this runs
            this.nextDirective = this.nextDirective.copy();
            this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);

            //pass the UI a copy of the directive we wish to send
            toUI = this.prevDirective.copy();
        }
        //Silent else
            //toUI = null;
        return toUI;
    }


    // called by ui
    // used by logic
    // determines if a click was on the pet
    // deter
    public boolean clickDetected(int x_pos, int y_pos){
        boolean petWasClicked = false;

        Log.d("FetchLogic", "click detected called");

        //determine if this click happened where the pet was
        int minX = this.prevDirective.getPetPositionX();
        int maxX = minX + this.prevDirective.getPetWidth();
        int minY = this.prevDirective.getPetPositionY();
        int maxY = minY + this.prevDirective.getPetHeight();

        Log.d("FetchLogic", String.format("Click occurred at x=%d, y=%d", x_pos, y_pos));
        Log.d("FetchLogic", String.format("pet is at x=[%d, %d], y=[%d, %d]", minX, maxX, minY, maxY));

        //check if click location is on the pets area
        if(petStateInitialised && minX <= x_pos && x_pos <= maxX && minY <= y_pos && y_pos <= maxY){
            //click occurred on the pet

            Log.d("FetchLogic", "click occurred on the pet");

            // increase total number of points
            this.nextDirective.addPoints(5);

            //Inform UI cat was tapped
            petWasClicked = true;
        }

        //indicate to UI wether pet was clicked, note that if pet has not been initialised it cannot have been clicked
        return petWasClicked;
    }


    // update currency in DB when window is closing
    public void gameIsClosing(){
        int tokensGained = this.nextDirective.getPoints()/5;
        //cleanup
            //just kidding, Java is chill like that
    }
}
