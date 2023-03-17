
package Enums;

/**
 * @author FRANKLIN
 */
public enum USC_IncomeBand {
    
    // Income Band RATES
    USC_RATE_BAND_1(12012), 
    USC_RATE_BAND_2(22920),  
    USC_RATE_BAND_3(70044);    
    
    final int USC_BAND;
    
    USC_IncomeBand(int deduct){
        this.USC_BAND=deduct;
    }
    
     public int getUSC_Band() {
        return USC_BAND;
    }
}
