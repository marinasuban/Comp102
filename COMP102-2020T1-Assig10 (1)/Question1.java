// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 8
 * Name: Kamonchanok Suban Na Ayudtaya
 * Username:Subankamo
 * ID:300471606
 */

import ecs100.*;

/**
 * Question 1. Writing programs with if.    
 */ 
public class Question1{

    /**
     * This method should calculate and return the infringement fine.
     * It has two integer parameters: 
     * - the speed limit of a road and,
     * - the speed of a car, 
     * 
     * How far over the speed limit   Infringement fine
     * 10km/h or less                   $30
     * 11-15km/h                        $80
     * 16km/h or more                   $150  
     */
    public int calculateFine(int speedLimit, int speed) {
        if(speed-speedLimit<=10){
        return 30;
        }
        else if(speed-speedLimit>=11 && speed-speedLimit<=15){
        return 80;
        }
        else if(speed-speedLimit>=16){
        return 150;
        }
        else{
        return -1;
        }
    }

    //----------------------------------------------------------------------
    /* 
     * This method should return the hour that an alarm should be set
     * It has three parameters: 
     * - day: integer, 1-5 for Mon-Fri, 6-7 for Sat-Sun;  
     * - holiday: boolean
     * - sick: boolean
     * 
     * return the alarm time as an integer: 7, 9, 10, 11, or -1 (none)
     *
     * Your alarm should normally be set to 7am during weekdays and 9am
     *    during weekends
     * But if it is a holiday, you sleep in and the alarm should be set
     *    to 10am on weekdays and 11am on weekends
     * However, if you are sick, you do not set the alarm at all, whatever
     *    day it is, and the method should return -1
     */ 
    public int setAlarm(int day, boolean holiday, boolean sick) {
        if(day>0 && day<=5 && sick==false){
            if(holiday==true){
                return 10;
            }
            else if (holiday==false){
                return 7;
            }
        }
        else if(day>5 && day<=7 && sick==false){
            if(holiday==true){
                return 11;
            }
            else if (holiday==false){
                return 9;
            }
        }
        else if (sick==true){
            return -1;
        }
        return -1;
    }

    /******************************************************
     * YOU CAN USE THE METHODS BELOW THIS LINE            * 
     * TO TEST YOUR PROGRAM                               *
     ******************************************************/
    public void testFine(){
        int speedLimit = UI.askInt("Limit is:");
        int speed = UI.askInt("Speed is: ");
        int fine = this.calculateFine(speedLimit, speed);
        UI.printf("calculateFine(%d, %d) is $%d\n",
            speedLimit, speed, fine);
    }

    public void testAlarm(){
        int day = UI.askInt("What day is it:");
        boolean holiday = UI.askBoolean("Is it a holiday?");
        boolean sick = UI.askBoolean("Are you sick?");
        int alarm = this.setAlarm(day, holiday, sick);
        UI.printf("setAlarm(%d, %b, %b) is %d\n", day, holiday, sick, alarm);
    }   

    public void setupGUI() {
        UI.initialise();
        UI.addButton("Test Fine", this::testFine);
        UI.addButton("Test Alarm", this::testAlarm);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(500,500);
        UI.setDivider(1.0);
    }

    public static void main(String[] args) {
        new Question1().setupGUI();
    }
}

