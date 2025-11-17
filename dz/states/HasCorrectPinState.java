package dz.states;

import dz.ATMMachine;

public class HasCorrectPinState implements ATMState {
    private final ATMMachine atmMachine;
    
    public HasCorrectPinState(ATMMachine atmMachine) {
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
        System.out.println("PIN-код уже введен.");
    }
    
    @Override
    public void requestCash(int amount) {
        if (amount <= atmMachine.getCashInMachine()) {
            System.out.println("Выдано " + amount + " рублей.");
            atmMachine.setCashInMachine(atmMachine.getCashInMachine() - amount);
            
            if (atmMachine.getCashInMachine() == 0) {
                atmMachine.setState(atmMachine.getNoCashState());
            }
            
            System.out.println("Карта извлечена.");
            atmMachine.setState(atmMachine.getNoCardState());
        } else {
            System.out.println("Недостаточно средств в банкомате.");
            System.out.println("Карта извлечена.");
            atmMachine.setState(atmMachine.getNoCardState());
        }
    }
}
