package com.vending.state;

import com.vending.VendingMachine;
import com.vending.model.Coin;
import com.vending.model.Item;
import java.util.List;

public class DispenseState implements State {

    public DispenseState(VendingMachine machine, int codeNumber) {
        System.out.println("State: Dispense");
        dispenseProduct(machine, codeNumber);
    }

    @Override
    public void clickInsertCoinButton(VendingMachine machine) {
        throw new IllegalStateException("Wait, dispensing...");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        throw new IllegalStateException("Wait, dispensing...");
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine, int codeNumber) {
        throw new IllegalStateException("Wait, dispensing...");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) {
        throw new IllegalStateException("Too late, dispensing...");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) {
        System.out.println("Product Dispensed.");
        Item item = machine.getInventory().getItem(codeNumber);
        machine.getInventory().updateSoldOutItem(codeNumber);
        machine.setVendingMachineState(new IdleState());
        return item;
    }
}