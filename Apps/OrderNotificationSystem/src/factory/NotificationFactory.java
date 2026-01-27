package factory;


import constant.ChannelType;
import strategy.EmailStrategy;
import strategy.NotificationStrategy;
import strategy.PushStrategy;
import strategy.SMSStrategy;

import static constant.ChannelType.*;

public class NotificationFactory {
    public static NotificationStrategy getStrategy(ChannelType type) {
        return switch (type) {
            case EMAIL -> new EmailStrategy();
            case SMS -> new SMSStrategy();
            case APP -> new PushStrategy();
            default -> throw new IllegalArgumentException("Unknown channel");
        };
    }
}