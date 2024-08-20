package Util;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Common Input Tasks 
 * Includes:
 * KEYBOARD INPUT UTILITIES
 * getUserText
 * getUserInt - with a range
 * getUserInt - with a minimum
 * getUserDouble - with a range
 * @author Franklin
 */
 public class Utilities {
 
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
    
    
    /**
     * Ask user to input an number within a range and return an integer value
     * If not valid, keep asking
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
     * @return a valid double (includes decimals)
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
                         System.err.println("Invalid value entered. Please enter a number greater than ZERO");    
                     }
                     else {
                         //must be OK
                         valid = true;
                     }
                 }catch(Exception e){
                     // this will be if the parseInt threw an error -- so the user did not enter a number
                     System.err.println("Only numbers!!!");  
                 }    
             }while (!valid);

             //userInput must be double now
             return (userInput);  
        }
}
