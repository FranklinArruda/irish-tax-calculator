
package taxcalculatorapp;

// Setting up Attributes  
public class Tax {
    
    private double rateBand1;
    private int singlePersonTaxCredits;
    private int marriedPersonTaxCredits;
    private double percentage;
    private int regularTaxPercentage;
    private int emergencyTaxPercentage;
    private double taxCreditsTwoEmployeers;

    // default constructor (initialised)  
    public Tax(){

        this.rateBand1 = 40000;
        this.singlePersonTaxCredits = 3500;
        this.marriedPersonTaxCredits = 5325;
        this.percentage = 100; 
        this.regularTaxPercentage = 20;
        this.emergencyTaxPercentage = 40;                     
    }

    
    /**
     * 
     * @return weekly Tax Credits
     */
    public int getWeeklyTaxCredits() {
        
        int numberOfWeeks = 52;
        int weeklyTaxCrdits = this.singlePersonTaxCredits / numberOfWeeks;
        
        return weeklyTaxCrdits;
    }

    
    /**
     * 
     * @return  Weekly Pay Limit
     */
    public double getWeeklyPayLimit() {
        
        // number of weeks throghout the year
        int numberOfWeeks = 52;
        
        double weeklyPayLimit = this.rateBand1 / numberOfWeeks;

        // two decimal number formating    
        weeklyPayLimit = Math.round(weeklyPayLimit * 100);
        weeklyPayLimit = weeklyPayLimit/100;
        
        return weeklyPayLimit;
    }
    
    
    /**
     * 
     * @return Fortnightly Tax Credits
     */
    public int getFortnightlyTaxCredits() {
        
        int numberOfWeeks = 26;
        int weeklyTaxCrdits = this.singlePersonTaxCredits / numberOfWeeks;
        
        return weeklyTaxCrdits;
    }
    
    
    /**
     * 
     * @return fortnightly Pay Limit
     */
    public double getFortnightlyPayLimit() {
        
        // number of fortnightly throghout the year
        int numberOfWeeks = 26;       
        
        double fortnightlyPayLimit = this.rateBand1 / numberOfWeeks;

        // two decimal number formating    
        fortnightlyPayLimit = Math.round(fortnightlyPayLimit * 100);
        fortnightlyPayLimit = fortnightlyPayLimit/100;
        
        return fortnightlyPayLimit;
    }

    
    /**
     * 
     * @return monthly Tax Credits
     */
    public int getMonthlyTaxCredits() {
        
        int numberOfWeeks = 12;
        int weeklyTaxCrdits = this.singlePersonTaxCredits / numberOfWeeks;

        return weeklyTaxCrdits;
    }
    
    
    /**
     * 
     * @return Monthly Pay Limit
     */
    public double getMonthlyPayLimit() {
        
        // number of throghout the year
        int numberOfMonths = 12; 
        
        double monthlyPayLimit=0;
       
        monthlyPayLimit = this.rateBand1 / numberOfMonths;

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

       if(this.singlePersonTaxCredits < amount)
       {
           System.out.println("Invalid");
       }else{
           this.singlePersonTaxCredits = (this.singlePersonTaxCredits - amount);
           System.out.println(" ");
           System.out.println("Remaining Tax Credits : " + this.singlePersonTaxCredits);
       }
   }
    
    /**
     * @return the amount of (TAX Credits) left 
     */
    public int getSinglePersonTaxCredits() {
        return singlePersonTaxCredits;
    } 
    
    
    /** 
     * @param amount get user input
     * Calculate the user input against Yearly Tax Credits
     */
    public void MarriedPersonTaxCreditBalance(int amount){

       if(this.marriedPersonTaxCredits < amount)
       {
           System.out.println("Invalid");
       }else{
           this.marriedPersonTaxCredits = (this.marriedPersonTaxCredits - amount);
           System.out.println(" ");
           System.out.println("Remaining Tax Credits : " + this.marriedPersonTaxCredits);
       }
   }
    
    
    /**
     * @return the amount of (TAX Credits) left 
     */
    public int getMarriedPersonTaxCredits() {
        return marriedPersonTaxCredits;
    } 

    
      
}
