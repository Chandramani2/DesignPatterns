package com.vending.state;

import com.vending.VendingMachine;
import com.vending.model.Coin;
import com.vending.model.Item;
import java.util.List;

public interface State {
    void clickInsertCoinButton(VendingMachine machine);
    void insertCoin(VendingMachine machine, Coin coin);
    void clickSelectProductButton(VendingMachine machine, int codeNumber);
    List<Coin> refundFullMoney(VendingMachine machine);
    Item dispenseProduct(VendingMachine machine, int codeNumber);
}