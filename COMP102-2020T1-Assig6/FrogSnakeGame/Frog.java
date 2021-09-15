// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 6
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: SUbankamo
 * ID:300471606
 */

import ecs100.*;
import java.awt.Color;

/** Frog
 *  A new frog starts at the given position, with the given direction, and 
 *     has either a "light" or "dark" shade.
 *  Frogs can turn in 4 directions: left, right, up, and down. 
 *  Frogs move around at a constant speed in an arena with an enclosing wall,
 *     following its direction, until it hits a wall. In which case it stops moving.
 *  Frog can grow in size, and (for the completion) can also shrink by resetting their size
 *      to the orginal value.
 *
 *  The walls of the arena are determined by the constants:
 *    FrogGame.TOP_WALL, FrogGame.BOT_WALL, FrogGame.LEFT_WALL and FrogGame.RIGHT_WALL
 */

public class Frog {
    // Constants
    public static final int INITIAL_SIZE = 30;
    public static final int GROWTH_RATE = 3;
    public static final int SPEED = 2;

    public static final int LEFT_WALL = FrogGame.LEFT_WALL;
    public static final int RIGHT_WALL = FrogGame.RIGHT_WALL;
    public static final int TOP_WALL = FrogGame.TOP_WALL;
    public static final int BOT_WALL = FrogGame.BOT_WALL;
    // Fields

    private double figX;
    private double figY;
    private String direction;
    private String color;
    private double size=INITIAL_SIZE;
    
    //Constructor 
    /** 
     * Make a new frog
     * The parameters specify the initial position of the top left corner,
     *   the direction, and the shade of the Frog ("light" or "dark")
     * We assume that the position is within the boundaries of the arena
     */
    public Frog(double left, double top, String dir, String shade)  {
        this.figX=left;
        this.figY=top;
        this.direction=dir;
        this.color=shade;

    }

    /**
     * Turn right  (don't redraw)
     */
    public void turnRight(){
        this.direction="Right";

    }

    /**
     * Turn left  (don't redraw)
     */
    public void turnLeft(){
        this.direction="Left";

    }

    /**
     * Turn up  (don't redraw)
     */
    public void turnUp(){
         this.direction="Up";

    }

    /**
     * Turn down  (don't redraw)
     */
    public void turnDown(){
        this.direction="Down";

    }

    /**
     * Moves the frog: 
     *   use SPEED unit forward in the correct direction
     *   by changing the position of the frog.
     * Make sure that the frog does not go outside the arena, by making sure 
     *  - the top of the frog is never smaller than FrogGame.TOP_WALL
     *  - the bottom of the frog is never greater than FrogGame.BOT_WALL
     *  - the left of the frog is never smaller than FrogGame.LEFT_WALL
     *  - the right of the frog is never greater than FrogGame.RIGHT_WALL
     *  DO NOT REDRAW THE FROG!!!
     */
    public void move() {
        if(this.direction=="Left"){
        this.figX=this.figX-SPEED;
        if (this.figX<FrogGame.LEFT_WALL){
        this.figX=FrogGame.LEFT_WALL;
        }
        }
        else if (this.direction=="Right"){
         this.figX=this.figX+SPEED;
         if ((this.figX+this.size)>FrogGame.RIGHT_WALL){
        this.figX=FrogGame.RIGHT_WALL-this.size;
        }
        }
        else if(this.direction=="Up"){
        this.figY=this.figY-SPEED;
        if(this.figY<FrogGame.TOP_WALL){
        this.figY=FrogGame.TOP_WALL;
        }
        }
        else if(this.direction=="Down"){
        this.figY=this.figY+SPEED;
        if((this.figY+this.size)>FrogGame.BOT_WALL){
        this.figY=FrogGame.BOT_WALL-this.size;
        }
        }

    }

    /**
     * Check whether the frog is touching the given point, eg, whether the
     *   given point is included in the area covered by the frog.
     * Return true if the frog is on the top of the position (x, y)
     * Return false otherwise
     */
    public boolean touching(double x, double y) {
        if ((figX>=x-size && figX<=x) && (figY>=y-size && figY<=y)){
            return true;
        }
        else{
        return false;
        }

    }


    /**
     * The Frog has just eaten a bug
     * Makes the frog grow larger by GROWTH_RATE.
     */
    public void grow(){
        this.size= this.size + GROWTH_RATE;

    }

    /**
     * The Frog has just bumped into a snake
     * Makes the frog size reset to its original size
     * ONLY NEEDED FOR COMPLETION
     */
    public void shrink(){
        this.size=INITIAL_SIZE;

    }

    /**
     * Draws the frog at the current position and the current size.
     * Use the correct image file (darkfrog.jpg or lightfrog.jpg)
     */
    public void draw(){
        String imageFileName=this.color+"frog.jpg";
        UI.drawImage(imageFileName, this.figX, this.figY, this.size, this.size);
    }
}

