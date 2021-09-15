// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

/** Program for calculating cost of shipping a package */

public class ShippingCalculator{

    public static final double HANDLING = 1.60;    // cost of handling a shipment
    public static final double SIZE_RATE = 300.00;           // cost per cubic meter
    public static final double WEIGHT_RATE = 1.50;        // cost per kg

    /**
     * Calculates and prints cost of shipping a package.
     */
    public void calculateShippingCore(){       
        double height = UI.askDouble("Height of package (cm)");
        double width = UI.askDouble("Width of package (cm)");
        double length = UI.askDouble("Length of package (cm)");
        double weight = UI.askDouble("Weight of package (kg)");
        double zone = UI.askDouble("Number of zones (minimum 1)");
        double sizechargeperzone = ((height/100) * (width/100) * (length/100) * SIZE_RATE);
        double weightchargeperzone = (weight * WEIGHT_RATE);
        double costofshipping = (((sizechargeperzone) + (weightchargeperzone)) * zone + HANDLING);
        UI.printf("Size Charge Per Zone: $%.2f", sizechargeperzone);
        UI.println(" ");
        UI.printf("Weight Charge Per Zone: $%.2f", weightchargeperzone);
        UI.println(" ");
        UI.println("Zones:" + zone);
        UI.println(" ");
        UI.printf("Total Cost of Shipping: $%.2f", costofshipping);
        UI.println(" ");

    }

    /**
     * Calculates and prints cost of shipping a collection of packages.
     */
    public void calculateShippingCompletion(){
        double zone = UI.askDouble("Number of zones (minimum 1)");
        double package1 = UI.askDouble("Number of packages of first size");
        double height1 = UI.askDouble("Height of package (cm)");
        double width1 = UI.askDouble("Width of package (cm)");
        double length1 = UI.askDouble("Length of package (cm)");
        double weight1 = UI.askDouble("Weight of package (kg)");
        double sizechargeperzone1 = ((height1/100) * (width1/100) * (length1/100) * SIZE_RATE * package1);
        double weightchargeperzone1 = (weight1 * WEIGHT_RATE * package1);
        double costofshipping1 = (((sizechargeperzone1) + (weightchargeperzone1)) * zone);
        double package2 = UI.askDouble("Number of packages of second size");
        double height2 = UI.askDouble("Height of package (cm)");
        double width2 = UI.askDouble("Width of package (cm)");
        double length2 = UI.askDouble("Length of package (cm)");
        double weight2 = UI.askDouble("Weight of package (kg)");
        double sizechargeperzone2 = ((height2/100) * (width2/100) * (length2/100) * SIZE_RATE * package2);
        double weightchargeperzone2 = (weight2 * WEIGHT_RATE * package2);
        double costofshipping2 = (((sizechargeperzone2) + (weightchargeperzone2)) * zone);
        double totalpackage = (package1 + package2);
        double totalcostbeforediscount = ((costofshipping1 + costofshipping2) + HANDLING);
        UI.println("Zones:" + zone);
        UI.println(" ");
        UI.printf("Size Charge Per Zone of first size: $%.2f", sizechargeperzone1);
        UI.println(" ");
        UI.printf("Weight Charge Per Zone of first size: $%.2f", costofshipping1);
        UI.println(" ");
        UI.printf("Size Charge Per Zone of second size: $%.2f", sizechargeperzone2 );
        UI.println(" ");
        UI.printf("Weight Charge Per Zone of second size: $%.2f", costofshipping2);
        UI.println(" ");
        UI.printf("Total Cost of Shipping Before Discount: $%.2f" , totalcostbeforediscount);
        UI.println(" ");
        double discount = ((totalcostbeforediscount) * (1.00 - (1.00/ totalpackage)) / 3.00);
        UI.printf("Total Discount: $%.2f", discount);
        UI.println(" ");
        double totalcost = (totalcostbeforediscount-discount);
        UI.printf("Total Cost of Shipping: $%.2f", totalcost);
        UI.println(" ");
        String addpackage= UI.askString("Add a third group of packages? (YES/NO)");
        if(addpackage.equals("YES")){
        double package3 = UI.askDouble("Number of packages of third size");
        if(package3>9){
        double height3 = UI.askDouble("Height of package (cm)");
        double width3 = UI.askDouble("Width of package (cm)");
        double length3 = UI.askDouble("Length of package (cm)");
        double weight3 = UI.askDouble("Weight of package (kg)");
        double sizechargeperzone3 = ((height3/100) * (width3/100) * (length3/100) * SIZE_RATE * package3);
        double weightchargeperzone3 = (weight3 * WEIGHT_RATE * package3);
        double costofshipping3 = (((sizechargeperzone3) + (weightchargeperzone3)) * zone);
        double totalpackage1 = (package1 + package2 + package3);
        double totalcostbeforediscount1 = ((costofshipping1 + costofshipping2 +costofshipping3) + HANDLING);
        UI.printf("Size Charge Per Zone of third size: $%.2f", sizechargeperzone3 );
        UI.println(" ");
        UI.printf("Weight Charge Per Zone of third size: $%.2f", costofshipping3);
        UI.println(" ");
        UI.printf("Total Cost of Shipping Before Discount: $%.2f" , totalcostbeforediscount1);
        UI.println(" ");
        double discount1 = ((totalcostbeforediscount1) * (1.00 - (1.00/ totalpackage1)) / 3.00);
        UI.printf("Total Discount: $%.2f", discount1);
        UI.println(" ");
        double totalcost1 = (totalcostbeforediscount1-discount1);
        UI.printf("Total Cost of Shipping: $%.2f", totalcost1);
    }
    else{
        UI.println("Sorry, to be eligible to ship a package of another size you must ship at least 10 packages of that size");
    }
    }
    else{
        UI.println("Thank you for using our service");
    }
}
    
    
   

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Calculate Cost of Shipping a Package", this::calculateShippingCore ); 
        UI.addButton("Calculate Cost of Shipping a Collection of Packages", this::calculateShippingCompletion );
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);    // Expand the Text pane
    }	


}
