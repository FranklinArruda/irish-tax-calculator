/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author STUDENT
 */
public enum USC {
    
    USC_A(12012),
    USC_B(22920),
    USC_C(70044);
    
    final int deduction;
    
    USC(int deduct){
    deduction=deduct;
    }
    
     public int getDeduction() {
        return deduction;
    }
}
