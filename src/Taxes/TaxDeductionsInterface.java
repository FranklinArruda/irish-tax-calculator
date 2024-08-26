package Taxes;

/**
 *
 * @author FRANKLIN
 */
public interface TaxDeductionsInterface {
    
    /**
     * Pay Related Social Insurance (PRSI)
     * Enums OBJECTS to get PRSI/**
     * Calculates Pay Related Social Insurance (PRSI) for a given weekly salary.
     * PRSI is a tax on income, calculated based on specific salary thresholds.
     * The method applies different logic based on income brackets to compute the PRSI deductions.
     * 
     * This method calculates PRSI as follows:
     * 1. If the salary is less than or equal to 352.00, no PRSI is applied.
     * 2. If the salary is between 352.01 and 424.00, PRSI is calculated with a credit of 12.
     *    - One-sixth of the earnings over 352 is calculated.
     *    - The PRSI credit is reduced by this amount.
     *    - A basic PRSI at 4% of the total salary is calculated.
     *    - The final PRSI deduction is the 4% tax minus any remaining credit.
     * 3. If the salary is greater than 424.00, PRSI is applied at 4% on the full salary without credits.
     * 
     * @param salary The weekly salary for which PRSI is to be calculated.
     * @return The PRSI amount deducted from the salary.
    */
    public double getPRSI(double salary);
    
    /**
     * If your annual income is less than €13,000, you do not pay USC.
     * 
     * If your annual income exceeds €13,000, USC is applied to your entire income, 
     * in real time based on your current pay period, but at different rates depending on 
     * the portion of your income that falls within each band.
     * 
     * This method applies USC as follows:
     * 
 *   * 1. No USC is applied if YTD income is less than or equal to 12,012.00.
     * 2. If YTD income for the year is up to 12.012,01 ( taxed at 0.5% )
     * 3. If YTD income for the year is in between 12.012,01 and 25.760,00 ( taxed at 2% )
     * 4. If YTD income for the year is in between 25.760,00 and 70.000,00 ( taxed at 4% )
     * 5. If YTD income for the year is greater than 70.000,00 ( taxed at 8%)
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
