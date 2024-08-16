
package USER;

/**
 *
 * @author STUDENT
 */
public class Married extends User{

    private double PAYEE;
    private double PersonalTax;
    
    public Married(){
          super(); // it calls the parent class attributes
          this.PAYEE = 1875;
          this.PersonalTax = 3785;
    }
      
     /**
     * It sets the PAYEE + PersonalTax as the total amount for Married Person
     * @return threshold for single person (5770)
     */
    @Override
     public double getTaxCreditThreshold() {
        double taxThresholdSinglePerson = this.PAYEE + this.PersonalTax;  
        return taxThresholdSinglePerson; 
    }    
 }

