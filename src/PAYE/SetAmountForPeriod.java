package PAYE;
import Enums.EnumTaxContainer.RateLimits;
public class SetAmountForPeriod implements SetAmountForPeriodInterface {
    
     // set the amount for both tax credits and cut-off for period based on frequency of payment
   // this function works for both TAX CREDITS AND CUT OFF FOR PERIOD (PAY LIMIT)
    
     /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * Set the amount for both tax credits and cut-off for period based on frequency of payment
     * this function works for both TAX CREDITS AND CUT OFF FOR PERIOD (PAY LIMIT)
     * 
     * We use the (Rate Band 1) > 40,000.00 / 52 weeks,26 weeks OR 12months of the year = (Amount for period) 
     * @param YearlyAmount
     * @param frequency
     * @return weekly pay limit 
     */
    @Override
    public double setAmountForPeriod(double YearlyAmount, RateLimits frequency){
            double amountForPeriod = YearlyAmount / frequency.getFrequencyLimit();
        return amountForPeriod;
    }
    
}
