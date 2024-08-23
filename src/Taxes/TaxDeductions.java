
package Taxes;
import Enums.EnumTaxContainer.PRSIRates;
import Enums.EnumTaxContainer.USCThresHold;

/**
 *
 * @author FRANKLIN
 */
public class TaxDeductions implements TaxDeductionsInterface{

    @Override
    public double getPRSI(double salary) {
    
        // imports Helper class to process the rates
        Helper getMath = new Helper();
        
        
        int ONE_SIXTH_DIVISOR = 6;
        int PRSI_credits = 12;
        int TAX_RATE = 4;
        double PRSIresults = 0;
        
    try{
        
        //If INCOME is equal or less than 352.00, not applicable here 
        if(salary <= PRSIRates.RATE1.getPRSIRate()){
            System.out.println("No PRSI applied.");
            return 0;
        }

        // if INCOME is between 352.01 and 424 PRSI is applied with 12 credits
        else if((salary > PRSIRates.RATE1.getPRSIRate()) &&(salary <= PRSIRates.RATE2.getPRSIRate())){
            System.out.println("PRSI is applied with 12 Credits.");

            // 1) CALCULATE One-sixth of your earnings over 352
            // subtract PRSI band by salary. It will return the remaining balance for the PRSI 
            double PRSIremainder = salary - PRSIRates.RATE1.getPRSIRate();
            System.out.println("PRSI Remainder " + PRSIremainder);

            // 2) Divide remiander (remaining balance by 6) It will return the (one-sixth) of salary 
            double oneSixthSalary = PRSIremainder / ONE_SIXTH_DIVISOR;
            System.out.println("PRSI one-sixth Salary: " + oneSixthSalary);

            // 3) Subtract the one-sixth salary by PRSI credits to get PRSI after credits
            double PRSI_after_credits = PRSI_credits - oneSixthSalary;
            System.out.println("PRSI After credits : " + PRSI_after_credits);

            /**
             * Since PRSI credits is applied to values between 352 and 424, when it reaches the 424 income
             * the 12 credits is used up, and any value above 424 will make the credits a negative value.
             * 
             * So in order to adjust the credits when it exhausted and avoid PRSI results being negative or not 
             * taxed properly, the below condition checks the PRSI after credits and set to zero if negative.
             */
            if (PRSI_after_credits < 0){
                System.out.println("PRSI IS NEGATIVE HERE: " +PRSI_after_credits );
                PRSI_after_credits = 0; 
            }

            System.out.println("PRSI After credits with condition: " + PRSI_after_credits);

            // 4) Calculate the BASIC PRSI at 4% of your earnings
            double PRSIdeductions =  getMath.applyRate(salary,TAX_RATE);
            System.out.println("PRSI Deductions at 4% rate: " + PRSIdeductions);

            // 5) Deduct the PRSI credits from the PRSI charge to get the amount of PRSI you pay 
            // PRSI Results: remainder taxed at 4% less PRSI credit of 12. 
            // Returns the PRSI for this period from (one-sixth) salary
            PRSIresults = PRSIdeductions - PRSI_after_credits;

            System.out.println("PRSI RESEULTS:" + getMath.roundToTwoDecimalPlaces(PRSIresults));
        }

          // if INCOME is greater than 424 PRSI is applied without credits on full income at 4%
        else if(salary > PRSIRates.RATE2.getPRSIRate()){
            System.out.println("PRSI is applied without Credits.");
            PRSIresults =  getMath.applyRate(salary,TAX_RATE); // call apply rate 
        }
        else{
            System.out.println("Something went Wrong!");
        }

     }catch (ArithmeticException e){   
      System.out.println("An arithmetic error occurred: " + e.getMessage());
    }
    return PRSIresults;        
}

    @Override
    public double getUSC(double userSalary, double YearToDateIncome) {

        // imports Helper class to process the rates
        Helper getApplyRate = new Helper();
        
       double limit = 13000; // Limit that is used to validate first USC deduction between 12,012 and 13,000
       
       // this variable is useless( MUST BE REMOVED)
       
       double YTD_BLC = 0.0; //it holds the difference between USC rates like (12012), (25760), and (70000) 
       double Total_USC = 0.0; // it holds the total USC results

    try{
        // USC tax no applied as the YTD is less than 12012
        if(YearToDateIncome <= USCThresHold.RATE1.getUSCThreshold()){
            System.out.println("Exempt!");
            return 0.0;
        }

        // USC on full income at 0.5% for the first time = 60;
        else if((YearToDateIncome > USCThresHold.RATE1.threshold)&& (YearToDateIncome <= limit)){
            YTD_BLC = YearToDateIncome - USCThresHold.RATE1.threshold;
            Total_USC = Total_USC + getApplyRate.applyRate(USCThresHold.RATE1.threshold,USCThresHold.RATE1.rate);
            

            System.out.println("USC at 0.5%: " + Total_USC);

                // means that the USC applied on full income 12012
                if(Total_USC == 60.06){ 
                    System.out.println(Total_USC + " at 0.5% on full income of 12012 deducted!");

                }
                
                // It validates income at 2% between 12012 - 13000
                if((Total_USC == 60.06) && ( YearToDateIncome > USCThresHold.RATE1.threshold)){
                    
                    System.out.println("Is greater than 12012");
                    
                    double incomeAt2 =getApplyRate.applyRate(userSalary,USCThresHold.RATE2.rate);
                    System.out.println("Difference: " + YTD_BLC );
                    System.out.println("USC at 2% on income above 12012 : " + incomeAt2);

                    // it return the total USC for the first 12012 at 2% and remaining balance at 2% that is
                    // not greater than 13000
                    Total_USC = Total_USC + incomeAt2;
                    System.out.println("Final USC when reaches 12012 and income is a bit higher: " + Total_USC);
                }
        }
        // at 2% from 13000 onwards and less than 25760
        else if((YearToDateIncome > limit)&& (YearToDateIncome <= USCThresHold.RATE2.threshold)){
            Total_USC = Total_USC + getApplyRate.applyRate(userSalary,USCThresHold.RATE2.rate);
            System.out.println("USC at 2% but it is greater than 13000 but less than 25760: " + Total_USC);
        }
        
        
        // at 4% from 25760 onwards and less than 44284
        else if((YearToDateIncome > USCThresHold.RATE2.threshold) && (YearToDateIncome <= USCThresHold.RATE4.threshold)){
            Total_USC = Total_USC + getApplyRate.applyRate(userSalary,USCThresHold.RATE3.rate);
            System.out.println("USC at 4% as it is greater than 25760 and less than 70000: " + Total_USC);
        }
              
        // at 8% as it reaches the 70000
        else if((YearToDateIncome > USCThresHold.RATE4.threshold)){
            Total_USC = Total_USC + getApplyRate.applyRate(userSalary,USCThresHold.RATE4.rate);
            System.out.println("USC at 8% as it is greater than 70000: " + Total_USC);
        }
    }catch(ArithmeticException e){
         System.out.println("An arithmetic error occurred: " + e.getMessage());
    }
      return Total_USC;
    
}
    @Override
    public double getPension(double salary) {
        return 0.0;
    }
    
    
    
}
