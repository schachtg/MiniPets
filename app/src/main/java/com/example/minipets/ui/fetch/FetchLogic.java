package com.example.minipets.ui.fetch;

public class FetchLogic implements FetchGameLogic, TimerLogic{

    protected LogicFetchDirective prevDirective;    //stores the directive the UI has
    protected LogicFetchDirective nextDirective;    //stores the next directive to pass to the UI
    protected int mapWidth;
    protected int mapHeight;

    protected final int START_TIMER = 1000; //1000 miliseconds to click the pet on the first go

    protected FetchUI userInterface;

    protected MovePetTimer timeTillPetMoves;
    protected Thread movePetTimerThread;

    protected boolean to_slow;

    public FetchLogic(FetchUI userInterface, int mapWidth, int mapHeight, int petWidth, int petHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.nextDirective = new LogicFetchDirective(petWidth, petHeight, 0, 0, this.START_TIMER, 0);
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);
        this.userInterface = userInterface;
        this.to_slow = false;

        this.timeTillPetMoves = new MovePetTimer(this, START_TIMER);
    }


    //used incase the player just needs a new directive for some reason
    public FetchDirective getNewDirective(){
        this.to_slow = false;
        //the next directive becomes the directive we will send
        this.prevDirective = this.nextDirective;

        //create a new directive for the next time this runs    //TODO make a getNextDirective() method
        this.nextDirective = this.nextDirective.copy();
        this.nextDirective.generateLocation(this.mapWidth, this.mapHeight);

        //modify time untill the timer "goes off"
        timeTillPetMoves.setTimer_ms(this.prevDirective.getTimer());

        //create the thread to act as teh timer
        this.movePetTimerThread = new Thread(this.timeTillPetMoves);

        //create and start a timer untill the pet moves
        this.movePetTimerThread.start();

        //pass the UI a copy of the directive we wish to send
        return this.prevDirective.copy();
    }

    public void timeIsUp(){
        // inform user time is Up
        this.to_slow = true;
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
        if(minX <= x_pos && x_pos <= maxX && minY <= y_pos && y_pos <= maxY && !to_slow){
            //click occurred on the pet

            //stop the timer
            if(this.timeTillPetMoves != null && this.movePetTimerThread != null && !this.movePetTimerThread.isInterrupted()){
                this.movePetTimerThread.interrupt();
            }

            // increase total number of points and increase dificulty
            this.nextDirective.addPoints(5);
            modifyDirectiveTimer(this.nextDirective);

            //Inform UI cat was tapped
            petWasClicked = true;
        }

        //indicate to UI wether pet was clicked
        return petWasClicked;
    }

    protected void modifyDirectiveTimer(LogicFetchDirective directive){
        int points = directive.getPoints();
        if(points <= 50)
            directive.setTimer(2000);   //user has 2 seconds to click pet
        else if(points <= 100)
            directive.setTimer(1000);   //user has 1 second to click pet
        else if(points <= 150)
            directive.setTimer(800);    //user has .8 seconds to click pet
        else if(points <= 200)
            directive.setTimer(600);
        else if(points <= 225)
            directive.setTimer(500);
        else if(points <= 250)
            directive.setTimer(400);
        else if(points <= 275)
            directive.setTimer(300);
        else if(points <= 300)
            directive.setTimer(200);
        else
            directive.setTimer(100);

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
