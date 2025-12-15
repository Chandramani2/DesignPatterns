package observer;

import model.User;
import model.Transaction;
import java.util.List;

public interface ExpenseObserver {
    void onExpenseAdded(User paidBy, double amount, List<User> splitBetween);
    void onSettlementCalculated(List<Transaction> transactions);
}