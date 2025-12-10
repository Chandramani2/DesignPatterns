package com.atm.model;

public class Account {
    private double balance;
    public Account(double balance) { this.balance = balance; }
    public double getBalance() { return balance; }
    public void deduct(double amount) { this.balance -= amount; }
}