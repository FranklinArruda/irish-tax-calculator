
    package Menu;

    import Enums.TaxCalculatorContainer.HourOfTheDay;
    import Enums.TaxCalculatorContainer.Status;
    import UserStatus.UserStatus;
    import Utilities.Utilities;

    import java.io.IOException;
    import java.util.Calendar;
    import java.util.GregorianCalendar;


    /**
     * @author FRANKLIN
     */
    public class UserCalculatorMenu {

        int selectedOption; 

        public void userGreetings() throws IOException{

            // getting current time to greeting the user
            GregorianCalendar time = new GregorianCalendar();
            int hour = time.get(Calendar.HOUR_OF_DAY);      
            
            // Main users MENU
            String userOption ="Please Choose: " + "\n"
                    + "------------------" + "\n"
                    + "1: Tax Calculation" + "\n"
                    + "2: Financial Planning";
           
                if (hour < HourOfTheDay.MID_DAY.getHour()){
                 System.out.println("Good Morning!" + "\n" + userOption);
                }

                else if (hour <= HourOfTheDay.AFTERNOON.getHour()){ // && (hour == HourOfTheDay.AFTERNOON.getHour())){
                System.out.println("Good Afternoon" + "\n" + userOption);
                }

                else if (hour == HourOfTheDay.MID_DAY.getHour()){
                System.out.println("Good Noon" + "\n" + userOption);
                }

                else{
                System.out.println("Good Evening" + "\n" + userOption);
                }

                // calling method to get users INT and initializing the return type to zero
                Utilities getUserInput = new Utilities();
                            
                // get user input
                selectedOption = getUserInput.getUserInt();
                
                if (selectedOption == 1){
                    String userMenu ="Welcome to your Tax Calculation! \n" 
                            + "\n" 
                            + "The purpose of Tax Calculation is just that. To find out whether you are paying more tax than you \n" 
                            + "should and how to fix it. In most cases, specifically foreigners who can only part-time (20 hrs) and \n" 
                            + "has multiple employers in which very often are being taxed at 40% called (Emergency Tax).\n"
                            + "\n" 
                            + "In order for us to find out the amount of tax that best suits you, or whether you are currently working for \n"
                            + "more than one employer. Some information is required as depending on your status the (Tax Credits) apply : \n";

                    // prompting user walkthrough
                    System.out.println(userMenu);
                    
                    // for loop to Inerate Enums (Status)assigning type to -1 to count the Enums
                    for (int type = 1; type <=Status.values().length; type++) {
                         System.out.println( type + ": " + Status.values()[type-1]);
                    }
                    
                    // calling method to get users INT and initializing the return type to zero
                    int user = getUserInput.getUserInt();

                        // user option switch 
                        switch(user){
                            case 1:
                                UserStatus single = new UserStatus();
                                single.SinglePersonTax();
                                break;

                            case 2:
                                System.out.println("Married");
                                System.out.println("You MUST Finish the calculation for a SINGLE PERSON first");
                                break;

                            default:// do nothing  
                   }
            } 
                
                
            else if (selectedOption == 2){      
                     System.out.println("In order to proceed you must finish the TAX CALCULATION first!");
            }
        }
    }
