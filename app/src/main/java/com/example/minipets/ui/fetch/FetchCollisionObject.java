package com.example.minipets.ui.fetch;

public abstract class FetchCollisionObject {
    protected int width;        //the object's width
    protected int height;       //the objects height
    protected int x_origine;    //the x point of origine of any projectiles
    protected int y_origine;    //the y point of origine of any projectiles


    public FetchCollisionObject(int width, int height, int x_origine, int y_origine) {
        this.x_origine = x_origine;
        this.y_origine = y_origine;

        //ensure the size of the target is not bigger than half the screen in any dimension.
        this.width = width;
        if (this.width > x_origine)
            this.width = x_origine - 1;

        this.height = height;
        if (this.height > y_origine)
            this.height = y_origine - 1;
    }


    public boolean hasCenter(int x, int y){
        boolean shared_center = false;
        if(this.x_origine == x && this.y_origine == y){
            shared_center = true;
        }
        return shared_center;
    }
}
