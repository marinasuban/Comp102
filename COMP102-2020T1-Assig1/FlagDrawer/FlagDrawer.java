// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/**
 * Draws various flags
 *
 * You can find lots of flag details (including the correct dimensions and colours)
 * from  http://www.crwflags.com/fotw/flags/    
 */

public class FlagDrawer{

    public static final double left = 100;  // the left side of the flags
    public static final double top = 50;    // the top of the flags

    /**   CORE
     * Draw the flag of Germany.
     * The flag has three horizontal stripes;
     * The top is black, the middle is red, and the bottom is yellow.
     * The flag is 3/5 as high as it is wide (ratio 3:5).
     */
    public void drawGermanFlag(){
        UI.clearGraphics();
        UI.println("German Flag");
        double width = UI.askDouble("How wide: ");
        double height = (width/5);
        /*# YOUR CODE HERE */
        UI.setColor(Color.black ); 
        UI.drawRect(left, top, width, height);
        UI.fillRect(left, top, width, height );
        UI.setColor(Color.red ); 
        UI.drawRect(left, (top+(height)), width, height );
        UI.fillRect(left, (top+(height)), width, height );
        UI.setColor(Color.yellow ); 
        UI.drawRect(left, (top+(height*2)), width, height );
        UI.fillRect(left, (top+(height*2)), width, height );

    }

    /** CORE
     *  The flag for Norway is a red rectangle with
     *  a white cross containing a thinner blue cross
     *  slightly off-set to the left-hand side;
     */
    public void drawNorwayFlag() {
        UI.clearGraphics();
        UI.println("Norway Flag");
        double width = UI.askDouble("How wide: ");
        double height = (width/1.375);
        /*# YOUR CODE HERE */
        UI.setColor(Color.red);
        UI.drawRect(left, top, width, height );
        UI.fillRect(left, top, width, height );
        UI.setColor(Color.white);
        UI.drawRect(left, (top + (height*0.375)), width, (height*(0.25)));
        UI.fillRect(left, (top + (height*0.375)), width, (height*(0.25)));
        UI.drawRect((left + (width*(6.0/22.0))), top, (width*(4.0/22.0)), height);
        UI.fillRect((left + (width*(6.0/22.0))), top, (width*(4.0/22.0)), height);
        UI.setColor(Color.blue.darker());
        UI.drawRect(left, (top+(height*(7.0/16.0))), width, (height*(2.0/16.0)));
        UI.fillRect(left, (top+(height*(7.0/16.0))), width, (height*(4.0/16.0)));
        UI.drawRect((left+(width*(7.0/22.0))), top, (width*(2.0/22.0)), height );
        UI.fillRect((left+(width*(7.0/22.0))), top, (width*(2.0/22.0)), height );
        
    }

    /** COMPLETION
     *  Pacman
     *  A red pacman looking up on a black background chasing yellow, green, and blue dots.
     */
    public  void drawPacman() {
        UI.clearGraphics();        
        UI.println("Pacman Flag");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */
        UI.setColor(Color.black);
        UI.drawRect(100, 100, 200, 300 );
        UI.fillRect(100, 100, 200, 300 );
        UI.setColor(Color.red);
        UI.drawOval(150, 270, 100, 100 );
        UI.fillOval(150, 270, 100, 100 );
        UI.setColor(Color.black);
        UI.fillArc(150,265,100,100,50,80);
        UI.setColor(Color.yellow);
        UI.drawOval(190, 240, 20, 20 );
        UI.fillOval(190, 240, 20, 20 );
        UI.setColor(Color.green);
        UI.drawOval(190, 190, 20, 20 );
        UI.fillOval(190, 190, 20, 20 );
        UI.setColor(Color.blue);
        UI.drawOval(190, 140, 20, 20 );
        UI.fillOval(190, 140, 20, 20 );
        
    }

    /** COMPLETION
     *  The Czech flag
     *  The flag is 2/3 as high as it is wide (ratio 2:3).
     *  Two horizontal stripes (white and red) with a blue isoceles triangle on the left side.
     *  Note: there is no fillTriangle method in the UI class! Maybe you could use arcs?
     */
    public  void drawCzechFlag(){
        UI.clearGraphics();        
        UI.println("Czech Flag");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */
        UI.setColor(Color.blue.darker());
        UI.drawRect(100, 100, 240, 160 );
        UI.fillRect(100, 100, 240, 160 );
        UI.setColor(Color.red);
        UI.drawRect(220, 180, 120, 80 );
        UI.fillRect(220, 180, 120, 80 );
        UI.fillArc(-49,111,300,300,0,33.69);
        UI.setColor(Color.white);
        UI.drawRect(220, 100, 120, 80 );
        UI.fillRect(220, 100, 120, 80 );
        UI.fillArc(-51,-52,300,300,0,-33.69);

    }

    /**  CHALLENGE
     *  The Jamaican flag has a yellow diagonal cross with 
     *  green triangles top and bottom, and black triangles left and right.
     */
    public void drawJamaicaFlag(){
        UI.clearGraphics();
        UI.println("Flag of Jamaica");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */

    }

    /**   CHALLENGE
     * The 3 stars flag has a blue vertical stripe on the left and black
     * vertical stripe on the right and 3 red 5 pointed stars in the middle stripe
     * The  height is 2/3 of the width,
     * A full marks solution will have a method for drawing a 5 pointed star,
     * and call that method for each of the stars
     */
    public void drawThreeStarsFlag() {
        UI.clearGraphics();        
        UI.setColor(Color.blue ); 
        UI.drawRect(100, 100, 60, 90 );
        UI.fillRect(100, 100, 60, 90 );
        UI.setColor(Color.white ); 
        UI.drawRect(160, 100, 60, 90 );
        UI.fillRect(160, 100, 60, 90 );
        UI.setColor(Color.black ); 
        UI.drawRect(220, 100, 60, 90);
        UI.fillRect(220, 100, 60, 90 );
        UI.setColor(Color.red ); 
      

    }


    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Core: Flag of Germany", this::drawGermanFlag);
        UI.addButton("Core: Flag of Norway",  this::drawNorwayFlag);
        // COMPLETION
        UI.addButton("Completion: Pacman Flag", this::drawPacman);
        UI.addButton("Completion: Czech Flag", this::drawCzechFlag);
        // CHALLENGE
        UI.addButton("Challenge: Flag of Jamaica", this::drawJamaicaFlag);
        UI.addButton("Challenge: Three stars flag", this::drawThreeStarsFlag);
        UI.addButton("Quit", UI::quit);

        UI.setDivider(0.3);
    }


}
