package com.example.minipets.ui.fetch;

import android.view.MotionEvent;

public class ReleaseTouchEvent extends FetchEvent{
    private MotionEvent release_point;
    public ReleaseTouchEvent(MotionEvent release){
        this.release_point = release_point;
    }



    public boolean hasReleasePoint(){
        boolean valid = false;
        if(release_point != null)
            valid = true;
        return valid;
    }



    public float getReleasePosX(){
        float ret_val = 0;
        if(release_point != null)
            ret_val = release_point.getX();     //gets x location where held touch is released
        return ret_val;
    }



    public float getReleasePosY(){
        float ret_val = 0;
        if(release_point != null)
            ret_val = release_point.getY();     //gets y location where held touch is released
        return ret_val;
    }
}
