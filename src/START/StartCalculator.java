
package START;
import Taxes.Helper;
import Taxes.TaxDeductions;
import Taxes.TaxRates;
import User.Married;
import User.Single;
import java.io.IOException;

/**
 *
 * @author FRANKLIN
 */
public class StartCalculator {
    public static void main(String[] args) throws IOException {
       
        
        // TESTING SINGLE PERSON 
        
        String name = "Franklin";
        Single  singlePerson = new  Single();
        singlePerson.setName(name);
        System.out.println("Single Person's Name: " + singlePerson.getName());
        
        singlePerson.setTaxCreditsBalance(4000);
        singlePerson.taxCreditsWithdraw(5000);
        System.out.println("Single Person's Tax Credits Balance: " + singlePerson.getTaxCredits());
        System.out.println("Single Person's toSTRING > Tax Credits Balance:" + singlePerson.toString());
        System.out.println("------------------------------------------------------------");
        System.out.println("djndd");
        
      
                
        // TESTING MARRIED PERSON
        
        String marriedName = "Patricia";
        Married  mariedPerson = new  Married();
        mariedPerson.setName(marriedName);
        System.out.println("Married Person's Name: " + mariedPerson.getName());
        mariedPerson.setTaxCreditsBalance(5000);
        mariedPerson.taxCreditsWithdraw(500);
        
        System.out.println("Maried Person's Tax Credits Balance: " + mariedPerson.getTaxCredits());
        System.out.println("Maried Person's toSTRING > Tax Credits Balance:" + mariedPerson.toString());
        
        TaxDeductions calculateTax = new TaxDeductions();
        
        
        // ==================== TESTING 'PRSI + USC' ==================================
        System.out.println("----------------------------------\n");
        double PRSIsalry = 450.00;
        double PRSIRESULTS = calculateTax.getPRSI(PRSIsalry);
        
        Helper getMath = new Helper();
        System.out.println("PRSI RESULTS MAIN: " + getMath.roundToTwoDecimalPlaces(PRSIRESULTS));
        
        System.out.println("--------------------------------------\n");
        double USCRESULTS = calculateTax.getUSC(690, 11100);
        System.out.println("USC RESULTS MAIN: " + getMath.roundToTwoDecimalPlaces(USCRESULTS));
        
        
        
        
        
        // ==================== TESTING 'PENSION' ==================================
        String prompt = "Are you Pension Scheme?"
                + "-------------------------------"
                + "Enter: Y (YES) to proceed"
                + "Enter: N (No) if not sure";
        
        System.out.println(prompt);       
        
        System.out.println("Are you in any Pension Scheme?");
        System.out.println("--------------------------");
        System.out.println("Enter: Y (YES) to proceed");
        System.out.println("Or");
        System.out.println("Enter: N (No) if not sure");
        
        double pensionResults = calculateTax.getPension(690); 
        System.out.println("PENSION: " + pensionResults); 
        
        
        
        
        // ==================== TESTING 'RATES 20% & 40%' ===========================
        
        System.out.println("\n \n \n");
        double payLimit = 690;
        double GrossPay = 960;
        double remainingBLC = GrossPay - payLimit;
       
        
        TaxRates applyRegularRate = new TaxRates();
        
        double regularTaxResults = applyRegularRate.regularTax20(payLimit);
        double emergencyTaxResults = applyRegularRate.emergencyTax40(remainingBLC);
       
        double totalDeductions = regularTaxResults + emergencyTaxResults;
        System.out.println("BREAKDOWN:");
        System.out.println("Regular Tax: " + regularTaxResults + " Because your salary limit is: " + payLimit);
        System.out.println("Emergency Tax: " + emergencyTaxResults + " On the remaining BLC: " + remainingBLC);
        System.out.println("--------------------------------------------------------");
        System.out.println("( TOTAL Deductions after TAX: " + totalDeductions + " ) ");
        
    }
}
