
package Taxes;

/**
 * @author FRANKLIN
 */
public interface TaxDeductionInterface {
        
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
    
    
    /** 
     * Income being TAXED AT 20% (Regular TaxDeduction)
     * Exceeds the weekly limit which is (Rate Band 1 divided by weeks of the year)
     * @param salary that holds the salary value when method is called
     * @return gross deductions taxed at 20%
     */
    public double regularTaxDeduction(double salary);
    
    
    /**
     * Will use the remaining balance from income basis and be taxed at 40%
     * Income being TAXED AT 40% (Emergency TaxDeduction)
     * @param remainingBalance that holds the salary value when method is called
     * @return gross deductions taxed at 40%
     */
    public double emergencyTaxDeduction(double remainingBalance);
    
    
    /**
     * Numbers of weeks through the year (52)
     * @param weeklyTaxCrdits Divide the numbers of weeks by the Weekly Tax credits in the parameter
     * @return weekly TaxDeduction Credits
     */
    public double getWeeklyTaxCredits(double weeklyTaxCrdits);
    
    
    /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * We use the (Rate Band 1) > 40,000.00 / 52 weeks of the year = 769.00 (weekly) 
     * The weekly limit of 769.00 will then be calculated at 20%
     * If your weekly income is over that limit the remaining balance will then be taxed at 40%
     * @return weekly pay limit 
     */
    public double getWeeklyPayLimit();
    
    
    /**
     * Numbers of weeks through the year (26)
     * @param fortnightlyTaxCrdits Divide the numbers of weeks by the Weekly Tax credits in the parameter
     * @return Fortnightly TaxDeduction Credits
     */
    public double getFortnightlyTaxCredits(double fortnightlyTaxCrdits);
    
    
     /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * We use the (Rate Band 1) > 40,000.00 / 26 weeks of the year = 1538.00 (fortnightly) 
     * The weekly limit of 769.00 will then be calculated at 20%
     * If your fortnight income is over that limit the remaining balance will then be taxed at 40%
     * @return fortnight pay limit
     */
    public double getFortnightlyPayLimit();
    
    
    /**
     * Numbers of weeks through the year (12)
     * @param MonthlyTaxCredits Divide the numbers of weeks by the Weekly Tax credits in the parameter
     * @return monthly TaxDeduction Credits 
     */
    public double getMonthlyTaxCredits(double MonthlyTaxCredits);
    
    
    
     /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * We use the (Rate Band 1) > 40,000.00 / 12 weeks of the year = 3333.00 (monthly) 
     * The weekly limit of 769.00 will then be calculated at 20%
     * If your monthly income is over that limit the remaining balance will then be taxed at 40%
     * @return monthly pay limit
     */
    public double getMonthlyPayLimit();

    
    /**
     * @param companyName print out company name  
     * @param amount  
     * @return  amount
     * Get user input to set the TaxDeduction Credit validation by using UTILITIES CLASS
     */
    public double SinglePersonTaxCreditBalance(String companyName, double amount );
     
    
    /**
     * @return the remaining TaxDeduction Credits
     */
    public double getSinglePersonTaxCredits();
    
    
    /**
     * @param companyName print out company name 
     * @param amount  
     * @return  amount
     * Get user input to set the TaxDeduction Credit validation 
     */
   // public double MarriedPersonTaxCreditBalance(String companyName, double amount);
    
    
    /**
     * @return the amount of (TAX Credits) left 
     */
    //public double getMarriedPersonTaxCredits();  
}
