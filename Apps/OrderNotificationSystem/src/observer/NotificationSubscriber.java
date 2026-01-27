package observer;

import constant.EventType;

public interface NotificationSubscriber {
    String getId();

    void onEvent(EventType eventType, String orderId, String message);

}
