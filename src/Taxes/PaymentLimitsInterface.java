package Taxes;

/**
 *
 * @author FRANKLIN
 */
public interface PaymentLimitsInterface {
    
    /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * We use the (Rate Band 1) > 40,000.00 / 52 weeks of the year = 769.00 (weekly) 
     * The weekly limit of 769.00 will then be calculated at 20%
     * If your weekly income is over that limit the remaining balance will then be taxed at 40%
     * @return weekly pay limit 
     */
    public double getWeeklyPayLimit();
    
    /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * We use the (Rate Band 1) > 40,000.00 / 26 weeks of the year = 1538.00 (fortnightly) 
     * The weekly limit of 769.00 will then be calculated at 20%
     * If your fortnight income is over that limit the remaining balance will then be taxed at 40%
     * @return fortnight pay limit
     */
    public double getFortnightPayLimit();
    
    /**
     * PAY LIMIT WORKS AS FOLLOW:
     * 
     * We use the (Rate Band 1) > 40,000.00 / 12 weeks of the year = 3333.00 (monthly) 
     * The weekly limit of 769.00 will then be calculated at 20%
     * If your monthly income is over that limit the remaining balance will then be taxed at 40%
     * @return monthly pay limit
     */
    public double getMonthlyPayLimit();
    
}
