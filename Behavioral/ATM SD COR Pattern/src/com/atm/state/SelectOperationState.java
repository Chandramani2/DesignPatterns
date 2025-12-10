package com.atm.state;

import com.atm.ATM;
import com.atm.model.Card;
import com.atm.model.TransactionType;

public class SelectOperationState implements ATMState {
    @Override
    public void insertCard(ATM atm, Card card) { return; }

    @Override
    public void enterPin(ATM atm, int pin) { return; }

    @Override
    public void selectOperation(ATM atm, TransactionType type, int amount) {
        if (type == TransactionType.CASH_WITHDRAWAL) {
            if(atm.getCurrentAccount().getBalance() >= amount) {
                System.out.println("Processing Withdrawal of $" + amount);
                atm.getCurrentAccount().deduct(amount);
                atm.getDispenserChain().dispense(amount); // Chain Trigger
                ejectCard(atm);
            } else {
                System.out.println("Insufficient Balance.");
                ejectCard(atm);
            }
        } else if (type == TransactionType.BALANCE_CHECK) {
            System.out.println("Balance: " + atm.getCurrentAccount().getBalance());
            ejectCard(atm);
        }
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Transaction finished. Card Ejected.");
        atm.setAtmState(new IdleState());
    }
}