package com.vending.state;

import com.vending.VendingMachine;
import com.vending.model.Coin;
import com.vending.model.Item;
import java.util.List;

public class HasMoneyState implements State {
    public HasMoneyState() { System.out.println("State: HasMoney"); }

    @Override
    public void clickInsertCoinButton(VendingMachine machine) { return; }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        System.out.println("Accepted: " + coin.name());
        machine.getCoinList().add(coin);
    }

    @Override
    public void clickSelectProductButton(VendingMachine machine, int codeNumber) {
        machine.setVendingMachineState(new DispenseState(machine, codeNumber));
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) {
        System.out.println("Refunding money...");
        machine.setVendingMachineState(new IdleState());
        return machine.getCoinList();
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) {
        throw new IllegalStateException("Product not selected yet");
    }
}