package com.example.minipets.ui.fetch;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import com.example.minipets.R;

public class FetchActivity extends AppCompatActivity {

    protected DisplayMetrics display_metrics;    //stores the metrics for this display
    protected int center_x;       //the x coordinate of the center of this scree
    protected int center_y;       //the y coordinate of the senter of this screen

    protected ImageView pet_image;
    protected ImageView ball_image;

    protected DisplayMetrics pet_metrics;
    protected DisplayMetrics ball_metrics;

    protected FetchGameLogic game_logic;

    protected int ball_offset_x;
    protected int ball_offset_y;

    protected TextView text_test;      //TODO remove

    protected float ball_start_x;
    protected float ball_start_y;
    protected float ball_end_x;
    protected float ball_end_y;

    protected boolean locked = true;    //program does not allow clicking

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.locked = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        //get our images
        this.pet_image  = (ImageView) findViewById(R.id.pet_target);
        this.ball_image = (ImageView) findViewById(R.id.ball_projectile);

        //get the display metrics of this activity
        this.display_metrics = this.getResources().getDisplayMetrics();

        //find the center of this activity (screen's center)
        this.findViewCenter(this.display_metrics.widthPixels, this.display_metrics.heightPixels);

        //create a game logic controller for this game of fetch
        this.game_logic = new FetchLogic(this.display_metrics.widthPixels,
                this.display_metrics.heightPixels, this.pet_image.getWidth(),
                this.pet_image.getHeight(), (float) -0.15, this.ball_image.getWidth(),      //can be set to -0.15 to make it more dificult
                this.ball_image.getHeight());

        //get an initial location for the pet image
        this.getPetLocation();

        this.text_test = (TextView) findViewById(R.id.the_last_test_text);  //TODO remove this

        this.ball_offset_x = this.ball_image.getWidth() / 2;
        this.ball_offset_y = this.ball_image.getHeight() / 2;
    }


    //detect touch interaction occuring to the game screen
    @Override
    public boolean onTouchEvent(MotionEvent event){
        ThrowBallDirective directive;
        int action = MotionEventCompat.getActionMasked(event);

        //read this as the ball being released
        if(action == MotionEvent.ACTION_UP){
            //finger up means throw the ball
            directive = this.game_logic.ballReleased(event.getX(), event.getY());

            //TODO remove these
            if(directive != null && directive.isTheCatchMade())
                this.text_test.setText("the cat caught the ball");
            else
                this.text_test.setText("the cat did not catch the ball");

            //handel errors
            if(directive != null) {
                this.showBallAnimation(directive);
            }

            //TODO show message

            //TODO dealay

            //TODO reset
            this.ball_image.setX(this.center_x);
            this.ball_image.setY(this.center_y);
            this.getPetLocation();

        }
        return true;
    }


    // called internally to get the x and y coordinates of the center of this screen
    protected void findViewCenter(int width, int height){
        this.center_x = width / 2;
        this.center_y = height / 2;
    }


    protected void getPetLocation(){
        this.pet_image.setX(this.game_logic.getPetX());
        this.pet_image.setY(this.game_logic.getPetY());
    }


    protected void showBallAnimation(ThrowBallDirective directive){


        missAnimation(directive);
        //TODO add catch animation if they caught the ball

        //TODO use Grahm's timer to delay before becoming invisible again.
        //this.ball_image.setVisibility(this.ball_image.INVISIBLE);
    }


    protected void missAnimation(ThrowBallDirective directive){


        //set start and end points of ball trajectory
        this.findStartAndEndOfTrajectory(directive.getVectorX(), directive.getVectorY());

        //overset the end position so the ball goes off the screen
        this.ball_end_x *= 5;
        this.ball_end_y *= 5;

        //set the motion difference for the ball. (a vector, but w/ magnitude)
        float delta_x = this.ball_end_x - this.ball_start_x;
        float delta_y = this.ball_end_y - this.ball_start_y;

        this.ball_image.setTranslationX(this.ball_start_x); //ball starting position
        this.ball_image.setTranslationY(this.ball_start_y);

        //make the ball visible
        this.ball_image.setVisibility(View.VISIBLE);

        //a feeble attempt
        this.ball_image.animate().translationXBy(delta_x).translationYBy(delta_y).setDuration(2000); //2 seconds
    }


    protected void findStartAndEndOfTrajectory(float x_vect, float y_vect){

        float temp_end_x;
        float temp_end_y;
        //start point is at x = screen_center - 1/2*ball_width
        //end point is at y = screen_center - 1/2* ball width
        this.ball_start_x = (float) this.center_x - (float) (this.ball_image.getWidth() / 2);
        this.ball_start_y = (float) this.center_y - (float) (this.ball_image.getHeight() / 2);

        temp_end_x = this.ball_start_x;
        temp_end_y = this.ball_start_y;
        //find end point by iterating //TODO there is a better way to do this. I could probably just math it out
        while(0 < temp_end_x && temp_end_x < this.display_metrics.widthPixels && 0 < temp_end_y && temp_end_y < this.display_metrics.heightPixels){
            temp_end_x += x_vect;
            temp_end_y += y_vect;
        }
        this.ball_end_x = temp_end_x;
        this.ball_end_y = temp_end_y;
    }
}