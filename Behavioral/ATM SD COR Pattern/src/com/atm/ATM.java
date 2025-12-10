package com.atm;

import com.atm.dispenser.*;
import com.atm.model.Account;
import com.atm.model.Card;
import com.atm.state.ATMState;
import com.atm.state.IdleState;

public class ATM {
    private ATMState atmState;
    private Card currentCard;
    private Account currentAccount;
    private DispenseChain dispenserChain;

    public ATM() {
        this.atmState = new IdleState();
        this.currentAccount = new Account(2000.00);
        initializeDispenserChain();
    }

    private void initializeDispenserChain() {
        this.dispenserChain = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();
        dispenserChain.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public ATMState getAtmState() { return atmState; }
    public void setAtmState(ATMState atmState) { this.atmState = atmState; }
    public Card getCurrentCard() { return currentCard; }
    public void setCurrentCard(Card currentCard) { this.currentCard = currentCard; }
    public Account getCurrentAccount() { return currentAccount; }
    public DispenseChain getDispenserChain() { return dispenserChain; }
}