// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 4
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username: Subankamo
 * ID:300471606
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/** Displays an animated story of at least two characters from a script in a file.
 *  Each line of a script file is an instruction.
 *  See "simple-script.txt" for an example.
 *
 *  These are the possible instructions (<..> is describing a value
 *    CHARACTER  <name> <type>
 *         creates a new Animal with the given name and type, but holds them off-screen
 *         <type> can be any of bird, dinosaur, dog, grasshopper, snake, tiger, turtle.
 *         This instruction must occur before any other instructions involving the animal
 *         It is OK to insist that all the CHARACTER instructions are at the beginning of
 *         the script.
 *    ENTER <name> <x> <y>   :
 *         make the animal with that name appear on the screen at the position <x>,<y>
 *    GO <name> <direction> <distance> :
 *         turn the animal with that name to the direction (left or right) and move by <distance>
 *    TELEPORT <name> <x> <y> :
 *         teleport the animal with that name to the new position
 *    EXIT <name>
 *         make the animal with that name disappear from the screen
 *    JUMP <name> <height> :
 *         make the animal with that name jump by the height
 *    SPEAK <name> <words> :
 *         make the animal with that name speak the words (can be multiple tokens)
 *    THINK/SHOUT/INTRODUCE work just like SPEAK.
 *    CAPTION <words>  :
 *         display the words as a caption at the top of the window.
 * 
 *  extras (for top marks)
 *    ASK <question words>, ......
 *    ELSE
 *    ENDASK
 *         ask the user a question and follow two different paths in the story depending
 *         on the answer:
 *           the instructions between ASK and ELSE if they said "yes"
 *           the instructions between ELSE and ENDASK if they said "no"
 *         You do not have to handle ASK instructions inside other ASK instructions,
 *         but you can if you want! (you might need to label the ASK/ELSE/ENDASK instructions!
 *    
 *    REPEAT <number>
 *    ENDREPEAT
 *         repeat the instructions between REPEAT and ENDREPEAT <number> times.
 *    
 */
public class AnimalStory{

    /** Set up the buttons */
    public void setupGUI (){
        UI.addButton("Story", this::storyFromFile);
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Quit", UI::quit );
    }        

    /**
     * storyFromFile opens a script file, and animates the story in the file
     *  by following all the instructions in the script
     *  Each line of the file is one instruction (see above).
     */
    public void storyFromFile(){
        String C1="";
        String S1="";
        String C2="";
        String S2="";
        double x1=0;
        double y1=0;
        double x2=0;
        double y2=0;
        Animal N1=new Animal(S1,C1,x1,y1);
        Animal N2=new Animal(S2,C2,x2,y2);
    try{List<String> allLines=Files.readAllLines(Path.of("simple-script.txt"));
        for(String line:allLines){
            Scanner scan = new Scanner(line);
            String start=scan.next();
            String name=scan.next();
       while(start.equals("CHARACTER")){
                String species=scan.next();
                if (C1.equals("")){
                C1=name;
                S1=species;
                break;
                }
                else if(!C1.equals("")){
                C2=name;
                S2=species;
                break;
                }
        }
       while(start.equals("ENTER")){
            double x=scan.nextDouble();
            double y=scan.nextDouble();
       if(name.equals(C1)){
        x1=x1+x;
        y1=y1+y;
        N1=new Animal(S1,C1,x1,y1);
        N1.teleport(x1,y1);
        break;
        }
       else if(name.equals(C2)){
        x2=x2+x;
        y2=y2+y;
        N2=new Animal(S2,C2,x2,y2);
        N2.teleport(x2,y2);
        break;
       }
       }
        while(start.equals("GO")) {
           String direction=scan.next();
           double distance=scan.nextDouble();
        if(name.equals(C1)){
          if(direction.equals("right")){
          N1.goRight(distance);
          break;
        }
          else if(direction.equals("left")){
          N1.goLeft(distance);
          break;
            }
        }
        if(name.equals(C2)){
          if(direction.equals("right")){
          N2.goRight(distance);
          break;
        }
          else if(direction.equals("left")){
          N2.goLeft(distance);
          break;
                }  
            }
        }
        while(start.equals("TELEPORT")){
            double tx=scan.nextDouble();
            double ty=scan.nextDouble();
            if(name.equals(C1)){
                x1=tx;
                y1=ty;
                N1.teleport(x1,y1);
                break;
            }
            if(name.equals(C2)){
                x1=tx;
                y2=ty;
                N1.teleport(x2,y2);
                break;
            }   
        }
        while(start.equals("EXIT")){
            if(name.equals(C1)){
            N1.teleport(-500,-500);
            break;
           }
            if(name.equals(C2)){
            N2.teleport(-500,-500);
            break;
           }
    
        }
         while (start.equals("JUMP")){
            double height=scan.nextDouble();
            if(name.equals(C1)){
            N1.jump(height);
            break;
            }
         else if(name.equals(C2)){
            N2.jump(height);
            break;
            }
        }
        while (start.equals("SPEAK")){
            String words=scan.nextLine();
               if(name.equals(C1)){
            N1.speak(words);
            break;
            }
         else if(name.equals(C2)){
            N2.speak(words);
            break;
            }
        }
        while (start.equals("THINK")){
            String words=scan.nextLine();
               if(name.equals(C1)){
            N1.think(words);
            break;
            }
         else if(name.equals(C2)){
            N2.think(words);
            break;
            }
        }
        while (start.equals("SHOUT")){
            String words=scan.nextLine();
               if(name.equals(C1)){
            N1.shout(words);
            break;
            }
         else if(name.equals(C2)){
            N2.shout(words);
            break;
            }
        }
        while (start.equals("INTRODUCE")){
            String words=scan.nextLine();
               if(name.equals(C1)){
            N1.introduce(words);
            break;
            }
         else if(name.equals(C2)){
            N2.introduce(words);
            break;
            }
        }
        while (start.equals("CAPTION")){
            String words=scan.nextLine();
              if(name.equals(C1)){
            N1.caption(words);
            break;
            }
         else if(name.equals(C2)){
            N2.caption(words);
            break;
            }
       }
}}catch(IOException e){UI.println("File failed"+e);}
}
}
