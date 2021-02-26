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
//    private FetchLogic logic;   //stores the logic that the UI must respond to and inform
//    private FetchPet pet;       //stores the needed info about our pet that is being used to play fetch
    protected DisplayMetrics display_metrics;    //stores the metrics for this display
    protected int center_x;       //the x coordinate of the center of this scree
    protected int center_y;       //the y coordinate of the senter of this screen

    protected ImageView pet_image;
    protected ImageView ball_image;

    protected FetchGameLogic game_logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        
    }


    //detect touch interaction occuring to the game screen
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);

        //read this as the ball being released
        if(action == MotionEvent.ACTION_UP){
            //TODO for now, just record the releasing of touch on the screen.
            //TODO later, we will want to send the location of the relase to the user.
            String stuff = "You just pressed on the scree! is this fetch yet?";
            this.fech_text_temp.setText(stuff);
            String screenCent = String.format("Screen is centered at x = %d and y = %d", this.center_x, this.center_y);
            this.test_center_position.setText(screenCent);
            String releasePoint = String.format("Pointer released at x = %f, y = %f", event.getX(), event.getY());
            this.test_pointer_position.setText(releasePoint);
        }
        return true;
    }


    // called internally to get the x and y coordinates of the center of this screen
    protected void findViewCenter(int width, int height){
        this.center_x = width / 2;
        this.center_y = height / 2;
    }
}