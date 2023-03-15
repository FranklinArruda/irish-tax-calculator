
package Enums;
/**
 *
 * @author STUDENT
 */
public enum HourOfTheDay {
    
    MID_DAY(12),
    AFTERNOON(17);
    
    final int hourOfTheDay;
    
    HourOfTheDay(int hourOfTheDay){
        this.hourOfTheDay = hourOfTheDay;
    }
    
     public int getHour() {
        return hourOfTheDay;
    }
}
