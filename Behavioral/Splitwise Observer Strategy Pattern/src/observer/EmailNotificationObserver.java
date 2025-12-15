package observer;

import model.User;
import model.Transaction;
import java.util.List;

public class EmailNotificationObserver implements ExpenseObserver {

    @Override
    public void onExpenseAdded(User paidBy, double amount, List<User> splitBetween) {
        System.out.println("\n[Email Service] Notification:");
        System.out.println("   Hello " + paidBy.getName() + ", you added an expense of $" + amount);
        for (User u : splitBetween) {
            if (!u.equals(paidBy)) {
                System.out.println("   -> Sending email to " + u.getName() + ": You owe share for an expense paid by " + paidBy.getName());
            }
        }
    }

    @Override
    public void onSettlementCalculated(List<Transaction> transactions) {
        System.out.println("\n[Email Service] Settlement Plan Created:");
        for (Transaction t : transactions) {
            System.out.println("   -> Notifying users: " + t.toString());
        }
    }
}