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

<a name="structural"></a> 2. Structural Patterns
Goal: Compose classes or objects into larger structures.

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