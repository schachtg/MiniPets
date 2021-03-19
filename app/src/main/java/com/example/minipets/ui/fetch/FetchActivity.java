package com.example.minipets.ui.fetch;

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

    protected ImageView ball_image;

    protected FetchGameLogic game_logic;

    protected TextView points;

    protected FetchDirective directive;

   // protected  CountDownTimer timeLimit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        //get our images
        this.pet_image = (ImageView) findViewById(R.id.pet_target);
        this.pet_image.setPadding(0,0,0,0);

        this.ball_image = (ImageView) findViewById(R.id.ball_projectile);
        this.ball_image.setPadding(0,0,0,0);

        //get the display metrics of this activity
        this.display_metrics = this.getResources().getDisplayMetrics();

        this.points = (TextView) findViewById(R.id.points);
        this.points.setPadding(0,0,0,0);
    }

    @Override
    protected void onStart(){

        super.onStart();

        //find the center of this activity (screen's center)

    }


    @Override
    public void onResume() {
        super.onResume();

        this.points.setText("Points: 0");

        //create a game logic controller for this game of fetch
        this.game_logic = new FetchLogic(this.display_metrics.widthPixels,
                this.display_metrics.heightPixels, this.pet_image.getWidth(),
                this.pet_image.getHeight());

        //get an initial location for the pet image
        this.directive = this.game_logic.getNewDirective();
        this.pet_image.setY(this.directive.getPetPositionY());
        this.pet_image.setX(this.directive.getPetPositionX());
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        //read this as the ball being released
        if (action == MotionEvent.ACTION_UP) {//TODO this needs to move to logic
            //finger up means throw the ball
            float x_pos = event.getX();
            float y_pos = event.getY();

            if (this.pet_image.getX() <= x_pos && x_pos <= this.pet_image.getWidth() + this.pet_image.getX() &&
                    this.pet_image.getY() <= y_pos && y_pos <= this.pet_image.getY() + this.pet_image.getHeight()) {
                this.directive = this.game_logic.petWasClicked();
                this.points.setText(String.format("Points: %d",this.directive.totalPoints));
                this.pet_image.setX(this.directive.getPetPositionX());
                this.pet_image.setY(this.directive.getPetPositionY());
            }
            else{ //TODO get rid of this. this should only occur on times up
                this.directive = this.game_logic.getNewDirective();
                this.pet_image.setX(this.directive.getPetPositionX());
                this.pet_image.setY(this.directive.getPetPositionY());
            }
        }
        return true;
    }


/*

    protected void missAnimation(ThrowBallDirective directive){


        //set start and end points of ball trajectory
        this.findStartAndEndOfTrajectory(directive.getVectorX(), directive.getVectorY());

        //overset the end position so the ball goes off the screen
        this.ball_end_x *= 5;
        this.ball_end_y *= 5;

        //TODO set ball's initial position
        this.ball_image.setX(this.center_x - this.ball_offset_x);
        this.ball_image.setY(this.center_y - this.ball_offset_y);

        //TODO make ball visible
        this.ball_image.setVisibility(View.VISIBLE);

        //TODO register animatiomn for ball
        AnimatorSet path = new AnimatorSet();
        ObjectAnimator y_path = ObjectAnimator.ofFloat(this.ball_image, "translationY", this.ball_image.getY(), this.ball_end_y);//TODO using globals here is stinky (for end position)
        ObjectAnimator x_path = ObjectAnimator.ofFloat(this.ball_image, "translationX", this.ball_image.getX(), this.ball_end_x);

        //TODO register animations to play at the same time
        path.playTogether(x_path, y_path);
        path.setInterpolator(new LinearInterpolator());

        //TODO register animation duration for ball
        path.setDuration(2000); //TODO determine animation duration later if needed.

        //TODO start animation
        path.start();

        //TODO wait a few seconds
        //android.os.SystemClock.sleep(1000);

        //TODO make the ball invisible
        //this.ball_image.setVisibility(View.INVISIBLE);

        //TODO remove the animation
    }
*/
}