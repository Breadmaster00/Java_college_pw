package dz.states;

import dz.ATMMachine;

public class HasCardState implements ATMState {
    private final ATMMachine atmMachine;
    
    public HasCardState(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }
    
    @Override
    public void insertCard() {
        System.out.println("Карта уже вставлена.");
    }
    
    @Override
    public void ejectCard() {
        System.out.println("Карта извлечена.");
        atmMachine.setState(atmMachine.getNoCardState());
    }
    
    @Override
    public void enterPin(int pin) {
        if (pin == 1234) { 
            System.out.println("PIN-код верный.");
            atmMachine.setState(atmMachine.getHasCorrectPinState());
        } else {
            System.out.println("Неверный PIN-код.");
            System.out.println("Карта извлечена.");
            atmMachine.setState(atmMachine.getNoCardState());
        }
    }
    
    @Override
    public void requestCash(int amount) {
        System.out.println("Сначала введите PIN-код.");
    }
}
