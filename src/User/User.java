package User;

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
    
   
    public void setTaxCreditsBalance(double taxAmount){
         
        // holds threshold limit tax credits
        double taxThreshold = getTaxCreditThreshold();
        
        // Warning msg: if user input is greater than Thresgold then display message 
        if(taxAmount > taxThreshold){
            System.out.println("Tax Credits is usually high than the " + taxThreshold + " Treshold");
        }
        
        // Warning msg: if user input is greater than Thresgold then display message 
        if(taxAmount < taxThreshold){
            System.out.println("Tax Credits is usually low than the " + taxThreshold + " Treshold");
        }

        // tax can't be zero!
        if(taxAmount <= 0.0){
            System.out.println("Tax Credits cannot be zero!");
        }

          // tax credits OK
        if(taxAmount > 0){
            System.out.println("Tax Credits Set: " + taxAmount );
            this.personalTaxCredits = this.personalTaxCredits + taxAmount;  
        }    
    };
   
    public double taxCreditsWithdraw(double withdrawAmount){
        
        // it holds the current tax balance
        double currentTaxBLC = getTaxCredits();
        
        // withdraw can't be less than zero. 
        if(withdrawAmount <=0){
            System.out.println("Withdraw must be greater than zero!");
            return 0.0;
        }
         
        // Tax Withdraw = if withdraw is greater than zero and not higher than current tax limit, then withdraw
        if((withdrawAmount > 0) && (withdrawAmount <= this.personalTaxCredits)){
            System.out.println("Successful withdraw: " + withdrawAmount);
            this.personalTaxCredits = this.personalTaxCredits - withdrawAmount;
        }
        
        // No tax Withdraw = if withdraw is greater than current tax, display current amount
        if(withdrawAmount > this.personalTaxCredits){
            System.out.println("Not enought Tax Credits"  + currentTaxBLC);
        }  
        return withdrawAmount; 
    };
      
    public double getTaxCredits(){
        return this.personalTaxCredits;
    };
    
    // Abstract method to be implemented by subclasses 
    public abstract double getTaxCreditThreshold();
    
    // Return user status such as name and current tax credits
    @Override
    public String toString(){
        return "Name: " + getName() + ", Tax Credits Balance: " + getTaxCredits();
    }
    
}
