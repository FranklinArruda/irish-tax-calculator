
    package taxcalculatorapp;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
import java.util.Scanner;

    /**
     * @author FRANKLIN
     */
    public class Tax {
    
        // Setting up Attributes 
        private double singlePersonRateBand;
        private double marriedPersonRateBand;
        private double singlePersonTaxCredits;
        private double marriedPersonTaxCredits;
        private int regularTax;
        private double emergencyTax;
        private double PRSI_credits;


        // default constructor (initialised)  
        public Tax(){

            this.singlePersonRateBand = 40000;
            this.marriedPersonRateBand = 49000;
            this.singlePersonTaxCredits = 3500;
            this.marriedPersonTaxCredits = 5325; //3500 + 1775 = Married person (5325)
            this.regularTax = 20;
            this.emergencyTax = 40; 
            this.PRSI_credits = 12;
        }
        
        
        /**
         * it calculates 4% from income over 352 per week
         * @param salary that holds the variable of weekly payment
         * @return Deducted salary
         */
        public double getPRSI_Tax(double salary) {

           double taxRate_4 = 4;
         
           double PRSIresults = taxRate_4 * salary / 100 - this.PRSI_credits;

            // two decimal number formating    
            PRSIresults = Math.round(PRSIresults * 100);
            PRSIresults = PRSIresults/100;

        return PRSIresults;
        }
        
        
        /**
         * It calculates USC Based on the user input
         * If gross income for the year is up to 12.012,01 ( taxed at 0.5% )
         * If gross income for the year is above 12.000,01 ( taxed at 2% )
         * If gross income for the year is in between 22,9920.01 and 70,044,00 ( taxed at 4.5% )
         * @param salary to assign the result from the main
         * @return USC results
         */
        public double getUSC(double salary) {

            // I chose scanner over BufferedReader as I am reading double from the user 
            Scanner myKeyboard = new Scanner( System.in );
            
            double incomeBand_1 = 0.5; // Up to €12,012.01  at 0.5%
            double incomeBand_2 = 2;  // From €12,012.01 at 2%
            double incomeBand_3 = 4.5;  //From €22,920.01 to €70,044 at 4.5%     
            
            double USC_results = 0; // USC results
            double getUserUSC = -1; // get user input
            boolean valid = false; // boolean to validate USC
            
            //will keep doing while is not valid
            do{  
                try {
                     //getUserpension = Integer.parseInt(myKeyboard.readLine().trim()); 
                     getUserUSC = myKeyboard.nextDouble();
                     
                    //check that the value is allowed by checking range
                    if (getUserUSC <0){
                        System.err.println("Invalid value entered. Please enter a number greater than ZERO");    
                        valid = false;
                    }
                    
                    // it calculates income up to 12,012.00 a year at (0,5%)
                    else if (getUserUSC <12012){ 
                        USC_results = incomeBand_1 * salary / 100;
                        valid = true;//must be OK
                    }
                    
                    // it calculates income from 12,012.00 to 22,920.00 a year at (2%)
                    else if ((getUserUSC >12012) && (getUserUSC <22920)){
                        USC_results = incomeBand_2 * salary / 100;
                        valid = true;//must be OK
                    }
                    
                    // it calculates income from 22,920.00 up to 70,044.00 a year at (4.5%)
                    else if ((getUserUSC >22920) && (getUserUSC <70044)){
                        USC_results = incomeBand_3 * salary / 100;
                        valid = true;//must be OK
                    }
                    
                        // two decimal number formating   
                        getUserUSC = Math.round(getUserUSC * 100);
                        getUserUSC = getUserUSC/100;
                        
                }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number
                        System.err.println("Only numbers.Please try again!");  
                    }  
            }while (!valid);
        return USC_results;
        }
 
       
        /**
         * Calculates Pension Scheme against user input
         * @param salary as parameter
         * @return pension results
         */
        public double getPension(double salary) {

            // I chose scanner over BufferedReader as I am reading double from the user 
            Scanner myKeyboard = new Scanner( System.in );
             
            double PensionResults = 0;
            double getUserpension = -1;
            boolean valid = false;
            String userChoice = "";
            
                userChoice=myKeyboard.next().trim();
           
                try {
                    switch(userChoice){
                        case "Y": 
                           do{
                                System.out.println("What percentage are you paying on your Pension Scheme?");
                                getUserpension = myKeyboard.nextDouble();
                                
                               //check that the value is allowed by checking range
                               if (getUserpension <0){
                                   System.err.println("Invalid value entered. Please enter a number greater than ZERO");    
                                } 
                                
                               //must be OK
                               else { 
                                PensionResults = getUserpension * salary / 100;
                                valid = true;
                                } 
                           }while (!valid);
                            break;
                            
                        case "N":
                            System.out.println("No pension contribution for this period");
                            break;
                    }
                     
                        // two decimal number formating    
                        PensionResults = Math.round(PensionResults * 100);
                        PensionResults = PensionResults/100;
                        
                }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number
                        System.err.println("Only numbers.Please try again!");  
                    }  
        return PensionResults;
        }
        
        
        /**
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
         * 
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
         * @return weekly Tax Credits
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
         * @return Fortnightly Tax Credits
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
         * @return monthly Tax Credits
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
         * Get user input to set the Tax Credit validation 
         */
        public double SinglePersonTaxCreditBalance(String companyName, double amount ){

             double remainingBalnce = 0;
             
             do{ 
                try {
                    System.out.println("Enter Tax Credits for " + companyName );
                   
                    // call method get user Double
                    amount = getUserDouble(); 

                }catch(Exception e){
                   // this will be if the parseInt threw an error -- so the user did not enter a number
                    System.err.println("Only numbers.Please try again!");  
                }    
                        if( this.singlePersonTaxCredits >= amount ) {
                            
                            this.singlePersonTaxCredits = this.singlePersonTaxCredits - amount;
                            remainingBalnce = this.singlePersonTaxCredits; // stores the remaining Tax Credits
                            
                            System.out.println("Remaining Tax Credits : " + remainingBalnce + "\n");
                            break; // will stop the loop if there is anough Tax Credits           
                        }
                        else {
                            System.err.println("Not enough Tax Credits");
                        }
             }while((amount > remainingBalnce)); 
             
             return amount;
        }
        
     
        /**
         * @return the remaining Tax Credits
         */
        public double getSinglePersonTaxCredits() {
            return singlePersonTaxCredits;
        } 
        
        
        /**
         * @param companyName print out company name  
         * @param amount  
         * @return  amount
         * Get user input to set the Tax Credit validation 
         */
        public double MarriedPersonTaxCreditBalance(String companyName, double amount){
            
            double remainingBalnce = 0;
             
             do{ 
                try {
                    System.out.println("Enter Tax Credits for " + companyName );
                    amount = getUserInt(); // call method get user input

                }catch(Exception e){
                // this will be if the parseInt threw an error -- so the user did not enter a number
                System.err.println("Only numbers.Please try again!");  
                }    
                         if( this.marriedPersonTaxCredits >= amount ) {
                            
                            this.marriedPersonTaxCredits = this.marriedPersonTaxCredits - amount;
                            remainingBalnce = this.marriedPersonTaxCredits; // stores the remaining Tax Credits
                            
                            System.out.println("Remaining Tax Credits : " + remainingBalnce + "\n");
                            break; // will stop the loop if there is anough Tax Credits           
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
        
        
        /**
        * If not valid, keep asking
        * @param prompt -- the number to the user
        * @return -- valid user input
        */
        public int getUserInt(){

            BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));

            boolean valid = false;
            int userInput=-1; //defaulted to -1 because it needs to have a value for validating

                do{     
                    try {
                        userInput = Integer.parseInt(myKeyboard.readLine());

                        //check that the value is allowed by checking range
                        if (userInput <=0){
                            System.err.println("Invalid value entered. Please enter a number greater than ZERO");    
                        }
                        else {
                            //must be OK
                            valid = true;
                        }
                    }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number
                        System.err.println("Only numbers.Please try again!");  
                    }    
                }while (!valid);

                //userInput must be int now
                return (userInput);  
        }
        
        
        /**
        * If not valid, keep asking
        * @return -- valid user input
        */
        public double getUserDouble(){

            Scanner myKeyboard = new Scanner( System.in );
            boolean valid = false;
            double userInput=-1; //defaulted to -1 because it needs to have a value for validating

                do{     
                    try {
                        userInput = myKeyboard.nextDouble();

                        //check that the value is allowed by checking range
                        if (userInput <= 0){
                            System.err.println("Invalid value entered. Please enter a number greater than ZERO");    
                        }
                        else {
                            //must be OK
                            valid = true;
                        }
                    }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number
                        System.err.println("Only numbers.Please try again!");  
                    }    
                }while (!valid);

                //userInput must be double now
                return (userInput);  
           }


        /**
         * Get some text from the user (via keyboard)
         * @param prompt -- the message or request to the user
         * @return - the users input as a String
         * If user does not enter text, output an error and ask them again
         */
        public String getUserText (String prompt){

            BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));

            boolean valid=false;
            String userInput ="";

                //prompt user until input is valid
                do{  
                   try {

                    System.out.println(prompt); 
                    userInput = myKeyboard.readLine().trim();

                     // space between letter 'Z' and square brackets ']' in case the user types two words   
                     if(!userInput.matches("[a-zA-Z ]+")){
                        System.err.println("Only Letters Allowed.Please try again!");  
                        valid=false;
                    }

                     else{
                        valid=true;
                     }
                   }catch(Exception e){
                        // this will be if the parseInt threw an error -- so the user did not enter a number   
                    System.err.println("Something went Wrong. Please try Again!");  
                   }
                // space between letter 'Z' and square brackets ']' in case the user types two words 
                }while (!userInput.matches("[a-zA-Z ]+"));

                //userInput must be text now
                return (userInput);
        }
    }
