package com.example.minipets.ui.fetch;

public class FetchLogic implements FetchGameLogic{

    protected LogicFetchDirective nextDirective;
    protected int mapWidth;
    protected int mapHeight;

    protected final int START_TIMER = 1000; //1000 miliseconds to click the pet on the first go

    public FetchLogic(int mapWidth, int mapHeight, int petWidth, int petHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.nextDirective = new LogicFetchDirective(petWidth, petHeight, 0, 0, this.START_TIMER, 0);
    }


    //used incase the player just needs a new directive for some reason
    public FetchDirective getNewDirective(){
        FetchDirective toSend;

        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);

        toSend = this.nextDirective;

        //make a copy of the directive
        this.nextDirective = this.nextDirective.copy();

        return toSend;
    }


    public FetchDirective petWasClicked(){
        FetchDirective toSend;

        //cive the player 10 miliseconds less to click the pet
        this.nextDirective.increaseTime(-10);   //decrease timer by 10 mili-seconds
        this.nextDirective.addPoints(5);

        //randomize the position for the pet
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);

        toSend = this.nextDirective;

        //create directive for next run of game
        this.nextDirective = this.nextDirective.copy();

        return toSend;
    }


    public FetchDirective timesUp(){
        FetchDirective toSend;

        //give the player 10 extra miliseconds to click the pet
        this.nextDirective.increaseTime(10);

        toSend = this.nextDirective;

        //create directive for the next run of the game
        this.nextDirective = this.nextDirective.copy();

        return toSend;
    }
}
