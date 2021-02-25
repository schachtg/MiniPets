package com.example.minipets.ui.fetch;

import android.widget.ImageView;

public class FetchLogic {
    private ImageView ball_image;//Ball image
    private ImageView pet_image;//Pet image

    private Projectile ball;    //stores the height and width of the ball used to play fetch
    private Target     pet;     //stores the position, height, and width of the pet on the map

    //
    public FetchLogic(FetchActivity fetch_ui){
        //this.ball_image = (ImageView) findViewById(R.id.baseball); //TODO interface with file system to get ball image or recieve as arg
        //this.pet_image = (ImageView) findViewById(R.drawable.cat);      //TODO interface with file system to get pet image or recieve as arg
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
    //  center_x :  the x coordinate of the center of the screen
    //  center_y :  the y coordinate of the center of the screen
    //  release_x : the x coordinate for where the touch release occurred on the screen
    //  release_y : the y coordinate for where the touch release occurred on the screen
    //---------------------------------------------------------------------------------------------
    public FetchDirective touchReleased(int center_x, int center_y, float release_x, float release_y){
        FetchDirective directive = null;

        // Verify input is valid
        if(center_x <= 0 || center_y <= 0                                   //check that location of center is reasonable
                || release_x < 0 || release_y < 0                           //check that release is not off the screen in a negative direction
                || release_x > 2*center_x+1 || release_y > 2*center_y+1){   //check that release is not off the screen in a positive direction
                                                                            //+1 is added to the bounds just incase an integer devision and rounding resulted in a dropped value.

            // Create an error Directive
            directive = new ErrorDirective();

            //add on error messages depending on what is wrong with the input args
            if(center_x <= 0 || center_y <= 0)
                ((ErrorDirective)directive).appendToErrorMsg("Passed coordinates for screen center are not possible.");
            if(release_x < 0 || release_y < 0 || release_x > 2*center_x+1 || release_y > 2*center_x+1)
                ((ErrorDirective)directive).appendToErrorMsg("Passed location of release would be off of the screen and therefore not possible.");
        }


        return final_directive;
    }
}
