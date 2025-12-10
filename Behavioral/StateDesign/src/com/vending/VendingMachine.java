package com.vending;

import com.vending.inventory.Inventory;
import com.vending.model.Coin;
import com.vending.state.IdleState;
import com.vending.state.State;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private State vendingMachineState;
    private Inventory inventory;
    private List<Coin> coinList;

    public VendingMachine() {
        this.vendingMachineState = new IdleState();
        this.inventory = new Inventory(10);
        this.coinList = new ArrayList<>();
    }

    public State getVendingMachineState() { return vendingMachineState; }

    public void setVendingMachineState(State vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public Inventory getInventory() { return inventory; }
    public List<Coin> getCoinList() { return coinList; }
}