package com.vending.model;

public class Item {
    ItemType type;
    int price;
    public Item(ItemType type, int price) {
        this.type = type;
        this.price = price;
    }
    public int getPrice() { return price; }
}