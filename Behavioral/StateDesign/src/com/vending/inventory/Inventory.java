package com.vending.inventory;

import com.vending.model.Item;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<Integer, Item> itemMap;
    Map<Integer, Integer> stockCount;

    public Inventory(int itemCount) {
        itemMap = new HashMap<>();
        stockCount = new HashMap<>();
    }

    public void addItem(Item item, int code) {
        itemMap.put(code, item);
        stockCount.put(code, stockCount.getOrDefault(code, 0) + 1);
    }

    public Item getItem(int code) {
        return itemMap.get(code);
    }

    public void updateSoldOutItem(int code) {
        stockCount.put(code, stockCount.get(code) - 1);
    }
}