/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author STUDENT
 */
public enum StandardIncomeBand {
    
    RATE_BAND_1(40000), // Rate band of 40,000.00 year
    RATE_BAND_2(70000); // Rate band of 70,000.00 year
    
    final int rateBand;
    
    StandardIncomeBand(int rateBand){
        this.rateBand = rateBand;
    }
    
    public int getRateBand(){
        return rateBand;
    }
}
