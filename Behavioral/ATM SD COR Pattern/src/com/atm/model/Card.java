package com.atm.model;

public class Card {
    private int cardNumber;
    private int pin;
    public Card(int cardNumber, int pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }
    public int getPin() { return pin; }
}