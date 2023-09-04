
    
    package UserStatus;
    import Enums.TaxCalculatorContainer.PaymentFrequency;
    import Enums.TaxCalculatorContainer.UserMessage;
    import Enums.TaxCalculatorContainer.IncomePRSI;
    import Utilities.Utilities;
    import java.text.DecimalFormat;
    import Taxes.TaxDeduction;
    

    /**
     * @author FRANKLIN
     */
    public class UserStatus implements UserStatusInterface {
                    
        // GLOBAL DECIMAL FORMAT to display double in 2 decimal place 
        public static final DecimalFormat df = new DecimalFormat("0.00");
       /* 
        // GLOBAL INCOME VARIABLE to validate using if/else statement
        static final int INCOME_BAND = 352;
*/
        
        @Override
        public void SinglePersonTax(){
                                  
            Utilities getUserInput = new Utilities();    // get user input
            TaxDeduction getUserTax = new TaxDeduction();     // call method for taxes porpuses            
            UserTaxCredits getUserTaxCredits = new UserTaxCredits(); // call method for the Single Person Tax Credits
         
            //=========================================================================================           
            
            //Ask user how many employers they are currently working 
            System.out.println("How many employers are you currently working for?");
            int numberOfEmployers = getUserInput.getUserInt();  // method to get numbers of employers from user
            
            /*-----------------------------------------------------------
             Getting employers name based on the numbers of employers entered by the user
             and storing inside the array (Company Name)
            --------------------------------------------------------------*/
            String companyName [] = new String [numberOfEmployers];

                //===========FOR LOOPS==============================================================
                
                // To access array through the numbers of emplyers and calling get user text method
                // inside the for loop to state the companys name through the number of employers. 
                for(int i=0; i < numberOfEmployers; i++){
                    companyName[i] = getUserInput.getUserText("Employer " +(i +1) + " Name:"); // calling get user text Method
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
                    double UserTaxCreditsWithDraw = getUserTaxCredits.SinglePersonTaxCreditsWithdraw(companyName[i], 0);
                                       
                    
                    /*---------------------------------------------------------------------------------
                    Getting User Tax Credits (BALANCE)
                    
                    Calling the (Get SIngle Person Tax Credits Balance) method to 
                    display the remaining tax credits left 
                    ----------------------------------------------------------------------------------*/
                    double UserTaxCreditsBalance = getUserTaxCredits.getSinglePersonTaxCreditsBalance();
                    System.out.println("Remaining BLNC = " + UserTaxCreditsBalance);
          
                                        
                    // Asks user how often their are getting paid
                    System.out.println("How often are getting paid by " + companyName[i]);
 
                    // for loop to Inerate ENUM (Payment Frequency)assigning type to -1 to count the Enums
                    for (int type = 1; type <=PaymentFrequency.values().length; type++) {
                         System.out.println( type + ". " + PaymentFrequency.values()[type-1]);
                    }
                    
                    // Calling get user int method to validate user input
                    int paymentFrequency = getUserInput.getUserInt();
           
                    // --------- PAYMENT FREQUENCY STATEMENT --------------
                    switch (paymentFrequency){
                        
                        case 1:
                        //------------- WEEKLY PAYMENT ---------
                         
                        //========== GLOBAL VARIABLES FOR IF STATEMENTS (WEEKLY) =======================
                            
                        // get weekly gross pay from user
                        System.out.println("Enter your Weekly Groos pay for " + companyName[i]);
                        double weeklyGrossPay = getUserInput.getUserDouble(); // calling get user double to validate input 
                        
                        // get hours per week
                        System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                        double weeklyHours = getUserInput.getUserDouble(); // calling get user double to validate input

                        /*---------------------------------------------------------------------------------
                        Calling the (Get user Pension) method and assigning the weekly gross pay
                        in the parameter. Weekly Pension variable will hold the return value of the pension. 
                        ----------------------------------------------------------------------------------*/
                        double weeklyPension = getUserTax.getPension(weeklyGrossPay);                     

                   
                        /*---------------------------------------------------------------------------------
                        Calling the (Get user USC) method and assigning the weekly gross pay and company name
                        in the parameter. Weekly USC variable will hold the return value of the value. 
                        ----------------------------------------------------------------------------------*/
                        double weeklyUSC = getUserTax.getUSC(weeklyGrossPay,companyName[i]);
                             
                        
                        /*---------------------------------------------------------------------------------
                        Calling the (Get PRSI) method and assigning the weekly gross pay in the parameter.
                        Weekly PRSI variable will hold the return value of the value. 
                        ----------------------------------------------------------------------------------*/
                        double weeklyPRSI = getUserTax.getPRSI(weeklyGrossPay);
                        
                        
                        /*---------------------------------------------------------------------------------
                        Finding the hourly paid by divifing weekly gross pay against the weekly hours and 
                        storing the results in the hourlyPaid.
                        ----------------------------------------------------------------------------------*/
                        double salaryPerHourWeekly = weeklyGrossPay / weeklyHours;
                       
                                                
                        /*---------------------------------------------------------------------------------
                        Calling(Get user weekly pay limit) method and storing the return value in the weekly
                        pay limit to make it easier to manipulet rather than call the method all the time.
                        ----------------------------------------------------------------------------------*/
                        double weeklyPayLimit = getUserTax.getWeeklyPayLimit();
                        
                        
                        /*---------------------------------------------------------------------------------
                        Calling(Get weekly Tax credits) method and assigning the User Tax Credits withdraw
                        so that will return the exact tax credits for that payment depending on your crdits 
                        for a specific company. Will store the results in the weekly tax credits variable.

                        Company: AIB = Tax credits is > 1850 only. The get weekly tax credits will use the 
                        User Tax Credits withdraw in the parameter to do the calculation as follow:

                        1850 / 52 weeks and return the credits for that period which is in this case (35)
                        euros credits for that company. The remaining credits will be use it for other companies.
                        ----------------------------------------------------------------------------------*/
                        double weeklyTaxCredits = getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);
                        
                                           
                        /**
                         * IF WEEKLY PAYMENT IS GREATER THAN WEEKLY PAY LIMIT
                         * Check whether user is being taxed at emergency tax
                         * Calling get weekly pay limit Method against the weekly gross pay
                         *(Regular Tax 20%) + (Emergency Tax at 40%) + (PRSI 4% applies)
                         */     
                        if (weeklyGrossPay > weeklyPayLimit){
                            
                            //-------------- REGULAR TAX DEDUCTION at 20% ---------------
                            
                            // 1) Diference between gross pay & weekly gross pay limit = balance to be taxed at 40%
                            double weeklyRemainingBalance = weeklyGrossPay - weeklyPayLimit;

                            // 2) Finding the Gross deduction by Calling regular tax deduction 20% and assingning
                            //the weekly limit in the parameter. (Weekly pay limit x 20%) = gross deduction
                            double weeklyGrossDeduction = getUserTax.regularTaxDeduction(weeklyPayLimit);

                            // 3) Finding Tax Deduction Payable at 20%. Calling Get weekly tax credits method and assigning
                            // the USED tax credits in the parameters. (Gross deduction - weeklytax = weekly tax payable at 20%) 
                            double weeklyTaxPayable_20 = weeklyGrossDeduction - getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);

                            // 4) finding net pay 20%. Because this option goes above the limit we calculate the weekly limit 
                            // at 20%.  (weekly pay litmit - weekly tax payable = weekly net pay 20%)
                            double weeklyNetPay_20 = weeklyPayLimit - weeklyTaxPayable_20;


                            //-------------- EMERGENCY TAX DEDUCTION at 40% ---------------
                            
                            // 1) Calling emergency tax deduction and assigning the weekly remaning balance
                            //in the parameter.
                            double taxPayable_40 = getUserTax.emergencyTaxDeduction(weeklyRemainingBalance);

                            // 2) TOTAL deduction. Adding up the taxes and all the deductions and storing in the 
                            // TOTAL deduction variable. 
                            double TOTAL_Deductions = weeklyTaxPayable_20 + taxPayable_40 + weeklyPRSI + weeklyUSC + weeklyPension;

                            // 3) finding NET PAY for this period. Weekly gross pay - Total deductions = (NET PAY) 
                            double NET_PAY = weeklyGrossPay - TOTAL_Deductions;
                        
                            //-------------------- DISPLAYING THE BREAKDOWN --------------------
                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                 + " Understand why by reading the information below \n");

                            // Displaying the 4 different ways on how the emergency Tax is calculates
                            // Enums String toString to display messages
                            System.out.println("Emergency Tax is calculaded in four different ways:\n"        
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_1.toString() + "\n"
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_2.toString() + "\n"
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_3.toString() + "\n"
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_4.toString() + "\n");


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
                                    + " - \u20ac " + df.format(weeklyTaxCredits) + " Tax Credits = \u20ac " + df.format(weeklyTaxPayable_20) + " Tax paybale");
                            System.out.println("Gross Pay (limit) \u20ac " + df.format(weeklyPayLimit)+ " -  \u20ac " + df.format(weeklyTaxPayable_20) + " > Tax at 20%  = NET PAY at 20 percent \u20ac " + df.format(weeklyNetPay_20) + "\n");

                            System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%) \n" 
                                    +"We must now calculate the Difference we found at Rate Band 2 (40%) \n");

                            System.out.println("The remainig balance is => \u20ac " + df.format(weeklyRemainingBalance) + " x 40% / 100% =  \u20ac " + df.format(taxPayable_40) + "\n");

                            System.out.println("Here is your Breaking down of your ( INCOME TAX CALCULATION ) of your gross pay:");
                            System.out.println("---------------------------------------------------------------------------------");

                            String TAX_breakdown =  "PRSI = \u20ac " + weeklyPRSI + "\n"
                                                    +"USC = \u20ac " + weeklyUSC + "\n"
                                                    +"Pension Scheme = \u20ac " + weeklyPension + "\n"
                                                    +"Gross pay at 20% (Tax Payable) = \u20ac " + df.format(weeklyTaxPayable_20) + "\n"
                                                    +"Difference between (gross pay) and (gross pay limit) at 40% (Emergency Tax) = \u20ac " + taxPayable_40 +"\n" 
                                                    +"Salary per hour on a weekly bases = \u20ac " + df.format(salaryPerHourWeekly) + "\n" 
                                                    +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions) +"\n" + "\n"
                                                    +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                            // printing out the breakdown
                                            System.out.println(TAX_breakdown);
                            } 
                        
                        /**
                         * if weekly payment is less than weekly pay limit, but GREATER than 352 income band
                         * (Regular TaxDeduction 20%) + (PRSI) + USC + Pension
                         */
                        else if (weeklyGrossPay <= weeklyPayLimit && (weeklyGrossPay > IncomePRSI.WEEKLY.getIncomePRSI())){
                        
                            // 1) if weekly payment is less than weekly pay limit, but greater than 352 
                            // weekly gross deduction from the Weekly gross pay x 20%
                            double weeklyGross = getUserTax.regularTaxDeduction(weeklyGrossPay);

                            // 2) Finding Tax Deduction Payable at 20%. Calling Get weekly tax credits method and assigning
                            // the USED tax credits in the parameters. (Weekly gross - weeklytax = weekly tax payable at 20%) 
                            double weeklyTax_20 = weeklyGross - getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);

                            // 3) TOTAL deduction. Adding up the taxes and all the deductions and storing in the 
                            // TOTAL deduction variable. 
                            double TOTAL_Deductions_2 = weeklyTax_20 + weeklyPRSI + weeklyUSC + weeklyPension;

                            // 4) Finding NET PAY for this period. Weekly gross pay - Total deductions = (NET PAY)
                            double NET_PAY = weeklyGrossPay - TOTAL_Deductions_2;

                            
                            //=================== DISPLAYING BREAKDOWN ============================================
                            

                            System.out.println("Because your weekly Gross Pay is " + df.format(weeklyGrossPay)
                            +" below the weekly pay limit > ( " + weeklyPayLimit + "). Your wages is calculate at 20% ONLY" + "\n");

                            System.out.println("However, your groos pay is above the 352 weekly which means you also must "
                            + " pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");

                            System.out.println("Your Tax Credits is Currently \u20ac" + UserTaxCreditsWithDraw + " divided by " 
                                    + "52 Number of weeks = \u20ac" +df.format(weeklyTaxCredits) + " for each gross payment limit on a weekly bases");
                            
                            System.out.println("First we find the gross deductions of your weekly gross pay "
                            + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(weeklyGross));

                            System.out.println("Then we use your current (Tax Credits) of  \u20ac " + getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw) + " less gross deduction"
                            + " \u20ac" + df.format(weeklyGross) + " = \u20ac " + df.format(weeklyTax_20) + " Tax Deduction Payble" + "\n");

                            System.out.println("At last, gross payment \u20ac " + df.format(weeklyGrossPay) +"\n" 
                            + " - \u20ac" + df.format(weeklyTax_20) + " TaxDeduction payable " + "\n"
                            + " - \u20ac " + df.format(weeklyPRSI) + " PRSI" + "\n");


                            //===============================BREAKDOWN==============================================
                            
                            String TAX_breakdown =  "PRSI = \u20ac " + weeklyPRSI + "\n"
                                                        +"USC = \u20ac " + weeklyUSC + "\n"
                                                        +"Pension Scheme = \u20ac " + weeklyPension + "\n"
                                                        +"Gross pay at 20% (Tax Payable)= \u20ac " + df.format(weeklyTax_20) + "\n"
                                                        +"Salary per hour on a weekly bases = \u20ac " + df.format(salaryPerHourWeekly) + "\n" 
                                                        +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions_2) +"\n" + "\n"
                                                        +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                                // printing out the breakdown
                                                System.out.println(TAX_breakdown);
                        }
                                                
                        /**
                         * if weekly payment is less than weekly pay limit, but LESS than 352 income band
                         * (Regular TaxDeduction 20%) + USC + Pension
                         * 
                         * PRSI DOES NOT APPLY HERE Because PRSI is not applied to income less than 352 on a weekly basis
                         */
                        else if (weeklyGrossPay <= weeklyPayLimit && (weeklyGrossPay < IncomePRSI.WEEKLY.getIncomePRSI())){
                            
                            // 1)if weekly payment is less than weekly pay limit, but greater than 352 
                            // weekly gross deduction from the Weekly gross pay x 20%
                            double weeklyGross = getUserTax.regularTaxDeduction(weeklyGrossPay);

                            // 2) Finding Deduction at 20% ONLY. (Weekly gross - tax credits = NET PAY) 
                            double weeklyTax_20 = weeklyGross - getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw);

                            // 3) TOTAL deduction. Adding up the taxes and all the deductions and storing in the 
                            // TOTAL deduction variable.
                            double TOTAL_Deductions_3 = weeklyTax_20 + weeklyPRSI + weeklyUSC + weeklyPension;

                            // 4) Finding NET PAY for this period. Weekly gross pay - Total deductions = (NET PAY)
                            double NET_PAY = weeklyGrossPay - TOTAL_Deductions_3;

                             //-------------------- DISPLAYING THE BREAKDOWN --------------------
                           //  System.out.println("You are not being taxed  at (Emergency TaxDeduction) by " + companyName[i] + "."
                             //+ " Understand why by reading the information below " + "\n");

                             System.out.println("Because your weekly Gross Pay is " + df.format(weeklyGrossPay)
                                 +" below the weekly pay limit > ( " + weeklyPayLimit + "). Your wages is calculate at 20% ONLY" + "\n");

                             System.out.println("First we find the gross deductions of your weekly gross pay "
                              + df.format(weeklyGrossPay) + " x 20% = \u20ac " + df.format(weeklyGross));

                             System.out.println("Then we use your current (Tax Credits) of  \u20ac " + getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw) + " less gross deduction"
                              + " \u20ac" + df.format(weeklyGross) + " = \u20ac " + df.format(weeklyTax_20) + " Tax Deduction Payble" + "\n");

                             System.out.println("At last, tax payble \u20ac " + df.format(weeklyTax_20) + " - \u20ac" + df.format(weeklyGrossPay) + " weekly gross pay");

                             System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        
                                
                             //===============================BREAKDOWN==============================================
                            String TAX_breakdown =  "PRSI = not applied as your weekly pay is less than " + IncomePRSI.WEEKLY.getIncomePRSI() + "\n"
                                                        +"USC = \u20ac " + weeklyUSC + "\n"
                                                        +"Pension Scheme = \u20ac " + weeklyPension + "\n"
                                                        +"Gross pay at 20% (Tax Payable)= \u20ac " + df.format(weeklyTax_20) + "\n"
                                                        +"Salary per hour on a weekly bases = \u20ac " + df.format(salaryPerHourWeekly) + "\n" 
                                                        +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions_3) +"\n" + "\n"
                                                        +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                                // printing out the breakdown
                                                System.out.println(TAX_breakdown);
                                                
                            }
                             // CASE 1 = WEEKLY payment Finihes here
                             break;
                 
                            
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             
                        //----- FORTINIGHTLY PAYMENT ---------
                         case 2:
                        
                        // get fortnight gross pay from user
                        System.out.println("Enter your Fortnightly Groos pay for " + companyName[i]);
                        double fortnightGrossPay = getUserInput.getUserDouble(); // calling get user double to validate input

                        // fortnight hours
                        System.out.println("How many hours do you usually work Fortnightly for " + companyName[i]);
                        double fortnightHours = getUserInput.getUserDouble(); // calling get user double to validate input
                       
                       
                        /*---------------------------------------------------------------------------------
                        Calling the (Get user Pension) method and assigning the fortnight gross pay
                        in the parameter. Fortnight Pension variable will hold the return value of the pension. 
                        ----------------------------------------------------------------------------------*/
                        double fortnightPension = getUserTax.getPension(fortnightGrossPay);                     


                        /*---------------------------------------------------------------------------------
                        Calling the (Get user USC) method and assigning the weekly gross pay and company name
                        in the parameter. Fortnight USC variable will hold the return value of the value. 
                        ----------------------------------------------------------------------------------*/
                        double fortnightUSC = getUserTax.getUSC(fortnightGrossPay,companyName[i]);


                        /*---------------------------------------------------------------------------------
                        Calling the (Get PRSI) method and assigning the weekly gross pay in the parameter.
                        Fortnight PRSI variable will hold the return value of the value. 
                        ----------------------------------------------------------------------------------*/
                        double fortnightPRSI = getUserTax.getPRSI(fortnightGrossPay);


                        /*---------------------------------------------------------------------------------
                        Finding the hourly paid by divifing Fortnight gross pay against the Fortnight hours and 
                        storing the results in the hourlyPaid.
                        ----------------------------------------------------------------------------------*/
                        double salaryPerHourFortnighly = fortnightGrossPay / fortnightHours;
                        

                        /*---------------------------------------------------------------------------------
                        Calling(Get user Fortnight pay limit) method and storing the return value in the Fortnight
                        pay limit to make it easier to manipulet rather than call the method all the time.
                        ----------------------------------------------------------------------------------*/
                        double fortnightPayLimit = getUserTax.getFortnightPayLimit();


                        /*---------------------------------------------------------------------------------
                        Calling(Get Fortnight Tax credits) method and assigning the User Tax Credits withdraw
                        so that will return the exact tax credits for that payment depending on your crdits 
                        for a specific company. Will store the results in the Fortnight tax credits variable.

                        Company: AIB = Tax credits is > 1850 only. The get weekly tax credits will use the 
                        User Tax Credits withdraw in the parameter to do the calculation as follow:

                        1850 / 26 weeks and return the credits for that period which is in this case (71)
                        euros credits for that company. The remaining credits will be use it for other companies.
                        ----------------------------------------------------------------------------------*/
                        double fortnightTaxCredits = getUserTax.getFortnightTaxCredits(UserTaxCreditsWithDraw);


                        /**
                        * check whether user is being taxed at emergency tax
                        * calling get Fortnight pay limit Method against the fortnight gross pay
                        * if fortnightly payment is greater than fortnightly pay limit
                        * (Regular TaxDeduction 20%) + (Emergency TaxDeduction at 40%) + (PRSI 4% applies)
                        */
                        if (fortnightGrossPay > fortnightPayLimit){
                        
                       //-------------- REGULAR TAX DEDUCTION at 20% ---------------
                            
                            // 1) Diference between gross pay & fortnight gross pay limit = balance to be taxed at 40%
                            double fortnightRemainingBalance = fortnightGrossPay - fortnightPayLimit;

                            // 2) Finding the Gross deduction by Calling regular tax deduction 20% and assingning
                            //the fortnight limit in the parameter. (fortnight pay limit x 20%) = gross deduction
                            double fortnightGrossDeduction = getUserTax.regularTaxDeduction(fortnightPayLimit);

                            // 3) Finding Tax Deduction Payable at 20%. Calling Get fortnight tax credits method and assigning
                            // the USED tax credits in the parameters. (Gross deduction - weeklytax = fortnight tax payable at 20%) 
                            double fortnightTaxPayable_20 = fortnightGrossDeduction - getUserTax.getMonthlyTaxCredits(UserTaxCreditsWithDraw);

                            // 4) finding net pay 20%. Because this option goes above the limit we calculate the fortnight limit 
                            // at 20%.  (fortnight pay litmit - weekly tax payable = fortnight net pay 20%)
                            double fortnightNetPay_20 = fortnightPayLimit - fortnightTaxPayable_20;


                            //-------------- EMERGENCY TAX DEDUCTION at 40% ---------------
                            
                            // 1) Calling emergency tax deduction and assigning the weekly remaning balance
                            //in the parameter.
                            double taxPayable_40 = getUserTax.emergencyTaxDeduction(fortnightRemainingBalance);

                            // 2) TOTAL deduction. Adding up the taxes and all the deductions and storing in the 
                            // TOTAL deduction variable. 
                            double TOTAL_Deductions = fortnightTaxPayable_20 + taxPayable_40 + fortnightPRSI + fortnightUSC + fortnightPension;

                            // 3) finding NET PAY for this period. Weekly gross pay - Total deductions = (NET PAY) 
                            double NET_PAY = fortnightGrossPay - TOTAL_Deductions;
                        
                            //-------------------- DISPLAYING THE BREAKDOWN --------------------
                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                 + " Understand why by reading the information below \n");

                            // Displaying the 4 different ways on how the emergency Tax is calculates
                            // Enums String toString to display messages
                            System.out.println("Emergency Tax is calculaded in four different ways:\n"        
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_1.toString() + "\n"
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_2.toString() + "\n"
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_3.toString() + "\n"
                                                +UserMessage.EMERGENCY_TAX_MESSAGE_4.toString() + "\n");


                            System.out.println("As you are (Single) your RATE BAND is at 40,000.00 a year. That is divided by "
                            +"52 weeks because you are getting paid fortnight \n");

                            System.out.println("Therefore \u20ac " + df.format(fortnightPayLimit) +" is your gross pay (limit) per fortnight "
                            + "and any value above that limit will be taxed at 40% \n");

                            System.out.println("However, your groos pay is above the 700 every two weeks which means you also must "
                                    + "pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");

                            System.out.println("Your Tax Credits is Currently \u20ac" + UserTaxCreditsWithDraw + " divided by " 
                                    + "26 Number of weeks = \u20ac" +df.format(fortnightTaxCredits) + " for each gross payment limit on a fortnight bases");

                            System.out.println("This \u20ac " + df.format(fortnightTaxCredits) + " only works for the Gross Pay (Limit) as shown below: \n");

                            System.out.println("Your fortnight Gross Pay is \u20ac " + df.format(fortnightGrossPay) + " over the limit of \u20ac" + df.format(fortnightPayLimit));

                            System.out.println("Firt we calculate the Pay Related Social Insurance(PRSI) at 4% of your gross pay " + df.format(fortnightGrossPay) +" PRSI = \u20ac" + df.format(fortnightPRSI));

                            System.out.println("The Universal Social Charge (USC) based on your estimated income for " + companyName[i] + " this year of your pay " + df.format(fortnightGrossPay) +" USC = \u20ac" + df.format(fortnightUSC));

                            System.out.println("If you are in any Pension Scheme you must contribute depending on your employement against your gross pay " + df.format(fortnightGrossPay) +" Pension = \u20ac" + df.format(fortnightPension));

                            System.out.println("Lastly, the difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(fortnightRemainingBalance)  + " in which is calculate at 40% "
                                    + "and the \u20ac " + df.format(fortnightPayLimit) + " at 20% \n");

                            System.out.println("Then your Gross Pay Limit \u20ac " + df.format(fortnightPayLimit) +" x 20% = "+ "\u20ac " + df.format(fortnightGrossDeduction)
                                    + " - \u20ac " + df.format(fortnightTaxCredits) + " Tax Credits = \u20ac " + df.format(fortnightTaxPayable_20) + " Tax paybale");
                            System.out.println("Gross Pay (limit) \u20ac " + df.format(fortnightPayLimit)+ " -  \u20ac " + df.format(fortnightTaxPayable_20) + " > Tax at 20%  = NET PAY at 20 percent \u20ac " + df.format(fortnightNetPay_20) + "\n");

                            System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%) \n" 
                                    +"We must now calculate the Difference we found at Rate Band 2 (40%) \n");

                            System.out.println("The remainig balance is => \u20ac " + df.format(fortnightRemainingBalance) + " x 40% / 100% =  \u20ac " + df.format(taxPayable_40) + "\n");

                            System.out.println("Here is your Breaking down of your ( INCOME TAX CALCULATION ) of your gross pay:");
                            System.out.println("---------------------------------------------------------------------------------");

                            String TAX_breakdown =  "PRSI = \u20ac " + fortnightPRSI + "\n"
                                                    +"USC = \u20ac " + fortnightUSC + "\n"
                                                    +"Pension Scheme = \u20ac " + fortnightPension + "\n"
                                                    +"Gross pay at 20% (Tax Payable) = \u20ac " + df.format(fortnightTaxPayable_20) + "\n"
                                                    +"Difference between (gross pay) and (gross pay limit) at 40% (Emergency Tax) = \u20ac " + taxPayable_40 +"\n" 
                                                    +"Salary per hour on a fortnighly bases = \u20ac " + df.format(salaryPerHourFortnighly) + "\n" 
                                                    +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions) +"\n" + "\n"
                                                    +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                            // printing out the breakdown
                                            System.out.println(TAX_breakdown);
                                            System.out.println("FIRTS OPTION 1500");
                            } 
                        
                        
                        /**
                         * if fortnight payment is less than fortnight pay limit, but greater than 704 income band
                         * (Regular TaxDeduction 20%) + (PRSI) + USC + Pension
                         */
                        else if (fortnightGrossPay <= fortnightPayLimit && (fortnightGrossPay > IncomePRSI.FORTNIGHT.getIncomePRSI())){
                        
                            // 1) if fortnight payment is less than fortnight pay limit, but greater than 352 
                            // weekly gross deduction from the Weekly gross pay x 20%
                            double fortnightGross = getUserTax.regularTaxDeduction(fortnightGrossPay);

                            // 2) Finding Tax Deduction Payable at 20%. Calling Get fortnight tax credits method and assigning
                            // the USED tax credits in the parameters. (fortnight gross - weeklytax = fortnight tax payable at 20%) 
                            double fortnightTax_20 = fortnightGross - getUserTax.getFortnightTaxCredits(UserTaxCreditsWithDraw);

                            // 3) TOTAL deduction. Adding up the taxes and all the deductions and storing in the 
                            // TOTAL deduction variable. 
                            double TOTAL_Deductions_2 = fortnightTax_20 + fortnightPRSI + fortnightUSC + fortnightPension;

                            // 4) Finding NET PAY for this period. fortnight gross pay - Total deductions = (NET PAY)
                            double NET_PAY = fortnightGrossPay - TOTAL_Deductions_2;

                            
                            //=================== DISPLAYING BREAKDOWN ============================================
          
                            System.out.println("Because your fortnight Gross Pay is " + df.format(fortnightGrossPay)
                            +" below the fortnight pay limit > ( " + fortnightPayLimit + "). Your wages is calculate at 20% ONLY" + "\n");

                            System.out.println("However, your groos pay is above the 700 fortnight which means you also must "
                            + " pay 4% PRSI of your Gross payment less (PRSI Credits of 12 euros for each payment)");

                            System.out.println("Your Tax Credits is Currently \u20ac" + UserTaxCreditsWithDraw + " divided by " 
                                    + "26 Number of fortnight = \u20ac" +df.format(fortnightTaxCredits) + " for each gross payment limit on a fortnight bases");
                            
                            System.out.println("First we find the gross deductions of your fortnight gross pay "
                            + df.format(fortnightGrossPay) + " x 20% = \u20ac " + df.format(fortnightGross));

                            System.out.println("Then we use your current (Tax Credits) of  \u20ac " + getUserTax.getFortnightTaxCredits(UserTaxCreditsWithDraw) + " less gross deduction"
                            + " \u20ac" + df.format(fortnightGross) + " = \u20ac " + df.format(fortnightTax_20) + " Tax Deduction Payble" + "\n");

                            System.out.println("At last, gross payment \u20ac " + df.format(fortnightGrossPay) +"\n" 
                            + " - \u20ac" + df.format(fortnightTax_20) + " TaxDeduction payable " + "\n"
                            + " - \u20ac " + df.format(fortnightPRSI) + " PRSI" + "\n");


                            //===============================BREAKDOWN==============================================
                            
                            String TAX_breakdown =  "PRSI = \u20ac " + fortnightPRSI + "\n"
                                                        +"USC = \u20ac " + fortnightUSC + "\n"
                                                        +"Pension Scheme = \u20ac " + fortnightPension + "\n"
                                                        +"Gross pay at 20% (Tax Payable)= \u20ac " + df.format(fortnightTax_20) + "\n"
                                                        +"Salary per hour on a fortnight bases = \u20ac " + df.format(salaryPerHourFortnighly) + "\n" 
                                                        +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions_2) +"\n" + "\n"
                                                        +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                                // printing out the breakdown
                                                System.out.println(TAX_breakdown);
                                                System.out.println("SECOND OPTION 850");
                            }
                        
                         /**
                         * if fortnight payment is less than fortnight pay limit, but LESS than 704 income band
                         * (Regular TaxDeduction 20%) + USC + Pension
                         * 
                         * PRSI DOES NOT APPLY HERE Because PRSI is not applied to income less than 704 on a weekly basis
                         */
                        else if (fortnightGrossPay <= fortnightPayLimit && (fortnightGrossPay < IncomePRSI.FORTNIGHT.getIncomePRSI())){
                            
                            // 1)if fortnight payment is less than fortnight pay limit, but greater than 704 
                            // fortnight gross deduction from the fortnight gross pay x 20%
                            double fortnightGross = getUserTax.regularTaxDeduction(fortnightGrossPay);

                            // 2) Finding Deduction at 20% ONLY. (fortnight gross - tax credits = NET PAY) 
                            double fortnightTax_20 = fortnightGross - getUserTax.getFortnightTaxCredits(UserTaxCreditsWithDraw);

                            // 3) TOTAL deduction. Adding up the taxes and all the deductions and storing in the 
                            // TOTAL deduction variable.
                            double TOTAL_Deductions_3 = fortnightTax_20 + fortnightPRSI + fortnightUSC + fortnightPension;

                            // 4) Finding NET PAY for this period. fortnight gross pay - Total deductions = (NET PAY)
                            double NET_PAY = fortnightGrossPay - TOTAL_Deductions_3;

                             //-------------------- DISPLAYING THE BREAKDOWN --------------------
                  
                             System.out.println("Because your fortnight Gross Pay is " + df.format(fortnightGrossPay)
                                 +" below the fortnight pay limit > ( " + fortnightPayLimit + "). Your wages is calculate at 20% ONLY" + "\n");

                             System.out.println("First we find the gross deductions of your weekly gross pay "
                              + df.format(fortnightGrossPay) + " x 20% = \u20ac " + df.format(fortnightGross));

                             System.out.println("Then we use your current (Tax Credits) of  \u20ac " + getUserTax.getWeeklyTaxCredits(UserTaxCreditsWithDraw) + " less gross deduction"
                              + " \u20ac" + df.format(fortnightGross) + " = \u20ac " + df.format(fortnightTax_20) + " Tax Deduction Payable" + "\n");

                             System.out.println("At last, tax payable \u20ac " + df.format(fortnightTax_20) + " - \u20ac" + df.format(fortnightGrossPay) + " fortnight gross pay and - \u20ac" + df.format(fortnightUSC) + " USC");

                             System.out.println("Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period " + "\n");
                        
                                
                             //===============================BREAKDOWN==============================================
                            String TAX_breakdown =  "PRSI = not applied as your FORTNIGHT pay is less than " + IncomePRSI.WEEKLY.getIncomePRSI() + "\n"
                                                        +"USC = \u20ac " + fortnightUSC + "\n"
                                                        +"Pension Scheme = \u20ac " + fortnightPension + "\n"
                                                        +"Gross pay at 20% (Tax Payable)= \u20ac " + df.format(fortnightTax_20) + "\n"
                                                        +"Salary per hour on a weekly bases = \u20ac " + df.format(salaryPerHourFortnighly) + "\n" 
                                                        +"TOTAL Deductions = \u20ac " + df.format(TOTAL_Deductions_3) +"\n" + "\n"
                                                        +"Therefore your NET PAY for ( " + companyName[i] + " ) is: \u20ac " + df.format(NET_PAY) + " for this period \n";

                                                // printing out the breakdown
                                                System.out.println(TAX_breakdown);
                                                 System.out.println("THIRD OPTION 500");
                                                
                            }
                             // CASE 2 = FORTNIGHT payment Finihes here
                             break;
                        
                        
                        
                        
                         /*
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    }
        



