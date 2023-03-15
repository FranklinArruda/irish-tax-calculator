
package Taxes;

/**
 *
 * @author FRANKLIN
 */
public interface TaxDeductionInterface {
    
    public double getPRSI(double salary);
    
    public double getUSC(double salary, String companyName);
    
    public double getPension(double salary);
    
    public double regularTaxDeduction(double salary);
    
    public double emergencyTaxDeduction(double remainingBalance);
    
    public double getWeeklyTaxCredits(double weeklyTaxCrdits);
    
    public double getWeeklyPayLimit();
    
    public double getFortnightlyTaxCredits(double fortnightlyTaxCrdits);
    
    public double getFortnightlyPayLimit();
    
    public double getMonthlyTaxCredits(double MonthlyTaxCredits);
    
    public double getMonthlyPayLimit();

    public double SinglePersonTaxCreditBalance(String companyName, double amount );
     
    public double getSinglePersonTaxCredits();
    
    public double MarriedPersonTaxCreditBalance(String companyName, double amount);
    
    public double getMarriedPersonTaxCredits();
    
}
