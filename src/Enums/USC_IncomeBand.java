
package Enums;

/**
 * @author FRANKLIN
 */
public enum USC_IncomeBand {
    
    // Income Band RATES
    USC_BAND_1(12012), 
    USC_BAND_2(22920),  
    USC_BAND_3(70044);    
    
    final int USC;
    
    USC_IncomeBand(int deduct){
        this.USC=deduct;
    }
    
     public int getUSC_Band() {
        return this.USC;
    }
}
