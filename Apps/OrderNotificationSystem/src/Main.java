import constant.ChannelType;
import constant.EventType;
import facade.NotificationFacade;
import model.Order;
import observer.StakeholderSubscriber;
import service.NotificationService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. Get the Facade Instance (Singleton)
        NotificationFacade notificationSystem = NotificationFacade.getInstance();
        NotificationService notificationService = NotificationService.getInstance();
        // 2. Register Observers into the Registry
        // In a real system, these would be registered when users create accounts
        notificationService.registerSubscriber(new StakeholderSubscriber("CUSTOMER-1"));
        notificationService.registerSubscriber(new StakeholderSubscriber("SELLER-1"));
        notificationService.registerSubscriber(new StakeholderSubscriber("DELIVERY-PARTNER-1"));

        // 3. Define the Order
        Order order001 = new Order("ORDER-001", "CUSTOMER-1", "SELLER-1", "DELIVERY-PARTNER-1");

        System.out.println("=== SCENARIO 1: ORDER PLACED ===");
        // Setup initial preferences: Customer wants SMS,EMAIL AND APP,,, Seller wants EMAIL
        notificationSystem.updateSubscription("CUSTOMER-1", EventType.ORDER_PLACED, List.of(ChannelType.SMS, ChannelType.EMAIL, ChannelType.APP));
        notificationSystem.updateSubscription("SELLER-1", EventType.ORDER_PLACED, List.of(ChannelType.EMAIL));

        notificationSystem.postOrderEvent(order001, EventType.ORDER_PLACED, "Your package with orderId:" + order001.getOrderId() + " has been placed successfully.");
        Thread.sleep(500); // Allow async threads to log

        System.out.println("\n=== SCENARIO 2: ORDER SHIPPED ===");
        // Customer wants APP push for shipping, Logistics wants SMS
        notificationSystem.updateSubscription("CUSTOMER-1", EventType.ORDER_SHIPPED, List.of(ChannelType.APP));
        notificationSystem.updateSubscription("DELIVERY-PARTNER-1", EventType.ORDER_SHIPPED, List.of(ChannelType.SMS));

        notificationSystem.postOrderEvent(order001, EventType.ORDER_SHIPPED, "Your order is on the way!");
        Thread.sleep(500);

        System.out.println("\n=== SCENARIO 3: ORDER DELIVERED ===");
        // Customer wants both EMAIL and SMS for delivery confirmation
        notificationSystem.updateSubscription("CUSTOMER-1", EventType.ORDER_DELIVERED, List.of(ChannelType.EMAIL, ChannelType.SMS));

        notificationSystem.postOrderEvent(order001, EventType.ORDER_DELIVERED, "Package delivered. Enjoy your purchase!");
        Thread.sleep(500);

        System.out.println("\n=== SCENARIO 4: BONUS - REPLAY MESSAGE ===");
        // Requirement: Replay uses LATEST preferences.
        // Let's change Customer preference for ORDER_PLACED from SMS to APP before replaying.
        notificationSystem.updateSubscription("CUSTOMER-1", EventType.ORDER_PLACED, List.of(ChannelType.APP));

        notificationSystem.replay("ORDER-001", EventType.ORDER_PLACED, "CUSTOMER-1", "Your package with orderId:" + order001.getOrderId() + " has been placed successfully.");
        Thread.sleep(500);

        System.out.println("\n=== SCENARIO 5: UNSUBSCRIBE ===");
        // Stakeholder stops receiving notifications for a specific event
        notificationSystem.updateSubscription("CUSTOMER-1", EventType.ORDER_DELIVERED, List.of()); // Or a dedicated unsubscribe method

        System.out.println("System demonstration complete.");
        System.exit(0); // Shutdown executor threads
    }
}