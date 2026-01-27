package strategy;

import constant.ChannelType;

public class PushStrategy implements NotificationStrategy {
    public void send(String id, String msg) { System.out.println("[SMS] " + msg); }
    public ChannelType getChannelType() { return ChannelType.APP; }
}
