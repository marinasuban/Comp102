// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 3
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: Subankamo
 * ID: 300471606 
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;

/**
 * The program contains methods which analyse and plot hourly Nitrogen Dioxide
 * air pollution levels over the course of a day.
 * There are several things about the pollution level that
 * a user may be interested in:
 *    The average, maximum, and minimum pollution levels during the day.
 *    Any times when the pollution level went over the danger threshold.
 *    A plot of the values
 *    Whether the pollution level was over the danger threshold for more than 50% of the day.
 */
public class PollutionAnalyser {

    //Constants
    public static final int NO2_DANGER_LEVEL = 120;

    public final int BASE_OF_PLOT = 450; // base of the bars on the plot
    public final int LEFT_OF_PLOT = 50;  // left side of the plot
    public final int STEP = 25;          // distance between plotted points
    public static int MAX_VALUE = 400;   // maximum height of a bar in the graph

    /**
     * analyse() method reads a sequence of levels from the user
     * and calls 6 methods which each do one part of the analysis:
     * See the assignment instructions
     */
    public void analyse() {
        UI.clearPanes();
        // Reads a sequence of levels from the user,
        ArrayList<Double> levels = UI.askNumbers("Enter levels, end with 'done': ");
        if (levels.size() == 0) {
            UI.println("No readings");
        }
        else {
            this.printAverageLevel(levels);
            this.printDangerousLevels(levels);
            this.plotLevels(levels);
            UI.printf("Maximum level was:  %.0f ppb\n", this.maximumLevel(levels));
            UI.printf("Minimum level was:  %.0f ppb\n", this.minimumLevel(levels));
            this.medianLevel(levels);
            this.reportDangerPeriod(levels);
        }
    }

    /**
     * CORE
     * Calculate and print the average of the pollution levels
     *  There is guaranteed to be at least one level,
     */
    public void printAverageLevel(ArrayList<Double> levels) {
        double total=0;
        double total1=0;
        for(double num: levels){
            total=total + num;
            total1=(total/levels.size());
        }
        UI.println("Average pollution level: " + total1);
    }

    /**
     * CORE
     * Find and print out all the readings where the pollution level is above the danger threshold (120 ppb).
     * then print out the percentage of readings that were above the danger threshold.
     * - may assume there is at least one level in the list.
     * - The method will need a variable to keep track of the counts of danger levels, which
     *   needs to be initialised to an appropriate value.
     */
    public void printDangerousLevels(ArrayList<Double> levels) {
        double danger=0;
        double count;
        for (double read: levels){
            if(read>120){
                UI.printf(read + " is dangerous");
                danger=danger+1;
                UI.println();
    }
}
    count=((danger/levels.size())*100);
    UI.println(count +"% of reading were dangerous levels");
}

    /**
     * CORE
     *   Plots a bar graph of the sequence of levels,
     *     using narrow rectangles whose heights are equal to the level.
     *   Draws a horizontal line for the x-axis (or baseline) without any labels.
     * COMPLETION
     *   Any level greater than NO2_DANGER_LEVEL should be plotted in red rather than blue
     *   Any level greater than MAX_VALUE (400) should be plotted as if it were just 400,
     *     putting an asterisk ("*") above it to show that it has been cut off.
     */
    public void plotLevels(ArrayList<Double> levels) {
        int SIZE=25;
        int box=1;
        int y1=BASE_OF_PLOT-MAX_VALUE;
        String asterix="*";
        for(double length: levels){
        int x= LEFT_OF_PLOT + box*SIZE + box*STEP;
        double y=BASE_OF_PLOT-length;
        box=box+1;
        if ((length>NO2_DANGER_LEVEL) && (length<MAX_VALUE)){
            UI.setColor(Color.red); 
            UI.fillRect(x,y, SIZE, length);
        }
        if (length<=NO2_DANGER_LEVEL){
            UI.setColor(Color.blue); 
            UI.fillRect(x,y, SIZE, length);
        }
        if (length>=MAX_VALUE){
            UI.setColor(Color.red); 
            UI.fillRect(x,y1, SIZE, MAX_VALUE);
            UI.drawString(asterix, (x+5), (y1-10));
            
        }
        }
        
        // ---------Challenge; --------------------------
        /**Extend the plotLevels method to draw both an x-axis and a y-axis, 
         * and put labels on them, roughly every 50 pixels.
        */
        int x1= LEFT_OF_PLOT + ((SIZE+STEP)*box);
        int y2= BASE_OF_PLOT - (y1);
        UI.setLineWidth(2);
        UI.setColor(Color.black);
        UI.drawLine(LEFT_OF_PLOT, BASE_OF_PLOT, x1, BASE_OF_PLOT);
        UI.drawLine(LEFT_OF_PLOT, BASE_OF_PLOT, LEFT_OF_PLOT, y1);
        double plot=(x1/50);
        double xAxis;
        double plot1=(y2/50);
        double yAxis;
        for (int i=0; i<plot; i++){
        xAxis=LEFT_OF_PLOT+(50*i);
        UI.drawLine(xAxis, BASE_OF_PLOT, xAxis, (BASE_OF_PLOT+10));
        }
        for (int j=0; j<=plot1; j++){
        yAxis=BASE_OF_PLOT-(50*j);
        UI.drawLine(LEFT_OF_PLOT,yAxis,(LEFT_OF_PLOT-10),yAxis);
        }
        UI.setLineWidth(2);
        UI.setColor(Color.red);
        UI.drawLine(LEFT_OF_PLOT, (BASE_OF_PLOT-NO2_DANGER_LEVEL), x1, (BASE_OF_PLOT-NO2_DANGER_LEVEL));   
   }
   
    public void medianLevel(ArrayList<Double> levels){
       Collections.sort(levels);
       double middle;
       if (levels.size()%2==1){
       middle=(levels.get((levels.size()+1)/2-1));
       UI.println("the median is:" + middle);
    }
       else{
           double lower=levels.get((levels.size()/2)-1);
           double upper=levels.get(levels.size()/2);
           middle = ((lower+upper)/2.0);
           UI.println("the median is:" + middle);
      }
    }


    // ---------COMPLETION --------------------------

    /**
     * COMPLETION
     * Find and return the maximum level in the list
     *  - There is guaranteed to be at least one level,
     *  - The method will need a variable to keep track of the maximum, which
     *    needs to be initialised to an appropriate value.
     */
    public double maximumLevel(ArrayList<Double> levels) {
        double previous=0;
        for(double max: levels){
            if(previous<=max){
                previous = max;
        }
        if(previous>max){
               previous=previous;
                
            }
        }
        return previous;
    }

        

    /**
     * COMPLETION
     * Find and return the minimum level in the list
     *  - There is guaranteed to be at least one level,
     *  - The method will need a variable to keep track of the minimum, which
     *    needs to be initialised to an appropriate value.
     */
    public double minimumLevel(ArrayList<Double> levels) {
        double max=this.maximumLevel(levels);

        for(double min: levels){
            if(min<=max){
                max = min;
        }
        if(min>max){
               max=max;
                
            }
        }
        return max;
    }

    /**
     * COMPLETION
     * Find and calculates if the pollution level was above the danger threshold
     * continuously for more than half (50%) of the readings, and what percentage of
     * the readings were in the above that level.
     */
    public void reportDangerPeriod(ArrayList<Double> levels) {
        double over=0;
        double over1;
        double over2;
        for (double report:levels){
            if (report>120){
                over=over+1;
            }
            if (report<=120){
                over=over-over;
        
            }
            if(over>(levels.size()/2)){
                over1=(over/levels.size());
                UI.println((over1*100) +"% of reading were continuously over the danger level, readings: "+(over));
            }
        }
    }


    // Helper methods - setupGUI and simpleTest

    // ------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI() {
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Analyse Levels", this::analyse);
        UI.addButton("simple test", this::simpleTest);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Test method with some "magic" to test your methods
     * on the example data from the assignment handout.
     * THIS IS NOT A COMPLETE TEST FOR YOUR PROGRAM!!     
     */
    public void simpleTest() {
        UI.clearPanes();
        ArrayList<Double> levels = new ArrayList<Double>(Arrays.asList(new Double[]{
                    91.0,149.0,167.0,84.0,158.0,185.0,196.0,182.0,125.0,90.0}));
        this.printAverageLevel(levels);
        this.printDangerousLevels(levels);
        this.plotLevels(levels);
        UI.printf("Maximum level was:  %.0f ppb\n", this.maximumLevel(levels));
        UI.printf("Minimum level was:  %.0f ppb\n", this.minimumLevel(levels));
        this.medianLevel(levels);
        this.reportDangerPeriod(levels);
    }


}
