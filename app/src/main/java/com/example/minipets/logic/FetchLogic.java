package com.example.minipets.logic;

import com.example.minipets.ui.fetch.IFetchDirective;

public class FetchLogic implements IFetchGameLogic {

    //Logic knows everything about the FetchDirective Object because it calls literally every method in FetchDirective

    protected FetchDirective prevDirective;    //stores the directive the UI has
    protected FetchDirective nextDirective;    //stores the next directive to pass to the UI
    protected int mapWidth;
    protected int mapHeight;
    protected int points;

    protected boolean petStateInitialised = false;  //checks wether declairPetStats has been declaired at all

    public FetchLogic( int mapWidth, int mapHeight){

        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        //create blank directives for prevDirective and nextDirective
        this.prevDirective = new FetchDirective(0,0,0,0);
        this.nextDirective = this.prevDirective.copy();

        //make sure game logic knows that it has not had petState initialised yet (we don't have size or dimensions of the pet image)
        this.petStateInitialised = false;

        this.points = 0;
    }

    // this only needs to be called once, but could easily be modified to allow the
    // resizing of the pet image by the UI layer
    //------------------------------------------------------------------------------------------------------
    public void definePetState(int petWidth, int petHeight, int xPos, int yPos){
        if(petWidth < this.mapWidth &&
                0 < petWidth &&
                petHeight < this.mapHeight &&
                0 < petHeight &&
                0 <= xPos &&
                (xPos + petWidth) <= this.mapWidth &&
                0 <= yPos &&
                (yPos + petHeight) <= this.mapHeight){

            if(petWidth != prevDirective.getPetWidth() && petHeight != prevDirective.getPetHeight() && xPos != prevDirective.getPetPositionX() && yPos != prevDirective.getPetPositionY()) {
                this.prevDirective = new FetchDirective(petWidth, petHeight, xPos, yPos);
                this.nextDirective = this.prevDirective.copy();
                this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);
                this.petStateInitialised = true;
            }
        }
    }


    //used incase the player just needs a new directive for some reason
    public IFetchDirective getNewDirective(){

        IFetchDirective toUI = null;  //the directive to send to the UI

        if(petStateInitialised) {

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

        //determine if this click happened where the pet was
        int minX = this.prevDirective.getPetPositionX();
        int maxX = minX + this.prevDirective.getPetWidth();
        int minY = this.prevDirective.getPetPositionY();
        int maxY = minY + this.prevDirective.getPetHeight();

        //check if click location is on the pets area
        if(petStateInitialised && minX <= x_pos && x_pos <= maxX && minY <= y_pos && y_pos <= maxY){
            //click occurred on the pet

            // increase total number of points
            this.points += 5;

            //Inform UI cat was tapped
            petWasClicked = true;
        }

        //indicate to UI wether pet was clicked, note that if pet has not been initialised it cannot have been clicked
        return petWasClicked;
    }

    public int getPoints(){
        return this.points;
    }


    // update currency in DB when window is closing
    public int gameIsClosing(){
        int tokensGained = this.points / 5;
        return tokensGained;
        //cleanup
            //just kidding, Java is chill like that
    }

    // not in interface because interface doesn't needed it, but my tests do, and i don't doubt that
    // future modifications to this project could use it
    public boolean isPetInitialized(){
        return this.petStateInitialised;
    }
}
