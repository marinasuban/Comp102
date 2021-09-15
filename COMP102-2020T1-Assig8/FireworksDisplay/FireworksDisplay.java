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
import java.util.*;

/** FireworksDisplay
 * Runs a fireworks display with waves of fireworks.
 * Each wave has lots of fireworks that start together
 * When they have all finished, it starts the next wave.
 * The "Size" slider controls the number of fireworks in the next wave.
 */

public class FireworksDisplay{

    private int numFireworks = 20;  // number of fireworks in the wave. (set by slider)
    
    /**
     * Set up the GUI with a button and a slider
     * and start the simulation
     */
    public void setupGUI(){
        UI.addSlider("Size", 2, 30, this.numFireworks, this::setWaveSize);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800, 600);
        UI.setDivider(0.0);     // hide the text pane
        this.runDisplay();
    }

    /**
     * Set the number of fireworks for the next wave
     */
    public void setWaveSize(double value){
        int value1=(int)Math.round(value);
        this.numFireworks= value1;
       

    }

    /**
     * Run the display
     */
    public void runDisplay(){

        // create a list of fireworks
        // (stored in local variable because only used in this method)
        // - declare the variable
        // - make an empty list in the variable
        // - put numFireworks into the list.
        /*# YOUR CODE HERE */
       ArrayList<Firework> fireworks=new ArrayList<Firework>();
       ArrayList<Integer> number=new ArrayList<Integer>();
       number.add(this.numFireworks);
       int count=0;
       for(int i=0; i<number.get(count); i++){
            Firework fire = new Firework();
            fireworks.add(fire);
            }
       
        // main loop (goes forever)
        while (true){
            // redraw the background
            UI.clearGraphics();
            UI.setColor(Color.black);
            UI.fillRect(0, 0, 1000, 700);
            
            /*# YOUR CODE HERE */
            // draw all the fireworks and pause for 70 milliseconds
            for(int i=0; i<number.get(count); i++){
            Firework fire= fireworks.get(i);
            fire.draw();
           
            }
            UI.sleep(70);
            for(int i=0; i<number.get(count); i++){
            Firework fire1= fireworks.get(i);
            fire1.step();
            }
            
            // make all the fireworks take one step
            // COMPLETION
            // find out if ALL the fireworks have finished,
            // (if there is one firework that is not finished, then
            //  they are not all finished)
            boolean allFinished = true;
        
            for(int i=0; i<number.get(count); i++){
            Firework fire1= fireworks.get(i);
            if(fire1.isFinished()==false){
            allFinished=false;
            }
            
            }
       
        
            
            // COMPLETION
            // if all the fireworks have finished, create a new wave of new fireworks
            if(allFinished){
            fireworks.clear();
            count=count+1;
            number.add(this.numFireworks);
            for(int i=0; i<number.get(count); i++){
            Firework fire = new Firework();
            fireworks.add(fire);
            fire.draw();
            
            }
       
        }
    }
}

    public static void main(String[] args){
        new FireworksDisplay().setupGUI();
    }
}

