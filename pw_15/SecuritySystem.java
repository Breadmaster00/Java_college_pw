import java.util.ArrayList;
import java.util.List;

public class SecuritySystem {
    private List<SecurityEventListener> listeners = new ArrayList<>();
    
    public void addSecurityEventListener(SecurityEventListener listener) {
        listeners.add(listener);
    }
    
    public void removeSecurityEventListener(SecurityEventListener listener) {
        listeners.remove(listener);
    }
    
    public void detectIntruder(String location) {
        System.out.println("Обнаружено движение в: " + location);
        IntruderDetectedEvent event = new IntruderDetectedEvent(this, location);
        notifyListeners(event);
    }
    
    private void notifyListeners(IntruderDetectedEvent event) {
        for (SecurityEventListener listener : listeners) {
            listener.onIntruderDetected(event);
        }
    }
}