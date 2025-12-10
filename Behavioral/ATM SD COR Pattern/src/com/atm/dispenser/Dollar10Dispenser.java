package com.atm.dispenser;

public class Dollar10Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(int currency) {
        if (currency >= 10) {
            int num = currency / 10;
            int remainder = currency % 10;
            System.out.println("Dispensing " + num + " x $10 note");
            if (remainder != 0) System.out.println("Cannot dispense remaining: $" + remainder);
        } else {
            System.out.println("Cannot dispense amount smaller than $10: $" + currency);
        }
    }
}