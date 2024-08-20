package Taxes;

/**
 *
 * @author FRANKLIN
 */
public interface TaxDeductionsInterface {
    
    /**
     * Pay Related Social Insurance (PRSI)
     * Enumerated OBJECTS to get PRSI
     * it calculates 4% from income over 352 per week
     * @param salary that holds the variable of weekly payment
     * @return Deducted salary
     */
    public double getPRSI(double salary);
    
    /**
     * Universal Social Charge (USC_Income_Bands)
     * Use Utilities class to get user input and validate it 
     * It calculates (USC Income Bands) Based on the user input
     * 
     * Enums OBJECTS to get USC Rate bands as well as USC percentage deduction
     * If gross income for the year is up to 12.012,01 ( taxed at 0.5% )
     * If gross income for the year is above 12.000,01 ( taxed at 2% )
     * If gross income for the year is in between 22,9920.01 and 70,044,00 ( taxed at 4.5% )
     * 
     * @param salary to assign the result from the main
     * @param companyName
     * @return USC_Income_Bands results
     */
    public double getUSC(double salary, String companyName);
    
    /**
     * PENSION SCHEME:
     * Calculates Pension Scheme against user input
     * VALIDATE Yes or No for the user
     * If user chooses Y, then (case "Y") is executed
     * If user chooses N, then (case "N") carry on with the program
     * 
     * BOOLEAN Variable Types:
     * userPension: 
     * 
     * 
     * DO WHILE Inside another:
     * First which holds everything keeps going while the user does not enter a valid value | Boolean type: valid;
     * Second will keep going while user does not enter a number greater than zero (0) | Boolean type: userPension;
     * 
     * TRY-CATCH:
     * First catches the String and displays message to the user
     * Second catcher the Int and displays message in case the user types a number not greater than zero
     * @param salary as parameter to assigns the input from the user when calling method and asks for salary to do the math 
     * @return pension results 
     */
    public double getPension(double salary);
}
