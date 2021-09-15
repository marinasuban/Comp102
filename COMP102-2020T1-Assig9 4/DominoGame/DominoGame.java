// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 9
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: SubanKamo
 * ID: 300471606
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;


/**
 *  Lets a player play a simple Solitaire dominoes game.
 *  Dominoes are rectangular tiles with two numbers from 0 to 6 on
 *  them (shown with dots).
 *  The player has a "hand" which can contain up to six dominoes.
 *  They can reorder the dominoes in their hand, they can place dominoes
 *  from their hand onto the table, and they can pick up more dominoes from
 *  a bag to fill the gaps in their "hand".
 *  The core and completion do not involve any of the matching and scoring
 *  of real dominoes games. 
 *
 *  PROGRAM DESIGN
 *  The dominoes are represented by objects of the Domino class.
 *  The Domino constructor will construct a new, random domino.
 *  Dominos have a draw(double x, double y) method that will draw the
 *  Domino on the graphics pane at the specified position.
 *  
 *  The program has two key fields:
 *    hand:  an array that can hold 6 Dominos. 
 *    table: an ArrayList of the Dominos that have been placed on the table.
 *    
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each domino drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected domino (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected domino
 *  (or the space) to the left or the right, in which case the domino is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a domino, the user
 *  can use the "Place" button to move the selected domino to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) domino which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of dominos.
 *  At the beginning of the game the table should be empty.
 *  Dominos should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class DominoGame{
    public static final int NUM_HAND = 6;    // Number of dominos in hand

    // Fields: hand, table and selectedPos
    private Domino[] hand= new Domino[NUM_HAND];
    private ArrayList<Domino> table=new ArrayList <Domino>();

    private int selectedPos = 0;      //  selected position in the hand.

    // (You shouldn't add any more fields for core or completion)

    // constants for the layout
    public static final int HAND_LEFT = 60; // x-position of the leftmost Domino in the hand
    public static final int HAND_TOP = 5;   // y-Position of all the Dominos in the hand 
    public static final int DOMINO_SPACING = 104; 
    //spacing is the distance from left side of Domino to left side of next domino
    public static final int DOMINO_HEIGHT = 50; 

    public static final int TABLE_LEFT = 10;                
    public static final int TABLE_TOP = 120;   

    public void setupGUI(){
        UI.setMouseListener(this::doMouse);
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Pickup", this::pickup);
        UI.addButton("Place", this::placeDomino);
        UI.addButton("Flip", this::flipDomino);
        UI.addButton("Left", this::moveLeft);
        UI.addButton("Right", this::moveRight);
        UI.addButton("Suggest", this::suggestDomino);
        UI.addButton("Restart", this::restart);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(1100,500);
        this.redraw();
    }   

    /**
     * Restart the game:
     *  set the table to be empty,
     *  set the hand to have no dominos
     */
    public void restart(){
        this.table = new ArrayList<Domino>();  // or  this.table.clear();
        /*# YOUR CODE HERE */
        this.hand=new Domino[NUM_HAND];
        
        this.redraw();
    }

    /**
     * If there is at least one empty position on the hand, then
     * create a new random domino and put it into the first empty
     * position on the hand.
     * (needs to search along the array for an empty position.)
     */
    public void pickup(){
        for(int i=0; i<NUM_HAND; i++){
        if(hand[i] ==null){
        hand[i]=new Domino();
        i=NUM_HAND;
        }
        }
        this.redraw();
    }

    /**
     * Move domino from selected position on hand (if there is domino there) to the table
     * The selectedPos field contains the index of the selected domino.
     */
    public void placeDomino(){
        table.add(hand[selectedPos]);
        hand[selectedPos] = null;
        this.redraw();
        UI.println(table);
    }

    /**
     * If there is a domino at the selected position in the hand, 
     * flip it over.
     */
    public void flipDomino(){
        if(hand[selectedPos]!=null){
        hand[selectedPos].flipNums();
        }
        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the domino 
     */
    public void moveLeft(){
        if(selectedPos!=0){
        Domino hold=hand[selectedPos-1];
        hand[selectedPos-1]=hand[selectedPos];
        hand[selectedPos]=hold;
        selectedPos = selectedPos-1;
        }

        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the domino 
     */
    public void moveRight(){
       if(selectedPos!=5){
        Domino hold=hand[selectedPos+1];
        hand[selectedPos+1]=hand[selectedPos];
        hand[selectedPos]=hold;
        selectedPos = selectedPos+1;
        }

        this.redraw();
    }

    /**
     * If the table is empty, only a double (left and right the same) can be suggested.
     * If the table is not empty, see if one domino has a number that matches the right 
     *    number of the last domino on the table.
     */
    public void suggestDomino(){
        double count=0;
        for(int i=0; i<NUM_HAND; i++){
            while(hand[i]==null){
               if (i<5){
                   count=count+1;
                   i=i+1;
                }        
            }
            
            if(table.size()==0){
                if(hand[i].getLeftNum() == hand[i].getRightNum()){
                    selectedPos=i;
                    this.redraw();
                    break;
                }
                else{
                    count=count+1;
                    if (count>5){
                        count=0;
                        UI.println("No suggestion avaliable");
                        
                    }
                }
            }  
            else{
                if((hand[i].getLeftNum() == table.get(table.size()-1).getRightNum() || hand[i].getRightNum() == table.get(table.size()-1).getRightNum()))
                {
                    selectedPos=i;
                    this.redraw();
                    break;
                }
                else{
                    count=count+1;
                    if (count>5){
                        count=0;
                        UI.println("No suggestion avaliable");
                        
                    }
                }
            }
            
        }
    
        
    }

    // Methods for displaying and the mouse.  ALL WRITTEN FOR YOU

    /**
     * Draws the outline of the hand,
     * draws all the Dominos in the hand,
     * highlights the selected position in some way
     * This needs to use the constants:
     *   DOMINO_SPACING, DOMINO_HEIGHT, HAND_LEFT, HAND_TOP
     */
    public void drawHand(){
        for (int t=0; t<this.hand.length; t++){
            if (this.hand[t] != null){
                int left = HAND_LEFT + t*(DOMINO_SPACING);
                this.hand[t].draw(left, HAND_TOP);
            }
        }
        // outline the hand and the selected position
        UI.setLineWidth(2);
        UI.setColor(Color.black);
        UI.drawRect(HAND_LEFT-4, HAND_TOP-4, (DOMINO_SPACING)*NUM_HAND+8, DOMINO_HEIGHT+8);
        UI.setLineWidth(2);
        UI.setColor(Color.green);
        int selLeft = HAND_LEFT + (this.selectedPos * (DOMINO_SPACING)) - 2;
        UI.drawRect(selLeft, HAND_TOP - 2, DOMINO_SPACING, DOMINO_HEIGHT+4);

    }

    /**
     * Draws the list of Dominos on the table, 7 to a row
     * Note, has to wrap around to a new row when it gets to the
     * edge of the table
     * This needs to use the constants:
     *   DOMINO_SPACING, DOMINO_HEIGHT, TABLE_LEFT, TABLE_TOP
     */
    public void drawTable(){
        int left = TABLE_LEFT;
        int top = TABLE_TOP;
        int count = 0;
        for (Domino domino : this.table){
            domino.draw(left, top);
            left = left + DOMINO_SPACING;
            count++;
            if (count == 7){
                left = TABLE_LEFT;
                top = top + DOMINO_HEIGHT+10;
                count = 0;
            }
        }

    }

    /** Allows the user to select a position in the hand using the mouse.
     * If the mouse is released over the hand, then sets  selectedPos
     * to be the index into the hand array.
     * Redraws the hand and table */
    public void doMouse(String action, double x, double y){
        if (action.equals("released")){
            if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.clearText();UI.println("selected "+this.selectedPos);
                this.redraw();
            }
        }
    }

    /**
     *  Redraw the table and the hand.
     */
    public void redraw(){
        UI.clearGraphics();
        this.drawHand();
        this.drawTable();
    }

    public static void main(String[] args){
        DominoGame obj = new DominoGame();
        obj.setupGUI();
        obj.restart();
    }   
}
