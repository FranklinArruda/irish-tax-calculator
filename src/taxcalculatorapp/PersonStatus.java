
    package taxcalculatorapp;
    import java.io.IOException;
    import java.text.DecimalFormat;

    /**
     *
     * @author FRANKLIN
     */
    public class PersonStatus {
            
        public PersonStatus(){   
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
            Tax PRSI_tax = new Tax();
            Tax tax_40 = new Tax();
  
                      
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
                    int userTax = userInt.SinglePersonTaxCreditBalance(companyName[i],0);
                      
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
                    

                    // --------- PAYMENT FREQUENCY STATEMENT --------------
                    switch (paymentFrequency){

                        //----- WEEKLY PAYMENT ---------
                        case 1:

                        // get weekly gross pay from user
                        System.out.println("Enter your Weekly Groos pay for " + companyName[i]);
                        double weeklyGrossPay = userDouble.getUserDouble(); // calling get user double to validate input 

                        // hours per week
                        System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                        double hoursPerWeek = userDouble.getUserDouble(); // calling get user double to validate input
                        
                        /**
                         * check whether user is being taxed at emergency tax
                         * calling get weekly pay limit Method against the weekly gross pay
                         * if weekly payment is greater than weekly pay limit 
                         * (Regular Tax 20%) + (Emergency Tax at 40%) + (PRSI 4% applies)
                         */
                        if (weeklyGrossPay > weeklyPayLimit.getWeeklyPayLimit()){
         
                            //-------------- REGULAR TAX DEDUCTION at 20% ---------------
                            
                            // Calling PRSI method agains the weekly gross pay in the parameters 
                            double PRSI = PRSI_tax.getPRSI_Tax(weeklyGrossPay);
                            System.out.println("PRSI DEDUCTIONS => " + PRSI ); 
                            
                            // 1) diference between gross pay & weekly gross pay limit = balance to be taxed at 40%
                            double remainingBalance = weeklyGrossPay - weeklyPayLimit.getWeeklyPayLimit();
                            System.out.format("Difference between Gross pay " + weeklyGrossPay + " & " + " Gross Pay limit is " + weeklyPayLimit.getWeeklyPayLimit() + " = %.2f\n", remainingBalance);
                         
                            // 2) Calling regular tax deduction and assingning the weekly limit in the parameters
                            double grossDeduction = weeklyPayLimit.regularTaxDeduction(weeklyPayLimit.getWeeklyPayLimit());
                            System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                            
                            // 3) finding the weekly tax credits
                            int currentTaxCredits = taxCredits.getWeeklyTaxCredits(userTax);
                            System.out.println("CURRENT TAX CREDITS OUT OF " + userTax + " = " +  currentTaxCredits);
                           
                            // 4) finding Tax Payable 20%
                            double taxPayble_20 = grossDeduction - taxCredits.getWeeklyTaxCredits(userTax);
                            System.out.format("TAX at 20 percent = %.2f\n", taxPayble_20);
                           
                            // 5) finding net pay 20%
                            double netPay_20 = weeklyPayLimit.getWeeklyPayLimit() - taxPayble_20;
                            System.out.format("NET PAY 20 percent = %.2f\n", netPay_20);
                           
                            
                            //-------------- EMERGENCY TAX DEDUCTION at 40% ---------------
                          
                            // 1) Calling emergency tax deduction and assigning the remaning balance in the parameter
                            double taxPayble_40 = tax_40.emergencyTaxDeduction(remainingBalance);
                            System.out.format("GROSS DEDUCTIONS at 40 = %.2f\n", taxPayble_40);
                          
                            // 2) total deduction 
                            double TotalPayble = taxPayble_20 + taxPayble_40 ;
                            System.out.format("TOTAL DEDUCTIONS = %.2f\n", TotalPayble);
                         
                            // 3) finding NET PAY
                            double NET_PAY = weeklyGrossPay - TotalPayble;
                            System.out.format("NET PAY = %.2f\n", NET_PAY);
                     
                            //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
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

                             System.out.println("However, your groos pay is above the 352 weekly which means you also must "
                                    + "pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");
                             
                            System.out.println("Your Tax Credits is Currently \u20ac" + userTax + " divided by " 
                                    + "52 Number of weeks = \u20ac" +df.format(currentTaxCredits) + " for each gross payment limit on a weekly bases");

                            System.out.println("This \u20ac " + df.format(currentTaxCredits) + " only works for the Gross Pay (Limit) as shown below:" + "\n");

                            System.out.println("Your weekly Gross Pay is \u20ac " + df.format(weeklyGrossPay) + " over the limit of \u20ac" + weeklyPayLimit.getWeeklyPayLimit());

                            System.out.println("The difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(remainingBalance)  + " in which is calculate at 40% "
                                    + "and the \u20ac " + weeklyPayLimit.getWeeklyPayLimit() + " at 20%" + "\n");

                            System.out.println("First, we calculate your gross pay \u20ac " + df.format(weeklyGrossPay) + " at 4% PRSI = \u20ac " + PRSI);
                            
                            System.out.println("Then your Gross Pay Limit \u20ac " + weeklyPayLimit.getWeeklyPayLimit() +" x 20% = "+ "\u20ac " + df.format(grossDeduction)
                                    + " - \u20ac " + df.format(currentTaxCredits) + " Tax Credits = \u20ac " + df.format(taxPayble_20) + " Tax paybale");
                            System.out.println("Gross Pay (limit) \u20ac " + weeklyPayLimit.getWeeklyPayLimit() + " -  \u20ac " + taxPayble_20 + " > Tax at 20%  = NET PAY at 20 percent \u20ac " + df.format(netPay_20) + "\n");

                            System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%)" + "\n" 
                                    +"We must now calculate the Difference we found at Rate Band 2 (40%)" + "\n");

                            System.out.println("The remainig balance is => \u20ac " + df.format(remainingBalance) + " x 40% / 100% =  \u20ac " + df.format(taxPayble_40));
                            System.out.println("Your tax calculation is \u20ac " + df.format(taxPayble_20) + " + \u20ac " + df.format(taxPayble_40) +" = TOATL Deductions \u20ac " + df.format(TotalPayble));
                            System.out.println("( \u20ac " + df.format(TotalPayble) + " - \u20ac " + df.format(weeklyGrossPay) +" )"+ "\n");

                            System.out.println("And \u20ac " + df.format(weeklyGrossPay) + " - PRSI " + PRSI);
                            System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n"); 
                        }

                           
                       /**
                        * if weekly payment is less than weekly pay limit, but greater than 352 
                        * (Regular Tax 20%) + (PRSI 4% applies)
                        */
                        else if (weeklyGrossPay <= weeklyPayLimit.getWeeklyPayLimit() && (weeklyGrossPay > 352)){
                            
                            // Calling PRSI method agains the weekly gross pay in the parameters 
                            double PRSI = PRSI_tax.getPRSI_Tax(weeklyGrossPay);
                            System.out.println("PRSI DEDUCTIONS => " + PRSI );
                            
                            // 1) Calling regular tax deduction and assingning the weekly limit in the parameters
                            double grossDeduction = weeklyPayLimit.regularTaxDeduction(weeklyGrossPay);
                            System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                          
                            // 2) finding Tax Payble
                            double taxPayable = grossDeduction - taxCredits.getWeeklyTaxCredits(userTax);

                            // 3) finding net pay 20%
                            double NET_PAY = weeklyGrossPay - taxPayable - PRSI;
                            
                            
                            //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                            System.out.println("You are not at (Emergency Tax) by " + companyName[i] + " "
                                  + " Understand why by reading the information below " + "\n");

                            System.out.println("Because your Gross Pay is " + df.format(weeklyGrossPay)
                                     +" below the weekly pay limit > ( " + weeklyPayLimit.getWeeklyPayLimit() + "). Your wages is calculate at 20% ONLY" + "\n");
                            
                            System.out.println("However, your groos pay is above the 352 weekly which means you also must "
                                    + " pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");
                            
                            System.out.println("First we find the gross deductions of your weekly gross pay " 
                            + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(grossDeduction));

                            System.out.println("Then we use your current Tax Credits of  \u20ac " + taxCredits.getWeeklyTaxCredits(userTax) + " less gross deduction" 
                                     + " \u20ac" + df.format(grossDeduction) + " = \u20ac " + df.format(taxPayable) + " Tax Payble" + "\n");
                          
                            System.out.println("At last, gross payment \u20ac " + df.format(weeklyGrossPay) + " - \u20ac" + df.format(taxPayable) + " Tax payable "
                                    + " - \u20ac " + df.format(PRSI) + " PRSI");

                            System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n"); 
                       }
                           
                       /**
                        * if weekly payment is less than weekly pay limit and 352 euros 
                        * (Regular Tax 20%) ONLY
                        */
                        else {
                            
                            // 1) Calling regular tax deduction and assingning the weekly limit in the parameters
                            double grossDeduction = weeklyPayLimit.regularTaxDeduction(weeklyGrossPay);
                            System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                          
                            // 2) finding Tax Payble
                            double taxPayable = grossDeduction - taxCredits.getWeeklyTaxCredits(userTax);

                            // 3) finding net pay 20%
                            double NET_PAY = weeklyGrossPay - taxPayable;


                            //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                            System.out.println("You are not being taxed  at (Emergency Tax) by " + companyName[i] + "."
                                  + " Understand why by reading the information below " + "\n");

                            System.out.println("Because your Gross Pay is " + df.format(weeklyGrossPay)
                                     +" below the weekly pay limit and is less than 352 > ( " + weeklyPayLimit.getWeeklyPayLimit() + "). Your salary is calculate at 20% ONLY" + "\n");

                            System.out.println("First we find the gross deductions of your weekly gross pay " 
                            + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(grossDeduction));

                            System.out.println("Then we use your current Tax Credits of  \u20ac " + taxCredits.getWeeklyTaxCredits(userTax) + " less gross deduction" 
                                     + "  \u20ac" + df.format(grossDeduction) + " = \u20ac " + df.format(taxPayable) + " Tax Payble" + "\n");

                            System.out.println("At last, tax payble \u20ac " + df.format(taxPayable) + " - \u20ac" + df.format(weeklyGrossPay) + " weekly gross pay");

                            System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n"); 
                        }
                    }
                }
            }
        }



