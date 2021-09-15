// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 2
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: Subankamo
 * ID:300471606
 */

import ecs100.*;
import java.awt.Color;

/**
 * Key:
 * Core:       Method must report whether the key is valid, or
 *             report that it is invalid and give one reason why it is invalid.
 *             To be valid, the key must
 *             - be at least 7 characters and at most 20 characters long,
 *             - not start with the special characters '#' or '_',
 *             - not have a space character anywhere
 *            
 * Completion: Method should either report that the key is valid, or
 *             report that it is invalid and list ALL the reasons that it is invalid.
 *             To be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have at least one Upper case character and at least one Lower case character,
 *             - not contain the user's name
 *             - contain either a '#' or a '_', but not both.
 * Challenge:  Same as completion, except that to be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have a mix of numbers and letters
 *             - not contain any "prefix" of the user's name of 2 characters or more in any case.
 *               (eg if name is Peter, it does not contain "Pe", or "pE" or "Pet", or "pete" or "PEtE", or...)
 *
 * Hint.  Look at the documentation in the String class.
 * You will definitely find the length(), startsWith(...), and contains(...) methods to be helpful
 */

public class KeyValidator {

    /**
     * Asks user for key word and then checks if it is a valid key word.
     */
    public void doCore(){
        String key = UI.askString("Key:   ");
        this.validateKeyCore(key);
    }

    /** CORE
     * Report "Valid" or "Invalid: ...reason...."
     */
    public void validateKeyCore(String key){
       if (key.length()<7||key.length()>20){
        UI.println("Invalid: The Key must be between 7-20 characters long");
    }
    else if (key.startsWith("#")||key.startsWith("_")){
         UI.println("Invalid: The Key must not start with '#' or '_'");
        }
        else if(key.contains(" ")){
            UI.println("Invalid: The Key cannot contain space");
        }
        else{
            UI.println("Valid");
        }
         

    }

    /** COMPLETION
     * Asks user for key word and the name and then checks if it is a valid key word.
     */
    public void doCompletion(){
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        this.validateKeyCompletion(key, name);
    }

    /** COMPLETION
     * Report that the key is valid or report ALL the rules that the key failed.
     */
    public void validateKeyCompletion(String key, String name){
     int con1;
     int con2;
     int con3;
     int con4;
     int con5;
     int con6;
        if (key.length()<7||key.length()>20){
        UI.println("Invalid: The Key must be between 7-20 characters long");
        con1 = 1;
    }
    else{ con1 = 0;
    }
    if (key.startsWith("#")||key.startsWith("_")){
         UI.println("Invalid: The Key must not start with '#' or '_'");
          con2 = 1;
    }
    else{ con2 = 0;
    }
    if(key.contains(" ")){
            UI.println("Invalid: The Key cannot contain space");
             con3 = 1;
    }
    else{ con3 = 0;
    }
    if(key == key.toLowerCase() || key == key.toUpperCase()){
            UI.println("Invalid: The key must have at least one Upper case character and at least one Lower case character");
             con4 = 1;
    }
    else{ con4 = 0;
    }
    if(key.contains(name)){
            UI.println("Invalid: The Key cannot contain name");
          con5 = 1;
    }
    else{ con5 = 0;
    }
    if((key.contains("#") && key.contains("_")) || (!key.contains("#")) && (!key.contains("_"))){
            UI.println("Invalid: The key must contain either a '#' or a '_', but not both.");
          con6 = 1;
    }
    else{ con6 = 0;
    }
    double validity = (con1+con2+con3+con4+con5+con6);
    if(validity > 0){
        UI.printf(key + "  is invalid");
    }
    else{UI.printf(key + "  is valid");}
    
    }
    /** Challenge
     * Asks user for key word and the name and then checks if it is a valid key word.
     */
    public void doChallenge(){
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        this.validateKeyChallenge(key, name);
    }
    /** challenge
     * Same as completion, except that to be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have a mix of numbers and letters
 *             - not contain any "prefix" of the user's name of 2 characters or more in any case.
 *               (eg if name is Peter, it does not contain "Pe", or "pE" or "Pet", or "pete" or "PEtE", or...)
     */
    public void validateKeyChallenge(String key, String name){
     int con1;
     int con2;
     int con3;
     int con4;
     int con5;
     int con6;
     int con7;
     int con8;
        if (key.length()<7||key.length()>20){
        UI.println("Invalid: The Key must be between 7-20 characters long");
        con1 = 1;
    }
    else{ con1 = 0;
    }
    if (key.startsWith("#")||key.startsWith("_")){
         UI.println("Invalid: The Key must not start with '#' or '_'");
          con2 = 1;
    }
    else{ con2 = 0;
    }
    if(key.contains(" ")){
            UI.println("Invalid: The Key cannot contain space");
             con3 = 1;
    }
    else{ con3 = 0;
    }
    if(key == key.toLowerCase() || key == key.toUpperCase()){
            UI.println("Invalid: The key must have at least one Upper case character and at least one Lower case character");
             con4 = 1;
    }
    else{ con4 = 0;
    }
    if(key.contains(name)){
            UI.println("Invalid: The Key cannot contain name");
          con5 = 1;
    }
    else{ con5 = 0;
    }
    if((key.contains("#") && key.contains("_")) || (!key.contains("#")) && (!key.contains("_"))){
            UI.println("Invalid: The key must contain either a '#' or a '_', but not both.");
          con6 = 1;
    }
    else{ con6 = 0;
    }
   double digit = key.replaceAll("\\D", "").length();
   double alpha = key.replaceAll("[^a-zA-Z]","").length();
   if((digit>0) && (alpha>0)){
          con7 = 1;
    }
    else{ con7 = 0;
        UI.println("Invalid: The key must have a mix of numbers and letters");
    }
  
    String name1 = name.substring(0,2);
    String name2 = name1.toLowerCase();
    String key1 = key.toLowerCase();
    if(key1.contains(name2)){
        UI.println("The key must not contain any prefix of the user's name of 2 characters or more in any case.");
        con8 = 1;
    }
     else{ con8 = 0;
    }
    
    double validity = (con1+con2+con3+con4+con5+con6+con7+con8);
    if(validity > 0){
        UI.printf(key + "  is invalid");
    }
    else{UI.printf(key + "  is valid");}
    
    }
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Key Core", this::doCore );
        UI.addButton("Validate Key Completion", this::doCompletion );
        UI.addButton("Validate Key Challenge", this::doChallenge );   
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

}
