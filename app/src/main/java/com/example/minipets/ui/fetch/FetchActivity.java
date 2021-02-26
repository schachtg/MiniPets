package com.example.minipets.ui.fetch;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import com.example.minipets.R;

public class FetchActivity extends AppCompatActivity {
//    private FetchLogic logic;   //stores the logic that the UI must respond to and inform
//    private FetchPet pet;       //stores the needed info about our pet that is being used to play fetch
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
    protected TextView center_position;
    protected TextView tap_position;
    protected TextView ball_vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        //get our images
        this.pet_image  = (ImageView) findViewById(R.id.pet_target);
        this.ball_image = (ImageView) findViewById(R.id.ball_projectile);

        //get the display metrics of this activity
        this.display_metrics = this.getResources().getDisplayMetrics();

        //TODO remove these
        this.center_position = (TextView) findViewById(R.id.center_position);
        this.tap_position = (TextView) findViewById(R.id.tap_position);
        this.ball_vector = (TextView) findViewById(R.id.ball_vector);

        //find the center of this activity (screen's center)
        this.findViewCenter(this.display_metrics.widthPixels, this.display_metrics.heightPixels);

        //create a game logic controller for this game of fetch
        this.game_logic = new FetchLogic(this.display_metrics.widthPixels,
                this.display_metrics.heightPixels, this.pet_image.getWidth(),
                this.pet_image.getHeight(), (float) -0.15, this.ball_image.getWidth(),      //can be set to -0.15 to make it more dificult
                this.ball_image.getHeight());

        //get an initial location for the pet image
        this.getPetLocation();

        this.text_test = (TextView) findViewById(R.id.the_last_test_text);

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
            this.center_position.setText(String.format("center at x=%d, y=%d", this.center_x, this.center_y));
            this.tap_position.setText(String.format("tap at x=%f, y=%f", event.getX(), event.getY()));
            this.ball_vector.setText(String.format("ball vector x=%f, y=%f", directive.getVectorX(), directive.getVectorY()));
            this.text_test.setText(String.format("the cat is at x=%f, y=%f", this.pet_image.getX(), this.pet_image.getY()));

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
        this.ball_image.setVisibility(this.ball_image.VISIBLE);

        missAnimation(directive);
        //TODO add catch animation if they caught the ball

        //TODO use Grahm's timer to delay before becoming invisible again.
        this.ball_image.setVisibility(this.ball_image.INVISIBLE);
    }


    protected void missAnimation(ThrowBallDirective directive){
        //couldn't figure out how to doe the animations libraries. I just want to animate an image moving from one point to another.
        //I'm not trying to plot a dance routine here
        float x_pnt = this.center_x - this.ball_offset_x;   //current location of ball
        float y_pnt = this.center_y - this.ball_offset_y;   //current location of ball
        ObjectAnimator animation;

        while(0 < x_pnt+this.ball_offset_x && x_pnt+this.ball_offset_x < this.display_metrics.widthPixels && 0 < y_pnt+this.ball_offset_y && y_pnt+this.ball_offset_y < this.display_metrics.heightPixels){
            animation = ObjectAnimator.ofFloat(this.ball_image, "translationX", x_pnt);
            animation.setDuration(500);//100ms;
            animation.start();

            animation = ObjectAnimator.ofFloat(this.ball_image, "translationY", y_pnt);
            animation.setDuration(500);//100ms;
            animation.start();

            x_pnt += directive.getVectorX();
            y_pnt += directive.getVectorY();

        }
    }
}