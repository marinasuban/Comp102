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
 * Question 5: A line-breaking algorithm
 */

public class Question5 {

    public static final int LIMIT = 10;   // maximum number of characters allowed on a line.
    
    int loop=0;
    /** 
     * printText should print the String in its parameter in a series of lines.
     * Each line of output should have at most 10 characters on it.
     * There should be as few lines as possible.
     * Where necessary, it should break words between lines with hyphens;
     *  the hyphen should always be the last character on a line.
     * No word should start or end with a hyphen
     * Do not put a space at the beginning of any line.
     * You may assume that the String contains only words, with
     *  no punctuation and exactly one space between words.
     * See the assignment description for an example.
     */
    public void printText(String text1) {
        String text="";
        int start=0;
        int end=text1.length();
        if(text1.charAt(0)=='"'){
        start=start+1;    
        text=text1.substring(start,end);
        }
        if(text1.charAt(text1.length()-1)=='"'){
        end=end-1;
        text=text1.substring(start,end);
        }
        
        if (text.length()<=9){
            UI.println(text);
        }
        else{
            int arrayLength = (int) Math.ceil(text.length() / 9);
            String[] line = new String[arrayLength+1];
            int j = 0;
            int lastIndex = line.length - 1;
            
            for (int i = 0; i < lastIndex; i++) {
                if(text.charAt(j)==' '){
                    j=j+1;
                }
                if (Character.isDigit(text.charAt(j+8))){
                    line[i] = text.substring(j, j + 10);
                    j += 10;
                }
                else if (Character.isSpaceChar(text.charAt(j+8))){
                        line[i] = text.substring(j, j + 9);
                        j += 9;
                }
                else if (Character.isLetter(text.charAt(j+8))){
                        line[i] = text.substring(j, j + 9) + "-";
                        j += 9;
                    }
                
                    
                }
            line[lastIndex] = text.substring(j,end-1);
            for(int k=0; k<lastIndex+1; k++){
                UI.println(line[k]);
            }
        }
        }
    
        
        
    

    /******************************************************
     * YOU CAN USE THE METHODS BELOW THIS LINE            * 
     * TO TEST YOUR PROGRAM                               *
     ******************************************************/

    public void test(){      
        String text = UI.askString("Enter some words here: ");
        this.printText(text);
    }

    public void setupGUI() {
        UI.addButton("Run", this::test);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800,500);
        UI.setDivider(1.0);
    }

    public static void main(String[] args) {
        new Question5().setupGUI();
    }
}

