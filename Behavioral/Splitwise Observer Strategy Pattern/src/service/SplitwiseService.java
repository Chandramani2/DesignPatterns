package service;

import model.User;
import model.Transaction;
import observer.ExpenseObserver;
import strategy.DebtSimplificationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseService {
    private Map<User, Double> netBalances;
    private DebtSimplificationStrategy strategy;

    // 1. Maintain a list of Observers
    private List<ExpenseObserver> observers;

    public SplitwiseService(DebtSimplificationStrategy strategy) {
        this.netBalances = new HashMap<>();
        this.strategy = strategy;
        this.observers = new ArrayList<>();
    }

    // 2. Methods to Attach/Detach Observers
    public void addObserver(ExpenseObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(ExpenseObserver observer) {
        this.observers.remove(observer);
    }

    public void addUser(User user) {
        netBalances.putIfAbsent(user, 0.0);
    }

    public void addExpense(User paidBy, double amount, List<User> splitBetween) {
        double splitAmount = amount / splitBetween.size();

        netBalances.put(paidBy, netBalances.getOrDefault(paidBy, 0.0) + amount);

        for (User user : splitBetween) {
            netBalances.put(user, netBalances.getOrDefault(user, 0.0) - splitAmount);
        }

        // 3. Notify Observers about the new expense
        notifyExpenseAdded(paidBy, amount, splitBetween);
    }

    public List<Transaction> settleUp() {
        List<Transaction> transactions = strategy.simplifyDebts(netBalances);

        // 4. Notify Observers about the settlement
        notifySettlement(transactions);

        return transactions;
    }

    // Helper methods to trigger notifications
    private void notifyExpenseAdded(User paidBy, double amount, List<User> splitBetween) {
        for (ExpenseObserver observer : observers) {
            observer.onExpenseAdded(paidBy, amount, splitBetween);
        }
    }

    private void notifySettlement(List<Transaction> transactions) {
        for (ExpenseObserver observer : observers) {
            observer.onSettlementCalculated(transactions);
        }
    }
}