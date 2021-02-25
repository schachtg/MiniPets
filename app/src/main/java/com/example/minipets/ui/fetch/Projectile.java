package com.example.minipets.ui.fetch;

public class Projectile extends FetchCollisionObject{
    protected float x_vector;  //it's x component of it's trajectory
    protected float y_vector;  //the y component of its trajectory
    protected float x_unit_vector;  //the unit vector's x component
    protected float y_unit_vector;  //the unit vector's y component

    public Projectile(int width, int height, int x_origine, int y_origine, float x_vector, float y_vecotr){
        super(width, height, x_origine, y_origine);
        this.x_vector = x_vector;
        this.y_vector = y_vecotr;

        //find unit vector
        this.x_unit_vector = 0;
        this.y_unit_vector = 0;
        this.calculateUnitVector();
    }


    // calculates the unit vector of this projectile
    //-----------------------------------------------
    protected void calculateUnitVector() {
        float magnitude = (float) Math.pow((Math.pow(this.x_vector, 2) + Math.pow(this.y_vector, 2)), 0.5);
        if (magnitude != 0) {
            this.x_unit_vector = (float) this.x_vector / magnitude;
            this.y_unit_vector = (float) this.y_vector / magnitude;
        }
    }

    // returns this projectile's unit vector's
    // x component
    //----------------------------------------------
    public float getUnitVectorX(){
        return this.x_unit_vector;
    }

    public float getUnitVectorY(){
        return this.y_unit_vector;
    }
}
