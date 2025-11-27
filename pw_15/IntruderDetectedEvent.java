import java.util.EventObject;

public class IntruderDetectedEvent extends EventObject {
    private String location;
    
    public IntruderDetectedEvent(Object source, String location) {
        super(source); 
        this.location = location;
    }
    
    public String getLocation() {
        return location;
    }
}