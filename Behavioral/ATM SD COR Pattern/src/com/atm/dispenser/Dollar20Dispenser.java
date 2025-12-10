package com.atm.dispenser;

public class Dollar20Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(int currency) {
        if (currency >= 20) {
            int num = currency / 20;
            int remainder = currency % 20;
            System.out.println("Dispensing " + num + " x $20 note");
            if (remainder != 0) this.chain.dispense(remainder);
        } else {
            this.chain.dispense(currency);
        }
    }
}