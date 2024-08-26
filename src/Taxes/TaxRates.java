
package Taxes;

import Enums.EnumTaxContainer;

/**
 *
 * @author FRANKLIN
 */
public class TaxRates implements TaxRatesInterface {

@Override
public double regularTax20(double salary) {
    // Enum Object
    int RegularTax = EnumTaxContainer.TaxRates.REGULAR_TAX.getTaxRate(); 
    
    // Helper class OBEJCT to either (Apply Rate) and (Round to TWO decimal places)
    Helper getHelperMethods = new Helper();
    
    // apply rates at 20%
    double regularTaxResults = getHelperMethods.applyRate(salary, RegularTax);
    
    // round to two decimal place
    getHelperMethods.roundToTwoDecimalPlaces(regularTaxResults);

        return regularTaxResults;
}

     
@Override
public double emergencyTax40(double remainingBalance) {
        
    // Enum Object
    int EmergencyTax = EnumTaxContainer.TaxRates.EMERGENCY_TAX.getTaxRate(); 
    
    // Helper class OBEJCT to either (Apply Rate) and (Round to TWO decimal places)
    Helper getHelperMethods = new Helper();
    
    // apply rates at 40%
    double emergencyTaxResults = getHelperMethods.applyRate(remainingBalance, EmergencyTax);
    
    // round to two decimal place
    getHelperMethods.roundToTwoDecimalPlaces(emergencyTaxResults);

        return emergencyTaxResults;
    }    
}
