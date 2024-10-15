
package PAYE;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FRANKLIIN
 */
public class ApplyRateResults {
    
    private List<String> messages;
    private double result;
    
    public ApplyRateResults(){
        messages = new ArrayList<>();
        result = 0.0;
    }
    
    public void setMessage(String message){
       this.messages.add(message);
    }
    
    public void setResult(double result){
        this.result = result;
    }
    
     public List<String> getMessage(){
        return this.messages;
    }
    
    public double getResult(){
        return this.result;
    }
}
