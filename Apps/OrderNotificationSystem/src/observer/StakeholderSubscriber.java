package observer;

import constant.EventType;
import service.NotificationService;

// Concrete Observer
public class StakeholderSubscriber implements NotificationSubscriber {
    private final String id;
    private final NotificationService service;

    public StakeholderSubscriber(String id) {
        this.id = id;
        this.service = NotificationService.getInstance();
    }

    @Override
    public String getId() { return id; }

    @Override
    public void onEvent(EventType eventType, String orderId, String message) {
        service.executeNotification(id, eventType, orderId, message);
    }
}
