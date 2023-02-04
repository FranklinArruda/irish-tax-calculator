/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author STUDENT
 */
public class SinglePerson extends Tax {
    
     public void SinglePersonTax() throws IOException{

        BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
          
        // get numbers of employers from user 
        int numberOfEmployers=0;

        System.out.println("How many employers are you currently working for?");
        numberOfEmployers = Integer.parseInt(myKeyboard.readLine());


        // getting employers name based upon numbers of employers entered by the user
        String companyName [] = new String [numberOfEmployers];

        // get user input by reading employers name through numbers of employers 
        for(int i=0; i < numberOfEmployers; i++){
            System.out.println("Employer " +(i +1) + " Name:");

               companyName[i] = myKeyboard.readLine();
            
        }                    

            // VALIDATING TAX CREDITS LIMIT
            int weeklyTaxCrdits = 0;
           // int remainingTaxCredit = this.yearlyTaxCredits;


/* 
        for(int i=0; i<companyName.length; i++) {

           // do{
                System.out.println("Enter the Tax Credits for => " + companyName[i]);
                weeklyTaxCrdits = Integer.parseInt(myKeyboard.readLine());
                SinglePersonTaxCreditBalance(weeklyTaxCrdits);
                //System.out.println("Your BALANCE IS " + getYearlyTaxCredits());

            // Payment frequency menu   
            String userPrompt = "How often are getting paid by " + companyName[i] + " ? " + "\n"
                        + "Please Enter: " + "\n"
                        + "------------- " + "\n"
                        + "1: Weekly " + "\n"
                        + "2: Fortnightly " + "\n" 
                        + "3: Monthly "+ "\n";

            // output message for the user
            System.out.println(userPrompt); 

            // get user input
            int paymentFrequency = Integer.parseInt(myKeyboard.readLine());

            double weeklyGrossPay = 0;
            double fortnightlyGrossPay = 0;
            double monthlyGrossPay = 0;
            double hoursPerWeek = 0;
            double salaryPerHour = 0;
           // int weeklyTaxCrdits = 0;
           // double remainingBalance = 0;

            // limit is set up by the rate band 1 = 40,000.00 divided by how often a employee is getting paid 
            double weeklyGrossPayLimit = 0; // weekly limit = 40,000.00 / 52 
            double fortnightlyGrossPayLimit = 0; // fortnightly limit = 40,000.00 / 26 
            double monthlyGrossPayLimit = 0; // monthly limit = 40,000.00 / 26
            double netDeduction_20 = 0;
            double netDeduction_40 = 0;
            double totalDeduction = 0;
            double NET_PAY = 0;


           // Payment Frequency statement
            switch (paymentFrequency){

                // WEEKLY PAYMENT
                case 1:

                // weekly gross from the user
                System.out.println("Enter your Weekly Groos pay for " + companyName[i]);
                weeklyGrossPay = Double.parseDouble(myKeyboard.readLine());


                // hours per week
                System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                hoursPerWeek = Double.parseDouble(myKeyboard.readLine());

            //finding Weekly Gross Pay(LIMIT)
             weeklyGrossPayLimit = this.rateBand1 / this.weeklyPayLimit; 
   
                // check whether user is being taxed at emergency tax
                if (weeklyGrossPay > weeklyGrossPayLimit){

                     ///////// CALCULATION AT 20% first ///////////////////

                    // 2) diference between gross pay & weekly gross pay limit = balance to be taxed at 40%
                    double  remainingBalance = weeklyGrossPay - weeklyGrossPayLimit;

                    // 3) finding gross deductions (Gross pay * 20% / 100%)
                    double grossDeduction = weeklyGrossPayLimit * this.regularTaxPercentage / this.percentage;

                    // 4) finding the weekly tax credits
                    double netTaxCredits = weeklyTaxCrdits / this.weeklyTaxCreditsYear;

                    // 5) finding Tax Payble
                    double taxPayable = grossDeduction - netTaxCredits;

                    // 6) finding net pay 20%
                    double netPay_20 = weeklyGrossPayLimit - taxPayable;

                    // 5) finding NET Deductions 20%
                    netDeduction_20 = grossDeduction - netTaxCredits;


                    ///////// CALCULATION AT 40% now ///////////////////

                    // 1) calculating remaing balance from gross pay and limit per week
                    netDeduction_40 = remainingBalance * this.emergencyTaxPercentage / this.percentage;

                    // 2) total deduction 
                    totalDeduction = netDeduction_20 + netDeduction_40;

                    double netPay_40 = netDeduction_40;

                    // 3) finding NET PAY
                    NET_PAY = weeklyGrossPay - totalDeduction;

                    System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                            + " Understand why by reading the information below " + "\n");

                    System.out.println("Emergency Tax is calculaded in three different ways: " + "\n"
                    +"1: When you started in your new job and have no PPSN " + "\n"
                    +"2: When you started in your new Job. You have PPSN, but the first salary will always be taxed at 40%" + "\n"
                    +"3: When you are working for multiple employer and you do not have enough Tax Credits for each employer" +"\n");



                    System.out.println("As you are (Single) your RATE BAND is at " + this.rateBand1 + " a year. That is divided by "
                    +this.weeklyPayLimit + " weeks because you are getting paid weekly");


                    System.out.println("Therefore \u20ac " + df.format(weeklyGrossPayLimit) + " is your gross pay (limit) per week "
                    +"and any value above that limit will be taxed at 40% " + "\n");


                    System.out.println("Your Tax Credits is Currently \u20ac " + df.format(weeklyTaxCrdits)+ " divided by " 
                            +this.weeklyTaxCreditsYear + " No.weeks = \u20ac " + df.format(netTaxCredits) + " for each gross payment limit on a weekly bases");
                    System.out.println("This \u20ac " + df.format(netTaxCredits) + " only works for the Gross Pay (Limit) as shown below:" + "\n");


                    System.out.println("Your weekly Gross Pay is \u20ac " + df.format(weeklyGrossPay) + " over the limit");

                    // calling gross pay limit method
                    weeklyGrossPayLimit();


                    System.out.println("Difference between Gross pay & Gross Pay limit is  \u20ac " + df.format(remainingBalance) +" in which is calculate at 40% "
                            + "and the \u20ac " + df.format(weeklyGrossPayLimit) +" at 20%" + "\n");


                    System.out.println("First, we calculate your Gross Pay Limit \u20ac " + df.format(weeklyGrossPayLimit) +" x 20% = "+ "\u20ac " + df.format(grossDeduction) 
                            + " - \u20ac " + df.format(netTaxCredits) + " Tax Credits = \u20ac " + df.format(taxPayable) + " Tax paybale");
                    System.out.println("Gross Pay (limit) \u20ac " + df.format(weeklyGrossPayLimit) + " -  \u20ac " + df.format(taxPayable) + " = NET PAY at 20% > \u20ac " + df.format(netPay_20));

                    System.out.println("We have calculated your Gross Pay limit at Rate Band 1 (20%)" + "\n" 
                            +"We must now calculate the Difference we found at Rate Band 2 (40%)" + "\n");

                    System.out.println("The remainig balance is => \u20ac " + df.format(remainingBalance) + " x 40% / 100% =  \u20ac " + df.format(netDeduction_40));
                    System.out.println("Your tax calculation is \u20ac " + df.format(netDeduction_20) + " + \u20ac " +  df.format(netDeduction_40) +" = \u20ac " + df.format(totalDeduction));
                    System.out.println("\u20ac " + df.format(totalDeduction) + " - \u20ac " + df.format(weeklyGrossPay));
                    System.out.println("Therefore your NET PAY is for " + companyName[i] + "is: \u20ac " + df.format(NET_PAY) + "\n");

            }

                else if (weeklyGrossPay <= weeklyGrossPayLimit){

                     ///////// CALCULATION AT 20% only ///////////////////

                    // 1) finding gross deductions
                    //grossDeduction = weeklyGrossPayLimit * this.regularTaxPercentage / this.percentage;

                    // 2) finding the weekly tax credits
                   double taxPayable = weeklyTaxCrdits / this.weeklyTaxCreditsYear;

                    // 3) finding NET Deductions
                    //netDeduction_20 = grossDeduction - weeklyTaxCrdits;

                    // 4) finding NET PAY AT 
                    //NET_PAY = weeklyGrossPay - totalDeduction;

                   // String taxBreakDown = "Your weekly Gross Pay is " + weeklyGrossPay 
                         //   + "Your weekly gross pay (Limit) is " + weeklyGrossPayLimit 
                           // + "Difference between them is "   ;

                    System.out.println("You are not at (Emergency Tax) by " + companyName[i] + "\n");
                    System.out.println("YOUR TAX CREDIT IS CURRENTLY " + weeklyTaxCrdits + " divided by " 
                    + this.weeklyTaxCreditsYear + " No.weeks => " + taxPayable + " for this period "  );
            }
                else{
                    System.out.println("SOMETHING WENT WRONG!!!");
                }
            break;
*/
        }
      }
    

    

