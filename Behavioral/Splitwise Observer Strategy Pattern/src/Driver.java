import model.User;
import service.SplitwiseService;
import strategy.MinCashFlowStrategy;
import observer.EmailNotificationObserver;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        // Initialize Users
        User alice = new User("1", "Alice");
        User bob = new User("2", "Bob");
        User charlie = new User("3", "Charlie");

        // Initialize Service
        SplitwiseService service = new SplitwiseService(new MinCashFlowStrategy());

        // --- OBSERVER PATTERN USAGE ---
        // Attach the email notification listener
        service.addObserver(new EmailNotificationObserver());
        // ------------------------------

        service.addUser(alice);
        service.addUser(bob);
        service.addUser(charlie);

        // Add Expenses (Observer will trigger print statements automatically)
        service.addExpense(alice, 300, Arrays.asList(alice, bob, charlie));

        service.addExpense(bob, 100, Arrays.asList(alice));

        // Settle Up (Observer will trigger settlement notifications)
        service.settleUp();
    }
}