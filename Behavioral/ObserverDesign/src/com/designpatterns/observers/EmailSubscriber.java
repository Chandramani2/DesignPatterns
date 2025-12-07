package com.designpatterns.observers;
/*
The Observers (The Subscribers)
These classes implement Observer. They decide how to react to the news. One sends an email, the other sends a push notification.
 */
import com.designpatterns.interfaces.Observer;

public class EmailSubscriber implements Observer {
    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("EMAIL to " + email + ": New video uploaded -> " + videoTitle);
    }
}