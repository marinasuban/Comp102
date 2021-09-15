// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 4
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: SUbankamo
 * ID:300471606
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class WordSearcher {

    /** set up the buttons */
    public void setupGUI(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Search", this::searchPattern);
        UI.addButton("quit", UI::quit);
        UI.setDivider(1.0);
    }

    /**
     * Asks the user for a pattern and then finds and prints out (one per line)
     *     all the words in a dictionary that contain that pattern.
     * At the end, it prints out how many words in the dictionary contained
     *    the pattern.
     * It should print the words as it finds them, but should stop printing
     *    after it has found 100 of them
     * The dictionary is in the file dictionary.txt, and has one word per line.
     */
    public void searchPattern() {
        String word=UI.askString("Word to search for");
        try{List<String> allLines=Files.readAllLines(Path.of("dictionary.txt"));
            int lineNo=1;
            int count=0;
            for(String line:allLines){
                if(line.contains(word)){
                UI.printf("Found %s on line %d: %s\n", word, lineNo, line);
                count=count+1;
            }
                lineNo++;
            if (count==100){
                return;
            }
        }
            
        }catch(IOException e){UI.println("File failed"+e);}
    }


}

