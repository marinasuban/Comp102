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

/** Ball represents a ball that is launched by the mouse towards a direction.
 *    Each time the step() method is called, it will take one step.  
 * For the Completion part, gravity should act on the ball by reducing its vertical speed.
 */

public class Ball{

    // Constants for all balls: size, position of the ground
    public static final double RADIUS = 8;  // radius of the balls.
    public static final double GROUND = BallGame.GROUND;
    public static final double RIGHT_END = BallGame.RIGHT_END;

    // Fields to store state of the ball:
    // x position, height above ground, stepX, stepY, colour
    //   The ball should initially be not moving at all. (step should be 0)
    private double x;
    private double y;
    private double height;
    private double stepX=0;
    private double stepY=0;
    private Color color;
    private double count=0;
    private double bounce=0;
    private double moment=0;
    
    // Constructor
    /** Construct a new Ball object.
     *  Parameters are the initial position (x and the height above the ground),
     *  Stores the parameters into fields 
     *  and initialises the colour to a random colour
     *  SHOULD NOT DRAW THE BALL!
     */
    public Ball(double x, double h){
        this.x = x;
        this.height= h;
        this.color= Color.getHSBColor((float)Math.random(), 1.0f, 1.0f);
        
    }

    // Methods
    /**
     * Draw the ball on the Graphics Pane in its current position
     * (unless it is past the RIGHT_END )
     */
    public void draw(){
        if (x>RIGHT_END){
        return;
        }
        else{
        UI.setColor(this.color);
        this.y=(GROUND - this.height - (RADIUS*2));
        UI.fillOval((this.x-this.RADIUS),this.y, RADIUS*2, RADIUS*2);
      }
     }

    /**
     * Move the ball one step (DO NOT REDRAW IT)
     * Core:
     *    Change its height and x position using the vertical and horizonal steps
     * Completion:
     *    Reduce its vertical speed each step (due to gravity), 
     *    If it would go below ground, then change its y position to ground level and
     *      set the  vertical speed back to 0.
     */
    public void step(){
        if(this.height>0){
        this.x = this.x + this.stepX;
        this.height = this.height + this.stepY - (0.2*this.count);
        this.count=this.count+1;
       }
        else if (this.height<=0){
        this.bounce=this.bounce+1;
        this.x = this.x + this.stepX;
        this.height = (this.height*(-1)) + this.stepY + (0.2*this.count)- (0.2*this.bounce*2);
        this.count=this.count+1;
          if (bounce>5){
        this.x = this.x + 1;
        this.height=0;
        }
       
       }
      
    }

    /**
     * Set the speed of the ball.
     * The horizontal speed is how much it moves to the right in each step.
     * The vertical speed is how much it moves up in each step (negative if ball going down).
     */
    public void setSpeed(double xSpeed, double ySpeed){
        this.stepX = xSpeed;
        this.stepY= ySpeed;
    }

    /**
     * Return the height of the ball above the ground
     */
    public double getHeight(){
        return this.height;
    }

    /**
     * Return the horizontal position of the ball
     */
    public double getX(){
        return this.x;
    }


}
