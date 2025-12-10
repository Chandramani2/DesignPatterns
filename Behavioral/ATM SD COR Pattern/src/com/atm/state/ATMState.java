package com.atm.state;

import com.atm.ATM;
import com.atm.model.Card;
import com.atm.model.TransactionType;

public interface ATMState {
    void insertCard(ATM atm, Card card);
    void enterPin(ATM atm, int pin);
    void selectOperation(ATM atm, TransactionType type, int amount);
    void ejectCard(ATM atm);
}