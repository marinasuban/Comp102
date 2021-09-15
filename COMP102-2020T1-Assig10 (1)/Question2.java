// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 8
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username:Subankamo
 * ID:300471606
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.nio.file.*; 

/**
 * Question 2. Writing programs with loops.
 */ 
public class Question2 {

    /**
     * findMin should return the minimum number in an ArrayList
     * of numbers specified in the parameter.
     */
    public double findMin(ArrayList<Double> numbers){
        double min=Double.MAX_VALUE;
        for (int i=0; i<numbers.size()-1; i++){
        double value=numbers.get(i);
        if(value<min){
        min=value;
        }
        }
        return min;
    }

    //----------------------------------------------------------------------
    /**
     * removeMin should delete all occurrences of the number in min from
     * the ArrayList in numbers, except it must not delete all the values from
     * numbers - there should be at least one value left in the list.  
     * For example,
     *   if   numbers holds {2.1, 3.0, 4.5, 2.1, 2.1, 2.1},
     *   then removeMin(numbers, 2.1) should reduce numbers to {3.0, 4.5}
     *   if   numbers holds {3.2, 3.2, 3.2, 3.2},
     *   then removeMin(numbers, 3.2) should reduce numbers to {3.2}
     */
    public void removeMin(ArrayList<Double> numbers, double min) {
        for (int i=0; i<numbers.size()-1; i++){
            if (numbers.get(i)==min){
            numbers.remove(i);
            i=i-1;
            }
        }
        if(numbers.size()==0){
        numbers.add(min);
        }
    }


    /**********************************************************
     * The following is a test method and the GUI for testing *
     **********************************************************/

    public void test(){      
        ArrayList<Double> numbers = UI.askNumbers("Enter numbers, end with done ");
        double min = this.findMin(numbers);
        UI.println("Min is " + min);
        UI.println("numbers was: "+numbers);
        this.removeMin(numbers, min);
        UI.println("numbers is:  "+numbers);

    }

    public void setupGUI() {
        UI.addButton("test", this::test);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800,500);
        UI.setDivider(1.0);
    }

    public static void main(String[] args) {
        new Question2().setupGUI();
    }
}

