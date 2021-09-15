// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 7
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username:Subankamo
 * ID:300471606
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JButton;


/**
 * A simple drawing program.
 * The user can select from a variety of tools and options using the buttons and
 *   elements down the left side, and can use the mouse to add elements to the drawing
 *   according to the current tool and options
 * Note, most of the "action" in the program happens in response to mouse events;
 *   the buttons, textFields, and sliders mostly record information that is used
 *   later by the mouse responding.
 */


public class MiniPaint{

    // fields to remember:
   private String toolNext; //  - the "tool" - what will be drawn when the mouse is next released.
    //                 may be a shape, or an image, or a caption,
    //    [Completion] or freehand, or eraser
    private String fill="No";//  - whether the shape should be filled or not
    private double x1;//  - the position the mouse was pressed on X axis,
    private double y1;//  - the position the mouse was pressed on Y axis,
    private String text;//  - the string for the text caption
    private double size;//  - the width of the lines and the font size of the text captions.
    private String imgName;//  - [Completion] the name of the image file
    private Color currentColor; //  - [Completion] the colors for the border and fill for shapes and captions

    private String tool="Line";   // the current tool, governing what the mouse will do.
                                    // Initial value is "Line";  changed by the buttons.

    // More fields
    private double x2;
    private double y2;
    private double sizeF;
    private Color currentColor1;
    
    private JButton fillstate;
    private JButton linecol;
    private JButton fillcol;
    private boolean rubberbanding = false;
    private boolean draw_final = false;
    private boolean refresh = true;
    private double x3;
    private double y3;
    /**
     * Set up the interface: buttons, textfields, sliders,
     * listening to the mouse
    */
    public void setupGUI(){
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Line", this::doSetLine); 
        /*# YOUR CODE HERE */
        UI.setMouseListener(this::doMouse);
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Rectangle", this::doSetRect);
        UI.addButton("Oval", this::doSetOval);
        UI.addTextField( "Caption", this::doCaption);
        fillstate=UI.addButton("Fill", this::doFill);
        UI.addSlider( "Line Width", 1, 20, 1, this::setSize);
        UI.addSlider( "Font Size", 8, 40, 8, this::setSize1);
        linecol=UI.addButton( "line Color", this::setLineColor);
        linecol.setBackground(currentColor);
        linecol.setOpaque(true);
        fillcol=UI.addButton( "Fill Color", this::setLineColor1);
        fillcol.setBackground(currentColor1);
        fillcol.setOpaque(true);
        UI.addButton( "Image", this::setImg);
        UI.addButton( "Freehand", this::doFreehand);
        UI.addButton( "Erase", this::doErase);
        
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);  // Hide the text area.
        
    }   

    /** Respond to the Line button */
    public void doCurve(){
        this.tool="Curve";
    }
    public void doSetLine(){
        this.tool="Line";
    }
    public void doSetRect(){
        this.tool="Rect";
    }
    public void doSetOval(){
        this.tool="Oval";
    }
    public void doCaption(String text){
        this.tool="Caption";
        this.text=text;
    }
    public void doFill(){
        if (this.fill.equals("No")){
        this.fill="Yes";
        fillstate.setBackground(Color.black);
        fillstate.setOpaque(true);
        }
        else if (this.fill.equals("Yes")){
        this.fill="No";
        fillstate.setOpaque(false);
        }
    }
    public void setSize(double size){
    this.size=size;
    }
    public void setSize1(double size1){
    this.sizeF=size1;
    }
    public void setLineColor(){
        this.currentColor = JColorChooser.showDialog(null,"Choose colour", this.currentColor);
        linecol.setBackground(currentColor);  
    }
    public void setLineColor1(){
        this.currentColor1 = JColorChooser.showDialog(null,"Choose colour", this.currentColor);
        fillcol.setBackground(currentColor1);
    }
    public void setImg(){
        this.tool="Image";
        this.imgName=UIFileChooser.open();
    }
    public void doFreehand(){
        this.tool="Freehand";
    }
    public void doErase(){
        this.tool="Erase";
    }
    
    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current tool
     * Uses the value stored in the field to determine which kind of tool to draw.
     *  It should call the drawALine, drawARectangle, drawAnOval, etc, methods
     *  passing the pressed and released positions
     * [Completion] should respond to "dragged" events also to do erasing and freehand drawing
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("released")){
            this.x2 = x;
            this.y2 = y;
        if(this.tool.equals("Line")){
        this.drawALine(this.x1, this.y1, this.x2, this.y2);
        }
        else if(this.tool.equals("Rect")){
        this.drawARectangle(this.x1, this.y1, this.x2, this.y2);
        }
        else if(this.tool.equals("Oval")){
        this.drawAnOval(this.x1, this.y1, this.x2, this.y2);
        }
         else if(this.tool.equals("Caption")){
        this.drawCaption(this.x1, this.y1);
        }
        else if(this.tool.equals("Image")){
        this.drawAnImage(this.x1, this.y1, this.x2, this.y2);
        }
        
        }
        else if (action.equals("pressed")){
            this.x1 = x;
            this.y1 = y;
            
        }
        if(this.tool.equals("Freehand")||this.tool.equals("Erase")){
            if (action.equals("dragged")){
                this.x1 = x;
                this.y1 = y;
                if(this.tool.equals("Freehand")){
                    this.Freehand(this.x1, this.y1);}
                    else if(this.tool.equals("Erase")){
                        this.Erase(this.x1, this.y1);}
                    }
                }
        if (action.equals("dragged")){
                  this.x3=x;
                  this.y3=y;
                  if(this.tool.equals("Line")){
                      this.drawALine(this.x1, this.y1, this.x3, this.y3);
                      UI.sleep(7);
                      UI.invertLine(this.x1, this.y1, this.x3, this.y3);
                    }   
                  else if(this.tool.equals("Rect")){
                      this.drawARectangle(this.x1, this.y1, this.x3, this.y3);
                      UI.sleep(7);
                      UI.invertRect(this.x1, this.y1,this.x3-this.x1, this.y3-this.y1);
                    }  
                    else if(this.tool.equals("Oval")){
                      this.drawAnOval(this.x1, this.y1, this.x3, this.y3);
                      UI.sleep(7);
                      UI.invertOval(this.x1, this.y1,this.x3-this.x1, this.y3-this.y1);
                    }  
        }
        
    }
  
    /**
     * Draw a line between the two positions (x1, y1) and (x2, y2)
     */
    public void drawALine(double x1, double y1, double x2, double y2){
       UI.setLineWidth(this.size);
       UI.setColor(this.currentColor);
       UI.drawLine(x1, y1, x2, y2);

    }
    public void Freehand(double x1, double y1){
    UI.setColor(this.currentColor);
    UI.setLineWidth(this.size);
    UI.drawLine(x1, y1, x1, y1);
    }
    public void Erase(double x1, double y1){
    UI.eraseOval(x1, y1, this.size, this.size);
    }
    /**
     * Draw a rectangle between the two diagonal corners
     * [Completion] Works out the left, top, width, and height 
     * Then draws the rectangle, based on the options
     */
    public void drawARectangle(double x1, double y1, double x2, double y2){
        double height=y2-y1;
        double width=x2-x1;
        double left=x1;
        double top=y1;
        UI.setLineWidth(this.size);
        if (this.fill.equals("No")){
        UI.setColor(this.currentColor);
        UI.drawRect(left, top, width, height);}
        else if (this.fill.equals("Yes")){
        UI.setColor(this.currentColor1);
        UI.fillRect(left, top, width, height);
        UI.setColor(this.currentColor);
        UI.drawRect(left, top, width, height);
        }
    }

    /**
     * Draw an oval to fit the rectangle between the the two diagonal corners
     * [Completion] Works out the left, top, width, and height 
     * Then draws the oval, based on the options
     */
    public void drawAnOval(double x1, double y1, double x2, double y2){
        double height=y2-y1;
        double width=x2-x1;
        double left=x1;
        double top=y1;
        UI.setLineWidth(this.size);
        if (this.fill.equals("No")){
        UI.setColor(this.currentColor);
        UI.drawOval(left, top, width, height);}
        else if (this.fill.equals("Yes")){
        UI.setColor(this.currentColor1);
        UI.fillOval(left, top, width, height);
        UI.setColor(this.currentColor);
        UI.drawOval(left, top, width, height);
        }
    }
    
    /** 
     * Draws the current caption at the mouse released point.
     */
    public void drawCaption(double x, double y){
       UI.setFontSize(this.sizeF);
       UI.setColor(this.currentColor);
       UI.drawString(this.text, x,y);
    }


    /** [Completion]
     * Draws the current image between the two diagonal corners, unless
     *  they are very close, and then just draws the image at its natural size
     *  Works out the left, top, width, and height 
     * Then draws the image, if there is one.
     */
    public void drawAnImage(double x1, double y1, double x2, double y2){
        double height=y2-y1;
        double width=x2-x1;
        double left=x1;
        double top=y1;
        if (height<=5 || width<=5){
        UI.drawImage(this.imgName, left, top);
        } 
        else{
        UI.drawImage(this.imgName, left, top, width, height);
        }

        }

    // Main:  constructs a new MiniPaint object and set up GUI
    public static void main(String[] arguments){
        MiniPaint mp = new MiniPaint();
        mp.setupGUI();
    }


}
