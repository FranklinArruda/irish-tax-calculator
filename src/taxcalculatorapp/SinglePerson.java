
    package taxcalculatorapp;
    import java.io.IOException;
    import java.text.DecimalFormat;

    /**
     *
     * @author FRANKLIN
     */
    public class SinglePerson {
        
        private final int regular_tax;
        private final int emergency_tax;
        private final int percentage;
        
        public SinglePerson(){
            
            this.regular_tax = 20;
            this.emergency_tax = 40;
            this.percentage = 100;
        }
        
        // Global decimal format to display double in 2 decimal place 
        public static DecimalFormat df = new DecimalFormat("0.00");

        /**
         * 
         * @throws IOException 
         */
        public void SinglePersonTax() throws IOException{
 
            // Instantiating class to use method for calculating tax
            Tax userInt = new Tax(); 
            Tax userText = new Tax(); 
            Tax userDouble = new Tax();
            Tax weeklyPayLimit = new Tax();
            Tax taxCredits = new Tax();
  
            // variables
            double netDeduction_20 = 0;
            double netDeduction_40 = 0;
           
            System.out.println("How many employers are you currently working for?");

            // method to get numbers of employers from user
            int numberOfEmployers = userInt.getUserInt(0); // initialized zero

            // getting employers name based upon numbers of employers entered by the user
            String companyName [] = new String [numberOfEmployers];

                // get user input by reading employers name through numbers of employers
                // call get user text method 
                for(int i=0; i < numberOfEmployers; i++){

                    // calling get user text Method
                    companyName[i] = userText.getUserText("Employer " +(i +1) + " Name:");     
                }  

                for(int i=0; i<companyName.length; i++) {

                   //method to get USERs Tax Credits
                   userInt.SinglePersonTaxCreditBalance(companyName[i]);

                    // Payment frequency menu   
                    String userMenu = "How often are getting paid by " + companyName[i] + " ? " + "\n"
                            + "Please Enter: " + "\n"
                            + "------------- " + "\n"
                            + "1: Weekly " + "\n"
                            + "2: Fortnightly " + "\n" 
                            + "3: Monthly "+ "\n";

                    // output message for the user
                    System.out.println(userMenu);

                    // get user input and assigning the (i) counter into Get user int method
                    int paymentFrequency = userInt.getUserInt(i);

                    // Payment Frequency statement
                    switch (paymentFrequency){

                        // WEEKLY PAYMENT
                        case 1:

                        // weekly gross from the user
                        System.out.println("Enter your Weekly Groos pay for " + companyName[i]);
                        double weeklyGrossPay = userDouble.getUserDouble(); //Double.parseDouble(myKeyboard.readLine());

                        // hours per week
                        System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                        double hoursPerWeek = userDouble.getUserDouble();//Double.parseDouble(myKeyboard.readLine());

                            // check whether user is being taxed at emergency tax
                            // calling get weekly pay limit Method against the weekly gross pay
                           if (weeklyGrossPay > weeklyPayLimit.getWeeklyPayLimit()){
                               
                               ///////// CALCULATION AT 20% first ///////////////////

                             // 2) diference between gross pay & weekly gross pay limit = balance to be taxed at 40%
                            double remainingBalance = weeklyGrossPay - weeklyPayLimit.getWeeklyPayLimit();
                            //System.out.format("Difference between Gross pay & Gross Pay limit is = %.2f\"", grossDeduction);  
                         
                            // 3) finding gross deductions (Gross pay * 20% / 100%)
                            double grossDeduction = weeklyPayLimit.getWeeklyPayLimit() * this.regular_tax / this.percentage;
                           //System.out.format("Gross Dedctions = %.2f\"", grossDeduction);  
                          
                            // 4) finding the weekly tax credits
                               //System.out.println("NET TAX CREDITS = " + taxCredits.getWeeklyTaxCredits());

                            // 5) finding Tax Payble
                             double taxPayable = grossDeduction - taxCredits.getWeeklyTaxCredits();
                          
                            // 6) finding net pay 20%
                            double netPay_20 = weeklyPayLimit.getWeeklyPayLimit() - taxPayable;
                            //System.out.format("NET PAY = %.2f\"", netPay_20);
                            
                            // 5) finding NET Deductions 20%
                            netDeduction_20 = grossDeduction - taxCredits.getWeeklyTaxCredits();
                            //System.out.format("NET DEDUCTIONS = %.2f\"", netDeduction_20);

                            
                            ///////// CALCULATION AT 40% now ///////////////////

                             // 1) calculating remaing balance from gross pay and limit per week
                             netDeduction_40 = remainingBalance * this.emergency_tax / this.percentage;
                            // System.out.format("NET DEDUCTIONS 40 = %.2f\"", netDeduction_40);
                             
                            // 2) total deduction 
                            double totalDeduction = netDeduction_20 + netDeduction_40;
                            //System.out.format("TOTAL DEDUCTIONS = %.2f\"", totalDeduction);
                            
                            
                            //double netPay_40 = netDeduction_40;

                            // 3) finding NET PAY
                            double NET_PAY = weeklyGrossPay - totalDeduction;
                           // System.out.format("NET PAY = %.2f\"", NET_PAY);
                                 
                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                 + " Understand why by reading the information below " + "\n");

                            System.out.println("Emergency Tax is calculaded in three different ways: " + "\n"        
                            +"1: When you started in your new job and have no PPSN " + "\n"
                            +"2: When you started in your new Job. You have PPSN, but the first salary will always be taxed at 40%" + "\n"
                            +"3: When you are working for multiple employer and you do not have enough Tax Credits for each employer" +"\n");

                            System.out.println("As you are (Single) your RATE BAND is at 40,000.00 a year. That is divided by "
                            +"52 weeks because you are getting paid weekly" + "\n");
   
                            System.out.println("Therefore \u20ac " + df.format(weeklyPayLimit.getWeeklyPayLimit()) +" is your gross pay (limit) per week "
                            + "and any value above that limit will be taxed at 40%" + "\n");

                            System.out.println("Your Tax Credits is Currently \u20ac" + taxCredits.getWeeklyTaxCredits() + " divided by " 
                                    + " 52 Number of weeks = \u20ac" +df.format(netDeduction_20) + " for each gross payment limit on a weekly bases");
                            
                            System.out.println("This \u20ac " + df.format(netDeduction_20) + " only works for the Gross Pay (Limit) as shown below:" + "\n");

                            System.out.println("Your weekly Gross Pay is \u20ac " + df.format(weeklyGrossPay) + " over the limit of \u20ac" + weeklyPayLimit.getWeeklyPayLimit());

                            System.out.println("The difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(remainingBalance)  + " in which is calculate at 40% "
                                    + "and the \u20ac " + weeklyPayLimit.getWeeklyPayLimit() + " at 20%" + "\n");


                            System.out.println("First, we calculate your Gross Pay Limit \u20ac " + weeklyPayLimit.getWeeklyPayLimit() +" x 20% = "+ "\u20ac " + df.format(grossDeduction)
                                    + " - \u20ac " + df.format(netDeduction_20) + " Tax Credits = \u20ac " + df.format(taxPayable) + " Tax paybale");
                            System.out.println("Gross Pay (limit) \u20ac " + weeklyPayLimit.getWeeklyPayLimit() + " -  \u20ac " + taxPayable + " > Tax at 20%  = NET PAY \u20ac " + df.format(netPay_20) + "\n");

                            System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%)" + "\n" 
                                    +"We must now calculate the Difference we found at Rate Band 2 (40%)" + "\n");

                            System.out.println("The remainig balance is => \u20ac " + df.format(remainingBalance) + " x 40% / 100% =  \u20ac " + df.format(netDeduction_40));
                            System.out.println("Your tax calculation is \u20ac " + df.format(netDeduction_20) + " + \u20ac " + df.format(netDeduction_40) +" = TOATL Deductions \u20ac " + df.format(totalDeduction));
                            System.out.println("( \u20ac " + df.format(totalDeduction) + " - \u20ac " + df.format(weeklyGrossPay) +" )"+ "\n");
                            
                            System.out.println("Therefore your NET PAY ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n"); 
                           }

                           else if (weeklyGrossPay <= weeklyPayLimit.getWeeklyPayLimit()){
                           System.out.println("You are not at (Emergency Tax) by " + companyName[i] + "\n");
                           }
                        }
                     }
                  }
               }



