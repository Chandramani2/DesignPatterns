package com.vending.state;

import com.vending.VendingMachine;
import com.vending.model.Coin;
import com.vending.model.Item;
import java.util.ArrayList;
import java.util.List;

public class IdleState implements State {
    public IdleState() { System.out.println("State: Idle"); }

    @Override
    public void clickInsertCoinButton(VendingMachine machine) {
        machine.setVendingMachineState(new HasMoneyState());
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        throw new IllegalStateException("Click insert coin button first");
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine, int codeNumber) {
        throw new IllegalStateException("Insert money first");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) {
        System.out.println("No money to refund");
        return new ArrayList<>();
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) {
        throw new IllegalStateException("Select product first");
    }
}