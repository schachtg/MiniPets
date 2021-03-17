package com.example.minipets.ui.fetch;

public class FetchLogic implements FetchGameLogic{

    protected Projectile projectile;    //stores the height and width of the ball used to play fetch
    protected Target     target;     //stores the position, height, and width of the pet on the map
    protected /*int*/float x_origine;
    protected /*int*/float y_origine;
    protected /*int*/float screen_height;
    protected /*int*/float screen_width;

    protected boolean target_position_is_old;  //this is a flag used internally to determine if the position of the target
                                               //has changed since the creation of this object or since the last time a
                                               //ball-toss was requested

    //protected FetchLogicState state;

    //
    public FetchLogic(/*int*/float screen_width, /*int*/float screen_height, /*int*/float pet_width, /*int*/float pet_height, float target_hitbox, /*int*/float ball_width, /*int*/float ball_height){
        //this.ball_image = (ImageView) findViewById(R.id.baseball); //TODO interface with file system to get ball image or recieve as arg
        //this.pet_image = (ImageView) findViewById(R.drawable.cat);      //TODO interface with file system to get pet image or recieve as arg

        if(screen_height > 1)
            this.screen_height = screen_height;
        else
            this.screen_height = 1; //a default. for safety. If you passed a screen size of zero you're trying to mess this up.//TODO I ought to just crash the thing if they pass this

        if(screen_width > 1)
            this.screen_width = screen_width;
        else
            this.screen_width = 1;

        this.x_origine = screen_width / 2;//x_origine;
        this.y_origine = screen_height / 2;//y_origine;

        //these two can decide if thier input works. if they are given a size larger than half the screen they will shrink themselves
        this.projectile = new Projectile(ball_width,ball_height, this.x_origine,this.y_origine,0,0);  //abstract default Projecctile characteristics. can be changed
        this.target = new Target(pet_width,pet_height,this.x_origine,this.y_origine,target_hitbox);              //abstract default Target characteristics. can be changed
    }


    //
    // Called by the Fetch activity (User interface) to notify the game logic
    // that the screen has been released. If the logic is in a state where it would
    // make sense for the ball to be thrown, then the game logic must decide what vector
    // the ball is thrown in, how fast, and weather the ball will hit; an then return this to the
    // UI in a directive object (if we called a function instead of returning something we would
    // have a god awefull runtime stack.
    // If the game logic is not in a valid state for the ball to be thrown, It will return a
    // directive instructing the activity to "DO NOTHING" apout this event.
    // If the input is invalid, the game logic will return an error directive, It is up to the UI
    // to determine how this directive should be dealt with.
    //
    // INPUT:
    //  release_x : the x coordinate for where the touch release occurred on the screen
    //  release_y : the y coordinate for where the touch release occurred on the screen
    //---------------------------------------------------------------------------------------------
    public ThrowBallDirective ballReleased(float release_x, float release_y){
        ThrowBallDirective directive;

        //verify the target has been updated
        if(this.target_position_is_old){
            //if this is an old target you're not allowed to throw at it. You don't even kn
            directive = null;
        }
        // Verify input is valid
        else if(release_x < 0 || release_y < 0                           //check that release is not off the screen in a negative direction
                || release_x > this.screen_width || release_y > this.screen_height){   //check that release is not off the screen in a positive direction
                                                                            //+1 is added to the bounds just incase an integer devision and rounding resulted in a dropped value.
            //the input is invalid. Returning anything that COULD be treated as valid data would be reckless
            directive = null;
        }
        //if all is good
        else{

            //indicate the target has been thrown at in this position for the future.
            this.target_position_is_old = true;

            //generate the vector the ball is thrown at, and wether or not it reaches the target
            directive = this.generateBallToss(release_x, release_y);
        }

        return directive;
    }


    //handels the trowing of the ball and generates a directive for said ball throw
    protected ThrowBallDirective generateBallToss(float x_release, float y_release){
        ThrowBallDirective directive;
        boolean caught = false;     //will the pet catch the ball given the vector of the ball throw

        // find the release vector of the projectile
        this.calculateProjectileVector(x_release, y_release);

        // determine weather the ball will be caught by the pet
        caught = this.target.isHitByProjectile(this.projectile);

        directive = new ThrowBallDirective(caught, this.projectile.getUnitVectorX(), this.projectile.getUnitVectorY());  //TODO later allow magnitude of vector to alter the speed of the ball

        return directive;
    }


    // calculates the vector components of a projectile
    // based on the point of release and the centre of the screen
    //-------------------------------------------------------------
    protected void calculateProjectileVector(float x_release, float y_release){
        float x_vect = 0;
        float y_vect = 0;
        if(this.projectile != null){
            x_vect = this.x_origine - x_release;
            y_vect = this.y_origine - y_release;
            this.projectile.setVector(x_vect, y_vect);
        }
    }


    public /*int*/float getPetX(){
        if(this.target_position_is_old)
            this.generateNewPosition();

        return this.target.getX_pos();
    }

    public /*int*/float getPetY(){
        if(this.target_position_is_old)
            this.generateNewPosition();

        return this.target.getY_pos();
    }


    public void generateNewPosition(){
        this.target_position_is_old = false;
        this.target.generateRandomTargetPosition();
    }
}
