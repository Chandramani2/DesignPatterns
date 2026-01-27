package facade;

import service.*;
import model.*;
import constant.*;
import java.util.*;

public class NotificationFacade {
    private static NotificationFacade instance;
    private final NotificationService service;

    // Private constructor for Singleton
    private NotificationFacade() {
        this.service = NotificationService.getInstance();
    }

    // Thread-safe Singleton Implementation
    public static synchronized NotificationFacade getInstance() {
        if (instance == null) {
            instance = new NotificationFacade();
        }
        return instance;
    }

    // Simplified API for the Order System
    public void postOrderEvent(Order order, EventType event, String msg) {
        service.triggerEvent(order, event, msg);
    }

    public void updateSubscription(String userId, EventType type, List<ChannelType> channels) {
        service.updateSubscription(userId, type, channels);
    }

    public void replay(String orderId, EventType type, String userId, String msg) {
        service.replayMessage(orderId, type, userId, msg);
    }
}