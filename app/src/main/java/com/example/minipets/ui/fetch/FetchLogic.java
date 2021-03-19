package com.example.minipets.ui.fetch;

public class FetchLogic implements FetchGameLogic{

    protected LogicFetchDirective prevDirective;    //stores the directive the UI has
    protected LogicFetchDirective nextDirective;    //stores the next directive to pass to the UI
    protected int mapWidth;
    protected int mapHeight;

    protected int petLocX;  //pet's X location
    protected int petLocY;  //pet's y location

    protected int petWidth;
    protected int petHeight;

    protected final int START_TIMER = 1000; //1000 miliseconds to click the pet on the first go

    protected FetchUI userInterface;

    public FetchLogic(FetchUI userInterface, int mapWidth, int mapHeight, int petWidth, int petHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.nextDirective = new LogicFetchDirective(petWidth, petHeight, 0, 0, this.START_TIMER, 0);
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);
        this.userInterface = userInterface;
    }


    //used incase the player just needs a new directive for some reason
    public FetchDirective getNewDirective(){
        //the next directive becomes the directive we will send
        this.prevDirective = this.nextDirective;

        //create a new directive for the next time this runs    //TODO make a getNextDirective() method
        this.nextDirective = this.nextDirective.copy();
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);

        //TODO create and start a timer thread

        //pass the UI a coppy of the directive we wish to send
        return this.prevDirective.copy();
    }


    protected void timeIsUp(){

        //give the player 10 extra miliseconds to click the pet
        this.nextDirective.increaseTime(10);

        // inform user time is Up
        this.userInterface.timeIsUp();
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
        int maxY = minY + this.prevDirective.getPetWidth();

        //check if click location is on the pets area
        if(minX <= x_pos && x_pos <= maxX && minY <= y_pos && y_pos <= maxY){
            //click occurred on the pet

            //TODO stop the timer


            // increase total number of points and increase dificulty
            this.nextDirective.increaseTime(-10);   //decrease timer by 10 mili-seconds
            this.nextDirective.addPoints(5);

            //Inform UI cat was tapped
            petWasClicked = true;
        }

        //indicate to UI wether pet was clicked
        return petWasClicked;
    }


    // update currency in DB when window is closing
    public void gameIsClosing(){
        //TODO save player's points to DB

        //TODO points are located in this.nextDirective.getPoints().
        //TOdo take a portion of those points (like 1/5 or something) and add that many tokens to the player's wallet

        for(int i = 0; i < 10; i++){    //TODO dummy code
            i+=1;                       //TODO dummy code
        }                               //TODO dummy code
        //cleanup
            //just kidding, Java is chill like that
    }
}
