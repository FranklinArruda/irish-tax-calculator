
package Enums;

/**
 * @author FRANKLIN
 */
public final class TaxCalculatorContainer {
    
    //============================================================
    
    public enum Status {
    SINGLE, MARRIED,
    }
    
    //============================================================
    
    public enum PaymentFrequency {
    WEEKLY, FORTNIGHTLY, MONTHLY;
    }
    
    //============================================================
    
    public enum StandardIncomeBand {
    
    RATE_BAND_1(40000), // Rate band of 40,000.00 year
    RATE_BAND_2(70000); // Rate band of 70,000.00 year
    
    final int rateBand;
    
    StandardIncomeBand(int rateBand){
    this.rateBand = rateBand;
    }
    
    public int getRateBand(){
    return rateBand;
    }
    }
    
    //============================================================
    
    public enum HourOfTheDay {  
    
    MID_DAY(12),
    AFTERNOON(17);
    
    final int hourOfTheDay;
    
    HourOfTheDay(int hourOfTheDay){
    this.hourOfTheDay = hourOfTheDay;
    }
    
     public int getHour() {
    return hourOfTheDay;
    }   
    }
    
    //============================================================
    
    public enum TaxRates {
    
    REGULAR_TAX(20), // Regular TaxRates deduction at 20%
    EMERGENCY_TAX(40); // Emergency TaxRates decuction at 40%
    
    final int tax;
    
    TaxRates(int tax){
    this.tax = tax;
    }
    
    public int getTax(){
    return tax;
    }
    }

    //============================================================
    
    public enum USC_IncomeBand {
        
    // Income Band RATES    
    USC_RATE_BAND_1(12012), 
    USC_RATE_BAND_2(22920),  
    USC_RATE_BAND_3(70044);    
    
    final int USC_BAND;
    
    USC_IncomeBand(int deduct){
    this.USC_BAND = deduct;
    }
    
    public int getUSC_Band() {
    return USC_BAND;
    }
    }

    //============================================================
    
    public enum USC_PercentageBand {
    
    //Income Band PERCENTAGE 
    PERCENTAGE_BAND_1(5), // Up to €12,012.01  at 0.5%
    PERCENTAGE_BAND_2(2), // From €12,012.01 at 2%
    PERCENTAGE_BAND_3((int)4.5); //From €22,920.01 to €70,044 at 4.5% 
    
    final int income_band;
    
    USC_PercentageBand(int band){
    this.income_band = band;
    }
    
    public int getPercentage(){
    return income_band;
    }
    }
    
    //============================================================
    
    //USED this as reference to assign ENUMS with String values
    //https://www.programiz.com/java-programming/enum-string
    //https://stackoverflow.com/questions/3978654/best-way-to-create-enum-of-strings
    
    
    public enum UserMessage {
    
    EMERGENCY_TAX_MESSAGE_1{

        @Override
        public String toString(){
        return"- When you started in your new job and have no PPSN.";
        }
    },
           
    EMERGENCY_TAX_MESSAGE_2{        
    
        @Override
        public String toString(){
        return"- When you started in your new Job. You have PPSN, but the first salary will always be taxed at 40%.";
        }
    },
    
    EMERGENCY_TAX_MESSAGE_3{
        
        @Override
        public String toString(){
        return"- When you are working for multiple employer and you do not have enough Tax Credits for each employer.";
        }
    },
    
    EMERGENCY_TAX_MESSAGE_4{      
    
        @Override
        public String toString(){
        return "- When you are working for a specific employer and your Tax Credits is Zero (0).\n"
                + "Therefore, You are being Taxed at (Emergency Tax) even here because you have no Tax Credits left to use it.";
        }
      }
    }
  }
