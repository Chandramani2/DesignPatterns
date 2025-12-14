# The Strategy Design Pattern in Java

## 1. Overview
The **Strategy Pattern** is a Behavioral Design Pattern that enables selecting an algorithm's behavior at runtime. Instead of hardcoding behaviors into a class (which leads to rigid inheritance hierarchies), the pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.

* **Core Principle:** Composition over Inheritance.
* **Goal:** To separate the behavior (algorithm) from the host class (context) so that both can evolve independently.

---

## 2. Components of the Strategy Pattern
There are three main components in this pattern:

1.  **Strategy Interface:** Defines a common interface for all supported algorithms.
2.  **Concrete Strategies:** Classes that implement the Strategy Interface with specific behaviors.
3.  **Context:** A class that uses the Strategy Interface to call the algorithm defined by a Concrete Strategy.

---

## 3. Implementation (Based on Your Code)

### Step 1: The Strategy Interface
This interface guarantees that all driving behaviors have a common method `drive()`.
* **File:** `src/StrategyPattern/Strategy/DriveStrategy.java`
    ```java
    package StrategyPattern.Strategy;
    public interface DriveStrategy {
        public void drive();
    }
    ```


### Step 2: Concrete Strategies
These classes implement the specific driving logic. You have three different algorithms:

* **Normal Strategy:**
    ```java
    public class NormalStrategy implements DriveStrategy{
        public void drive(){
            System.out.println("Normal Drive Capability");
        }
    }
    ```


* **Special Strategy (Sports):**
    ```java
    public class SpecialStrategy implements DriveStrategy{
        public void drive(){
            System.out.println("Sports Drive Capability");
        }
    }
    ```


* **Bike Strategy:**
    ```java
    public class BikeStrategy implements DriveStrategy{
        public void drive(){
            System.out.println("Bike Drive Capability");
        }
    }
    ```


### Step 3: The Context (Vehicle)
The `Vehicle` class holds a reference to the `DriveStrategy`. It does not know *how* to drive; it simply delegates the task to the strategy object it was given.

* **Key Feature:** Constructor Injection allows the strategy to be set when the object is created.
    ```java
    package StrategyPattern;
    import StrategyPattern.Strategy.DriveStrategy;

    public class Vehicle {
        DriveStrategy driveObj;

        // Constructor Injection
        Vehicle(DriveStrategy driveObj){
            this.driveObj = driveObj;
        }

        public void drive(){
            driveObj.drive(); // Delegation
        }
    }
    ```


### Step 4: Concrete Contexts
These are specific types of vehicles that initialize the Context with a specific strategy.

* **SportsVehicle:** Automatically uses `SpecialStrategy`.
    ```java
    public class SportsVehicle extends Vehicle{
        public SportsVehicle() { super (new SpecialStrategy());}
    }
    ```


* **NormalVehicle:** Automatically uses `NormalStrategy`.
    ```java
    public class NormalVehicle extends Vehicle{
        public NormalVehicle(){super (new NormalStrategy());}
    }
    ```


---

## 4. Usage Example

The client code (`Main.java`) demonstrates how the behavior is determined by the object type created, without the client needing to know the internal logic.

```java
import StrategyPattern.NormalVehicle;
import StrategyPattern.SportsVehicle;
import StrategyPattern.Vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new NormalVehicle();
        vehicle.drive(); // Outputs: Normal Drive Capability
    }
}
```
# Strategy Pattern: Senior Developer Interview Q&A

### Q1: The Strategy Pattern relies on Composition. Why is Composition preferred over Inheritance in this scenario?
**Answer:**
Inheritance establishes an **"IS-A"** relationship which is static and defined at compile-time. Composition establishes a **"HAS-A"** relationship which is dynamic.

* **Inheritance Rigidness:** In the provided `WithoutStrategy` example, if we used inheritance, a `SportsVehicle` would strictly be locked into the logic defined in its class. To change behavior, you'd need to change the class or create a new subclass.
* **Composition Flexibility:** By using composition (the `Vehicle` class holding a `DriveStrategy` interface), we can easily swap the `driveObj` at runtime. For example, a car object could switch from "Sport Mode" to "Eco Mode" simply by calling a setter method like `setDriveStrategy(new NormalStrategy())`, without needing to destroy and recreate the `Vehicle` object.

### Q2: How does the Strategy Pattern help adhere to the Open/Closed Principle (OCP)?
**Answer:**
The **Open/Closed Principle** states that software entities should be *open for extension* but *closed for modification*.

* **Closed:** The Context class (`Vehicle`) is closed for modification. We don't need to touch the `Vehicle.java` code to add new driving capabilities.
* **Open:** The system is open for extension. If we need to add a "4x4OffRoad" driving capability:
    1.  We simply create a new class `OffRoadStrategy` implementing `DriveStrategy`.
    2.  We **do not** modify the existing `Vehicle` class or existing strategies (`NormalStrategy`, `BikeStrategy`).

### Q3: In modern Java (Java 8+), how can we optimize the Strategy Pattern to reduce boilerplate code?
**Answer:**
Since `DriveStrategy` is a **Functional Interface** (it defines exactly one abstract method `void drive()`), we can leverage **Lambda Expressions** to avoid creating separate class files for simple strategies.

**Example:**
```java
// Inline strategy definition without creating a new class file
Vehicle flyingCar = new Vehicle(() -> System.out.println("Flying Capability"));
flyingCar.drive();
```
This significantly reduces the number of physical files (like `FlyingStrategy.java`) when the strategy logic is concise.

### Q4: What is a potential downside of the Strategy Pattern regarding the "Context" (Client) awareness?
**Answer:**
The Client (or the Context) must be aware of the differences between the strategies to select the right one.
* In your `SportsVehicle` constructor, the class explicitly knows it needs `new SpecialStrategy()`.
* This couples the concrete vehicle to a specific strategy implementation initially.
* **Solution:** In enterprise applications, this is often resolved using **Dependency Injection (DI)** frameworks like Spring, where the specific strategy is injected via configuration (XML or Annotations) rather than hardcoded `new` keywords.

### Q5: How does the Strategy Pattern differ from the State Pattern? The structure looks identical.
**Answer:**
Structurally, they are indeed very similar (both have a Context, an Interface, and Concrete implementations). The difference lies in **Intent**:
* **Strategy Pattern:** The **Client** usually chooses the algorithm. In `Main.java`, the main method (the client) decides to create a `NormalVehicle`, essentially choosing the strategy. The strategy is often fixed for the task duration.
* **State Pattern:** The **State itself** or the Context triggers transitions to a new state based on internal conditions. The client typically doesn't manually "set" the state; the lifecycle and internal logic of the object dictate the transitions (e.g., `OrderPlaced` -> `OrderShipped`).

### Q6: If the Strategy methods need data from the Context (Vehicle) to execute, how should that be handled?
**Answer:**
There are two common approaches to passing data:
1.  **Pass Data as Method Arguments:** Change the interface to `void drive(VehicleContext ctx)`. The strategy can then call getters on the context to access necessary data (e.g., current speed, fuel level). This is cleaner but introduces a dependency from Strategy to Context.
2.  **Pass Data to Strategy Constructor:** When initializing the strategy, pass the required data: `new SpecialStrategy(MAX_SPEED)`. This keeps the Strategy independent of the Context class but might require the Strategy instance to hold state, which can be a thread-safety concern if shared.