package Taxes;

/**
 *
 * @author FRANKLIN
 */
public interface TaxCreditLimitsInterface {
    
    /**
     * Numbers of weeks through the year (12)
     * @param MonthlyTaxCredits Divide the numbers of weeks by the Weekly Tax credits in the parameter
     * @return monthly TaxDeduction Credits 
     */
    public double getMonthlyTaxCredits(double MonthlyTaxCredits);
    
     /**
     * Numbers of weeks through the year (26)
     * @param fortnightlyTaxCrdits Divide the numbers of weeks by the Weekly Tax credits in the parameter
     * @return Fortnightly TaxDeduction Credits
     */
    public double getFortnightTaxCredits(double fortnightlyTaxCrdits);
    
    /**
     * Numbers of weeks through the year (52)
     * @param weeklyTaxCrdits Divide the numbers of weeks by the Weekly Tax credits in the parameter
     * @return weekly TaxDeduction Credits
     */
    public double getWeeklyTaxCredits(double weeklyTaxCrdits);
    
}
