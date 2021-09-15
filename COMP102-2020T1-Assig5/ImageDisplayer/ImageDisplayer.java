// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 5
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: Subankamo
 * ID: 300471606
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Displays several kinds of images on the graphics panel:
 *  - colour gradients (vertical gradients and 2D gradients)
 *  - ppm images, the simplest possible colour image format.
 *  - any pnm (ppm, pgm, pbm) images, including animated images.
 */

public class ImageDisplayer{
    public static final double LEFT = 20;  // left edge of the image
    public static final double TOP = 20;   // top edge of the image
    public static final double GRAD_ROWS = 400;   // number of rows in the gradient
    public static final double GRAD_COLS = 400;   // number of columns in the gradient

    public void setupGUI(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Core: gradient", this::doCoreGradient);
        UI.addButton("Core: render", this::doCoreRender);
        UI.addButton("Compl: 2DGradient", this::doComplGradient);
        UI.addButton("Compl: PPM", this::doComplRender);
        UI.addButton("Chall: fullPPM", this::doChallRender);
        UI.addButton("Quit", UI::quit );
    }

    // CORE
    /**
     * drawVertGradient is passed the component values (red, green, blue)
     * for two colours.
     * It then draw a square image made of coloured pixels that is a
     * smooth gradient from the first colour along the top row to the second
     * colour along the bottom row.
     * On each row inbetween, the colour will need to make a small step
     * towards the target colour.
     * Note: The steps in the red, green, and blue components will be different,
     *       and depend on the number of row and the difference between the 
     *       top colour and the botton colour.
     * The image should be GRAD_ROWS x GRAD_COLS in size,
     *     and each pixel should be 1x1.
     * Hint: be careful with your division - use doubles!
     */
    public void drawVertGradient(double red1, double green1, double blue1, double red2, double green2, double blue2){
        double scaleRed=((red2-red1)/GRAD_ROWS);
        double scaleGreen=((green2-green1)/GRAD_ROWS);
        double scaleBlue=((blue2-blue1)/GRAD_ROWS);
        for(int i=0; i<GRAD_ROWS; i++){
            int r = (int)(red1+(scaleRed*i));
            int g = (int)(green1+(scaleGreen*i));
            int b = (int)(blue1+(scaleBlue*i));
            Color color = new Color(r,g,b);
            UI.setColor(color);
        
        for(int j=0; j<GRAD_COLS; j++){
            UI.fillRect(LEFT+j, TOP+ i, 1, 1);
        }
    }
    }

    /** 
     * Renders a 200x200 image from a file.
     * The file must contain exactly 200x200x3 integers:
     * three numbers (red, green, blue) for each pixel of the image.
     * the pixels are in row order - all the pixels for the first row,
     * from left to right, followed by all the pixels for the second row,
     * etc.
     * Each pixel should be drawn as a 1x1 square.
     * Asks for the name of the file and opens a Scanner on the file,
     * then reads three numbers at a time from the scanner, and draws the pixel.
     */
    public void render200x200Image(String fname){
        try{
            Scanner scan=new Scanner(Path.of(fname));
            while(scan.hasNext()){
            for(int i=0; i<200; i++){
          
            for(int j=0; j<200; j++){
            int r=scan.nextInt();
            int g=scan.nextInt();
            int b=scan.nextInt();
            Color color = new Color(r,g,b);
            UI.setColor(color);
            UI.fillRect(LEFT+j, TOP+i, 1, 1);
            }
            
            }
            break;
            }
        }catch(IOException e){UI.println("File error:" + e);}
    }

    // COMPLETION
    /**
     * draw2DGradient is passed the component values (red, green, blue)
     * for two colours.
     * It then draw an image made of coloured squares that is a
     * smooth 2D gradient from the first colour at the top left corner
     * to the second colour at the bottom right.
     * The red component of the colour should move smoothly from the first red value
     *  on the top row to the second red value on the last row
     * The blue component of the colour should move smoothly from the first blue value
     *  on the left column to the second blue value on the rightmost column
     * The green component of the colour should move smoothly from the first green value
     *  in the top-left corner to the second green value in the bottom-right corner.
     * The image should be GRAD_ROWS x GRAD_COLS in size, and each square should be 1x1.
     */
    public void draw2DGradient(double red1, double green1, double blue1, double red2, double green2, double blue2){
            double scaleRed=((red2-red1)/GRAD_ROWS);
            double scaleGreen=((green2-green1)/GRAD_ROWS);
            double scaleBlue=((blue2-blue1)/GRAD_COLS);
            int count=0;
            for(int i=0; i<GRAD_ROWS; i++){
            int r = (int)(red1+(scaleRed*i));
            int g = (int)(green1+(scaleGreen*i));
            for(int j=0; j<GRAD_COLS; j++){
            int b = (int)(blue1+(scaleBlue*j));
            int g1 = (int)(green1+(scaleGreen*j));
            int g2 = (g1+g)/2;
            Color color = new Color(r,g2,b);
            UI.setColor(color);
            UI.fillRect(LEFT+j, TOP+ i, 1, 1);
            }
            
            }
            
    }

    /**
     * Renders a ppm image file, given an open Scanner 
     * Renders the image at position (LEFT, TOP).
     * Each pixel of the image is rendered by a square of size PIXEL_SIZE
     * Assumes that
     * - the colour depth is 255,
     * - there is just one image in the file (not "animated"), and
     * - there are no comments in the file.
     * The first four tokens are "P3", number of columns, number of rows, 255
     * The remaining tokens are the pixel values (red, green, blue for each pixel)
     * Hint: to draw the pixels, it is probably easiest to have a nested for loop
     *  that will repeatedly
     *  - read the next three numbers from the scanner
     *  - construct the Color and set UI's color
     *  - draw the pixel.
     *  (ie, don't construct an ArrayList of numbers).
     */
    public void renderPPMImage(String fname){
       try{
            Scanner scan=new Scanner(Path.of(fname));
            while(scan.hasNext()){
                String file=scan.nextLine();
                int columns=scan.nextInt();
                int row=scan.nextInt();
                int colorDepth=scan.nextInt();
            if (!file.equals("P3")){
                UI.println("Invalid file");
                return;
            }
            else{
            for(int i=0; i<row; i++){
          
            for(int j=0; j<columns; j++){
            int r=scan.nextInt();
            int g=scan.nextInt();
            int b=scan.nextInt();
            Color color = new Color(r,g,b);
            UI.setColor(color);
            UI.fillRect(LEFT+j, TOP+i, 1, 1);
            }
            
            }
        }
            }
        }catch(IOException e){UI.println("File error:" + e);}
    }

    



    /** Challenge
     * Renders a pnm image file which
     *  may be a ppm, pgm, or pbm file
     *  may have comments in header (which it should ignore)
     *  may have a colour depth other than 255,
     *   (in which case, it scales the colour values appropriately
     *  may be animated (multiple images in the file)
     *   (in which case, it renders each image in the file in turn with
     *    200 mSec delay between, and repeats the sequence 3 times.
     */
    public void renderPNM(String fname){
       for(int k=0; k<3; k++){
       try{
            Scanner scan=new Scanner(Path.of(fname));     
            while(scan.hasNext()){
                String file=scan.next();
                int columns=scan.nextInt();
                int row=scan.nextInt();
                int colorDepth=scan.nextInt();
                if (file.equals("P1")){
                    for(int i=0; i<row; i++){
                        for(int j=0; j<columns; j++){
                int num=scan.nextInt();
                if (num==1){
                UI.setColor(Color.black);
                UI.fillRect(LEFT+j, TOP+i, 1, 1);
                }
                else{
                UI.setColor(Color.white);
                UI.fillRect(LEFT+j, TOP+i, 1, 1);
                }
                }
                
            }
        }
        
          if (file.equals("P2")){
            for(int i=0; i<row; i++){
          
            for(int j=0; j<columns; j++){
            int scale=scan.nextInt();
            int r= scale;
            int g=scale;
            int b=scale;
            Color color = new Color(r,g,b);
            UI.setColor(color);
            UI.fillRect(LEFT+j, TOP+i, 1, 1);
            }
            
        }}
        
            if (file.equals("P3")){
            for(int i=0; i<row; i++){
          
            for(int j=0; j<columns; j++){
            int r=(scan.nextInt()*255)/colorDepth;
            int g=(scan.nextInt()*255)/colorDepth;
            int b=(scan.nextInt()*255)/colorDepth;
            Color color = new Color(r,g,b);
            UI.setColor(color);
            UI.fillRect(LEFT+j, TOP+i, 1, 1);
            }
            
            }
        }
       UI.sleep(200);
    }

        }catch(IOException e){UI.println("File error:" + e);}
    }}

    /** method for button, calling drawVertGradient. */
    public void doCoreGradient(){
        UI.println("Top color:");
        double red1 = this.askComponent("red");
        double grn1 = this.askComponent("green:");
        double blu1 = this.askComponent("blue:");
        UI.println("Bottom color");
        double red2 = this.askComponent("red:");
        double grn2 = this.askComponent("green:");
        double blu2 = this.askComponent("blue:");
        this.drawVertGradient(red1, grn1, blu1, red2, grn2, blu2);
    }

    /** method for button, calling renderImage. */
    public void doCoreRender(){
        this.render200x200Image(UIFileChooser.open("Name of 200x200 file to render"));
    }

    /** method for button, calling draw2DGradient. */
    public void doComplGradient(){
        UI.println("Top Left color:");
        double red1 = this.askComponent("red:");
        double grn1 = this.askComponent("green:");
        double blu1 = this.askComponent("blue:");
        UI.println("Bottom Right color:");
        double red2 = this.askComponent("red:");
        double grn2 = this.askComponent("green:");
        double blu2 = this.askComponent("blue:");
        this.draw2DGradient(red1, grn1, blu1, red2, grn2, blu2);
    }

    /** method for button, calling renderImage. */
    public void doComplRender(){
        this.renderPPMImage(UIFileChooser.open("Name of ppm file to render"));
    }

    /** method for button, calling renderImage. */
    public void doChallRender(){
        this.renderPNM(UIFileChooser.open("Name of ppm/pgm/pbm file to render"));
    }

    /** ask for a colour component, ensuring it is between 0 and 255, (inclusive) */
    public double askComponent(String prompt){
        int ans = UI.askInt(prompt);
        return (double)Math.max(0, Math.min(255, ans));
    }

    public static void main(String[] args){
        ImageDisplayer ir = new ImageDisplayer();
        ir.setupGUI();
    }

}
