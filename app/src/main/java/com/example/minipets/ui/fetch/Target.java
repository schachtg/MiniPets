package com.example.minipets.ui.fetch;

import java.util.Random;

public class Target extends FetchCollisionObject{
    protected /*int*/float x_pos;    //the target's official x position on the screen
    protected /*int*/float y_pos;    //The target's official y position on the screen

    protected float hitbox_factor;  //how much bigger the hitbox for this target is. accepts numbers between -1 and 1, but defaults to 0.05

    protected TargetPosition position;  //where the pet is located if we draw a graph centered at the provided origine

    protected float x_unit_up;  //upper limit of a unit vector's x compnent to hit this target
    protected float x_unit_lo;
    protected float y_unit_up;
    protected float y_unit_lo;
    protected double lower_angle;   //the lowest acceptable angle that will hit this target (in radians (i think))
    protected double upper_angle;   //the highest acceptable angle that will hit this target (in radians(i think))


    public Target(/*int*/float width, /*int*/float height, /*int*/float x_origine, /*int*/float y_origine, float hitbox_factor) {
        super(width, height, x_origine, y_origine);
        this.x_pos = 0;
        this.y_pos = 0;
        this.position = TargetPosition.MID_MID;

        this.hitbox_factor = hitbox_factor;

        //ensure hitbox_factor is in valid range. if not, set to default of 0.05
        if ((this.hitbox_factor < -1.0) || (this.hitbox_factor > 1.0))
            this.hitbox_factor = (float) 0.05;

        this.generateRandomTargetPosition();
    }

    public /*int*/float getX_pos(){
        return this.x_pos;
    }

    public /*int*/float getY_pos(){
        return this.y_pos;
    }
    // generates a random position to put the target
    // generates a new position if the targets hitbox
    // overlaps with the origine in that position
    //-------------------------------------------------
    public void generateRandomTargetPosition(){
        Random rand = new Random();
        /*int*/float max_x = this.x_origine * 2 - this.width;
        /*int*/float max_y = this.y_origine * 2 - this.height;
        do {
            this.x_pos = rand.nextFloat() * (max_x );
            this.y_pos = rand.nextFloat() * (max_y );
            this.findTargetPosition();
        }while(this.position == TargetPosition.MID_MID);

        this.findAngleBounds();

        //TODO don't use the below
        findUnitVectorConstraintsTarget();  //find the constraints a unit vector must satisfy here. This way we can't skip that step.
    }


    //
    // Determins where (wrt the origine) the target lies
    // this is important when determining what trajectories will hit this target
    //----------------------------------------------------------------------------
    protected void findTargetPosition() {
        float top_side = (this.y_pos - (this.height * this.hitbox_factor));         //highest point of the target's hitbox
        float bottom_side = (this.y_pos + (this.height * (1 + this.hitbox_factor)));   //lowest point of the target's hitbox
        float left_side = (this.x_pos - (this.width * this.hitbox_factor));          //leftmost point of the target's hitbox
        float right_side = (this.x_pos + (this.width * (1 + this.hitbox_factor)));    //rightmost pont of the target's hitbox


        if (bottom_side < this.y_origine) {       //target is entirely in top half
            if (right_side < this.x_origine) {        //target is entirely in left half
                this.position = TargetPosition.TOP_LEFT;
            } else if (left_side > this.x_origine) {    //target is entirely in right half
                this.position = TargetPosition.TOP_RIGHT;
            } else {                                   //target is partially on the left, and partially on the right
                this.position = TargetPosition.TOP_MID;
            }
        } else if (top_side > this.y_origine) {    //target is entirely in bottom half
            if (right_side < this.x_origine) {        //Target is entirely in the left half
                this.position = TargetPosition.BOTTOM_LEFT;
            } else if (left_side > this.x_origine) {   //Target is entirely in the right half
                this.position = TargetPosition.BOTTOM_RIGHT;
            } else {                                   //Target lies on ither side of origine
                this.position = TargetPosition.BOTTOM_MID;
            }
        } else {                                   //Target lies partially above and partially below the  origine
            if (right_side < this.x_origine) {        //left half
                this.position = TargetPosition.MID_LEFT;
            } else if (left_side > this.x_origine) {    //right half
                this.position = TargetPosition.MID_RIGHT;
            }
            //MID_MID is not a valid position. If a target tries to take this position it's coordinates
            // should be re-assigned.
            else {                                  //middle
                this.position = TargetPosition.MID_MID;
            }
        }
    }

    //TODO could combine this with thee above function
    protected void findAngleBounds(){
        double top = (this.y_pos - (this.height * this.hitbox_factor));         //highest point of the target's hitbox
        double bottom = (this.y_pos + (this.height * (1 + this.hitbox_factor)));   //lowest point of the target's hitbox
        double left = (this.x_pos - (this.width * this.hitbox_factor));          //leftmost point of the target's hitbox
        double right = (this.x_pos + (this.width * (1 + this.hitbox_factor)));    //rightmost pont of the target's hitbox

        switch(this.position){
            case BOTTOM_RIGHT:
                this.lower_angle = this.calculateAngleTo( right, top);
                this.upper_angle = this.calculateAngleTo( left, bottom);
                break;
            case BOTTOM_MID:
                this.lower_angle = this.calculateAngleTo( right, top);
                this.upper_angle = this.calculateAngleTo( left, top);
                break;
            case BOTTOM_LEFT:
                this.lower_angle = this.calculateAngleTo( right, bottom);
                this.upper_angle = this.calculateAngleTo( left, top);
                break;
            case MID_LEFT:
                this.lower_angle = this.calculateAngleTo( right, bottom);
                this.upper_angle = this.calculateAngleTo( right, top);
                break;
            case TOP_LEFT:
                this.lower_angle = this.calculateAngleTo( left, bottom);
                this.upper_angle = this.calculateAngleTo( right, top);
                break;
            case TOP_MID:
                this.lower_angle = this.calculateAngleTo( left, bottom);
                this.upper_angle = this.calculateAngleTo( right, bottom);
                break;
            case TOP_RIGHT:
                this.lower_angle = this.calculateAngleTo( left, top);
                this.upper_angle = this.calculateAngleTo( right, bottom);
                break;
            case MID_RIGHT:
                this.lower_angle = this.calculateAngleTo( left, top);
                this.upper_angle = this.calculateAngleTo( left, bottom);
                break;
            default:
                this.lower_angle = 0;
                this.upper_angle = Math.PI;
                break;
        }
    }


    // calculates the angle between the center of the
    // playfield and the specified x,y location
    // since the +ive y direction is down for most
    // graphical interfaces, It may be beneficial to
    // imagine any layout to be mirrored allong
    // the x axis.
    // Otherwise you will want to measure angles counterclockwise
    // as opposed to clockwise
    //-------------------------------------------------------------
    protected double calculateAngleTo(double x, double y){
        double angle = Math.atan((double) ( (y - this.y_origine) / (x - this.x_origine) ));
        if(angle < 0)
            angle += 2 * Math.PI;

        return angle;
    }

    // determines the constraints a projectile's unit veector must
    // satisfy in order to hit this target.
    //  NOTE: the y component must not satisfy the magnitude outlined
    //         if the target is partially on one side of the x=0 axis
    //         and partially on the other. In this case the projectiles
    //         unit vector need only satisfy the constraints in the
    //         x direction.
    //         There is a similar rule if the target is crossing the
    //         horizontal plane
    //---------------------------------------------------------------
    protected void findUnitVectorConstraintsTarget() {
        //these are needed for calculations
        float[][] corner_vect = new float[4][2];    //create (non-unit) vectors from the origine to each corner of the target (accounting for an expanded hitbox)
                                                    //corner_vect[i][n] -> i=0 is to left corner, i = 1 is top right corner, i = 2 = bottom left corner, i = 3 is bottom right corner
                                                    //                  -> n=0 is x component, n = 1 is y component
        //Top-Left Corner
        corner_vect[0][0] = (this.x_pos - this.width*this.hitbox_factor) - (float)this.x_origine;
        corner_vect[0][1] = (this.y_pos - this.height*this.hitbox_factor) - (float)this.y_origine;

        //Top-Right Corner
        corner_vect[1][0] = (this.x_pos + this.width*(1+this.hitbox_factor)) - (float)this.x_origine;
        corner_vect[1][1] = corner_vect[0][1];

        //Bottom-Left Corner
        corner_vect[2][0] = corner_vect[0][0];
        corner_vect[2][1] = (this.y_pos + this.height*(1+this.hitbox_factor)) - (float)this.y_origine;

        //Bottom-Right Corner
        corner_vect[3][0] = corner_vect[1][0];
        corner_vect[3][1] = corner_vect[2][1];


        //
        switch(this.position){
            case BOTTOM_RIGHT:  //uses same corners for same bounds as top_left
            case TOP_LEFT:  //we use top-right and bottom left corners to define all upper and lower bounds
                                x_unit_up = corner_vect[1][0] / vectMag(corner_vect[1][0], corner_vect[1][1]);
                                x_unit_lo = corner_vect[2][0] / vectMag(corner_vect[2][0], corner_vect[2][1]);
                                y_unit_up = corner_vect[2][1] / vectMag(corner_vect[2][0], corner_vect[2][1]);//because down means higher y value
                                y_unit_lo = corner_vect[1][1] / vectMag(corner_vect[1][0], corner_vect[1][1]);
                                break;
            case TOP_MID:       //use bottom left and bottom right corners of target. only x component matters
                                x_unit_up = corner_vect[3][0] / vectMag(corner_vect[3][0], corner_vect[3][1]);
                                x_unit_lo = corner_vect[2][0] / vectMag(corner_vect[2][0], corner_vect[2][1]);
                                y_unit_up = 0;// TODO del dont care(float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                y_unit_lo = 0;// TODO del dont care(float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                break;
            case TOP_RIGHT:      //use top-left and bottom-right corners
            case BOTTOM_LEFT:   //uses same corners
                                x_unit_up = corner_vect[3][0] / vectMag(corner_vect[3][0], corner_vect[3][1]);
                                x_unit_lo = corner_vect[0][0] / vectMag(corner_vect[0][0], corner_vect[0][1]);
                                y_unit_up = corner_vect[3][1] / vectMag(corner_vect[3][0], corner_vect[3][1]);
                                y_unit_lo = corner_vect[0][1] / vectMag(corner_vect[0][0], corner_vect[0][1]);
                                break;
            case MID_LEFT:      //use top and bottom right corners. only y component matters
                                x_unit_up = 0;//TODO del (float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                x_unit_lo = 0;//TODO del (float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                y_unit_up = corner_vect[3][1] / vectMag(corner_vect[3][0], corner_vect[3][1]);
                                y_unit_lo = corner_vect[1][1] / vectMag(corner_vect[1][0], corner_vect[1][1]);
                                break;
            case MID_RIGHT:     //uses top and bottom left corners, only y component matters
                                x_unit_up = 0;//TODO del (float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                x_unit_lo = 0;//TODO del (float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                y_unit_up = corner_vect[2][1] / vectMag(corner_vect[2][0], corner_vect[2][1]);
                                y_unit_lo = corner_vect[0][1] / vectMag(corner_vect[0][0], corner_vect[0][1]);
                                break;
            case BOTTOM_MID:    //uses top left and right corners. only x component matters
                                x_unit_up = corner_vect[1][0] / vectMag(corner_vect[1][0], corner_vect[1][1]);
                                x_unit_lo = corner_vect[0][0] / vectMag(corner_vect[0][0], corner_vect[0][1]);
                                y_unit_up = 0;//TODO del (float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                y_unit_lo = 0;//TODO del (float) corner_vect[][] / vectMag(corner_vect[][], corner_vect[][]);
                                break;
            default:   //The target is ither nowhere or centered.
                        x_unit_up = 0;
                        x_unit_lo = 0;
                        y_unit_up = 0;
                        y_unit_lo = 0;
                        break;
        }
    }


    // calculates the magnitude of the 2d vector
    // with the corresponding x and y components
    //------------------------------------------------
    protected float vectMag(float x, float y){
        return (float) Math.pow( (Math.pow(x,2) + Math.pow(y,2)) , 0.5 );
    }


    //
    // Determines wether this target would be hit
    // by a specified projectile. Returns true if it would.
    //--------------------------------------------------------
    public boolean isHitByProjectile(Projectile projectile) {
        boolean hit = false;

        /* TODO restore this if below does not work
        switch(this.position){
            case TOP_LEFT:
            case TOP_RIGHT:
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                if( (projectile.getUnitVectorX() >= this.x_unit_lo) &&
                        (projectile.getUnitVectorX() <= this.x_unit_up) &&
                        (projectile.getUnitVectorY() >= this.y_unit_lo) &&
                        (projectile.getUnitVectorY() <= this.y_unit_up)){
                    hit = true;
                    System.out.println("Cat is located in 1 of cardinal directions. part of projectile vector is unimportant");//TODO remove
                }
                break;
            case TOP_MID:
                if( (projectile.getUnitVectorY() < 0) &&
                        (projectile.getUnitVectorX() >= this.x_unit_lo) &&
                        (projectile.getUnitVectorX() <= this.x_unit_up)){
                    hit = true;
                }
                break;
            case BOTTOM_MID:
                if( (projectile.getUnitVectorY() > 0) &&
                        (projectile.getUnitVectorX() >= this.x_unit_lo) &&
                        (projectile.getUnitVectorX() <= this.x_unit_up)){
                    hit = true;
                }
                break;
            case MID_LEFT:
                if( (projectile.getUnitVectorX() < 0) &&
                        (projectile.getUnitVectorY() >= this.y_unit_lo) &&
                        (projectile.getUnitVectorY() <= this.y_unit_up)){
                    hit = true;
                }
                break;
            case MID_RIGHT:
                if( (projectile.getUnitVectorX() > 0) &&
                        (projectile.getUnitVectorY() >= this.y_unit_lo) &&
                        (projectile.getUnitVectorY() <= this.y_unit_up)){
                    hit = true;
                }
                break;
            default:
                hit = false;
                break;
        }

         */

        //TODO ensure projectile is not null

        //calculate angle of projectile
        double projectileAngle = this.calculateProjectileAngle(projectile);

        if(this.upper_angle < this.lower_angle) {
            //then target is at MIDLE_LEFT position. and will have angles that are a little bit off
            if (projectileAngle <= upper_angle || lower_angle <= projectileAngle) {  //projectile angle is somewhere between these angles
                hit = true;
            }
        }
        else{
            if(this.lower_angle <= projectileAngle && projectileAngle <= this.upper_angle){
                hit = true;
            }
        }
        return hit;
    }



    protected double calculateProjectileAngle(Projectile projectile){
        //projectiles return vectors. vetors start an an origine 0,0

        double angle = Math.atan( (double) (projectile.getUnitVectorY() / projectile.getUnitVectorX()));

        if(angle < 0)
            angle += 2 * Math.PI;

        return angle;
    }
}
