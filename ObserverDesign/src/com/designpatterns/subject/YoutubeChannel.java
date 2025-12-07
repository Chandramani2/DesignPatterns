package com.designpatterns.subject;

import com.designpatterns.interfaces.Observer;
import com.designpatterns.interfaces.Subject;
import java.util.ArrayList;
import java.util.List;

/*
The Subject (The Publisher)
This class holds the state (the new video). When the state changes, it loops through its list of subscribers and notifies them.
 */
public class YoutubeChannel implements Subject {

    private String channelName;
    private List<Observer> subscribers; // Keeping track of observers
    private String latestVideoTitle;

    public YoutubeChannel(String channelName) {
        this.channelName = channelName;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
        System.out.println("New subscriber added!");
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
        System.out.println("Subscriber removed.");
    }

    @Override
    public void notifyObservers() {
        // The Core Logic: Loop through list and notify everyone
        for (Observer observer : subscribers) {
            observer.update(latestVideoTitle);
        }
    }

    // This is the trigger method
    public void uploadVideo(String title) {
        this.latestVideoTitle = title;
        System.out.println("\n--- " + channelName + " is uploading: " + title + " ---");
        notifyObservers();
    }
}