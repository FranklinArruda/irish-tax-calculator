/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author STUDENT
 */
public enum USC_PercentageBand {
    
    //Income Band PERCENTAGE 
    BANDS_1(5), // Up to €12,012.01  at 0.5%
    BANDS_2(2), // From €12,012.01 at 2%
    BANDS_3((int)4.5); //From €22,920.01 to €70,044 at 4.5% 
    
    final int income_band;
    
    USC_PercentageBand(int band){
    this.income_band=band;
    }
    
    public int getPercentage(){
    return this.income_band;
    }
}
