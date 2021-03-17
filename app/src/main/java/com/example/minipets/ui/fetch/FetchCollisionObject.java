package com.example.minipets.ui.fetch;

public abstract class FetchCollisionObject {
    static final double perc = 0.05;  //just a bit of wiggle room allowed for calculations to check if centre is shared
    protected /*int*/float width;        //the object's width
    protected /*int*/float height;       //the objects height
    protected /*int*/float x_origine;    //the x point of origine of any projectiles
    protected /*int*/float y_origine;    //the y point of origine of any projectiles


    public FetchCollisionObject(/*int*/float width, /*int*/float height, /*int*/float x_origine, /*int*/float y_origine) {
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


    public boolean hasCenter(/*int*/float x, /*int*/float y){
        boolean shared_center = false;
        if(this.x_origine <= ((1+perc) * x) && this.x_origine >= ((1-perc) * x) &&
                this.y_origine <= ((1+perc) * y) && this.y_origine >= ((1-perc) * y)){
            shared_center = true;
        }
        return shared_center;
    }
}
