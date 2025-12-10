package com.atm.state;

import com.atm.ATM;
import com.atm.model.Card;
import com.atm.model.TransactionType;

public class IdleState implements ATMState {
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card Inserted.");
        atm.setCurrentCard(card);
        atm.setAtmState(new HasCardState());
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("Please insert card first.");
    }

    @Override
    public void selectOperation(ATM atm, TransactionType type, int amount) {
        System.out.println("Please insert card first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("No card to eject.");
    }
}