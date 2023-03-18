/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UserStatus;

/**
 *
 * @author STUDENT
 */
public interface UserTaxCreditsInterface {
    
    public double SinglePersonTaxCreditsWithdraw(String companyName, double amount);
     
    public double getSinglePersonTaxCreditsBalance();
    
    public double MarriedPersonTaxCreditsWithdraw(String companyName, double amount);
    
    public double getMarriedPersonTaxCredits();
     
     
    
}
