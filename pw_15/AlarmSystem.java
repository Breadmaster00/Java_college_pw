public class AlarmSystem implements SecurityEventListener {
    
    @Override
    public void onIntruderDetected(IntruderDetectedEvent event) {
        System.out.println("ТРЕВОГА! Обнаружено движение в " + 
                          event.getLocation() + "! Включаю сигнализацию!");
    }
}
