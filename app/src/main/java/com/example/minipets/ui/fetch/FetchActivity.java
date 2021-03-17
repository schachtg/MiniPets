package com.example.minipets.ui.fetch;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import com.example.minipets.R;

public class FetchActivity extends AppCompatActivity {

    protected DisplayMetrics display_metrics;    //stores the metrics for this display
    protected /*int*/float center_x = 1;       //the x coordinate of the center of this scree
    protected /*int*/float center_y = 1;       //the y coordinate of the senter of this screen

    protected ImageView pet_image;
    protected ImageView ball_image;

    protected FetchGameLogic game_logic;

    protected float ball_offset_x;
    protected float ball_offset_y;

    protected float pet_offset_x;
    protected float pet_offset_y;

    protected float ball_start_x;
    protected float ball_start_y;
    protected float ball_end_x;
    protected float ball_end_y;

    protected TextView caught;
//    protected TextView calced;//TODO a
//    protected TextView measured;//TODO a

    protected ImageView pointer_A;//TODO test

//    protected ImageView bottom_right;//TODO h

//    protected TextView click_loc;//TODO c



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        //get our images
        this.pet_image = (ImageView) findViewById(R.id.pet_target);
        this.pet_image.setPadding(0,0,0,0);//TODO f
        this.ball_image = (ImageView) findViewById(R.id.ball_projectile);
        this.ball_image.setPadding(0,0,0,0);//TODO f

        //get the display metrics of this activity
        this.display_metrics = this.getResources().getDisplayMetrics();

        this.pointer_A = (ImageView) findViewById(R.id.pointer_A);//TODO test
        this.pointer_A.setPadding(0,0,0,0);//TODO f

//        this.bottom_right = (ImageView) findViewById(R.id.bottom_right);//TODO a
//        this.bottom_right.setPadding(0,0,0,0);//TODO f

//        this.measured = (TextView) findViewById(R.id.measured);//TODO a
//        this.measured.setPadding(0,0,0,0);//TODO f
//        this.calced = (TextView) findViewById(R.id.calced);//TODO a
//        this.calced.setPadding(0,0,0,0);//TODO f
//        this.click_loc = (TextView) findViewById(R.id.click_loc);//TODO c
//        this.click_loc.setPadding(0,0,0,0);//TODO c
        this.caught = (TextView) findViewById(R.id.caught);
        this.caught.setPadding(0,0,0,0);
    }

    @Override
    protected void onStart(){

        super.onStart();

        //find the center of this activity (screen's center)

    }

    @Override//TODO b
    public void onResume(){//TODO b
        super.onResume();//TODO b

        this.findViewCenter(this.display_metrics.widthPixels, this.display_metrics.heightPixels);

        //create a game logic controller for this game of fetch
        this.game_logic = new FetchLogic(this.display_metrics.widthPixels,
                this.display_metrics.heightPixels, this.pet_image.getWidth(),
                this.pet_image.getHeight(), (float) 0, this.ball_image.getWidth(),      //can be set to -0.15 to make it more dificult
                this.ball_image.getHeight());

        //get an initial location for the pet image
        this.getPetLocation();

        //TODO old//this.text_test = (TextView) findViewById(R.id.the_last_test_text);

        this.ball_offset_x = this.ball_image.getWidth() / 2;
        this.ball_offset_y = this.ball_image.getHeight() / 2;

        this.pet_offset_x = this.pet_image.getWidth() / 2;
        this.pet_offset_y = this.pet_image.getHeight() / 2;

//        this.calced.setText(String.format("calced w=%d, h=%d", this.display_metrics.widthPixels, this.display_metrics.heightPixels));//TODO b
//        this.measured.setText(String.format("measured w=%d, h=%d", getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()));//this.bottom_right.getRight(), this.bottom_right.getBottom()));//TODO b


    }//TODO b



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
                this.caught.setText("the cat caught the ball");
            else
                this.caught.setText("the cat did not catch the ball");

            //handel errors
            if(directive != null) {
                this.showBallAnimation(directive);
            }

            //TODO show message

            //TODO dealay

            //TODO reset
            this.ball_image.setX(this.center_x /*nw*/ - this.ball_offset_x);
            this.ball_image.setY(this.center_y /*nw*/ - this.ball_offset_y);

            //set new location for pet
            this.getPetLocation();
            this.pointer_A.setX(this.center_x);//TODO test
            this.pointer_A.setY(this.center_y);//TODO test
//            this.click_loc.setText(String.format("clk x=%f, y=%f", event.getX(), event.getY()));//TODO c
        }
        return true;
    }


    // called internally to get the x and y coordinates of the center of this screen
    protected void findViewCenter(float width, float height){
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
//
//        //set the motion difference for the ball. (a vector, but w/ magnitude)
//        float delta_x = this.ball_end_x - this.ball_start_x;
//        float delta_y = this.ball_end_y - this.ball_start_y;
//
//        this.ball_image.setTranslationX(this.ball_start_x); //ball starting position
//        this.ball_image.setTranslationY(this.ball_start_y);
//
//        //make the ball visible
//        this.ball_image.setVisibility(View.VISIBLE);
//
//        //a feeble attempt
//        this.ball_image.animate().translationXBy(delta_x).translationYBy(delta_y).setDuration(2000); //2 seconds
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

//TODO get rid of this. I need to modify code to treat items as unit point objects.
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