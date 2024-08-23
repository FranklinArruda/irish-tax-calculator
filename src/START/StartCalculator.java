
package START;
import Taxes.Helper;
import Taxes.TaxDeductions;
import User.Married;
import User.Single;
import java.io.IOException;


/**
 *
 * @author STUDENT
 */
public class StartCalculator {

    /**
     * @param args the command line arguments
     */
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
        
    }
}
