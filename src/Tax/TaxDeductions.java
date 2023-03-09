
    package Tax;
    import Utilities.Utilities;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import Enums.USC;
   

    /**
     * @author FRANKLIN
     */
    public class TaxDeductions {
    
        // Setting up Attributes 
        private double singlePersonRateBand;  // ENUM
        private double marriedPersonRateBand; // ENUM
        private double singlePersonTaxCredits;
        private double marriedPersonTaxCredits;
        private int regularTax;// ENUM
        private double emergencyTax;// ENUM
        private double PRSI;// ENUM


        // default constructor (initialised)  
        public TaxDeductions(){
            this.singlePersonRateBand = 40000;
            this.marriedPersonRateBand = 49000;
            this.singlePersonTaxCredits = 3500;
            this.marriedPersonTaxCredits = 5325; //3500 + 1775 = Married person (5325)
            this.regularTax = 20;
            this.emergencyTax = 40; 
            this.PRSI = 12;
        }
  
        
        /**
         * Pay Related Social Insurance (PRSI)
         * it calculates 4% from income over 352 per week
         * @param salary that holds the variable of weekly payment
         * @return Deducted salary
         */
        public double getPRSI(double salary) {

           double taxRate_4 = 4;
         
           double PRSIresults = taxRate_4 * salary / 100 - this.PRSI;

            // two decimal number formating    
            PRSIresults = Math.round(PRSIresults * 100);
            PRSIresults = PRSIresults/100;

        return PRSIresults;
        }
        
        
        /**
         * Universal Social Charge (USC)
         * Use Utilities class to get user input 
         * It calculates USC Based on the user input
         * If gross income for the year is up to 12.012,01 ( taxed at 0.5% )
         * If gross income for the year is above 12.000,01 ( taxed at 2% )
         * If gross income for the year is in between 22,9920.01 and 70,044,00 ( taxed at 4.5% )
         * @param salary to assign the result from the main
         * @param companyName
         * @return USC results
         */
        public double getUSC(double salary, String companyName) {
           
            // Enums OBJECTS
            USC A = USC.USC_A;
            USC B = USC.USC_B;
            USC C = USC.USC_C;
            
            // Getting enums deduction and putting in the if/else (stataement) to validate
            A.getDeduction();
            B.getDeduction();
            C.getDeduction();
            
            double incomeBand_1 = 0.5; // Up to €12,012.01  at 0.5%
            double incomeBand_2 = 2;  // From €12,012.01 at 2%
            double incomeBand_3 = 4.5;  //From €22,920.01 to €70,044 at 4.5%     
            
            double USC_results = 0; // USC results
            double getUserUSC = -1; // get user input
            boolean valid = false; // boolean to validate USC
             
                System.out.println("Enter your Estimated Income for " + companyName + " this year: ");
                Utilities userUSC = new Utilities();
                getUserUSC =userUSC.getUserDouble();
                //getUserUSC = getUserDouble();
            
                try {
                    do{
                        // it calculates income up to 12,012.00 a year at (0,5%)
                        // it must be higher than 0
                        if ((getUserUSC < A.getDeduction())){ // ENUM
                            USC_results = (incomeBand_1 * salary / 100);
                            valid = true;//must be OK
                        }

                        // it calculates income from 12,012.00 to 22,920.00 a year at (2%)
                        else if ((getUserUSC > A.getDeduction()) && (getUserUSC < B.getDeduction())){ // ENUM
                            USC_results = (incomeBand_2 * salary / 100);
                            valid = true;//must be OK
                        }

                        // it calculates income from 22,920.00 up to 70,044.00 a year at (4.5%)
                        else if ((getUserUSC > B.getDeduction()) && (getUserUSC < C.getDeduction())){ // ENUM
                            USC_results = (incomeBand_3 * salary / 100);
                            valid = true;//must be OK
                        }
                    
                        // two decimal number formating   
                        getUserUSC = Math.round(getUserUSC * 100);
                        getUserUSC = getUserUSC/100;
                        
                    }while (!valid);   
                    
                }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number
                        System.err.println("Only numbers.Please try again!");  
                    }     
        return USC_results;
        }
 
       
        /**
         * Pension Scheme
         * Calculates Pension Scheme against user input
         * VALIDATE Yes or No for the user
         * If user chooses Y, then (case "Y") is executed
         * If user chooses N, then (case "N") carry on with the program 
         * @param salary as parameter
         * @return pension results
         */
        public double getPension(double salary){

           BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
             
            double PensionResults = 0;
            double getUserpension = -1;
            boolean userPension = false;
            boolean valid = false;
            String userChoice = "";
               
                do{  
                   try {
                       
                    String prompt="Are you in any Pension Scheme? \n"
                                +"--------------------------\n"
                                +"Enter: Y (YES) to proceed \n"
                                +"Enter: N (No) if not sure:";
                    System.out.println(prompt);
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
                                    if (getUserpension <=0){
                                        System.err.println("Invalid value entered. Please enter a number greater than ZERO");
                                        userPension=false;
                                     } 
                                    //must be OK
                                    else {                                      
                                         PensionResults = getUserpension * salary / 100; 
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
        
        
        /** 
         * Income being TAXED AT 20% (Regular TaxDeductions)
  Exceeds the weekly limit which is (Rate Band 1 divided by weeks of the year)
         * @param salary that holds the salary value when method is called
         * @return gross deductions taxed at 20%
         */
        public double regularTaxDeduction(double salary){
            
            // finding gross deductions (Gross pay * 20% / 100%)
            int percentage = 100;                    
            double regularTaxDeduction =  salary * this.regularTax / percentage;
            
            // two decimal number formating    
            regularTaxDeduction = Math.round(regularTaxDeduction * 100);
            regularTaxDeduction = regularTaxDeduction/100;
                               
            return regularTaxDeduction;
        }
        
        
        /**
         * Income being TAXED AT 40% (Emergency TaxDeductions)
         * @param remainingBalance that holds the salary value when method is called
         * @return gross deductions taxed at 40%
         */
        public double emergencyTaxDeduction(double remainingBalance){
            
            // finding gross deductions (Gross pay * 20% / 100%)
            int percentage = 100;                    
            double emergencyTaxDeduction = remainingBalance * this.emergencyTax / percentage;
            
            // two decimal number formating    
            emergencyTaxDeduction = Math.round(emergencyTaxDeduction * 100);
            emergencyTaxDeduction = emergencyTaxDeduction/100;
                               
            return emergencyTaxDeduction;
        }
        
        
        /**
         * 
         * @param weeklyTaxCrdits
         * @return weekly TaxDeductions Credits
         */
        public double getWeeklyTaxCredits(double weeklyTaxCrdits) {

            int numberOfWeeks = 52;
            weeklyTaxCrdits = weeklyTaxCrdits  / numberOfWeeks;
            
            // two decimal number formating    
            weeklyTaxCrdits = Math.round(weeklyTaxCrdits * 100);
            weeklyTaxCrdits = weeklyTaxCrdits/100;

            return weeklyTaxCrdits;
        }                


        /**
         * 
         * @return  Weekly Pay Limit
         */
        public double getWeeklyPayLimit() {

            // number of weeks throghout the year
            int numberOfWeeks = 52;

            double weeklyPayLimit = this.singlePersonRateBand / numberOfWeeks;

            // two decimal number formating    
            weeklyPayLimit = Math.round(weeklyPayLimit * 100);
            weeklyPayLimit = weeklyPayLimit/100;

            return weeklyPayLimit;
        }


        /**
         * 
         * @param fortnightlyTaxCrdits
         * @return Fortnightly TaxDeductions Credits
         */
        public double getFortnightlyTaxCredits(double fortnightlyTaxCrdits) {

            // number of fortnightly throghout the year
            int numberOfWeeks = 26;
            fortnightlyTaxCrdits = fortnightlyTaxCrdits / numberOfWeeks;
            
            // two decimal number formating    
            fortnightlyTaxCrdits = Math.round(fortnightlyTaxCrdits * 100);
            fortnightlyTaxCrdits = fortnightlyTaxCrdits/100;

            return fortnightlyTaxCrdits;
        }


        /**
         * 
         * @return fortnightly Pay Limit
         */
        public double getFortnightlyPayLimit() {

            // number of fortnightly throghout the year
            int numberOfWeeks = 26;       

            double fortnightlyPayLimit = this.singlePersonRateBand / numberOfWeeks;

            // two decimal number formating    
            fortnightlyPayLimit = Math.round(fortnightlyPayLimit * 100);
            fortnightlyPayLimit = fortnightlyPayLimit/100;

            return fortnightlyPayLimit;
        }


        /**
         * 
         * @param MonthlyTaxCredits
         * @return monthly TaxDeductions Credits
         */
        public double getMonthlyTaxCredits(double MonthlyTaxCredits) {

            int numberOfWeeks = 12;
            MonthlyTaxCredits = MonthlyTaxCredits / numberOfWeeks;
            
            // two decimal number formating    
            MonthlyTaxCredits = Math.round(MonthlyTaxCredits * 100);
            MonthlyTaxCredits = MonthlyTaxCredits/100;
            
            return MonthlyTaxCredits;
        }


        /**
         * 
         * @return Monthly Pay Limit
         */
        public double getMonthlyPayLimit() {

            // number of throghout the year
            int numberOfMonths = 12; 

            double monthlyPayLimit = this.singlePersonRateBand / numberOfMonths;

            // two decimal number formating    
            monthlyPayLimit = Math.round(monthlyPayLimit * 100);
            monthlyPayLimit = monthlyPayLimit/100;

            return monthlyPayLimit;
        }

        
        /**
         * @param companyName print out company name  
         * @param amount  
         * @return  amount
 Get user input to set the TaxDeductions Credit validation by using UTILITIES CLASS
         */
        public double SinglePersonTaxCreditBalance(String companyName, double amount ){

             double remainingBalnce = 0;
             
             do{ 
                try {
                    System.out.println("Enter Tax Credits for " + companyName );
                   
                    // call method get user Double
                    Utilities userTax = new Utilities();
                    amount = userTax.getUserDouble(); //amount = getUserDouble();
                   
                }catch(Exception e){
                   // this will be if the parseInt threw an error -- so the user did not enter a number
                    System.err.println("Only numbers.Please try again!");  
                }    
                        if( this.singlePersonTaxCredits >= amount ) {
                            
                            this.singlePersonTaxCredits = this.singlePersonTaxCredits - amount;
                            //remainingBalnce = this.singlePersonTaxCredits; // stores the remaining TaxDeductions Credits
                            
                            //System.out.println("Remaining TaxDeductions Credits : " + remainingBalnce + "\n");
                            break; // will stop the loop if there is anough TaxDeductions Credits           
                        }
                        else {

                            // stores the remaining TaxDeductions Credits
                            remainingBalnce = this.singlePersonTaxCredits; 
                            System.err.println("Not enough Tax Credits");
                            System.out.println("This is the Remaining Tax Credits : " + remainingBalnce + " for company " + companyName + "\n");
                        }
             }while((amount > remainingBalnce)); 
          return amount;
        }
        
     
        /**
         * @return the remaining TaxDeductions Credits
         */
        public double getSinglePersonTaxCredits() {
            return singlePersonTaxCredits;
        } 
        
        
        /**
         * @param companyName print out company name  
         * @param amount  
         * @return  amount
 Get user input to set the TaxDeductions Credit validation 
         */
        public double MarriedPersonTaxCreditBalance(String companyName, double amount){
            
            double remainingBalnce = 0;
             
             do{ 
                try {
                    System.out.println("Enter Tax Credits for " + companyName );
                    
                }catch(Exception e){
                // this will be if the parseInt threw an error -- so the user did not enter a number
                System.err.println("Only numbers.Please try again!");  
                }    
                         if( this.marriedPersonTaxCredits >= amount ) {
                            
                            this.marriedPersonTaxCredits = this.marriedPersonTaxCredits - amount;
                            remainingBalnce = this.marriedPersonTaxCredits; // stores the remaining TaxDeductions Credits
                            
                            System.out.println("Remaining Tax Credits : " + remainingBalnce + "\n");
                            break; // will stop the loop if there is anough TaxDeductions Credits           
                        }
                        else {
                            System.err.println("Not enough Tax Credits");
                        }
             }while((amount > remainingBalnce)); 
         return amount;
       }
        
        /**
         * @return the amount of (TAX Credits) left 
         */
        public double getMarriedPersonTaxCredits() {
             return marriedPersonTaxCredits;
         }
    }
    
