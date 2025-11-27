public class Main {
    public static void main(String[] args) {
        SecuritySystem securitySystem = new SecuritySystem();
        
        AlarmSystem alarm = new AlarmSystem();
        
        securitySystem.addSecurityEventListener(alarm);
        
        securitySystem.detectIntruder("главный вход");
        securitySystem.detectIntruder("задний двор");
        securitySystem.detectIntruder("окно на первом этаже");
    }
}