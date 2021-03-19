package com.example.minipets.ui.fetch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import com.example.minipets.R;

public class FetchActivity extends AppCompatActivity {

    protected DisplayMetrics display_metrics;    //stores the metrics for this display

    protected ImageView pet_image;

    protected FetchGameLogic game_logic;

    protected TextView points;

    protected UiFetchDirective directive;

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

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();

        this.points.setText("Tab out and back in to start. (I don't get it either)");

        //create a game logic controller for this game of fetch
        this.game_logic = new FetchLogic( this.display_metrics.widthPixels,
                this.display_metrics.heightPixels, this.pet_image.getWidth(),
                this.pet_image.getHeight(), this.pet_image.getLeft(), this.pet_image.getTop());

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        boolean petWasClicked = false;
        //read this as the ball being released
        if (action == MotionEvent.ACTION_UP) {
            //finger up means throw the ball
            int x_pos = (int) event.getX();
            int y_pos = (int) event.getY();
            petWasClicked = this.game_logic.clickDetected(x_pos, y_pos);

            if(petWasClicked){
                updateGame();
            }
        }

        return true;
    }

    //used to update pet position and
    // player points when
    protected void updateGame(){
        this.directive = this.game_logic.getNewDirective();
        this.pet_image.setX(this.directive.getPetPositionX());
        this.pet_image.setY(this.directive.getPetPositionY());
        this.points.setText(String.format("Points: %d", this.directive.getPoints()));
    }


    @Override
    public void onPause() {
        this.game_logic.gameIsClosing();
        super.onPause();
    }
}