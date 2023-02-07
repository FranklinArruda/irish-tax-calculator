
            package taxcalculatorapp;

            import java.io.BufferedReader;
            import java.io.IOException;
            import java.io.InputStreamReader;

            /**
             *
             * @author STUDENT
             */
            public class SinglePerson {

                 public void SinglePersonTax() throws IOException{

                    BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
                    
                    Tax userInt = new Tax(); 
                    Tax userText = new Tax(); // method to get user TEXT
                    
                    System.out.println("How many employers are you currently working for?");

                    // method to get numbers of employers from user
                    int numberOfEmployers = userInt.getUserInt(0); // initialized zero

                    // getting employers name based upon numbers of employers entered by the user
                    String companyName [] = new String [numberOfEmployers];

                        // get user input by reading employers name through numbers of employers
                        // call get user text method 
                        for(int i=0; i < numberOfEmployers; i++){
                            
                            //method to get user TEXT
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
                                double weeklyGrossPay = Double.parseDouble(myKeyboard.readLine());

                                // hours per week
                                System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                                double hoursPerWeek = Double.parseDouble(myKeyboard.readLine());

                                Tax weeklyLimit = new Tax();
                                weeklyLimit.getWeeklyPayLimit();

                                    // check whether user is being taxed at emergency tax
                                   if (weeklyGrossPay > weeklyLimit.getWeeklyPayLimit()){

                                       System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                               + " Understand why by reading the information below " + "\n");
                                   }

                                   else if (weeklyGrossPay <= weeklyLimit.getWeeklyPayLimit()){
                                   System.out.println("You are not at (Emergency Tax) by " + companyName[i] + "\n");
                                   }
                    }
                  }
            }
            }



