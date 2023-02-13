
    package taxcalculatorapp;

    // Setting up Attributes  

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class Tax {

        private double rateBand1;
        private int singlePersonTaxCredits;
        private int marriedPersonTaxCredits;
        private int regularTax;
        private int emergencyTax;
        private int PRSI_credits;


        // default constructor (initialised)  
        public Tax(){

            this.rateBand1 = 40000;
            this.singlePersonTaxCredits = 3500;
            this.marriedPersonTaxCredits = 5325; //3500 + 1775 = Married person (5325)
            this.regularTax = 20;
            this.emergencyTax = 40; 
            this.PRSI_credits = 12;
        }

   


        
        
        /**
         * 
         * @param weeklyTaxCrdits
         * @return weekly Tax Credits
         */
        public int getWeeklyTaxCredits(int weeklyTaxCrdits) {

            int numberOfWeeks = 52;
            weeklyTaxCrdits = weeklyTaxCrdits  / numberOfWeeks;

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
         * @param fortnightlyTaxCrdits
         * @return Fortnightly Tax Credits
         */
        public int getFortnightlyTaxCredits(int fortnightlyTaxCrdits) {

            // number of fortnightly throghout the year
            int numberOfWeeks = 26;
            fortnightlyTaxCrdits = fortnightlyTaxCrdits / numberOfWeeks;

            return fortnightlyTaxCrdits;
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
         * @param MonthlyTaxCredits
         * @return monthly Tax Credits
         */
        public int getMonthlyTaxCredits(int MonthlyTaxCredits) {

            int numberOfWeeks = 12;
            MonthlyTaxCredits = MonthlyTaxCredits / numberOfWeeks;

            return MonthlyTaxCredits;
        }


        /**
         * 
         * @return Monthly Pay Limit
         */
        public double getMonthlyPayLimit() {

            // number of throghout the year
            int numberOfMonths = 12; 

            double monthlyPayLimit = this.rateBand1 / numberOfMonths;

            // two decimal number formating    
            monthlyPayLimit = Math.round(monthlyPayLimit * 100);
            monthlyPayLimit = monthlyPayLimit/100;

            return monthlyPayLimit;
        }

        
        /**
         * @param companyName print out company name  
         * @param amount  
         * @return  amount
         * @throws IOException 
         * Get user input to set the Tax Credit validation 
         */
        public int SinglePersonTaxCreditBalance(String companyName, int amount ) throws IOException{

             int remainingBalnce = 0;
             
             do{ 
                try {
                    System.out.println("Enter Tax Credits for " + companyName );
                    amount = getUserInt(0); // call method get user input

                }catch(Exception e){
                    
                    // this will be if the parseInt threw an error -- so the user did not enter a number
                    System.out.println("Only numbers.Please try again!");  
                }    
                        if( this.singlePersonTaxCredits >= amount ) {
                            
                            this.singlePersonTaxCredits -= amount;
                            remainingBalnce = this.singlePersonTaxCredits; // stores the remaining Tax Credits
                            
                            System.out.println("Remaining Tax Credits : " + remainingBalnce + "\n");
                            break; // will stop the loop if there is anough Tax Credits           
                   }
                    else{
                            System.out.println("Not enough Tax Credits");
                 }
             }while((amount > remainingBalnce)); 
             
             return amount;
        }
        
     
        /**
         * @return the remaining Tax Credits
         */
        public int getSinglePersonTaxCredits() {
            return singlePersonTaxCredits;
        } 
        
        
        
        
        
        
        
        


                

                    /**
                     * it calculates 4% of income over 352 per week
                     * @param salary that holds the variable of weekly payment
                     * @return Deducted salary
                     */
                    public double getPRSI_Tax(double salary) {
                       
                       double percentage = 4;
                       double percent = 100;
                       
                       double PRSIresults = percentage * salary / percent - this.PRSI_credits;
                      
                        // two decimal number formating    
                        PRSIresults = Math.round(PRSIresults * 100);
                        PRSIresults = PRSIresults/100;
                                    
                    return PRSIresults;
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
        
        /**
         * 
         * @return gross deductions taxed at 20%
         */
        public double regularTaxDeduction(){
            
            // finding gross deductions (Gross pay * 20% / 100%)
            int percentage = 100;                    
            double regularTaxDeduction = getWeeklyPayLimit() * this.regularTax / percentage;
            
            // two decimal number formating    
            regularTaxDeduction = Math.round(regularTaxDeduction * 100);
            regularTaxDeduction = regularTaxDeduction/100;
                               
            return regularTaxDeduction;
        }
        
        
        /**
         * 
         * @return gross deductions taxed at 40%
         */
        public double emergencyTaxDeduction(){
            
            // finding gross deductions (Gross pay * 20% / 100%)
            int percentage = 100;                    
            double emergencyTaxDeduction = getWeeklyPayLimit() * this.emergencyTax / percentage;
            
            // two decimal number formating    
            emergencyTaxDeduction = Math.round(emergencyTaxDeduction * 100);
            emergencyTaxDeduction = emergencyTaxDeduction/100;
                               
            return emergencyTaxDeduction;
        }


        /**
        * If not valid, keep asking
        * @param prompt -- the number to the user
        * @return -- valid user input
        */
        public int getUserInt(int prompt){

            BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));

            boolean valid = false;
            int userInput=-1; //defaulted to -1 because it needs to have a value for validating

               do{     
                   try {
                       userInput = Integer.parseInt(myKeyboard.readLine());

                       //check that the value is allowed by checking range
                       if (userInput <=0){
                           System.out.println("Invalid value entered. Please enter a number greater than ZERO");    
                       }
                       else {
                           //must be OK
                           valid = true;
                       }
                   }catch(Exception e){
                       // this will be if the parseInt threw an error -- so the user did not enter a number
                       System.out.println("Only numbers.Please try again!");  
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

            BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));

            boolean valid = false;
            double userInput=-1; //defaulted to -1 because it needs to have a value for validating

               do{     
                   try {
                       userInput = Double.parseDouble(myKeyboard.readLine());

                       //check that the value is allowed by checking range
                       if (userInput <= 0){
                           System.out.println("Invalid value entered. Please enter a number greater than ZERO");    
                       }
                       else {
                           //must be OK
                           valid = true;
                       }
                   }catch(Exception e){
                       // this will be if the parseInt threw an error -- so the user did not enter a number
                       System.out.println("Only numbers.Please try again!");  
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
                    System.out.println("Only Letters Allowed.Please try again!");  
                    valid=false;
                }

                 else{
                    valid=true;
                 }
               }catch(Exception e){
                    // this will be if the parseInt threw an error -- so the user did not enter a number   
                System.out.println("Something went Wrong. Please try Again!");  
               }
            // space between letter 'Z' and square brackets ']' in case the user types two words 
            }while (!userInput.matches("[a-zA-Z ]+"));

            //userInput must be text now
            return (userInput);
        }
    }
