               
                package taxcalculatorapp;
                import java.io.IOException;

                /**
                 * @author STUDENT
                 */
                public class TaxCalculatorApp {

                    /**
                     * @param args the command line arguments
                     */
                    public static void main(String[] args) throws IOException {
       
                        //UserMenu main = new UserMenu();
                        //main.MainMenu();
                                       
                        //PersonStatus single = new PersonStatus();
                        //single.SinglePersonTax();
                         
                        Tax userPension = new Tax();
                        
                        System.out.println("Are you in any Pension Scheme ? \n"
                                    +"Enter  Y (YES) / N (No):\n");
                            double weeklyPension = userPension.getPension(850);  
                        
                            System.out.println(weeklyPension);
                       
                       //userPension.getUserText("enter something");
                    }

                }
