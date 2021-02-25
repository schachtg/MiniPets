package com.example.minipets.ui.fetch;

public class ThrowBallDirective extends FetchDirective{
    protected boolean ball_caught = false;
    protected float x_vector = 0;
    protected float y_vector = 0;

    public ThrowBallDirective(boolean caught, int x_vect, int y_vect){
        this.ball_caught = caught;
        this.x_vector = x_vect;
        this.y_vector = y_vect;
    }

    public float getVectorX(){
        return this.x_vector;
    }


    public float getVectorY(){
        return this.y_vector;
    }

    public boolean isTheCatchMade(){
        return ball_caught;
    }
}
