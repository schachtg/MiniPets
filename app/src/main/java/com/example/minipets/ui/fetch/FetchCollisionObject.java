package com.example.minipets.ui.fetch;

public abstract class FetchCollisionObject {
    protected int width;        //the object's width
    protected int height;       //the objects height
    protected int x_origine;    //the x point of origine of any projectiles
    protected int y_origine;    //the y point of origine of any projectiles


    public FetchCollisionObject(int width, int height, int x_origine, int y_origine){
        this.width = width;
        this.height = height;
        this.x_origine = x_origine;
        this.y_origine = y_origine;
    }
}
