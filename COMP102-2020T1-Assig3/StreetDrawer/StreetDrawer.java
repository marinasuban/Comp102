// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 3
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: Subankamo
 * ID:300471606
 */

import ecs100.*;
import java.awt.Color;


public class StreetDrawer{
    /** CORE
     * Draws a street of houses
       * The street has at least four houses
       *All the houses on the street are identical
       *Every house has at least three windows
       *All the windows are the same .
    */
       
    /**method which should call the house drawing method at least four times to draw the houses.*/
    public void drawsStreet(){
    this.house(50,300,100,300);
    this.house(200,300,100,300);
    this.house(350,300,100,300);
    this.house(500,300,100,300);
    this.house(650,300,100,300);
    }
    /**a method to draw a house at a given position, which calls the window drawing method at least three times to draw the windows.*/
    public void house(double left, double top, double width,double height){
        double roofx= left + (width/2);
        double roofy= (top - (height/3));
        double roofx1= roofx + (width/2);
        double roofy1= (roofy + (height/3));
        UI.drawRect(left,top,width, height);
        UI.fillRect(left,top,width, height);
        UI.drawLine(left, top, roofx, roofy);
        UI.drawLine(roofx, roofy, roofx1, roofy1);
        double boarder=width/4;
        double floorheight=100;
        this.window((left+boarder), ((top)+boarder), 50, 50);
        this.window((left+boarder), ((top+(floorheight*1))+boarder), 50, 50);
        this.window((left+boarder), ((top+(floorheight*2))+boarder), 50, 50);
    }
    /**a method to draw a window at a given position and size.*/
    public void window(double left, double top, double width, double height){
        double winy=(top+(height/2));
        double winx=(left+width);
        double winx1=(left+(width/2));
        double winy1=(top+height);
        UI.setColor(Color.black);
        UI.drawRect(left, top, width, height);
        this.randomColor();
        UI.fillRect(left,top,width, height);
        UI.setColor(Color.black);
        UI.drawLine(left, winy, winx,winy);
        UI.drawLine(winx1, top, winx1,winy1);
    }
     /** COMPLETION
           * Draws a street of houses
           * Give the methods more parameters so that the houses and windows can be different sizes.
           *Make the houses more interesting (eg, put curtains on the windows, or have more than one window on each level....)
      */
     /**method which calls 4 houses of different sizes and shapes*/
    public void drawsRandomStreet(){
       this.drawsRandom(5);
    }
    /** CHALLENGE
     * Add some randomness to the construction, so that there are random variations in each house,
     */
    public void drawsRandom(int count){
       double start;
       double begin=30;
       for (int i=0; i<=count; i++){
        start=begin+(250*i);
        this.houseRandom(start,(180.0+Math.random()*200.0),(100.0+Math.random()*130.0),(200.0+Math.random()*200.0));
        }
    }
    /**a method to draw a house at a given position, which calls the window drawing method at least three times to draw the windows.*/
    public void houseRandom(double left, double top, double width,double height){
        /**house*/
        double roofx= left + (width/2);
        double roofy= (top - (height/3));
        double roofx1= roofx + (width/2);
        double roofy1= (roofy + (height/3));
        UI.setColor(Color.black);
        UI.drawRect(left,top,width, height);
        this.randomColor();
        UI.fillRect(left,top,width, height);
        UI.setColor(Color.black);
        UI.drawLine(left, top, roofx, roofy);
        UI.drawLine(roofx, roofy, roofx1, roofy1);
        /**challenge window: Add some randomness to the construction; Completion window: Make the houses more interesting*/
        double boarder1=width/4;
        double boarder2=width/10;
        double floorLevel=100;
        double numOfLevels=((height/floorLevel)-1);
        double floor;
        double roomLength=100;
        double numOfRooms=((width/roomLength)-1);
        double room;
        for (int i=0; i<numOfLevels; i++){
            floor= ((top+boarder2)+(floorLevel*i));
            for (int j=0; j<numOfRooms; j++){
                room=((left+boarder2)+((boarder2+roomLength)*j));
                if (numOfRooms>=1){
                    floor= ((top+boarder2)+(floorLevel*i));
                    this.window(room, floor, (width/6), (width/6));
        
                }
                if (numOfRooms<1){
                room=((left+boarder1)+((boarder2+roomLength)*j));
                this.window(room, floor, (width/2), (width/4));
                }
            }
        }
    }
    /** CHALLENGE
     * Enable the user to control the output
     */
    public void challenge(){
        UI.println("Number of houses must be between 1-3");
        int numberHouse = UI.askInt("How many neighbours will you have?");
        if (numberHouse>=0 && numberHouse<=3){
        this.drawsRandom(numberHouse);
    }
        else {
            UI.println("Invalid");
        }
    }
    /** Completion
     * Make the houses more interesting; random color
     */
    public void randomColor(){
        int R = (int)(0.0+Math.random()*256);
        int G = (int)(0.0+Math.random()*256);
        int B = (int)(0.0+Math.random()*256);
        Color color = new Color(R,G,B);
        UI.setColor(color);
    }
    public void setupGUI(){
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Street", this::drawsStreet);
        UI.addButton("Random", this::drawsRandomStreet);
        UI.addButton("Challenge", this::challenge);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0);
    }
}
