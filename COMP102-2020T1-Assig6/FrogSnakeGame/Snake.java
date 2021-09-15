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

/** The snake is created at a random position with a random 360 degree direction.
 * The constructor does not have any parameters.
 * It can move
 *  - makes it go forward one step in its current direction.
 *  - if outside arena boundaries, makes it go backward one step, and
 *         then turn to a new (random) direction.
 *  The walls of the arena are determined by the constants:
 *    ARENA_SIZE, TOP_WALL, BOT_WALL, LEFT_WALL and RIGHT_WALL
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  move() will make it move in the direction it is going. 
 *  draw() will make it draw itself (image size should be 30).
 */

public class Snake{

    public static final int ARENA_SIZE = FrogSnakeGame.ARENA_SIZE;
    public static final int LEFT_WALL = FrogSnakeGame.LEFT_WALL;
    public static final int RIGHT_WALL = FrogSnakeGame.RIGHT_WALL;
    public static final int TOP_WALL = FrogSnakeGame.TOP_WALL;
    public static final int BOT_WALL = FrogSnakeGame.BOT_WALL;
    
    private double size=30;
    private double figX=(LEFT_WALL) + (Math.random()*(ARENA_SIZE-size));
    private double figY=(TOP_WALL) + (Math.random()*(ARENA_SIZE-size));
    private double direction=(Math.random() * 360);
    
    public Snake(){
    } 
    public void draw(){
        String imageFileName="snake.jpg";
        UI.drawImage(imageFileName, this.figX, this.figY, this.size, this.size);
    }
    public void move(){
        
        if (this.figX<=LEFT_WALL){
        this.figX=this.figX+1;
        this.direction=(Math.random() * 360);
        }
        else if ((this.figX+this.size)>=RIGHT_WALL){
        this.figX=this.figX-1;
        this.direction=(Math.random() * 360);
        }
        else if ((this.figY+this.size)>=BOT_WALL){
        this.figY=this.figY-1;
        this.direction=(Math.random() * 360);
        }
        else if (this.figY<=TOP_WALL){
        this.figY=this.figY+1;
        this.direction=(Math.random() * 360);
        }
        if(this.direction>0 && this.direction<=90){
            this.figY=figY+(1*FrogSnakeGame.count3);
            this.figX=figX-(1*FrogSnakeGame.count3);
        }
        else if(this.direction>90 && this.direction<=180){
        this.figY=figY+(1*FrogSnakeGame.count3);
        this.figX=figX+(1*FrogSnakeGame.count3);
        }
        else if(this.direction>180 && this.direction<=270){
        this.figY=figY-(1*FrogSnakeGame.count3);
        this.figX=figX+(1*FrogSnakeGame.count3);
        }
        else if(this.direction>270 && this.direction<=360){
        this.figY=figY-(1*FrogSnakeGame.count3);
        this.figX=figX-(1*FrogSnakeGame.count3);
        }
    }
    
    public void turnUp(){this.direction=Math.random()*180;}
    public void turnDown(){this.direction=180+Math.random()*180;}
    
    public double getX(){
    return figX;
    }
    public double getY(){
    return figY;
    }
}
