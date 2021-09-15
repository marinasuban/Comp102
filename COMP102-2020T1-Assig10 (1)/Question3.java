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
 * Question 3: Writing programs with Files 
 */
public class Question3{

    /**
     * findLongestWord should read all the words from the file specified
     *   by the parameter,
     * and then find and return the longest word.
     * If there are two or more equally longest words, it should return
     *   the last one.
     * It should not count the punctuation. 
     */
    public String findLongestWord(String filename){
        String longestW="";
        try{
        Scanner scan = new Scanner(Path.of(filename));
        while(scan.hasNext()){
        String word=scan.next();
        if (word.contains(".")||word.contains(",")){
        word=word.substring(0,word.length()-1);
        }
        if (word.length() >= longestW.length()){
        longestW=word;
        }
        }
        }catch(IOException e){UI.println("Invalid file");}
        return longestW;

    }

    /**********************************************************
     * The following is a test method and the GUI for testing *
     **********************************************************/

    public void test1(){      
        String longestWord = this.findLongestWord("testfile.txt");
        UI.println("Longest word = "+ longestWord);
    }

    public void test2(){      
        String longestWord = this.findLongestWord(UIFileChooser.open());
        UI.println("Longest word = "+ longestWord);
    }

    public void setupGUI() {
        UI.initialise();
        UI.addButton("Run on testfile", this::test1);
        UI.addButton("Run", this::test2);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);
    }

    public static void main(String[] args) {
        new Question3().setupGUI();
    }
}

