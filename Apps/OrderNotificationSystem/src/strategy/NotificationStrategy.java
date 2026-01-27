package strategy;

import constant.ChannelType;

public interface NotificationStrategy {
    void send(String recipientId, String message);
    ChannelType getChannelType();
}