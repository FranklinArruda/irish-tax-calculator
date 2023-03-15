
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
     * Use Utilities class to get user input 
     * It calculates USC_Income_Bands Based on the user input
     * Enumerated OBJECTS to get USC Rate bands as well as USC percentage deduction
     * If gross income for the year is up to 12.012,01 ( taxed at 0.5% )
     * If gross income for the year is above 12.000,01 ( taxed at 2% )
     * If gross income for the year is in between 22,9920.01 and 70,044,00 ( taxed at 4.5% )
     * @param salary to assign the result from the main
     * @param companyName
     * @return USC_Income_Bands results
     */
    public double getUSC(double salary, String companyName);
    
    
    /**
     * Pension Scheme
     * Calculates Pension Scheme against user input
     * VALIDATE Yes or No for the user
     * If user chooses Y, then (case "Y") is executed
     * If user chooses N, then (case "N") carry on with the program 
     * @param salary as parameter
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
     * Income being TAXED AT 40% (Emergency TaxDeduction)
     * @param remainingBalance that holds the salary value when method is called
     * @return gross deductions taxed at 40%
     */
    public double emergencyTaxDeduction(double remainingBalance);
    
    
    /**
     * @param weeklyTaxCrdits
     * @return weekly TaxDeduction Credits
     */
    public double getWeeklyTaxCredits(double weeklyTaxCrdits);
    
    
    /**
     * @return weekly pay limit 
     */
    public double getWeeklyPayLimit();
    
    
    /**
     * @param fortnightlyTaxCrdits
     * @return Fortnightly TaxDeduction Credits
     */
    public double getFortnightlyTaxCredits(double fortnightlyTaxCrdits);
    
    
    /**
     * @return weekly pay limit 
     */
    public double getFortnightlyPayLimit();
    
    
    /**
     * 
     * @param MonthlyTaxCredits
     * @return monthly TaxDeduction Credits 
     */
    public double getMonthlyTaxCredits(double MonthlyTaxCredits);
    
    
    /**
     * @return Monthly Pay Limit
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
    public double MarriedPersonTaxCreditBalance(String companyName, double amount);
    
    
    /**
     * @return the amount of (TAX Credits) left 
     */
    public double getMarriedPersonTaxCredits();  
}
