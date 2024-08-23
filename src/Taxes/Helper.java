
package Taxes;
/**
 * @author FRANKLIN
 */
public class Helper implements HelperInterface{

    @Override
    public double applyRate(double amount, double rate) {
            double rater = (amount * rate) / 100;
      return rater;
    }

    @Override
    public double roundToTwoDecimalPlaces(double value) {
            double rounder = Math.round(value * 100.0) / 100.0;
        return rounder;
    }    
}
