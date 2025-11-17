package dz;

import dz.states.ATMState;
import dz.states.NoCardState;
import dz.states.HasCardState;
import dz.states.HasCorrectPinState;
import dz.states.NoCashState;

public class ATMMachine {
    private ATMState noCardState;
    private ATMState hasCardState;
    private ATMState hasCorrectPinState;
    private ATMState noCashState;
    
    private ATMState currentState;
    private int cashInMachine = 2000;
    
    public ATMMachine() {
        noCardState = new NoCardState(this);
        hasCardState = new HasCardState(this);
        hasCorrectPinState = new HasCorrectPinState(this);
        noCashState = new NoCashState(this);
        
        currentState = noCardState;
    }
    
    public void setState(ATMState state) {
        this.currentState = state;
    }

    public void insertCard() {
        currentState.insertCard();
    }
    
    public void ejectCard() {
        currentState.ejectCard();
    }
    
    public void enterPin(int pin) {
        currentState.enterPin(pin);
    }
    
    public void requestCash(int amount) {
        currentState.requestCash(amount);
    }
    
    public ATMState getNoCardState() { return noCardState; }
    public ATMState getHasCardState() { return hasCardState; }
    public ATMState getHasCorrectPinState() { return hasCorrectPinState; }
    public ATMState getNoCashState() { return noCashState; }
    
    public int getCashInMachine() { return cashInMachine; }
    public void setCashInMachine(int cash) { this.cashInMachine = cash; }
}