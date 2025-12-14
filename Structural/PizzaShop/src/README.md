# Decorator & Factory Patterns in Java (Pizza Shop)

## 1. Overview
This project demonstrates two powerful Structural and Creational design patterns working together:

1.  **Decorator Pattern:** Allows behavior (toppings) to be added to an individual object (pizza) dynamically at runtime without affecting other objects. It solves the problem of "Class Explosion" where you would otherwise need `MargheritaWithCheese`, `MargheritaWithMushroom`, etc.
2.  **Factory Pattern:** Encapsulates the object creation logic. The client doesn't need to know the specific class names (`Margherita`, `Farmhouse`) to instantiate them; it just asks the factory.

---

## 2. Components of the Patterns

### A. Decorator Pattern Components
* **Component:** The abstract base class (`Pizza`).
* **Concrete Component:** The specific base objects (`Margherita`, `Farmhouse`).
* **Decorator:** The abstract class that extends the Component (`ToppingDecorator`).
* **Concrete Decorators:** The specific add-ons (`ExtraCheese`, `Mushroom`).

### B. Factory Pattern Components
* **Factory Class:** Responsible for instantiating objects based on input (`PizzaFactory`).

---

## 3. Implementation Details

### Step 1: The Base Component (The Contract)
This abstract class defines the blueprint for all pizzas. Both the base pizzas and the toppings "are" Pizzas.
* **File:** `src/com/pizzashop/base/Pizza.java`
    ```java
    public abstract class Pizza {
        protected String description = "Unknown Pizza";
        public abstract double getCost();
    }
    ```


### Step 2: Concrete Components (The Base Objects)
These are the starting points of our objects.
* **Margherita:** Costs 200.00.
* **Farmhouse:** Costs 300.00.

### Step 3: The Decorator Abstract Class
It extends `Pizza` to ensure type compatibility (so a "Pizza with Cheese" can be passed to methods expecting a "Pizza").
* **File:** `src/com/pizzashop/decorators/ToppingDecorator.java`
    ```java
    public abstract class ToppingDecorator extends Pizza {
        public abstract String getDescription();
    }
    ```


### Step 4: Concrete Decorators (The Wrappers)
These classes wrap a `Pizza` object. They add their own cost to the wrapped pizza's cost.
* **Logic:** `Total Cost = My Cost + Wrapped Pizza Cost`
* **ExtraCheese:** Adds 50.00.
    ```java
    public class ExtraCheese extends ToppingDecorator {
        Pizza pizza;
        // Constructor wraps an existing pizza
        public ExtraCheese(Pizza pizza) { this.pizza = pizza; }
        
        @Override
        public double getCost() { return 50.00 + pizza.getCost(); }
    }
    ```

* **Mushroom:** Adds 40.00.

### Step 5: The Simple Factory
Centralizes the `new` keyword usage.
* **File:** `src/com/pizzashop/factory/PizzaFactory.java`
    ```java
    public class PizzaFactory {
        public Pizza createPizza(String type) {
            if (type.equalsIgnoreCase("Margherita")) return new Margherita();
            else if (type.equalsIgnoreCase("Farmhouse")) return new Farmhouse();
            // ... error handling ...
        }
    }
    ```


---

## 4. Usage Example (`Main.java`)

The client code combines both patterns. It uses the Factory to get the base, and then "wraps" it with Decorators.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Factory creates the Base (Margherita: 200)
        PizzaFactory factory = new PizzaFactory();
        Pizza myOrder = factory.createPizza("Margherita");

        // 2. Decorators wrap the object dynamically
        // Cost: 200 + 50 (Cheese) = 250
        myOrder = new ExtraCheese(myOrder);
        
        // Cost: 250 + 40 (Mushroom) = 290
        myOrder = new Mushroom(myOrder);

        System.out.println("Final: " + myOrder.getDescription() + " | $" + myOrder.getCost());
    }
}
```
# Decorator & Factory Patterns: Senior Developer Interview Q&A

### Q1: Why use the Decorator Pattern instead of simple Inheritance (e.g., creating `MargheritaWithCheese` class)?
**Answer:**
Using inheritance for every combination leads to a **Class Explosion**.
* **The Problem:** If you have 10 types of Pizza and 10 types of Toppings, trying to create a subclass for every possible combination (e.g., `FarmhouseCheese`, `FarmhouseCheeseMushroom`, `MargheritaCheese`...) would result in an unmanageable number of classes.
* **The Solution:** The Decorator Pattern allows you to mix and match. You only need 10 Pizza classes + 10 Topping classes. You combine them dynamically at runtime (e.g., `new Mushroom(new ExtraCheese(new Margherita()))`). It adheres to the **Open/Closed Principle**—you can add a new topping (`Jalapeno`) without touching any existing Pizza code.

### Q2: How does the recursive cost calculation work in `myOrder.getCost()`?
**Answer:**
It uses **Delegation** via the "wrapping" concept. When you call `getCost()` on the outermost wrapper:
1.  `Mushroom.getCost()` returns `40 + pizza.getCost()` (where `pizza` is the inner `ExtraCheese` object).
2.  `ExtraCheese.getCost()` returns `50 + pizza.getCost()` (where `pizza` is the inner `Margherita` object).
3.  `Margherita.getCost()` returns `200` (Base case).
4.  The stack unwinds, summing the values: `200 + 50 + 40 = 290`.

### Q3: What is a major downside of the Decorator Pattern regarding Object Identity?
**Answer:**
Once you wrap an object, the reference points to the **Decorator**, not the original object.
* **The Risk:** Code that relies on specific type checks (e.g., `if (myOrder instanceof Farmhouse)`) will fail if the object is wrapped in `ExtraCheese` (because `ExtraCheese` is a `ToppingDecorator`, not a `Farmhouse`).
* **Best Practice:** Avoid relying on concrete implementation types when using Decorators; always program to the abstract interface (`Pizza`).

### Q4: In `ToppingDecorator.java`, why do we extend `Pizza`? Isn't holding a reference to `Pizza` (Composition) enough?
**Answer:**
We extend `Pizza` to achieve **Type Conformance** (Subtyping), not just for behavior.
* **Composition:** We hold a `Pizza pizza` field to delegate the behavior (calling `getCost()` on the wrapped object).
* **Inheritance:** We extend `Pizza` so that the Decorator *is-a* Pizza. This is crucial because it allows us to pass a decorated object (e.g., `ExtraCheese`) into the constructor of another decorator (e.g., `new Mushroom(pizza)`), enabling the recursive nesting of wrappers.

### Q5: Your `PizzaFactory` uses string comparisons (`if type.equals...`). How can we make this more robust and OCP compliant?
**Answer:**
The current `if-else` block in the factory violates the Open/Closed Principle because adding a new pizza requires modifying the factory code.
**Better Approach:**
Use a **Registration Map** (often with Reflection or Supplier references).
```java
private Map<String, Supplier<Pizza>> registry = new HashMap<>();

// Register classes (can be done in a static block or via external config)
registry.put("Margherita", Margherita::new);
registry.put("Farmhouse", Farmhouse::new);

public Pizza createPizza(String type) {
    Supplier<Pizza> supplier = registry.get(type);
    if (supplier != null) return supplier.get();
    throw new IllegalArgumentException("Unknown type");
}
```

### Q6: What happens if the order of decorators matters? (e.g., A "Half-Price" discount vs. "Extra Cheese")
**Answer:**
The standard Decorator pattern is **order-dependent**, which can be both a feature and a risk.
* **In your code:** Since `ExtraCheese` and `Mushroom` only perform addition (`+ 50`, `+ 40`), the order `A(B(Pizza))` vs `B(A(Pizza))` produces the same result.
* **The Risk:** If you introduce a multiplication decorator (e.g., `HalfPriceDecorator`), the order becomes critical.
    * `HalfPrice(Cheese(Pizza))` = `(200 + 50) * 0.5 = 125`.
    * `Cheese(HalfPrice(Pizza))` = `(200 * 0.5) + 50 = 150`.
* **Architectural Fix:** If strict ordering is required by business rules (e.g., "Tax must always be applied last"), you should not rely on the client (`Main.java`) to chain them correctly. Instead, use a **Builder** or a **Factory** that enforces the correct wrapping order.

### Q7: Can you explain a real-world example of the Decorator pattern in the Java Standard Library?
**Answer:**
The **Java I/O** classes are the classic example of the Decorator pattern.
* **Component:** `InputStream` (Abstract base).
* **Concrete Component:** `FileInputStream` (Reads bytes from a file).
* **Decorator:** `BufferedInputStream` (Adds buffering behavior).
* **Usage:**
  ```java
  // Just like new Mushroom(new Margherita())
  InputStream in = new BufferedInputStream(new FileInputStream("data.txt"));
    ```
  This mirrors your Pizza Shop implementation where `Mushroom` wraps `Margherita` to add functionality without modifying the underlying class.


### Q8: Your `PizzaFactory` is a "Simple Factory". How does this differ from the "Factory Method" and "Abstract Factory" patterns?
**Answer:**
* **Simple Factory (Your Implementation):** It is a helper class that encapsulates the object creation logic (`if-else` block). It is an idiom, not a formal "Gang of Four" (GoF) pattern. Its main drawback is that it violates the Open/Closed Principle (you must modify the class to add new pizzas).
* **Factory Method:** Defines an interface for creating an object but lets subclasses decide which class to instantiate. You would have an abstract `PizzaStore` with `abstract Pizza createPizza()`, and concrete subclasses like `NYPizzaStore` would implement it.
* **Abstract Factory:** Provides an interface for creating *families* of related or dependent objects without specifying their concrete classes. In a pizza context, this would be an `IngredientFactory` that creates `Dough`, `Sauce`, and `Cheese` that typically go together (e.g., "NY Style" ingredients vs. "Chicago Style" ingredients).

### Q9: One common criticism of the Decorator Pattern is "Object Explosion". How does this impact memory and debugging in a production system?
**Answer:**
* **Memory Overhead:** Instead of a single large object (e.g., a `Pizza` with boolean flags for toppings), you create a chain of small objects (`Pizza` -> `Cheese` -> `Mushroom`). Each object wrapper has overhead (object header, reference pointer). In high-throughput systems, this can increase garbage collection pressure.
* **Debugging Complexity:** Stack traces become difficult to read because the logic is spread across many layers of wrappers. If an exception occurs in `getCost()`, you might see 10 calls to `ToppingDecorator.getCost()` before reaching the actual implementation. Inspecting the state of a "Pizza" in a debugger is annoying because you have to drill down through layers of `pizza.pizza.pizza...` to find the base object.

### Q10: Is your `PizzaFactory` thread-safe? What if we made it a Singleton?
**Answer:**
* **Current State:** Yes, the current `PizzaFactory` is thread-safe because it is **stateless**. The `createPizza` method relies only on local variables and arguments.
* **Singleton Context:** If we made it a Singleton (common for Factories), it would remain thread-safe *as long as it stays stateless*. However, if we introduced a caching mechanism (e.g., a `Map<String, Pizza>` to cache prototypes) without proper synchronization (`ConcurrentHashMap`), it would become thread-unsafe.

### Q11: In `ToppingDecorator`, you used Constructor Injection for the `Pizza` object. Why is this preferred over Setter Injection for Decorators?
**Answer:**
Constructor Injection is strictly preferred for Decorators to ensure **Immutability** and **Validity**.
* **Mandatory Dependency:** A Decorator *cannot* exist without wrapping something. Passing the component in the constructor ensures the Decorator is in a valid state the moment it is created.
* **Immutability:** It allows the `pizza` reference to be marked `final` (though it isn't in your specific code, it is a best practice). Setter injection would allow the wrapped object to be swapped at runtime, which makes the behavior unpredictable and the object mutable, causing potential concurrency issues.