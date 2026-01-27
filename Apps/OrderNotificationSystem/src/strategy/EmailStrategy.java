package strategy;

import constant.ChannelType;

public class EmailStrategy implements NotificationStrategy {
    public void send(String id, String msg) { System.out.println("[EMAIL] " + msg); }
    public ChannelType getChannelType() { return ChannelType.EMAIL; }
}