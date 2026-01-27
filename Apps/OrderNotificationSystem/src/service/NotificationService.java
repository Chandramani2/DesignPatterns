package service;

import constant.ChannelType;
import constant.EventType;
import factory.NotificationFactory;
import model.Order;
import observer.NotificationSubscriber;
import strategy.NotificationStrategy;

import java.util.concurrent.*;
import java.util.*;

public class NotificationService {
    private static NotificationService instance;
    private final Map<String, NotificationSubscriber> subscribers = new ConcurrentHashMap<>();

    // Notification Channels (Strategy Pattern)
    private final Map<ChannelType, NotificationStrategy> channelStrategies = new HashMap<>();

    // Thread-safe subscription management
    // Map<StakeholderID, Map<EventType, Set<ChannelType>>>
    private final Map<String, Map<EventType, Set<ChannelType>>> userPreferences = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private NotificationService() {
        initializeStrategies();
    }

    private void initializeStrategies() {
        for (ChannelType type : ChannelType.values()) {
            channelStrategies.put(type, NotificationFactory.getStrategy(type));
        }
    }

    public static synchronized NotificationService getInstance() {
        if (instance == null) instance = new NotificationService();
        return instance;
    }

    public void registerSubscriber(NotificationSubscriber s) {
        subscribers.put(s.getId(), s);
    }

    // --- Requirement: Manage Subscriptions ---
    public void updateSubscription(String stakeholderId, EventType type, List<ChannelType> channels) {
        userPreferences.computeIfAbsent(stakeholderId, k -> new ConcurrentHashMap<>())
                .put(type, new CopyOnWriteArraySet<>(channels));
    }

    public void unsubscribe(String stakeholderId, EventType type) {
        if (userPreferences.containsKey(stakeholderId)) {
            userPreferences.get(stakeholderId).remove(type);
            System.out.println("[SYSTEM][SUBSCRIPTION] - Stakeholder " + stakeholderId + " unsubscribed from " + type);
        }
    }

    // --- Requirement: Handle Event (Notify Observers) ---
    public void triggerEvent(Order order, EventType eventType, String message) {
        List<String> targetIds = determineStakeholders(order, eventType);

        for (String id : targetIds) {
            NotificationSubscriber subscriber = subscribers.get(id);
            if (subscriber != null) {
                // Asynchronous execution as per requirement
                executor.submit(() -> subscriber.onEvent(eventType, order.getOrderId(), message));
            }
        }
    }
    // Internal routing logic
    public void executeNotification(String stakeholderId, EventType eventType, String orderId, String message) {
        Map<EventType, Set<ChannelType>> prefs = userPreferences.get(stakeholderId);
        if (prefs != null && prefs.containsKey(eventType)) {
            for (ChannelType channel : prefs.get(eventType)) {
                String formatted = String.format("[%d][NOTIFICATION][Order %s][%s][%s][%s] - %s",
                        System.currentTimeMillis(), orderId, eventType, stakeholderId, channel, message);
                channelStrategies.get(channel).send(stakeholderId, formatted);
            }
        }
    }

    private List<String> determineStakeholders(Order order, EventType type) {
        return switch (type) {
            case ORDER_PLACED -> List.of(order.getCustomerId(), order.getSellerId());
            case ORDER_SHIPPED -> List.of(order.getCustomerId(), order.getDeliveryPartnerId());
            case ORDER_DELIVERED -> List.of(order.getCustomerId());
        };
    }

    // --- Bonus: Event Replay ---
    public void replayMessage(String orderId, EventType type, String stakeholderId, String msg) {
        System.out.println("\n[SYSTEM] - Replaying " + type + " for " + stakeholderId);
        executor.submit(() -> executeNotification(stakeholderId, type, orderId, msg));
    }
}