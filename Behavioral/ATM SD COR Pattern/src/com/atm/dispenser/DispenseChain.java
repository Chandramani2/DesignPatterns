package com.atm.dispenser;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(int currency);
}