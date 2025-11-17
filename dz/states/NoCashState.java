package dz.states;

import dz.ATMMachine;

public class NoCashState implements ATMState {
    private final ATMMachine atmMachine;
    
    public NoCashState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }
    
    @Override
    public void insertCard() {
        System.out.println("В банкомате нет денег. Карта не принята.");
    }
    
    @Override
    public void ejectCard() {
        System.out.println("Карта не вставлена. Нечего извлекать.");
    }
    
    @Override
    public void enterPin(int pin) {
        System.out.println("В банкомате нет денег.");
    }
    
    @Override
    public void requestCash(int amount) {
        System.out.println("В банкомате нет денег.");
    }
}