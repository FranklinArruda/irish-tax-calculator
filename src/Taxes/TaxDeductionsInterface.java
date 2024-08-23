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
     * If your annual income is less than €13,000, you do not pay USC.
     * 
     * If your annual income exceeds €13,000, USC is applied to your entire income, 
     * in real time based on your current pay period, but at different rates depending on 
     * the portion of your income that falls within each band.
     * 
     * If YTD income for the year is up to 12.012,01 ( taxed at 0.5% )
     * If YTD income for the year is in between 12.012,01 and 25.760,00 ( taxed at 2% )
     * If YTD income for the year is in between 25.760,00 and 70.000,00 ( taxed at 4% )
     * If YTD income for the year is greater than 70.000,00 ( taxed at 8%)
     * 
     * Enums OBJECTS to get USC Rate bands as well as USC percentage deduction
     * 
     * @param YearToDateIncome user enter their YTD(Year-to-Date) income which is cumulative income.
     * @param userSalary is the salary for the current period.
     * @return USC applied upon the cumulative income that falls within the range of rates, 
     * then applied to current user pay directly.
     * 
     */
    public double getUSC(double userSalary, double YearToDateIncome);
    
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
