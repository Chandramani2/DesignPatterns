# Chat System - Mediator Pattern Implementation

## Overview
This system implements a behavioral design pattern known as the **Mediator Pattern**. It facilitates communication between multiple users (Colleagues) by centralizing all interactions within a single hub (Mediator).

## Component Breakdown

### 1. `ChatMediator` (Interface)
- **Role:** Defines the contract for the communication protocol.
- **Methods:** - `sendMessage(String msg, User user)`: Dispatches messages to all participants except the sender.
    - `addUser(User user)`: Registers a new user into the system.
```java
package com.chat.system.mediator;
import com.chat.system.model.User;

public interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}
```
### 2. `ChatRoom` (Concrete Mediator)
- **Role:** The central hub that implements the `ChatMediator`.
- **Logic:** It maintains a `List<User>` and iterates through it to broadcast messages. This class encapsulates the "business rules" of message distribution (e.g., filtering, logging, or checking user status).
```java
package com.chat.system.mediator;
import com.chat.system.model.User;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : users) {
            // Logic: Don't send the message back to the sender
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}
```
### 3. `User` (Abstract Colleague)
- **Role:** The base class for all participants.
- **Dependency:** Holds a reference to the `ChatMediator` interface, enabling loose coupling. It doesn't know about other `User` implementations.
```java
package com.chat.system.model;
import com.chat.system.mediator.ChatMediator;

public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);
}
```
### 4. `ChatUser` (Concrete Colleague)
- **Role:** A specific implementation of a user.
- **Logic:** Overrides `send()` to delegate the work to the mediator and `receive()` to handle incoming message display logic.
```java
package com.chat.system.model;
import com.chat.system.mediator.ChatMediator;

public class ChatUser extends User {
    public ChatUser(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + " sends: " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + " receives: " + msg);
    }
}
```
### 5. `ChatApplication` (Main)
```java
import com.chat.system.mediator.*;
import com.chat.system.model.*;

public class ChatApplication {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatRoom();

        User user1 = new ChatUser(mediator, "Alice");
        User user2 = new ChatUser(mediator, "Bob");
        User user3 = new ChatUser(mediator, "Charlie");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        user1.send("Hello Team!");
    }
}
```
---

## Class Diagram Relationship
- **Mediator** ← (implements) — **ChatRoom**
- **User** — (references) → **Mediator**
- **ChatRoom** — (contains) → **List<User>**

### Design Principles Applied
1.  **Single Responsibility Principle (SRP):** The `ChatUser` only cares about user actions; the `ChatRoom` only cares about message routing.
2.  **Open/Closed Principle:** New types of users (e.g., `AdminUser`, `BotUser`) can be added without modifying the `ChatRoom` logic.
3.  **Loose Coupling:** Users do not have references to each other.

---

## 3. Senior Level Interview Q&A

### Q1: How does the Mediator pattern differ from the Observer pattern in this context?
**Answer:** The **Observer** pattern focuses on a one-to-many relationship where "Observers" react to state changes in a "Subject."
The **Mediator** pattern focuses on many-to-many communication. In a chat system, the Mediator centralizes the logic of *who* gets the message and *when*, decoupling the senders from the receivers entirely. While you can implement a Mediator using Observers, the Mediator's intent is to encapsulate the interaction logic.

### Q2: How would you handle scaling this to 100,000 concurrent users?
**Answer:** At that scale, a single JVM Mediator would fail. I would:
1.  **Distributed Mediator:** Use a message broker like **Redis Pub/Sub** or **Apache Kafka** as the backbone.
2.  **Sharding:** Partition the "Chat Rooms" across different server instances.
3.  **Asynchronous I/O:** Use Netty or NIO for non-blocking socket connections so the Mediator isn't held up by slow client network speeds.

### Q3: What is the biggest drawback of this pattern and how do you mitigate it?
**Answer:** The primary drawback is that the Mediator can become a **God Object** (a bloated class that knows and does too much).
**Mitigation:** * Use **Composition**: Delegate tasks like message validation, persistence, and logging to specialized helper classes.
* Use a **Chain of Responsibility**: Pass the message through a pipeline of processors before the Mediator broadcasts it.

### Q4: How do you ensure Thread Safety in the `ChatRoom` implementation?
**Answer:** In a multi-threaded environment, the `ArrayList` of users could throw a `ConcurrentModificationException` if a user joins while a message is being broadcast.
1.  Use `CopyOnWriteArrayList` for the user list to allow thread-safe iterations.
2.  Use `ConcurrentHashMap` if mapping users to specific IDs.
3.  Wrap the message delivery in a `Runnable` and execute it via an `ExecutorService` (Thread Pool) to prevent the main mediator thread from blocking.



---

## 4. Future Extensibility
To add "Private Messaging" to this design:
1.  Modify `sendMessage(String msg, User sender, String targetUserId)`.
2.  The `ChatRoom` would then look up the `targetUserId` in its list and call `receive()` only on that specific instance.