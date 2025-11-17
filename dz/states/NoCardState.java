package dz.states;

import dz.ATMMachine;

public class NoCardState implements ATMState {
    private final ATMMachine atmMachine;
    
    public NoCardState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }
    
    @Override
    public void insertCard() {
        System.out.println("Карта принята. Пожалуйста, введите PIN-код.");
        atmMachine.setState(atmMachine.getHasCardState());
    }
    
    @Override
    public void ejectCard() {
        System.out.println("Карта не вставлена. Нечего извлекать.");
    }
    
    @Override
    public void enterPin(int pin) {
        System.out.println("Сначала вставьте карту.");
    }
    
    @Override
    public void requestCash(int amount) {
        System.out.println("Сначала вставьте карту и введите PIN-код.");
    }
}
