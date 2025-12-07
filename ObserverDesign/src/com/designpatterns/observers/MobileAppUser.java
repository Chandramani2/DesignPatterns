package com.designpatterns.observers;

import com.designpatterns.interfaces.Observer;

public class MobileAppUser implements Observer {
    private String username;

    public MobileAppUser(String username) {
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("PUSH NOTIFICATION for User @" + username + ": Watch now -> " + videoTitle);
    }
}