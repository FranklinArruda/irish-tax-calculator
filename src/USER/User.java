package USER;

/**
 * @author FRANKLIN
 */
public abstract class User {
    // Attibutes
    private String name;
    private double personalTaxCredits;
   
    // Default constructor
    public User(){
    }
    
    // Default Getters & Setters
    public void setName (String name){
      this.name = name;
    }
    
    public String getName(){
      return this.name;
   }
    
   /**
    * Shared among child-classes
    * @param taxAmount is user Input
    * Set Tax Credits Balance given user Input @taxAmount
    * Global functionality that is shared in subclasses
    * It "get Tax Credits Threshold" method and validated with conditionals
    */
    public void setTaxCreditsBalance(double taxAmount){
         
        // holds the threshold value
        double taxThreshold = getTaxCreditThreshold();
         
        // tax credits can't be higher than THRESHOLD
        // if user input is greater than Thresgold then is set to its value 
        if(taxAmount > taxThreshold){
            System.out.println("Tax Credits cannot be Higher than " + taxThreshold + " Treshold");
            System.out.println("Tax Credits Set to: " + taxThreshold);
            this.personalTaxCredits = this.personalTaxCredits = taxThreshold;
        }

        // tax can't be zero!
        if(taxAmount <= 0.0){
            System.out.println("Tax Credits cannot be zero!");
        }

        // tax credits OK
        if((taxAmount > 0)&&( taxAmount <= taxThreshold)){
            System.out.println("Tax Credits Set: " + taxAmount );
            this.personalTaxCredits = this.personalTaxCredits + taxAmount;  
        }      
    };
   
    /**
     * Shared among child-classes
     * @param withdrawAmount takes user Input
     * @return withdraw amount by the user
     */
    public double taxCreditsWithdraw(double withdrawAmount){
        
        // return 0 if tax withdraw (usage) is less or equal to 'zero'
        if(withdrawAmount <=0){
            System.out.println("Withdraw must be greater than zero!");
            return 0.0;
        }
        
        // Sets the withdraw if user input is greater than 'zero' and not higher than current tax credits
        if((withdrawAmount > 0) && (withdrawAmount <= this.personalTaxCredits)){
            System.out.println("Successful withdraw: " + withdrawAmount);
            this.personalTaxCredits = this.personalTaxCredits - withdrawAmount;
        }
        
        // Call "getTaxCredist" method if withdraw amount is greater than current tax credits balance
        if(withdrawAmount > this.personalTaxCredits){
            System.out.println("Not enought Tax Credits"  + this.getTaxCredits());
        }
            return withdrawAmount;
    };
      
    /**
     * Shared among child-classes
     * @return current tax credits
     */
    public double getTaxCredits(){
        return this.personalTaxCredits;
    };
    
    /**
     * Abstract method to be overriding by sub-classes
     * @return Tax Credits Threshold set by sub-classes
     */ 
    public abstract double getTaxCreditThreshold();
    
    
    /**
     * Shared among child-classes
     * @return user name and current Tax Credits for all sub-classes 
     */
    @Override
    public String toString(){
        return "Name: " + getName() + ", Tax Credits Balance: " + getTaxCredits();
    }
    
}
