package Enums;

/**
 * @author FRANKLIN
 */
public class EnumTaxContainer {
    
    //===========  USC  ====================
    public enum USCThresHold {
       RATE1(12012, 0.5), 
       RATE2(25760, 2.0), // 12012 + 13748 = 25760 at 2% (Usc Applied between 12012 and 25760)
       RATE3(44284, 4.0), // 25760 + 44284 = 70000 at 4% (Usc Applied between 25760 and 70000)
       RATE4(70000, 8.0); // 70000 at 8% (Usc Applied on income above 70000 or Emergency Tax)

       public final double threshold;
       public final double rate;

       USCThresHold(double threshold, double rate) {
           this.threshold = threshold;
           this.rate = rate;
       }
       
       public double getUSCRate(){
       return this.rate;
       }
       
       public double getUSCThreshold(){
       return this.threshold;
       }
   }
    
    //===========  PRSI  ====================
    public enum PRSIRates {
        RATE1(352.00), 
        RATE2(424.00); 
       
         public final double rates;
      
         PRSIRates(double rates){
             this.rates = rates;    
        }
      
        public double getPRSIRate(){
        return this.rates;
        }
    } 
    
    
    //=======  TAX RATES 20% & 40%  =======
    public enum TaxRates{
        REGULAR_TAX(20),
        EMERGENCY_TAX(40);

        final int  tax;  

        TaxRates(int tax){
             this.tax = tax;
        }

        public int getTaxRate(){
        return this.tax;
        }      
    }
}
