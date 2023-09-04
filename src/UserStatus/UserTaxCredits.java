
package UserStatus;

    import Utilities.Utilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;

    /**
     * @author STUDENT
     */
    public class UserTaxCredits implements UserTaxCreditsInterface {
    
        // Setting up Attributes 
        private double singlePersonTaxCredits; // Single Person = (1775 + 1775) = 3500
        private double marriedPersonTaxCredits; // Marriege Person = (1775 + 3500) = 5275
                     
        // Initialized constructor => setting up values
        public UserTaxCredits(){
            this.singlePersonTaxCredits = 3500;
            this.marriedPersonTaxCredits = 5275;
        }

        //========================================================================
        
        @Override
        public double SinglePersonTaxCreditsWithdraw(String companyName, double amount){

            double remainingBalnce = 0; // holds the remaining balance
            boolean valid = false;
            do{ 
                try {
                    // user prompt
                   System.out.println("Enter Tax Credits for " + companyName );
                   
                   // get user input
                   BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
                   amount = Integer.parseInt(myKeyboard.readLine());
                   
                       // user validation (the value should be greater than zero)
                       if (amount <0){
                           System.err.println("Invalid value entered. The tax credits cannot be less than ZERO");    
                       }
                       
                       //must be OK
                       else {
                           valid = true;
                       }
               
                // call method get user Double
                // Utilities userTax = new Utilities();
                // amount = userTax.getUserDouble(); //amount = getUserDouble();

                }catch(Exception e){
                   // this will be if the parseInt threw an error -- so the user did not enter a number
                   System.err.println("Only numbers.Please try again!");  
                }    
                       if( this.singlePersonTaxCredits >= amount ) {
                           this.singlePersonTaxCredits = this.singlePersonTaxCredits - amount;
                           break; // will stop the loop if there is anough TaxDeduction Credits           
                       }

                       else {
                           // stores the remaining TaxDeduction Credits
                           remainingBalnce = this.singlePersonTaxCredits; 
                           System.err.println("Not enough Tax Credits");
                           System.out.println("This is the Remaining Tax Credits : " + remainingBalnce + " for company " + companyName + "\n");
                           break; // will stop the loop if there isn't anough TaxDeduction Credits LEFT
                       }
            }while((amount > remainingBalnce) && (!valid)); 
           return amount;                           
       }
        
        //========================================================================
       
        @Override
        public double getSinglePersonTaxCreditsBalance() {
            return singlePersonTaxCredits;
        }
        
        //========================================================================
        
        @Override
        public double MarriedPersonTaxCreditsWithdraw(String companyName, double amount){

            double remainingBalnce = 0; // holds the remaining balance

            do{ 
                try {
                   System.out.println("Enter Tax Credits for " + companyName );

                  // call method get user Double
                   Utilities userTax = new Utilities();
                   amount = userTax.getUserDouble(); //amount = getUserDouble();

                }catch(Exception e){
                   // this will be if the parseInt threw an error -- so the user did not enter a number
                   System.err.println("Only numbers.Please try again!");  
                }    
                       if( this.marriedPersonTaxCredits >= amount ) {
                           this.marriedPersonTaxCredits = this.marriedPersonTaxCredits - amount;
                           break; // will stop the loop if there is anough TaxDeduction Credits           
                       }

                       else {
                           // stores the remaining TaxDeduction Credits
                           remainingBalnce = this.marriedPersonTaxCredits; 
                           System.err.println("Not enough Tax Credits");
                           System.out.println("This is the Remaining Tax Credits : " + remainingBalnce + " for company " + companyName + "\n");
                       }
            }while((amount > remainingBalnce)); 
           return amount;                           
       }
    
        //========================================================================
        
        @Override
        public double getMarriedPersonTaxCredits(){
        return marriedPersonTaxCredits;
        }  
}
