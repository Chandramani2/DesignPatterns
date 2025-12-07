package com.designpatterns.main;
/*
        ObserverPattern/
    ├── src/
    │   └── com/
    │       └── designpatterns/
    │           ├── com.designpatterns.main/
    │           │   └── YoutubeApp.java         // The Entry Point
    │           ├── interfaces/
    │           │   ├── Subject.java            // The "Publisher" interface
    │           │   └── Observer.java           // The "Subscriber" interface
    │           ├── subject/
    │           │   └── YoutubeChannel.java     // Concrete Subject
    │           └── observers/
    │               ├── EmailSubscriber.java    // Concrete Observer 1
    │               └── MobileAppUser.java      // Concrete Observer 2
 */
import com.designpatterns.observers.EmailSubscriber;
import com.designpatterns.observers.MobileAppUser;
import com.designpatterns.subject.YoutubeChannel;

public class YoutubeApp {
    public static void main(String[] args) {

        // 1. Create the Subject (Channel)
        YoutubeChannel myChannel = new YoutubeChannel("CodeWithJava");

        // 2. Create Observers (Subscribers)
        EmailSubscriber sub1 = new EmailSubscriber("alice@example.com");
        EmailSubscriber sub2 = new EmailSubscriber("bob@work.com");
        MobileAppUser sub3 = new MobileAppUser("CharlieDev");

        // 3. Register them to the subject
        myChannel.subscribe(sub1);
        myChannel.subscribe(sub2);
        myChannel.subscribe(sub3);

        // 4. Change State (Upload Video 1) -> All 3 get notified
        myChannel.uploadVideo("Observer Pattern Explained");

        // 5. Dynamic Unsubscribe
        // Bob decides to unsubscribe
        System.out.println("\n(Bob is unsubscribing...)");
        myChannel.unsubscribe(sub2);

        // 6. Change State again (Upload Video 2) -> Only Alice and Charlie get notified
        myChannel.uploadVideo("Java Streams API Tutorial");
    }
}