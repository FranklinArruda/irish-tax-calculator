
package USER;

/**
 * @author FRANKLIN
 */
public class Single extends User{
    private double PAYEE;
    private double PersonalTax;
    
    public Single(){
          super(); // it calls the parent class attributes
          this.PAYEE = 1875;
          this.PersonalTax = 1875;
    }
    
    /**
     * It sets the PAYEE + PersonalTax as the total amount for Single Person
     * @return threshold for single person (3875)
     */
    @Override
     public double getTaxCreditThreshold() {
        double taxThresholdSinglePerson = this.PAYEE + this.PersonalTax;  
        return taxThresholdSinglePerson; 
    }  
}