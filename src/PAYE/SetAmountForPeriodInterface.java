
package PAYE;
import Enums.EnumTaxContainer.RateLimits;
public interface SetAmountForPeriodInterface {
    
   // set the amount for both tax credits and cut-off for period based on frequency of payment
   // this METHOD works for both TAX CREDITS AND CUT OFF FOR PERIOD (PAY LIMIT)
    public double setAmountForPeriod(double YearlyAmount, RateLimits frequency);
}
