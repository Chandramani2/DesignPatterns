package com.atm.dispenser;

public class Dollar50Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(int currency) {
        if (currency >= 50) {
            int num = currency / 50;
            int remainder = currency % 50;
            System.out.println("Dispensing " + num + " x $50 note");
            if (remainder != 0) this.chain.dispense(remainder);
        } else {
            this.chain.dispense(currency);
        }
    }
}