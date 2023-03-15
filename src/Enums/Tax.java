
package Enums;

/**
 *
 * @author STUDENT
 */
public enum Tax {
    
    SINGLE_PERSON_RATE_BAND(3500),  // Single Person Tax Credits
    MARRIED_PERSON_RATE_BAND(5325), // MarriedPerson Tax Credits 3500 + 1775 = (5325)
    REGULAR_TAX(20), // Regular Tax deduction at 20%
    EMERGENCY_TAX(40), // Regular Tax decuction at 40%
    PRSI(12); // PRSI Tax credits
    
    final int tax;
    
    Tax(int tax){
        this.tax=tax;
    }
    
    public int getTax(){
        return this.tax;
    }
}
