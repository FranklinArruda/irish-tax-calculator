/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author STUDENT
 */
public class TaxCalculatorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Tax paiLimit = new Tax();
        
        paiLimit.getWeeklyPayLimit();
        
        System.out.println(paiLimit.getWeeklyPayLimit());
        System.out.println(paiLimit.getWeeklyTaxCreditsYear());
     //  paiLimit.setWeeklyPayLimit(0);
     
        System.out.println("TESTING");

     
    }
    
}
