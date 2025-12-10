package com.atm;
import com.atm.model.Card;
import com.atm.model.TransactionType;

public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Card myCard = new Card(123456, 1122);

        atm.getAtmState().insertCard(atm, myCard);
        atm.getAtmState().enterPin(atm, 1122);
        System.out.println("\n--- Withdrawing $130 ---");
        atm.getAtmState().selectOperation(atm, TransactionType.CASH_WITHDRAWAL, 130);
    }
}