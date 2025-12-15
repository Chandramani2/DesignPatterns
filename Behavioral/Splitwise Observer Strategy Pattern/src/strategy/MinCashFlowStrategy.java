package strategy;

import model.User;
import model.Transaction;
import java.util.*;

public class MinCashFlowStrategy implements DebtSimplificationStrategy {

    @Override
    public List<Transaction> simplifyDebts(Map<User, Double> netBalances) {
        List<Transaction> transactions = new ArrayList<>();

        // Use a list to sort balances easily for the greedy approach
        // Each entry is user -> netAmount (+ve means get money, -ve means give money)
        List<Map.Entry<User, Double>> balances = new ArrayList<>(netBalances.entrySet());

        // Remove settled users (0 balance)
        balances.removeIf(entry -> entry.getValue() == 0);

        simplifyRecursive(balances, transactions);
        return transactions;
    }

    private void simplifyRecursive(List<Map.Entry<User, Double>> balances, List<Transaction> transactions) {
        // Base case: If no one owes anything
        if (balances.isEmpty()) return;

        // Sort to find max debtor and max creditor
        // Ascending order: Index 0 is max debtor (-ve), Last index is max creditor (+ve)
        balances.sort(Map.Entry.comparingByValue());

        Map.Entry<User, Double> maxDebtor = balances.get(0);
        Map.Entry<User, Double> maxCreditor = balances.get(balances.size() - 1);

        double debit = maxDebtor.getValue();
        double credit = maxCreditor.getValue();

        // If very small residual values remain due to floating point, stop
        if (Math.abs(debit) < 0.01 && Math.abs(credit) < 0.01) return;

        // The amount to settle is the minimum of the absolute values
        double minAmount = Math.min(Math.abs(debit), credit);

        // Record the transaction
        transactions.add(new Transaction(maxDebtor.getKey(), maxCreditor.getKey(), minAmount));

        // Update balances
        maxDebtor.setValue(debit + minAmount);
        maxCreditor.setValue(credit - minAmount);

        // Remove users who are now settled (approx 0)
        if (Math.abs(maxDebtor.getValue()) < 0.01) balances.remove(maxDebtor);
        if (Math.abs(maxCreditor.getValue()) < 0.01) balances.remove(maxCreditor);

        // Recursive call
        simplifyRecursive(balances, transactions);
    }
}