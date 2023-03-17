
package Enums;

/**
 * @author FRANKLIN
 */
public enum TaxRates {
    
    REGULAR_TAX(20), // Regular TaxRates deduction at 20%
    EMERGENCY_TAX(40); // Emergency TaxRates decuction at 40%
   
    final int tax;
    
    TaxRates(int tax){
        this.tax=tax;
    }
    
    public int getTax(){
        return tax;
    }
}
