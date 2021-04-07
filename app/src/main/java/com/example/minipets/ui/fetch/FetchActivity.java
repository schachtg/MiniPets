package com.example.minipets.ui.fetch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import com.example.minipets.R;
import com.example.minipets.logic.IFetchGameLogic;
import com.example.minipets.logic.FetchLogic;

public class FetchActivity extends AppCompatActivity {

    protected DisplayMetrics display_metrics;    //stores the metrics for this display

    protected ImageView pet_image;

    protected IFetchGameLogic game_logic;

    protected TextView points;

    protected UiFetchDirective directive;

    protected Button start_fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        //get our images
        this.pet_image = (ImageView) findViewById(R.id.pet_target);
        this.pet_image.setPadding(0,0,0,0);

        //get the display metrics of this activity
        this.display_metrics = this.getResources().getDisplayMetrics();

        this.points = (TextView) findViewById(R.id.points);
        this.points.setPadding(0,0,0,0);

        this.start_fetch = (Button) findViewById(R.id.start_fetch);
        this.start_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO put function to handle that here
                startFetchButtonHandler();
            }
        });

        Log.d("CREATE", "fetch activity is created");
    }


//    @SuppressLint("SetTextI18n")
//    @Override
//    public void /*onResume*/onStart() {
//        super.onStart()/*onResume()*/;
//
//        Log.d("START", "fetch activity begins start routine");
//
//        this.points.setText("Tab out and back in to start. (I don't get it either)");
//
//        //create a game logic controller for this game of fetch
//        this.game_logic = new FetchLogic( this.display_metrics.widthPixels,
//                this.display_metrics.heightPixels, this.pet_image.getWidth(),
//                this.pet_image.getHeight(), this.pet_image.getLeft(), this.pet_image.getTop());
//
//        Log.d("START", "fetch activity ends start routine");
//
//    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();

        Log.d("RESUME", "fetch activity begins resume routine");

        this.points.setText("Press the button to start the game");

        //make sure the button to start the game is visible
        start_fetch.setVisibility(View.VISIBLE);

        //make sure the pet is invisible
        pet_image.setVisibility(View.INVISIBLE);

        //create a game logic controller for this game of fetch
        this.game_logic = new FetchLogic( this.display_metrics.widthPixels, this.display_metrics.heightPixels);

        Log.d("FetchActivity", String.format("Resume sees screen dimensions width = %d, height = %d", this.display_metrics.widthPixels, this.display_metrics.heightPixels));

        Log.d("RESUME", "fetch activity ends resume routine");
    }


    // the user wishis to begin the game of fetch
    // this needs to make the pet visible, get the start button out of the way
    // tell the game logic the size and position of the pet, then tell the game logic that
    // it is ready to rock and roll
    //---------------------------------------------------------------------------------------
    private void startFetchButtonHandler(){

        //make sure the game's logic layer exists and that the start button is visible
        if(this.game_logic != null && this.start_fetch.getVisibility() == View.VISIBLE) {

            //make the pet image visible
            this.pet_image.setVisibility(View.VISIBLE);

            //pas the game logic the size and position of the game logic relative to the board
            this.game_logic.definePetState(this.pet_image.getWidth(), this.pet_image.getHeight(), this.pet_image.getLeft(), this.pet_image.getTop());

            //make the button used to start the game not exist (in the visible space)
            this.start_fetch.setVisibility(View.GONE);

            //let's rock and roll
            updateGame();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("FetchActivity", String.format("touch event sees screen dimensions width = %d, height = %d", this.display_metrics.widthPixels, this.display_metrics.heightPixels));
        Log.d("FetchActivity", String.format("touch event sees pet dimensions width = %d, height = %d",this.pet_image.getWidth(), this.pet_image.getHeight()));
        Log.d("FetchActivity", String.format("touch event sees pet position x = %d, y = %d", this.pet_image.getLeft(), this.pet_image.getTop()));


        Log.d("TOUCH", "fetch activity detected touch");

        int action = MotionEventCompat.getActionMasked(event);
        boolean petWasClicked = false;
        //read this as the ball being released
        if (action == MotionEvent.ACTION_UP) {
            //finger up means throw the ball
            int x_pos = (int) event.getX();
            int y_pos = (int) event.getY();

            Log.d("TOUCH", String.format("touch was at x=%d, y=%d", x_pos, y_pos));
            Log.d("TOUCH", String.format("pet was at x=[%d, %d], y=[%d, %d]", this.pet_image.getLeft(), this.pet_image.getLeft()+this.pet_image.getWidth(), this.pet_image.getTop(), this.pet_image.getTop()+this.pet_image.getHeight()));

            //TODO error check here. if gamelogic DNE need to log or throw an error
            petWasClicked = this.game_logic.clickDetected(x_pos, y_pos);

            if(petWasClicked){

                Log.d("TOUCH", "update game requested");

                updateGame();
            }
        }

        return true;
    }

    //used to update pet position and
    // player points when
    protected void updateGame(){
        this.directive = this.game_logic.getNewDirective();
        if(this.directive != null) {
            this.pet_image.setX(this.directive.getPetPositionX());
            this.pet_image.setY(this.directive.getPetPositionY());
            this.points.setText(String.format("Points: %d", this.directive.getPoints()));
        }
        else{
            //TODO need to throw an exception or log an error
            Log.e("FetchActivity", "update game received null directive");

            //we cold call the start button's handler function to try to restart the program
            //but that could infinately recurse, so let's just reset this UI
            this.pet_image.setVisibility(View.INVISIBLE);
            this.start_fetch.setVisibility(View.VISIBLE);
            this.points.setText("Sorry! try pressing that button again.");
        }
    }


    //onclick listener for the
    //pet image
    protected void petWasClicked(){

    }


    @Override
    public void onPause() {
        Log.d("PAUSE", "fetch activity begins pause routine");

        this.game_logic.gameIsClosing();
        super.onPause();
    }
}