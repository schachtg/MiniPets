package com.example.minipets.ui.fetch;

public class ThrowBallDirective {
    protected boolean ball_caught = false;
    protected float x_unit_vector = 0;
    protected float y_unit_vector = 0;

    public ThrowBallDirective(boolean caught, float x_vect, float y_vect){
        this.ball_caught = caught;
        this.x_unit_vector = x_vect;
        this.y_unit_vector = y_vect;
    }

    public float getVectorX(){
        return this.x_unit_vector;
    }


    public float getVectorY(){
        return this.y_unit_vector;
    }

    public boolean isTheCatchMade(){
        return ball_caught;
    }
}
