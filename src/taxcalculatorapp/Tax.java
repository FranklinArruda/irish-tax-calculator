
package taxcalculatorapp;

// Setting up Attributes  
public class Tax {
    
    private double rateBand1;
    private int yearlyTaxCredits;
    private int weeklyTaxCreditsYear; 
    private int fortnightlyTaxCreditsYear;
    private int monthlyTaxCreditsYear;
    
   
    
    
    private double percentage;
    private int regularTaxPercentage;
    private int emergencyTaxPercentage;
    private double taxCreditsTwoEmployeers;

    
    // default constructor (initialised)  
            public Tax(){

                this.rateBand1 = 40000;
                this.yearlyTaxCredits = 3500;
                this.weeklyTaxCreditsYear = 52; 
                this.fortnightlyTaxCreditsYear = 26;
                this.monthlyTaxCreditsYear = 12;
                
                
                
                this.percentage = 100; 
                this.regularTaxPercentage = 20;
                this.emergencyTaxPercentage = 40;
                this.taxCreditsTwoEmployeers = this.yearlyTaxCredits + 1775;                        
            }

  
    
    
    /**
     * 
     * @return weekly TAX CREDITS
     */
    public int getWeeklyTaxCreditsYear() {
          
        int weeklyTaxCrdits = this.yearlyTaxCredits / this.weeklyTaxCreditsYear;
        
        return weeklyTaxCrdits;
    }

    
    /**
     * 
     * @return  weekly Pay Limit
     */
    public double getWeeklyPayLimit() {
        
        double weeklyPayLimit=0;
       
        weeklyPayLimit = this.rateBand1 / this.weeklyTaxCreditsYear;

        // two decimal number formating    
        weeklyPayLimit = Math.round(weeklyPayLimit * 100);
        weeklyPayLimit = weeklyPayLimit/100;
        
        return weeklyPayLimit;
    }

    
    /**
     * 
     * @return fortnightly Pay Limit
     */
    public double getFortnighlyPayLimit() {
        
        double fortnightlyPayLimit=0;
       
        fortnightlyPayLimit = this.rateBand1 / this.weeklyTaxCreditsYear;

        // two decimal number formating    
        fortnightlyPayLimit = Math.round(fortnightlyPayLimit * 100);
        fortnightlyPayLimit = fortnightlyPayLimit/100;
        
        return fortnightlyPayLimit;
    }

    
   /**
    * 
    * @return monthly Pay Limit
    */
    public double getMonthlyPayLimit() {
        
        double monthlyPayLimit=0;
       
        monthlyPayLimit = this.rateBand1 / this.weeklyTaxCreditsYear;

        // two decimal number formating    
        monthlyPayLimit = Math.round(monthlyPayLimit * 100);
        monthlyPayLimit = monthlyPayLimit/100;
        
        return monthlyPayLimit;
    }

    
    /** 
    * @param amount get user input
    * Calculate the user input against Yearly Tax Credits
    */
    public void SinglePersonTaxCreditBalance(int amount){

       if(this.yearlyTaxCredits < amount)
       {
           System.out.println("Invalid");
       }else{
           this.yearlyTaxCredits = (this.yearlyTaxCredits - amount);
           System.out.println(" ");
           System.out.println("Remaining Tax Credits : " + this.yearlyTaxCredits);
       }
   }

    
    /**
     * @return the amount of (TAX Credits) left 
     */
    public int getYearlyTaxCredits() {
        return yearlyTaxCredits;
    }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
    

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getRegularTaxPercentage() {
        return regularTaxPercentage;
    }

    public void setRegularTaxPercentage(int regularTaxPercentage) {
        this.regularTaxPercentage = regularTaxPercentage;
    }

    public int getEmergencyTaxPercentage() {
        return emergencyTaxPercentage;
    }

    public void setEmergencyTaxPercentage(int emergencyTaxPercentage) {
        this.emergencyTaxPercentage = emergencyTaxPercentage;
    }

    public double getTaxCreditsTwoEmployeers() {
        return taxCreditsTwoEmployeers;
    }

    public void setTaxCreditsTwoEmployeers(double taxCreditsTwoEmployeers) {
        this.taxCreditsTwoEmployeers = taxCreditsTwoEmployeers;
    }
               
    
            
               
}
