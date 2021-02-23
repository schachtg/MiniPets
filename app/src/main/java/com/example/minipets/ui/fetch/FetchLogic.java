package com.example.minipets.ui.fetch;

import android.widget.ImageView;

import com.example.minipets.R;

public class FetchLogic {
    private FetchActivity fetch_ui; // the fetch user interface
    private ImageView ball_image;//Ball image
    private ImageView pet_image;//Pet image

    //
    public FetchLogic(FetchActivity fetch_ui){
        this.fetch_ui = fetch_ui;                                         //Stores pointer to the fetch UI so we can talk with it
        //this.ball_image = (ImageView) findViewById(R.id.baseball); //TODO interface with file system to get ball image or recieve as arg
        //this.pet_image = (ImageView) findViewById(R.drawable.cat);      //TODO interface with file system to get pet image or recieve as arg
    }

    //public void handleFetchEvent(FetchEvent fetch_event){
//
  //  }
}
