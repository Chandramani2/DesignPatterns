# 🏛️ The Ultimate Design Patterns Cheat Sheet

A comprehensive reference guide to the "Gang of Four" (GoF) Design Patterns. This repository contains definitions, diagrams, and concise Java examples to help you understand and implement these patterns.

![Design Patterns](https://img.shields.io/badge/Pattern%20Type-Gang%20of%20Four-blue) ![Language](https://img.shields.io/badge/Language-Java-orange) ![Status](https://img.shields.io/badge/Status-Complete-green)

## 📋 Table of Contents

1. [Creational Patterns](#creational) (Construction)
2. [Structural Patterns](#structural) (Structure)
3. [Behavioral Patterns](#behavioral) (Interaction)

---

## <a name="creational"></a> 1. Creational Patterns
**Goal:** Abstract the instantiation process. They help make a system independent of how its objects are created, composed, and represented.

### 🔹 Singleton
**Use when:** You need strictly one instance of a class (e.g., Database connection, Logger).



```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {} // Private constructor

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

🔹 Factory Method
Use when: A class can't anticipate the class of objects it must create. It delegates creation to subclasses.

```java
// Interface
interface Button { void render(); }

// Concrete Implementations
class HTMLButton implements Button {
    public void render() { System.out.println("<button>Click</button>"); }
}

// Factory
abstract class Dialog {
    abstract Button createButton(); // Factory Method

    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }
}
```

🔹 Builder
Use when: You need to construct complex objects step-by-step (e.g., an SQL query builder, a Pizza with toppings).

```java
User user = new User.Builder()
    .setName("Alice")
    .setAge(30)
    .setEmail("alice@example.com")
    .build();
```

🔹 Prototype
Use when: Creating an object is costly (e.g., parsing a database), and you want to clone an existing instance instead of creating a new one from scratch.

```java
abstract class Shape implements Cloneable {
    public Object clone() {
        Object clone = null;
        try { clone = super.clone(); } catch (CloneNotSupportedException e) { e.printStackTrace(); }
        return clone;
    }
}
```

🔹 Abstract Factory
Use when: You need to create families of related objects (e.g., DarkTheme Scrollbar + DarkTheme Window vs. LightTheme Scrollbar + LightTheme Window).

## <a name="structural"></a> 2. Structural Patterns
**Goal:**: Compose classes or objects into larger structures.

🔹 Adapter
Use when: You want to use an existing class, but its interface does not match the one you need (e.g., connecting a new payment gateway to a legacy app).

```java
// Target interface
interface Bird { void fly(); }

// Adaptee (Incompatible)
class PlasticToyDuck { void squeak() { System.out.println("Squeak"); } }

// Adapter
class BirdAdapter implements Bird {
    PlasticToyDuck toyDuck;
    public BirdAdapter(PlasticToyDuck toyDuck) { this.toyDuck = toyDuck; }
    
    public void fly() { 
        // Translate the behavior
        toyDuck.squeak(); 
    }
}
```

🔹 Decorator
Use when: You want to add responsibilities to individual objects dynamically without affecting other objects (e.g., Java I/O Streams).

```java
// Base
Coffee c = new SimpleCoffee();
// Decorate
c = new MilkDecorator(c);
c = new SugarDecorator(c); 

System.out.println(c.getCost()); // Cost of Coffee + Milk + Sugar
```

🔹 Facade
Use when: You want to provide a simplified interface to a complex subsystem (e.g., a "Smart Home" class that turns on lights, heater, and music with one button).

```java
class VideoConverterFacade {
    public File convert(String filename, String format) {
        // Hides complex logic of CodecFactory, BitrateReader, AudioMixer, etc.
        return new File(filename + "." + format);
    }
}
```

🔹 Proxy
Use when: You need a placeholder for another object to control access to it (e.g., Lazy loading images, access control).

```java
interface Image { void display(); }

class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) { this.fileName = fileName; }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // Load only when needed
        }
        realImage.display();
    }
}
```
- Composite: Compose objects into tree structures (e.g., File system folders and files).

- Bridge: Decouple an abstraction from its implementation so the two can vary independently.

- Flyweight: Reduce storage costs for large numbers of fine-grained objects (e.g., rendering characters in a text editor).

## <a name="behavioral"></a> 3. Behavioral Patterns
Goal: Concern algorithms and the assignment of responsibilities between objects.

🔹 Observer
Use when: One object changes state, and all its dependents need to be notified automatically (e.g., YouTube channel subscribers).

```java
class NewsAgency {
    private List<Observer> channels = new ArrayList<>();
    
    public void addObserver(Observer o) { channels.add(o); }
    
    public void broadcast(String news) {
        for(Observer o : channels) o.update(news);
    }
}
```

🔹 Strategy
Use when: You have multiple algorithms for a specific task and want to switch between them at runtime (e.g., Sorting algorithms, Payment methods).

```java
class ShoppingCart {
    PaymentStrategy strategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void checkout(int amount) {
        strategy.pay(amount);
    }
}
// Usage: cart.setStrategy(new PaypalStrategy()); or cart.setStrategy(new CreditCardStrategy());
```

🔹 Command
Use when: You want to parameterize objects with operations, queue operations, or support undoable operations.

```java
// Turns a request "Light On" into a stand-alone object
class LightOnCommand implements Command {
    Light light;
    public void execute() { light.turnOn(); }
}
```

🔹 State
Use when: An object's behavior depends on its state, and it must change its behavior at runtime (e.g., A Document in Draft -> Moderation -> Published states).

🔹 Template Method
Use when: You want to define the skeleton of an algorithm in an operation, deferring some steps to subclasses.

```java
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // Template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}
```
- Chain of Responsibility: Pass requests along a chain of handlers (e.g., Tech support tiers).

- Iterator: Access elements of a collection without exposing its underlying representation.

- Mediator: Centralize complex communications and control between related objects (e.g., Air Traffic Control).

- Memento: Capture and restore an object's internal state (Undo/Redo).

- Visitor: specific operations from the objects on which they operate.