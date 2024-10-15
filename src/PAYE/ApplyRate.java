package PAYE;

import Utilities.CalculateHelper;

/**
 * @author FRANKLIN
 */

public class ApplyRate {
  // set Attribute fields
  private double totalDeductions;
  private double deductionOnRemainingBlc_40;
  private double deductionOnCutOffLimit_20;
  private double cutOffForPeriod;
  private double remainingBlcDifference;

  // it creates an obejct from the method to format to two decimal places
  CalculateHelper DF = new CalculateHelper();
  
// default construcotr and variables initialized with a clean state
  public ApplyRate() {
    totalDeductions = 0.0;
    deductionOnRemainingBlc_40 = 0.0;
    deductionOnCutOffLimit_20 = 0.0;
    cutOffForPeriod = 0.0;
    remainingBlcDifference = 0.0;
  }

  // getters and setter
  public double getTotalDeductions() {
    return DF.roundToTwoDecimalPlaces(this.totalDeductions);
  }

  public double getDeductionOnRemainingBlc_40() {
    return DF.roundToTwoDecimalPlaces(this.deductionOnRemainingBlc_40);
  }

  public double getDeductionOnCutOffLimit_20() {
    return DF.roundToTwoDecimalPlaces(this.deductionOnCutOffLimit_20);
  }

  public double getCutOff_ForPeriod() {
    return DF.roundToTwoDecimalPlaces(this.cutOffForPeriod);
  }

  public double getRemainingBlcDifference() {
    return DF.roundToTwoDecimalPlaces(this.cutOffForPeriod);
  }

  // apply both rates, 20% lower rate and 40& higher rate(emergency tax)    
  public ApplyRateResults applyRate(double grossPay, double cutOffForPeriod, double taxCredits) {

    ApplyRateResults myResults = new ApplyRateResults();

    // assinging cut-off for period to attributes and use getter method to get the current cut off.
    this.cutOffForPeriod = this.cutOffForPeriod = cutOffForPeriod;

        // ------ RATES AT 40% WITH NO CREDITS------
        if (!(taxCredits > 0)) {
          this.totalDeductions = this.totalDeductions + grossPay * 40 / 100; // no credits applied  
          
          myResults.setResult(this.totalDeductions);
          myResults.setMessage("Because your tax Credits is (0.0), Deductions at a Higher rate on full income is applied at 40%." +
            "qualifying you to be on (Emergency Tax) Basis.");

          return myResults; // breaks out of the if-else statement if credits are zero.          
        } 
        
    
        // ------ RATES AT 20% ------
        else if (grossPay <= getCutOff_ForPeriod()) {
          this.totalDeductions = grossPay * 20 / 100 - taxCredits; 

          if (taxCredits >= this.totalDeductions) {
            myResults.setMessage("No tax applied here as your Tax Credits of: " + DF.roundToTwoDecimalPlaces(taxCredits) +
              " covers the deduction for this period of: " + getTotalDeductions());

            myResults.setResult(this.totalDeductions);
            return myResults;
          } else {
            myResults.setMessage("Deductions at a Lower rate of 20% as the gross pay is less or equal to cut-off for period. " +
              " This means your taxes are normal and correct.");
            return myResults;
          }
        } 
        
        
       // ------ RATES AT 20% & 40% WITH CREDITS------
        else if (grossPay > getCutOff_ForPeriod()) {
            this.remainingBlcDifference = this.remainingBlcDifference + grossPay - cutOffForPeriod;
            this.deductionOnRemainingBlc_40 = this.totalDeductions + this.remainingBlcDifference * 40 / 100; // no credits applied  
            this.deductionOnCutOffLimit_20 = this.totalDeductions + cutOffForPeriod * 20 / 100 - taxCredits;

            myResults.setMessage("Deductions on the first amount within cut-off limit of: " + getCutOff_ForPeriod() + " is: " + getDeductionOnCutOffLimit_20() +
              " Deductions at a Higher rate of 40% on the remaining balance from cut-off for period of: " + getRemainingBlcDifference() + " is: " + getDeductionOnRemainingBlc_40());
          }
          this.totalDeductions += (this.deductionOnRemainingBlc_40 + this.deductionOnCutOffLimit_20);

          return myResults;
        }
}