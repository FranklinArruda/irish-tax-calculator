
package Enums;

/**
 * @author FRANKLIN
 */
public enum USC_PercentageBand {
    
    //Income Band PERCENTAGE 
    PERCENTAGE_BAND_1(5), // Up to €12,012.01  at 0.5%
    PERCENTAGE_BAND_2(2), // From €12,012.01 at 2%
    PERCENTAGE_BAND_3((int)4.5); //From €22,920.01 to €70,044 at 4.5% 
    
    final int income_band;
    
    USC_PercentageBand(int band){
        this.income_band=band;
    }
    
    public int getPercentage(){
        return income_band;
    }
}
