
    
    package UserStatus;
    import Enums.TaxCalculatorContainer.PaymentFrequency;
    import Utilities.Utilities;
    import java.text.DecimalFormat;
    import Taxes.TaxDeduction;

    /**
     * @author FRANKLIN
     */
    public class UserStatus implements UserStatusInterface {
                    
        // GLOBAL DECIMAL FORMAT to display double in 2 decimal place 
        public static final DecimalFormat df = new DecimalFormat("0.00");

        @Override
        public void SinglePersonTax(){
            
            //USING UTILITIES CLASS FOR GETTING USER INPUT
            
            Utilities userInt = new Utilities();    // call method get user int 
            Utilities userDouble = new Utilities(); // call method get user text
            Utilities userText = new Utilities();   // call method get user double
           
            //=========================================================================================
            
            TaxDeduction weeklyLimit = new TaxDeduction(); // call weekly pay limit method
            TaxDeduction weekly = new TaxDeduction(); // call weekly gross deduction at 20%
            TaxDeduction fortnightlyPayLimit = new TaxDeduction(); // call fortnightly pay limit method
            TaxDeduction fortnightly = new TaxDeduction(); // call FORTNIGHT gross deduction at 20%
            TaxDeduction monthlyPayLimit = new TaxDeduction(); //// call monthly pay limit method
            TaxDeduction monthly = new TaxDeduction(); // call MONTHLY gross deduction at 20%
            
            //=========================================================================================
            
            UserTaxCredits UserTaxCredits = new UserTaxCredits(); // call method Set Single Person Tax Credits
                       
            //=========================================================================================
            
            TaxDeduction getWeeklyTax = new TaxDeduction(); // call method get weekly, fortnightly and monthly tax credits
            TaxDeduction get_PRSI = new TaxDeduction(); // call method get PRSI TaxDeduction
            TaxDeduction userPension = new TaxDeduction(); // call method get PRSI TaxDeduction
            TaxDeduction userUSC = new TaxDeduction(); // call method get USC TaxDeduction
            
            //=========================================================================================
            
            
            //Ask user how many employers they are currently working 
            System.out.println("How many employers are you currently working for?");
            int numberOfEmployers = userInt.getUserInt();  // method to get numbers of employers from user
            
            /*-----------------------------------------------------------
             Getting employers name based on the numbers of employers entered by the user
             and storing inside the array (Company Name)
            --------------------------------------------------------------*/
            String companyName [] = new String [numberOfEmployers];

            
                //===========NESTED LOOP==============================================================
                
                // to access array through the numbers of emplyers and calling get user text method
                // inside the for loop to state the companys name through the number of employers. 
                for(int i=0; i < numberOfEmployers; i++){
                    companyName[i] = userText.getUserText("Employer " +(i +1) + " Name:"); // calling get user text Method
                } 
                
                // to read companys name through numbers of employers
                // it will gets repeated until number of employers finish
                for(int i=0; i<companyName.length; i++) {
         
                    /*---------------------------------------------------------------------------------
                    Getting User Tax Credits (WITHDRAW)
                    
                    Holds in the UserTaxCreditsBlance variable to use it everywhere
                    using companys name in the parameter and amount of tax credits initialized to zero
                    will return the companys name and tax credits for each company progressively
                    ----------------------------------------------------------------------------------*/
                    double UserTaxCreditsWithDraw = UserTaxCredits.SinglePersonTaxCreditsWithdraw(companyName[i], 0);
                   
                    /*---------------------------------------------------------------------------------
                    Getting User Tax Credits (BALANCE)
                    
                    Calling the (Get SIngle Person Tax Credits Balance) method to 
                    display the remaining tax credits left 
                           ---------------------------------------------------------------------------*/
                    double UserTaxCreditsBalance = UserTaxCredits.getSinglePersonTaxCreditsBalance();
                    System.out.println("Remaining BLNC = " + UserTaxCreditsBalance);
          
                    // Asks user how often their are getting paid
                    System.out.println("How often are getting paid by " + companyName[i]);
 
                    // for loop to Inerate ENUM (Payment Frequency)assigning type to -1 to count the Enums
                    for (int type = 1; type <=PaymentFrequency.values().length; type++) {
                         System.out.println( type + ". " + PaymentFrequency.values()[type-1]);
                    }
                    
                    // Calling get user int method to validate user input
                    int paymentFrequency = userInt.getUserInt();
           
                    // --------- PAYMENT FREQUENCY STATEMENT --------------
                    switch (paymentFrequency){
                        
                        case 1:
                             //------------- WEEKLY PAYMENT ---------
                         
                        //========== GLOBAL VARIABLES FOR IF STATEMENTS (WEEKLY) =======================
                        // get weekly gross pay from user
                        System.out.println("Enter your Weekly Groos pay for " + companyName[i]);
                        double weeklyGrossPay = userDouble.getUserDouble(); // calling get user double to validate input 
                        
                        // hours per week
                        System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                        double weeklyHours = userDouble.getUserDouble(); // calling get user double to validate input

                        // get pension percentage from user and returning pension results
                        double weeklyPension = userPension.getPension(weeklyGrossPay);                     

                        // Getting USC based on gross eraning for the current year
                        double weeklyUSC = userUSC.getUSC(weeklyGrossPay,companyName[i]);
                        
                       // userUSC.getUSC(weeklyGrossPay,companyName[i]);

                        // Calling PRSI method agains the weekly gross pay in the parameters 
                        double weeklyPRSI = get_PRSI.getPRSI(weeklyGrossPay);
                        
                        //6) finding the salary per hour
                        double hourlyPaid = weeklyGrossPay / weeklyHours;
                       
                        // getting weekly pay limit an storing in a variable weekly pay limit
                        double weeklyPayLimit = weeklyLimit.getWeeklyPayLimit();
                        
                        // 3) finding the weekly tax credits
                        double weeklyTaxCredits = getWeeklyTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);
                                           
                        
                        /**
                         * Check whether user is being taxed at emergency tax
                         * Calling get weekly pay limit Method against the weekly gross pay
                         * If weekly payment is greater than weekly pay limit 
                         * (Regular Tax 20%) + (Emergency Tax at 40%) + (PRSI 4% applies)
                         */
                        if (weeklyGrossPay > weeklyPayLimit){

                            //======= IF ============================================== 
                            //-------------- REGULAR TAX DEDUCTION at 20% ---------------
                            // 1) diference between gross pay & weekly gross pay limit = balance to be taxed at 40%
                            double weeklyRemainingBalance = weeklyGrossPay - weeklyPayLimit;

                            // 2) Calling regular tax deduction and assingning the weekly limit in the parameters
                            // weekly gross deduction from the Weekly pay limit 769 x 20%
                            double weeklyGrossDeduction = weeklyLimit.regularTaxDeduction(weeklyPayLimit);

                            // 4) finding TaxDeduction Payable 20%
                            double weeklyTaxPayble_20 = weeklyGrossDeduction - getWeeklyTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);

                            // 5) finding net pay 20%
                            double weeklyNetPay_20 = weeklyPayLimit - weeklyTaxPayble_20;


                            //-------------- EMERGENCY TAX DEDUCTION at 40% ---------------

                            // 1) Calling emergency tax deduction and assigning the remaning balance in the parameter
                            double taxPayble_40 = getWeeklyTax.emergencyTaxDeduction(weeklyRemainingBalance);

                            // 2) TOTAL deduction 
                            double TOTAL_Deductions = weeklyTaxPayble_20 + taxPayble_40 + weeklyPRSI + weeklyUSC + weeklyPension;

                            // 3) finding NET PAY 
                            double weeklyNetPay = weeklyGrossPay - TOTAL_Deductions;
                        
                            //-------------------- DISPLAYING THE BREAKDOWN --------------------
                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                 + " Understand why by reading the information below \n");

                            System.out.println("Emergency Tax is calculaded in four different ways: \n"        
                            +"1: When you started in your new job and have no PPSN \n"
                            +"2: When you started in your new Job. You have PPSN, but the first salary will always be taxed at 40% \n"
                            +"3: When you are working for multiple employer and you do not have enough Tax Credits for each employer \n"
                            +"4: When you are working for a specific employer and your Tax Credits is Zero (0)\n");

                            System.out.println("As you are (Single) your RATE BAND is at 40,000.00 a year. That is divided by "
                            +"52 weeks because you are getting paid weekly \n");

                            System.out.println("Therefore \u20ac " + df.format(weeklyPayLimit) +" is your gross pay (limit) per week "
                            + "and any value above that limit will be taxed at 40% \n");

                            System.out.println("However, your groos pay is above the 352 weekly which means you also must "
                                    + "pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");

                            System.out.println("Your Tax Credits is Currently \u20ac" + UserTaxCreditsWithDraw + " divided by " 
                                    + "52 Number of weeks = \u20ac" +df.format(weeklyTaxCredits) + " for each gross payment limit on a weekly bases");

                            System.out.println("This \u20ac " + df.format(weeklyTaxCredits) + " only works for the Gross Pay (Limit) as shown below: \n");

                            System.out.println("Your weekly Gross Pay is \u20ac " + df.format(weeklyGrossPay) + " over the limit of \u20ac" + df.format(weeklyPayLimit));

                            System.out.println("Firt we calculate the Pay Related Social Insurance(PRSI) at 4% of your gross pay " + df.format(weeklyGrossPay) +" PRSI = \u20ac" + df.format(weeklyPRSI));

                            System.out.println("The Universal Social Charge (USC) based on your estimated income for " + companyName[i] + " this year of your pay " + df.format(weeklyGrossPay) +" USC = \u20ac" + df.format(weeklyUSC));

                            System.out.println("If you are in any Pension Scheme you must contribute depending on your employement against your gross pay " + df.format(weeklyGrossPay) +" Pension = \u20ac" + df.format(weeklyPension));

                            System.out.println("Lastly, the difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(weeklyRemainingBalance)  + " in which is calculate at 40% "
                                    + "and the \u20ac " + df.format(weeklyPayLimit) + " at 20% \n");

                            System.out.println("Then your Gross Pay Limit \u20ac " + df.format(weeklyPayLimit) +" x 20% = "+ "\u20ac " + df.format(weeklyGrossDeduction)
                                    + " - \u20ac " + df.format(weeklyTaxCredits) + " Tax Credits = \u20ac " + df.format(weeklyTaxPayble_20) + " Tax paybale");
                            System.out.println("Gross Pay (limit) \u20ac " + df.format(weeklyPayLimit)+ " -  \u20ac " + df.format(weeklyTaxPayble_20) + " > Tax at 20%  = NET PAY at 20 percent \u20ac " + df.format(weeklyNetPay_20) + "\n");

                            System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%) \n" 
                                    +"We must now calculate the Difference we found at Rate Band 2 (40%) \n");

                            System.out.println("The remainig balance is => \u20ac " + df.format(weeklyRemainingBalance) + " x 40% / 100% =  \u20ac " + df.format(taxPayble_40) + "\n");

                            System.out.println("Here is your Breaking down of your ( INCOME TAX CALCULATION ) of your gross pay:");
                            System.out.println("---------------------------------------------------------------------------------");

                            String TAX_breakdown =  "PRSI = \u20ac " + weeklyPRSI + "\n"
                                                    +"USC = \u20ac " + weeklyUSC + "\n"
                                                    +"Pension Scheme = \u20ac " + weeklyPension + "\n"
                                                    +"Gross pay at 20% (Tax Payable) = \u20ac " + df.format(weeklyTaxPayble_20) + "\n"
                                                    +"Difference between (gross pay) and (gross pay limit) at 40% (Emergency Tax) = \u20ac " + taxPayble_40 +"\n" 
                                                    +"Salary per hour on a weekly bases = \u20ac " + df.format(hourlyPaid) + "\n" 
                                                    +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions) +"\n" + "\n"
                                                    +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(weeklyNetPay) + " for this period \n";

                                            // printing out the breakdown
                                            System.out.println(TAX_breakdown);
                            } 
                        
                        /**
                         * if weekly payment is less than weekly pay limit, but greater than 352
                         * (Regular TaxDeduction 20%) + (PRSI 4% applies)
                         */
                        else if (weeklyGrossPay <= weeklyPayLimit && (weeklyGrossPay > 352)){
                        
                            //======= IF ELSE ==============================================  
                            // if weekly payment is less than weekly pay limit, but greater than 352 
                            // weekly gross deduction from the Weekly gross pay x 20%
                            double weeklyGross = weekly.regularTaxDeduction(weeklyGrossPay);

                            // 4) finding TaxDeduction Payable 20%
                            double weeklyTax_20 = weeklyGross - getWeeklyTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);

                            // finding total deductions 
                            // 2) TOTAL deduction 
                            double TOTAL_Deduct = weeklyTax_20 + weeklyPRSI + weeklyUSC + weeklyPension;

                            // finding weekly net pay
                            double NET_PAY = weeklyGrossPay - TOTAL_Deduct;


                            //-------------------- DISPLAYING THE BREAKDOWN --------------------
                            System.out.println("You are not at (Emergency TaxDeduction) by " + companyName[i] + " "
                            + " Understand why by reading the information below " + "\n");

                            System.out.println("Because your weekly Gross Pay is " + df.format(weeklyGrossPay)
                            +" below the weekly pay limit > ( " + weeklyPayLimit + "). Your wages is calculate at 20% ONLY" + "\n");

                            System.out.println("However, your groos pay is above the 352 weekly which means you also must "
                            + " pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");

                            System.out.println("Your Tax Credits is Currently \u20ac" + UserTaxCreditsWithDraw + " divided by " 
                                    + "52 Number of weeks = \u20ac" +df.format(weeklyTaxCredits) + " for each gross payment limit on a weekly bases");
                            
                            System.out.println("First we find the gross deductions of your weekly gross pay "
                            + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(weeklyGross));

                            System.out.println("Then we use your current (Tax Credits) of  \u20ac " + getWeeklyTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw) + " less gross deduction"
                            + " \u20ac" + df.format(weeklyGross) + " = \u20ac " + df.format(weeklyTax_20) + " Tax Deduction Payble" + "\n");

                            System.out.println("At last, gross payment \u20ac " + df.format(weeklyGrossPay) +"\n" 
                            + " - \u20ac" + df.format(weeklyTax_20) + " TaxDeduction payable " + "\n"
                            + " - \u20ac " + df.format(weeklyPRSI) + " PRSI" + "\n");


                            //===============================BREAKDOWN==============================================
                            String TAX_breakdown =  "PRSI = \u20ac " + weeklyPRSI + "\n"
                                                        +"USC = \u20ac " + weeklyUSC + "\n"
                                                        +"Pension Scheme = \u20ac " + weeklyPension + "\n"
                                                        +"Gross pay at 20% (Tax Payable)= \u20ac " + df.format(weeklyTax_20) + "\n"
                                                        +"Salary per hour on a weekly bases = \u20ac " + df.format(hourlyPaid) + "\n" 
                                                        +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deduct) +"\n" + "\n"
                                                        +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                                // printing out the breakdown
                                                System.out.println(TAX_breakdown);
                            }
                        
                        /**
                         * if weekly payment is less than weekly pay limit and 352 euros
                         * (Regular TaxDeduction 20%) ONLY
                         */
                        else {
                            // if weekly payment is less than weekly pay limit, but greater than 352 
                            // weekly gross deduction from the Weekly gross pay x 20%
                            double weeklyGross = weekly.regularTaxDeduction(weeklyGrossPay);

                            // 4) finding TaxDeduction Payable 20%
                            double weeklyTax_20 = weeklyGross - getWeeklyTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);

                            // finding total deductions 
                            // 2) TOTAL deduction 
                            double TOTAL_Deduct = weeklyTax_20 + weeklyPRSI + weeklyUSC + weeklyPension;

                            // finding weekly net pay
                            double NET_PAY = weeklyGrossPay - TOTAL_Deduct;

                             //-------------------- DISPLAYING THE BREAKDOWN --------------------
                             System.out.println("You are not being taxed  at (Emergency TaxDeduction) by " + companyName[i] + "."
                             + " Understand why by reading the information below " + "\n");

                             System.out.println("Because your weekly Gross Pay is " + df.format(weeklyGrossPay)
                                 +" below the weekly pay limit > ( " + weeklyPayLimit + "). Your wages is calculate at 20% ONLY" + "\n");

                             System.out.println("First we find the gross deductions of your weekly gross pay "
                              + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(weeklyGross));

                             System.out.println("Then we use your current (Tax Credits) of  \u20ac " + getWeeklyTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw) + " less gross deduction"
                              + " \u20ac" + df.format(weeklyGross) + " = \u20ac " + df.format(weeklyTax_20) + " Tax Deduction Payble" + "\n");

                             System.out.println("At last, tax payble \u20ac " + df.format(weeklyTax_20) + " - \u20ac" + df.format(weeklyGrossPay) + " weekly gross pay");

                             System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                             }

                             break;

                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        //----- FORTINIGHTLY PAYMENT ---------
                       /*  case 2:
                        
                        // get weekly gross pay from user
                        System.out.println("Enter your Fortnightly Groos pay for " + companyName[i]);
                        fornightlyGrossPay = userDouble.getUserDouble(); // calling get user double to validate input

                        // hours per week
                        System.out.println("How many hours do you usually work Fortnightly for " + companyName[i]);
                        hoursFortnightly = userDouble.getUserDouble(); // calling get user double to validate input
                        
                        /**
                        * check whether user is being taxed at emergency tax
                        * calling get weekly pay limit Method against the weekly gross pay
                        * if fortnightly payment is greater than fortnightly pay limit
                        * (Regular TaxDeduction 20%) + (Emergency TaxDeduction at 40%) + (PRSI 4% applies)
                        */
                       /*   if (fornightlyGrossPay > fortnightlyPayLimit.getFortnightlyPayLimit()){
                        
                        //-------------- REGULAR TAX DEDUCTION at 20% ---------------
                        
                        // Calling PRSI method agains the weekly gross pay in the parameters
                        double PRSI = PRSI_tax.getPRSI_Tax(fornightlyGrossPay);
                        System.out.println("PRSI DEDUCTIONS => " + PRSI );
                        
                        // 1) diference between gross pay & fortnightly gross pay limit = balance to be taxed at 40%
                        double remainingBalance = fornightlyGrossPay - fortnightlyPayLimit.getFortnightlyPayLimit();
                        System.out.format("Difference between Gross pay " + fornightlyGrossPay + " & " + " Gross Pay limit is " + weeklyPayLimit.getWeeklyPayLimit() + " = %.2f\n", remainingBalance);
                        
                        // 2) Calling regular tax deduction and assingning the fortnightly limit in the parameters
                        double grossDeduction = fortnightlyPayLimit.regularTaxDeduction(fortnightlyPayLimit.getFortnightlyPayLimit());
                        System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                        
                        // 3) finding the fortnightly tax credits
                        double currentTaxCredits = getTaxCredits.getFortnightlyTaxCredits(userTax);
                        System.out.println("CURRENT TAX CREDITS OUT OF " + userTax + " = " +  currentTaxCredits);
                        
                        // 4) finding TaxDeduction Payable 20%
                        double taxPayble_20 = grossDeduction - getTaxCredits.getFortnightlyTaxCredits(userTax);
                        System.out.format("TAX at 20 percent = %.2f\n", taxPayble_20);
                        
                        // 5) finding net pay 20%
                        double netPay_20 = fortnightlyPayLimit.getFortnightlyPayLimit() - taxPayble_20;
                        System.out.format("NET PAY 20 percent = %.2f\n", netPay_20);
                        
                        
                        //-------------- EMERGENCY TAX DEDUCTION at 40% ---------------
                        
                        // 1) Calling emergency tax deduction and assigning the remaning balance in the parameter
                        double taxPayble_40 = getTaxCredits.emergencyTaxDeduction(remainingBalance);
                        System.out.format("GROSS DEDUCTIONS at 40 = %.2f\n", taxPayble_40);
                        
                        // 2) total deduction
                        double TotalPayble = taxPayble_20 + taxPayble_40 ;
                        System.out.format("TOTAL DEDUCTIONS = %.2f\n", TotalPayble);
                        
                        // 3) finding NET PAY
                        double NET_PAY = fornightlyGrossPay - TotalPayble;
                        System.out.format("NET PAY = %.2f\n", NET_PAY);
                        
                        //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                        System.out.println("You are being taxed at (Emergency TaxDeduction) by " + companyName[i] + "."
                        + " Understand why by reading the information below " + "\n");
                        
                        System.out.println("Emergency TaxDeduction is calculaded in three different ways: " + "\n"
                        +"1: When you started in your new job and have no PPSN " + "\n"
                        +"2: When you started in your new Job. You have PPSN, but the first salary will always be taxed at 40%" + "\n"
                        +"3: When you are working for multiple employer and you do not have enough TaxDeduction Credits for each employer" +"\n");
                        
                        System.out.println("As you are (Single) your RATE BAND is at 40,000.00 a year. That is divided by "
                        +"52 weeks because you are getting paid weekly" + "\n");
                        
                        System.out.println("Therefore \u20ac " + df.format(fortnightlyPayLimit.getFortnightlyPayLimit()) +" is your gross pay (limit) every two weeks "
                        + "and any value above that limit will be taxed at 40%" + "\n");
                        
                        System.out.println("However, your groos pay is above the 704 fortnightly which means you also must "
                        + "pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");
                        
                        System.out.println("Your TaxDeduction Credits is Currently \u20ac" + userTax + " divided by "
                        + "26 Number of weeks = \u20ac" +df.format(currentTaxCredits) + " for each gross payment limit Fortnightly");
                        
                        System.out.println("This \u20ac " + df.format(currentTaxCredits) + " only works for the Gross Pay (Limit) as shown below:" + "\n");
                        
                        System.out.println("Your Fortnightly Gross Pay is \u20ac " + df.format(fornightlyGrossPay) + " over the limit of \u20ac" + fortnightlyPayLimit.getFortnightlyPayLimit());
                        
                        System.out.println("The difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(remainingBalance)  + " in which is calculate at 40% "
                        + "and the \u20ac " + fortnightlyPayLimit.getFortnightlyPayLimit() + " at 20%" + "\n");
                        
                        System.out.println("First, we calculate your gross pay \u20ac " + df.format(fornightlyGrossPay) + " at 4% PRSI = \u20ac " + PRSI);
                        
                        System.out.println("Then your Gross Pay Limit \u20ac " + fortnightlyPayLimit.getFortnightlyPayLimit() +" x 20% = "+ "\u20ac " + df.format(grossDeduction)
                        + " - \u20ac " + df.format(currentTaxCredits) + " TaxDeduction Credits = \u20ac " + df.format(taxPayble_20) + " TaxDeduction paybale");
                        System.out.println("Gross Pay (limit) \u20ac " + fortnightlyPayLimit.getFortnightlyPayLimit() + " -  \u20ac " + taxPayble_20 + " > TaxDeduction at 20%  = NET PAY at 20 percent \u20ac " + df.format(netPay_20) + "\n");
                        
                        System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%)" + "\n"
                        +"We must now calculate the Difference we found at Rate Band 2 (40%)" + "\n");
                        
                        System.out.println("The remainig balance is => \u20ac " + df.format(remainingBalance) + " x 40% / 100% =  \u20ac " + df.format(taxPayble_40));
                        System.out.println("Your tax calculation is \u20ac " + df.format(taxPayble_20) + " + \u20ac " + df.format(taxPayble_40) +" = TOATL Deductions \u20ac " + df.format(TotalPayble));
                        System.out.println("( \u20ac " + df.format(TotalPayble) + " - \u20ac " + df.format(fornightlyGrossPay) +" )"+ "\n");
                        
                        System.out.println("And \u20ac " + df.format(fornightlyGrossPay) + " - PRSI " + PRSI);
                        System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        }
                        
                        
                        /**
                         * if fortnightly payment is less than fortnightly pay limit, but greater than 352
                         * (Regular TaxDeduction 20%) + (PRSI 4% applies)
                         */
                       /*  else if (fornightlyGrossPay <= fortnightlyPayLimit.getFortnightlyPayLimit() && (fornightlyGrossPay > 704)){
                        
                        // Calling PRSI method agains the weekly gross pay in the parameters
                        double PRSI = PRSI_tax.getPRSI_Tax(fornightlyGrossPay);
                        System.out.println("PRSI DEDUCTIONS => " + PRSI );
                        
                        // 1) Calling regular tax deduction and assingning the weekly limit in the parameters
                        double grossDeduction = fortnightlyPayLimit.regularTaxDeduction(fornightlyGrossPay);
                        System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                        
                        // 2) finding TaxDeduction Payble
                        double taxPayable = grossDeduction - getTaxCredits.getFortnightlyTaxCredits(userTax);
                        
                        // 3) finding net pay 20%
                        double NET_PAY = fornightlyGrossPay - taxPayable - PRSI;
                        
                        
                        //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                        System.out.println("You are not at (Emergency TaxDeduction) by " + companyName[i] + " "
                        + " Understand why by reading the information below " + "\n");
                        
                        System.out.println("Because your Gross Pay is " + df.format(fornightlyGrossPay)
                        +" below the weekly pay limit > ( " + fortnightlyPayLimit.getFortnightlyPayLimit() + "). Your wages is calculate at 20% ONLY" + "\n");
                        
                        System.out.println("However, your groos pay is above the 704 Fortnightly which means you also must "
                        + " pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");
                        
                        System.out.println("First we find the gross deductions of your fortnightly gross pay "
                        + df.format(fornightlyGrossPay) + " x 20% = \u20ac " + df.format(grossDeduction));
                        
                        System.out.println("Then we use your current TaxDeduction Credits of  \u20ac " + getTaxCredits.getFortnightlyTaxCredits(userTax) + " less gross deduction"
                        + " \u20ac" + df.format(grossDeduction) + " = \u20ac " + df.format(taxPayable) + " TaxDeduction Payble" + "\n");
                        
                        System.out.println("At last, gross payment \u20ac " + df.format(fornightlyGrossPay) + " - \u20ac" + df.format(taxPayable) + " TaxDeduction payable "
                        + " - \u20ac " + df.format(PRSI) + " PRSI");
                        
                        System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        }
                        */
                        /**
                         * if fortnightly payment is less than weekly pay limit and 352 euros
                         * (Regular Tax 20%) ONLY
                         */
                       /*   else {
                        
                        // 1) Calling regular tax deduction and assingning the weekly limit in the parameters
                        double grossDeduction = fortnightlyPayLimit.regularTaxDeduction(fornightlyGrossPay);
                        System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                        
                        // 2) finding TaxDeduction Payble
                        double taxPayable = grossDeduction - getTaxCredits.getFortnightlyTaxCredits(userTax);
                        
                        // 3) finding net pay 20%
                        double NET_PAY = fornightlyGrossPay - taxPayable;
                        
                        
                        //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                        System.out.println("You are not being taxed  at (Emergency TaxDeduction) by " + companyName[i] + "."
                        + " Understand why by reading the information below " + "\n");
                        
                        System.out.println("Because your Gross Pay is " + df.format(fornightlyGrossPay)
                        +" below the Fortnightly pay limit and it is less than 704 > ( " + fortnightlyPayLimit.getFortnightlyPayLimit() + "). Your salary is calculate at 20% ONLY" + "\n");
                        
                        System.out.println("First we find the gross deductions of your Fortnightly gross pay "
                        + df.format(fornightlyGrossPay) + " x 20% = \u20ac " + df.format(grossDeduction));
                        
                        System.out.println("Then we use your current TaxDeduction Credits of  \u20ac " + getTaxCredits.getFortnightlyTaxCredits(userTax) + " less gross deduction"
                        + "  \u20ac" + df.format(grossDeduction) + " = \u20ac " + df.format(taxPayable) + " TaxDeduction Payble" + "\n");
                        
                        System.out.println("At last, tax payble \u20ac " + df.format(taxPayable) + " - \u20ac" + df.format(fornightlyGrossPay) + " Fortnightly gross pay");
                        
                        System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        }
                        break;
                         
                        //----- MONTHLY PAYMENT ---------
                        case 3:

                        // get weekly gross pay from user
                        System.out.println("Enter your Monthly Groos pay for " + companyName[i]);
                        monthlyGrossPay = userDouble.getUserDouble(); // calling get user double to validate input

                        // hours per week
                        System.out.println("How many hours do you usually work Monthly bases for " + companyName[i]);
                        monthlyHours = userDouble.getUserDouble(); // calling get user double to validate input
                        
                        /**
                         * check whether user is being taxed at emergency tax
                         * calling get weekly pay limit Method against the weekly gross pay
                         * if monthly payment is greater than monthly pay limit 
                         * (Regular TaxDeduction 20%) + (Emergency TaxDeduction at 40%) + (PRSI 4% applies)
                         */
                      /*    if (monthlyGrossPay > monthlyPayLimit.getMonthlyPayLimit()){
                        
                        //-------------- REGULAR TAX DEDUCTION at 20% ---------------
                        
                        // Calling PRSI method agains the monthly gross pay in the parameters
                        double PRSI = PRSI_tax.getPRSI_Tax(monthlyGrossPay);
                        System.out.println("PRSI DEDUCTIONS => " + PRSI );
                        
                        // 1) diference between gross pay & weekly gross pay limit = balance to be taxed at 40%
                        double remainingBalance = monthlyGrossPay - monthlyPayLimit.getMonthlyPayLimit();
                        System.out.format("Difference between Gross pay " + monthlyGrossPay + " & " + " Gross Pay limit is " + weeklyPayLimit.getWeeklyPayLimit() + " = %.2f\n", remainingBalance);
                        
                        // 2) Calling regular tax deduction and assingning the weekly limit in the parameters
                        double grossDeduction = monthlyPayLimit.regularTaxDeduction(monthlyPayLimit.getMonthlyPayLimit());
                        System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                        
                        // 3) finding the weekly tax credits
                        double currentTaxCredits = getTaxCredits.getMonthlyTaxCredits(userTax);
                        System.out.println("CURRENT TAX CREDITS OUT OF " + userTax + " = " +  currentTaxCredits);
                        
                        // 4) finding TaxDeduction Payable 20%
                        double taxPayble_20 = grossDeduction - getTaxCredits.getMonthlyTaxCredits(userTax);
                        System.out.format("TAX at 20 percent = %.2f\n", taxPayble_20);
                        
                        // 5) finding net pay 20%
                        double netPay_20 = monthlyPayLimit.getMonthlyPayLimit() - taxPayble_20;
                        System.out.format("NET PAY 20 percent = %.2f\n", netPay_20);
                        
                        
                        //-------------- EMERGENCY TAX DEDUCTION at 40% ---------------
                        
                        // 1) Calling emergency tax deduction and assigning the remaning balance in the parameter
                        double taxPayble_40 = getTaxCredits.emergencyTaxDeduction(remainingBalance);
                        System.out.format("GROSS DEDUCTIONS at 40 = %.2f\n", taxPayble_40);
                        
                        // 2) total deduction
                        double TotalPayble = taxPayble_20 + taxPayble_40 ;
                        System.out.format("TOTAL DEDUCTIONS = %.2f\n", TotalPayble);
                        
                        // 3) finding NET PAY
                        double NET_PAY = monthlyGrossPay - TotalPayble;
                        System.out.format("NET PAY = %.2f\n", NET_PAY);
                        
                        //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                        System.out.println("You are being taxed at (Emergency TaxDeduction) by " + companyName[i] + "."
                        + " Understand why by reading the information below " + "\n");
                        
                        System.out.println("Emergency TaxDeduction is calculaded in three different ways: " + "\n"
                        +"1: When you started in your new job and have no PPSN " + "\n"
                        +"2: When you started in your new Job. You have PPSN, but the first salary will always be taxed at 40%" + "\n"
                        +"3: When you are working for multiple employer and you do not have enough TaxDeduction Credits for each employer" +"\n");
                        
                        System.out.println("As you are (Single) your RATE BAND is at 40,000.00 a year. That is divided by "
                        +"52 weeks because you are getting paid monthly" + "\n");
                        
                        System.out.println("Therefore \u20ac " + monthlyPayLimit.getMonthlyPayLimit() +" is your gross pay (limit) per week "
                        + "and any value above that limit will be taxed at 40%" + "\n");
                        
                        System.out.println("However, your groos pay is above the 1408 monthly which means you also must "
                        + "pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");
                        
                        System.out.println("Your TaxDeduction Credits is Currently \u20ac" + userTax + " divided by "
                        + "12 Number of months = \u20ac" +df.format(currentTaxCredits) + " for each gross payment limit monthly");
                        
                        System.out.println("This \u20ac " + df.format(currentTaxCredits) + " only works for the Gross Pay (Limit) as shown below:" + "\n");
                        
                        System.out.println("Your monthly Gross Pay is \u20ac " + df.format(monthlyGrossPay) + " over the limit of \u20ac" + monthlyPayLimit.getMonthlyPayLimit());
                        
                        System.out.println("The difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(remainingBalance)  + " in which is calculate at 40% "
                        + "and the \u20ac " + monthlyPayLimit.getMonthlyPayLimit() + " at 20%" + "\n");
                        
                        System.out.println("First, we calculate your gross pay \u20ac " + df.format(monthlyGrossPay) + " at 4% PRSI = \u20ac " + PRSI);
                        
                        System.out.println("Then your Gross Pay Limit \u20ac " + monthlyPayLimit.getMonthlyPayLimit() +" x 20% = "+ "\u20ac " + df.format(grossDeduction)
                        + " - \u20ac " + df.format(currentTaxCredits) + " TaxDeduction Credits = \u20ac " + df.format(taxPayble_20) + " TaxDeduction paybale");
                        System.out.println("Gross Pay (limit) \u20ac " + monthlyPayLimit.getMonthlyPayLimit() + " -  \u20ac " + taxPayble_20 + " > TaxDeduction at 20%  = NET PAY at 20 percent \u20ac " + df.format(netPay_20) + "\n");
                        
                        System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%)" + "\n"
                        +"We must now calculate the Difference we found at Rate Band 2 (40%)" + "\n");
                        
                        System.out.println("The remainig balance is => \u20ac " + df.format(remainingBalance) + " x 40% / 100% =  \u20ac " + df.format(taxPayble_40));
                        System.out.println("Your tax calculation is \u20ac " + df.format(taxPayble_20) + " + \u20ac " + df.format(taxPayble_40) +" = TOATL Deductions \u20ac " + df.format(TotalPayble));
                        System.out.println("( \u20ac " + df.format(TotalPayble) + " - \u20ac " + df.format(monthlyGrossPay) +" )"+ "\n");
                        
                        System.out.println("And \u20ac " + df.format(monthlyGrossPay) + " - PRSI " + PRSI);
                        System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        }
                        
                        
                        /**
                         * if monthly payment is less than weekly pay limit, but greater than 352
                         * (Regular TaxDeduction 20%) + (PRSI 4% applies)
                         */
                      /*     else if (monthlyGrossPay <= monthlyPayLimit.getMonthlyPayLimit() && (monthlyGrossPay > 1408)){
                        
                        // Calling PRSI method agains the monthly gross pay in the parameters
                        double PRSI = PRSI_tax.getPRSI_Tax(monthlyGrossPay);
                        System.out.println("PRSI DEDUCTIONS => " + PRSI );
                        
                        // 1) Calling regular tax deduction and assingning the monthly limit in the parameters
                        double grossDeduction = monthlyPayLimit.regularTaxDeduction(monthlyGrossPay);
                        System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                        
                        // 2) finding TaxDeduction Payble
                        double taxPayable = grossDeduction - getTaxCredits.getMonthlyTaxCredits(userTax);
                        
                        // 3) finding net pay 20%
                        double NET_PAY = monthlyGrossPay - taxPayable - PRSI;
                        
                        
                        //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                        System.out.println("You are not at (Emergency TaxDeduction) by " + companyName[i] + " "
                        + " Understand why by reading the information below " + "\n");
                        
                        System.out.println("Because your Gross Pay is " + df.format(monthlyGrossPay)
                        +" below the weekly pay limit > ( " + monthlyPayLimit.getMonthlyPayLimit() + "). Your wages is calculate at 20% ONLY" + "\n");
                        
                        System.out.println("However, your groos pay is above the 1408 monthly which means you also must "
                        + " pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");
                        
                        System.out.println("First we find the gross deductions of your monthly gross pay "
                        + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(grossDeduction));
                        
                        System.out.println("Then we use your current TaxDeduction Credits of  \u20ac " + getTaxCredits.getMonthlyTaxCredits(userTax) + " less gross deduction"
                        + " \u20ac" + df.format(grossDeduction) + " = \u20ac " + df.format(taxPayable) + " TaxDeduction Payble" + "\n");
                        
                        System.out.println("At last, gross payment \u20ac " + df.format(monthlyGrossPay) + " - \u20ac" + df.format(taxPayable) + " TaxDeduction payable "
                        + " - \u20ac " + df.format(PRSI) + " PRSI");
                        
                        System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        }
                        
                        /**
                         * if monthly payment is less than monthly pay limit and 352 euros
                         * (Regular TaxDeduction 20%) ONLY
                         */
                      /*   else {
                        
                        // 1) Calling regular tax deduction and assingning the weekly limit in the parameters
                        double grossDeduction = monthlyPayLimit.regularTaxDeduction(monthlyGrossPay);
                        System.out.format("GROSS DEDUCTION at 20 percent = %.2f\n", grossDeduction);
                        
                        // 2) finding TaxDeduction Payble
                        double taxPayable = grossDeduction - getTaxCredits.getMonthlyTaxCredits(userTax);
                        
                        // 3) finding net pay 20%
                        double NET_PAY = monthlyGrossPay - taxPayable;
                        
                        
                        //-------------------- DISPLAYING MESSAGE AND HOW IT WORKS --------------------
                        System.out.println("You are not being taxed  at (Emergency TaxDeduction) by " + companyName[i] + "."
                        + " Understand why by reading the information below " + "\n");
                        
                        System.out.println("Because your Gross Pay is " + df.format(monthlyGrossPay)
                        +" below the weekly pay limit and it is less than 1408 > ( " + monthlyPayLimit.getMonthlyPayLimit() + "). Your salary is calculate at 20% ONLY" + "\n");
                        
                        System.out.println("First we find the gross deductions of your monthly gross pay "
                        + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(grossDeduction));
                        
                        System.out.println("Then we use your current TaxDeduction Credits of  \u20ac " + getTaxCredits.getMonthlyTaxCredits(userTax) + " less gross deduction"
                        + "  \u20ac" + df.format(grossDeduction) + " = \u20ac " + df.format(taxPayable) + " TaxDeduction Payble" + "\n");
                        
                        System.out.println("At last, tax payble \u20ac " + df.format(taxPayable) + " - \u20ac" + df.format(monthlyGrossPay) + " monthly gross pay");
                        
                        System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        }
                        break;*/
                }
            }
        }

    @Override
    public void MarriedPersonTax() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }
        



