package com.atm.state;

import com.atm.ATM;
import com.atm.model.Card;
import com.atm.model.TransactionType;

public class HasCardState implements ATMState {
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        if (atm.getCurrentCard().getPin() == pin) {
            System.out.println("PIN Correct.");
            atm.setAtmState(new SelectOperationState());
        } else {
            System.out.println("Invalid PIN.");
            ejectCard(atm);
        }
    }

    @Override
    public void selectOperation(ATM atm, TransactionType type, int amount) {
        System.out.println("Please enter PIN first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Card Ejected.");
        atm.setAtmState(new IdleState());
    }
}