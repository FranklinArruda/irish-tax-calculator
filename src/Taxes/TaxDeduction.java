
    package Taxes;
    
import Enums.TaxCalculatorContainer;
    import Enums.TaxCalculatorContainer.StandardIncomeBand;
    import Enums.TaxCalculatorContainer.TaxRates;
    import Enums.TaxCalculatorContainer.USC_IncomeBand;
    import Enums.TaxCalculatorContainer.USC_PercentageBand;
    
    import Utilities.Utilities;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    
    /**
     * @author FRANKLIN
     */
    public class TaxDeduction implements TaxDeductionInterface {
        
        // GLOBAL INT PERCENTAGE 100 as helper for the calculation
        static final int PERCENTAGE = 100;
      
        //========================================================================
        
        @Override
        public double getPRSI(double salary) {
          
            double PRSI = 12;
            double taxRate_4 = 4;
            double PRSIresults = taxRate_4 * salary / PERCENTAGE - PRSI;

            // two decimal number formating    
            PRSIresults = Math.round(PRSIresults * PERCENTAGE);
            PRSIresults = PRSIresults / PERCENTAGE;

        return PRSIresults;
        }
        
        //======================================================================== 
                
        @Override
        public double getUSC(double salary, String companyName) {
        
            double USC_results = 0; // USC_Income_Bands results
            double getUserUSC = -1; // get user input
            boolean valid = false; // boolean to validate USC_Income_Bands
             
            // Display message to the user and the company 
            System.out.println("Enter your Estimated Income for " + companyName + " this year: ");
            
            Utilities userUSC = new Utilities(); // Get user input and validate it
            getUserUSC =userUSC.getUserDouble();
                    
            try {
                 do{    
                        
                        // it calculates income up to 12,012.00 a year at (0,5%)
                        if ((getUserUSC < USC_IncomeBand.USC_RATE_BAND_1.getUSC_Band())){ 
                            USC_results = (USC_PercentageBand.PERCENTAGE_BAND_1.getPercentage() * salary / PERCENTAGE);
                            valid = true;
                        }

                        // it calculates income from 12,012.00 to 22,920.00 a year at (2%)
                        else if ((getUserUSC > USC_IncomeBand.USC_RATE_BAND_1.getUSC_Band()) && (getUserUSC < USC_IncomeBand.USC_RATE_BAND_2.getUSC_Band())){ // ENUM
                            USC_results = (USC_PercentageBand.PERCENTAGE_BAND_2.getPercentage() * salary / PERCENTAGE);
                            valid = true;
                        }

                        // it calculates income from 22,920.00 up to 70,044.00 a year at (4.5%)
                        else if ((getUserUSC > USC_IncomeBand.USC_RATE_BAND_2.getUSC_Band()) && (getUserUSC < USC_IncomeBand.USC_RATE_BAND_3.getUSC_Band())){ // ENUM
                            USC_results = (USC_PercentageBand.PERCENTAGE_BAND_3.getPercentage() * salary / PERCENTAGE);
                            valid = true;
                        }
    
                    // two decimal number formating   
                    getUserUSC = Math.round(getUserUSC * PERCENTAGE);
                    getUserUSC = getUserUSC / PERCENTAGE;

                }while (!valid); // It keeps going while is not VALID

            }catch(Exception e){
                
                    // this will be if the parseInt threw an error -- so the user did not enter a number
                    System.err.println("Only numbers.Please try again!");  
                }     
        return USC_results; // Will return the results
        }
 
        //======================================================================== 
      
        @Override
        public double getPension(double salary){

           BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
             
            double PensionResults = 0; // initialized to zero
            double getUserpension; // holds user input
            boolean userPension = false; // It must be greater than zero (validate int)
            boolean valid = false; // Validate String
            String userChoice; // Validate with if / else
               
                do{  
                   try {
                       
                    // Prompt message to the User  
                    String prompt="Are you in any Pension Scheme? \n"
                                +"--------------------------\n"
                                +"Enter: Y (YES) to proceed \n"
                                +"Enter: N (No) if not sure:";
                    System.out.println(prompt);
                    
                    // removes any spaces and uppecasethe any letters
                    userChoice = myKeyboard.readLine().trim().toUpperCase(); 
                     
                    // space between letter 'Z' and square brackets ']' in case the user types two words   
                    
                    if(!userChoice.matches("[a-zA-Z ]+") && (!userChoice.equals("Y")) && (!userChoice.equals("N"))){
                        System.err.println("Only Letters Allowed. Please try again!");
                        valid=false;
                    }
                    
                     // if user enters ( Y )
                    else if (userChoice.equals("Y")){
                            
                            // ANOTHER do while within if statement
                            do{
                                try{
                                    System.out.println("What percentage are you paying on your Pension Scheme?");
                                    getUserpension = Double.parseDouble(myKeyboard.readLine());

                                    //check that the value is allowed by checking range
                                    // if user input notgreater than zero will keep reapeating until goes throug
                                    if (getUserpension <=0){
                                        System.err.println("Invalid value entered. Please enter a number greater than ZERO");
                                        userPension=false;
                                     } 
                                    
                                    // must be OK
                                    // it calculates the salary against percentage and user input
                                    // stores the results in (Pension Results)
                                    else {   
                                         PensionResults = getUserpension * salary / PERCENTAGE; 
                                         userPension = true;
                                     }
                                }catch(Exception e){
                                        // this will be if the parseInt threw an error -- so the user did not enter a number
                                        System.err.println("Only Numbers. Please try again!");  
                                } 
                            }while ((!userPension));
                            valid=true; // MUST be ok
                     }
                     
                    // if user enters ( N )
                    else if (userChoice.equals("N")){
                            System.out.println("No pension contribution for this period");
                            valid=true;
                    }
                    
                    // if user enters neither N or Y
                    else if (!valid){
                            System.err.println("Only  Y (YES) or  N (NO) allowed. Please try Again!");
                            valid=false;
                    }
                     
                   }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number   
                    System.err.println("Something went Wrong. Please try Again!");  
                   }
                }while (!valid);   
        return PensionResults;
        }
        
        //======================================================================== 
        
        @Override
        public double regularTaxDeduction(double salary){
            
            int RegularTax = TaxRates.REGULAR_TAX.getTax(); // Enum Object
            
            // finding gross deductions (Gross pay * 20% / 100%)                  
            double regularTaxDeduction =  salary * RegularTax / PERCENTAGE;
            
            // two decimal number formating    
            regularTaxDeduction = Math.round(regularTaxDeduction * PERCENTAGE);
            regularTaxDeduction = regularTaxDeduction / PERCENTAGE;
                               
            return regularTaxDeduction;
        }
        
        //======================================================================== 
        
        @Override
        public double emergencyTaxDeduction(double remainingBalance){
            
            int EmergencyTax = TaxRates.EMERGENCY_TAX.getTax(); // Enum Object
            
            // finding gross deductions (Gross pay * 20% / 100%)                
            double emergencyTaxDeduction = remainingBalance * EmergencyTax / PERCENTAGE;
            
            // two decimal number formating    
            emergencyTaxDeduction = Math.round(emergencyTaxDeduction * PERCENTAGE);
            emergencyTaxDeduction = emergencyTaxDeduction / PERCENTAGE;
                               
            return emergencyTaxDeduction;
        }
        
        //======================================================================== 
        
        @Override
        public double getWeeklyTaxCredits(double weeklyTaxCrdits) {

            int numberOfWeeks = 52;           
            weeklyTaxCrdits = weeklyTaxCrdits  / numberOfWeeks;
            
            // two decimal number formating    
            weeklyTaxCrdits = Math.round(weeklyTaxCrdits * PERCENTAGE);
            weeklyTaxCrdits = weeklyTaxCrdits / PERCENTAGE;

            return weeklyTaxCrdits;
        }                

        //======================================================================== 
      
        @Override
        public double getWeeklyPayLimit() {

            // number of weeks throghout the year
            int numberOfWeeks = 52;

            // Calling Enum Standard Income Band to calculate the monthly limit as per Rate Band
            double weeklyPayLimit = StandardIncomeBand.RATE_BAND_1.getRateBand() / numberOfWeeks;
            // two decimal number formating    
            weeklyPayLimit = Math.round(weeklyPayLimit * PERCENTAGE);
            weeklyPayLimit = weeklyPayLimit / PERCENTAGE;

            return weeklyPayLimit; // will return the weekly limit based on the (Rate band 1) 40,000.00 year
        }

        //======================================================================== 
        
        @Override
        public double getFortnightlyTaxCredits(double fortnightlyTaxCrdits) {

            // number of fortnightly throghout the year
            int numberOfWeeks = 26;
            fortnightlyTaxCrdits = fortnightlyTaxCrdits / numberOfWeeks;
            
            // two decimal number formating    
            fortnightlyTaxCrdits = Math.round(fortnightlyTaxCrdits * PERCENTAGE);
            fortnightlyTaxCrdits = fortnightlyTaxCrdits / PERCENTAGE;

            return fortnightlyTaxCrdits;
        }

        //======================================================================== 
        
        @Override
        public double getFortnightlyPayLimit() {

            // number of fortnightly throghout the year
            int numberOfWeeks = 26;       

            // Calling Enum Standard Income Band to calculate the monthly limit as per Rate Band
            double fortnightlyPayLimit = StandardIncomeBand.RATE_BAND_1.getRateBand() / numberOfWeeks;
            
            // two decimal number formating    
            fortnightlyPayLimit = Math.round(fortnightlyPayLimit * PERCENTAGE);
            fortnightlyPayLimit = fortnightlyPayLimit / PERCENTAGE;

            return fortnightlyPayLimit; // will return the fortnigh limit based on the (Rate band 1) 40,000.00 year
        }

        //======================================================================== 
        
        @Override
        public double getMonthlyTaxCredits(double MonthlyTaxCredits) {

            int numberOfWeeks = 12;
            MonthlyTaxCredits = MonthlyTaxCredits / numberOfWeeks;
            
            // two decimal number formating    
            MonthlyTaxCredits = Math.round(MonthlyTaxCredits * PERCENTAGE);
            MonthlyTaxCredits = MonthlyTaxCredits / PERCENTAGE;
            
            return MonthlyTaxCredits;
        }

        //======================================================================== 
        
        @Override
        public double getMonthlyPayLimit() {

            // number of throghout the year
            int numberOfMonths = 12; 

            // Calling Enum Standard Income Band to calculate the monthly limit as per Rate Band
            double monthlyPayLimit = StandardIncomeBand.RATE_BAND_1.getRateBand() / numberOfMonths;
            
            // two decimal number formating    
            monthlyPayLimit = Math.round(monthlyPayLimit * PERCENTAGE);
            monthlyPayLimit = monthlyPayLimit / PERCENTAGE;

            return monthlyPayLimit; // will return the monthly limit based on the (Rate band 1) 40,000.00 year
        }
    }
    
