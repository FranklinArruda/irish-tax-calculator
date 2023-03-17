
package Taxes;

/**
 *
 * @author STUDENT
 */
public interface UserStatusInterface {
    
    /**
     * It calculates the Tax for a Single person
     * Creates Objects from Utilities class for validating User Input
     * Creates Objects from Tax Deduction class to do the calculations 
     * For loop 1 to access array through the number of employers
     * For loop 2 to read from array list company's name through the number of employers and where everything happens
     * Another for loop inside For loop 2 that reads the values from Enums class (Payment Frequency)
     * ------------------------------------------------------------------------------------------------------------
     * SWITCH Statement:
     * Case 1: (Weekly Payment)  
     * Case 2: (Fortnight Payment)
     * Case 3: (Monthly Payment)
     */
    public void SinglePersonTax();
    
    /**
     * 
     */
    public void MarriedPersonTax();
}
