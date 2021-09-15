// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 8
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username:Subankamo
 * ID:300471606
 */

import ecs100.*;
import java.awt.Color;

/** Firework   */
public class Firework{
    public static final double GROUND = 550;
    public static final double RISE_STEP = 5;
    public static final double GROW_STEP = 2;

    private double xPos;
    private double ht = 0;
    private double maxHeight;
    private double radius = 2;
    private Color color;

    /** make a new firework with a random position and random color */
    public Firework(){
        this.xPos = 50+Math.random()*600;
        this.color = Color.getHSBColor((float)Math.random(), 1.0f, 1.0f);
        this.maxHeight = 100+Math.random()*400;
    }

    /**
     * Make the firework move one step:
     *  If below maxHeight, move it up by RISE_STEP
     *  If at maxHeight and not finished, make the radius bigger by GROW_STEP, 
     */
    public void step(){
        if (this.ht < maxHeight){
            this.ht = this.ht + RISE_STEP;
        }
        else if (! this.isFinished()){
            this.radius = this.radius + GROW_STEP;
        }
    }

    /**
     * Finished if the radius is at least half of maxHeight
     */
    public boolean isFinished(){
        return (this.radius >= this.maxHeight/2);
    }

    /**
     * Draw the firework as a circle of the current size, unless it is finished
     */
    public void draw(){
        if (! this.isFinished() ){
            UI.setColor(this.color);
            double left = this.xPos-this.radius;
            double top = GROUND-this.ht-this.radius;
            UI.drawOval(left, top, this.radius*2, this.radius*2);
        }
    }

}
