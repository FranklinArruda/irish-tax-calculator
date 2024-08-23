package Taxes;

/**
 *
 * @author STUDENT
 */
public interface HelperInterface {
 
   /**
    * Applies a percentage rate to a given amount.
    *
    * @param amount The base amount on which the rate will be applied.
    * @param rate   The percentage rate to apply (e.g., 4 for 4%).
    * @return The calculated result after applying the rate to the amount.
    */
   public  double applyRate(double amount, double rate);
   
   /**
    * Rounds a double value to two decimal places.
    *
    * @param value The double value to be rounded.
    * @return The value rounded to two decimal places.
    */
   public double roundToTwoDecimalPlaces(double value);
}
