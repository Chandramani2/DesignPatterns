package com.vending;

import com.vending.model.Coin;
import com.vending.model.Item;
import com.vending.model.ItemType;
import com.vending.state.State;

public class VendingMachineApplication {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        System.out.println("|--- Filling Inventory ---|");
        fillUpInventory(vendingMachine);

        System.out.println("\n|--- User Interaction Starts ---|");

        State currentState = vendingMachine.getVendingMachineState();

        // 1. User clicks Insert Coin Button
        currentState.clickInsertCoinButton(vendingMachine);

        // 2. User inserts coins
        currentState = vendingMachine.getVendingMachineState();
        currentState.insertCoin(vendingMachine, Coin.QUARTER);
        currentState.insertCoin(vendingMachine, Coin.QUARTER);

        // 3. User selects product (Code 101 - Coke)
        System.out.println("\n|--- Selecting Product 101 ---|");
        currentState.clickSelectProductButton(vendingMachine, 101);
    }

    private static void fillUpInventory(VendingMachine vendingMachine) {
        Item[] items = {
                new Item(ItemType.COKE, 25),
                new Item(ItemType.PEPSI, 35)
        };
        for(int i=0; i<items.length; i++) {
            vendingMachine.getInventory().addItem(items[i], 101 + i);
        }
    }
}