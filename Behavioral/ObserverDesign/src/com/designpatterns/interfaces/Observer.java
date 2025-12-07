package com.designpatterns.interfaces;
/*
The Interfaces ( The Contract)
We decouple the classes using interfaces. The Subject doesn't care if the observer is an Email or a Mobile App; it just knows it has an update() method.
 */
public interface Observer {
    // This method is called automatically when the Subject changes
    void update(String videoTitle);
}