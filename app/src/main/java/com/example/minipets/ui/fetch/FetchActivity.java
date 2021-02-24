package com.example.minipets.ui.fetch;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import com.example.minipets.R;

public class FetchActivity extends AppCompatActivity {
//    private FetchLogic logic;   //stores the logic that the UI must respond to and inform
//    private FetchPet pet;       //stores the needed info about our pet that is being used to play fetch
    private DisplayMetrics display;    //stores the metrics for this display
    private int center_x;       //the x coordinate of the center of this scree
    private int center_y;       //the y coordinate of the senter of this screen
    private int screen_height;
    private int screen_width;
    private TextView fech_text_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        //create a FetchLogic item to interface with
//        this.logic = new FetchLogic(this);  //does fetch logic need arguments?
        //create temp text view for testing TODO delete this
        this.fech_text_temp = (TextView) findViewById(R.id.fetch_test_text);


        this.display = this.getResources().getDisplayMetrics();
        this.screen_height = this.display.heightPixels;
        this.screen_width = this.display.widthPixels;
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
        }
        return true;
    }
}