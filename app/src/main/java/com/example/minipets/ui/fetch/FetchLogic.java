package com.example.minipets.ui.fetch;

import android.widget.ImageView;

public class FetchLogic {
    private ImageView ball_image;//Ball image
    private ImageView pet_image;//Pet image

    //
    public FetchLogic(FetchActivity fetch_ui){
        //this.ball_image = (ImageView) findViewById(R.id.baseball); //TODO interface with file system to get ball image or recieve as arg
        //this.pet_image = (ImageView) findViewById(R.drawable.cat);      //TODO interface with file system to get pet image or recieve as arg
    }

    public FetchActivityDirective recieveFetchEvent(FetchEvent event){
        FetchActivityDirective directive = null;

        //Check for error in input arg.
        if(event != null){

            //rerout any events involving the lifting of the finger from the screen to a specific method
            if(event instanceof ReleaseTouchEvent){
                directive = processReleaseTouch((ReleaseTouchEvent) event);
            }
        }

        return directive
    }


    protected FetchActivityDirective processReleaseTouch(ReleaseTouchEvent event){

    }
}
