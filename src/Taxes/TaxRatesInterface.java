
package Taxes;

/**
 *
 * @author FRANKLIN
 */
public interface TaxRatesInterface {
    
    /** 
     * Income being TAXED AT 20% (Regular TaxDeduction)
     * Exceeds the weekly limit which is (Rate Band 1 divided by weeks of the year)
     * @param salary that holds the salary value when method is called
     * @return gross deductions taxed at 20%
     */
    public double regularTax20(double salary);
    
     /**
     * Will use the remaining balance from income basis and be taxed at 40%
     * Income being TAXED AT 40% (Emergency TaxDeduction)
     * @param remainingBalance that holds the salary value when method is called
     * @return gross deductions taxed at 40%
     */
    public double emergencyTax40(double remainingBalance);
    
}
