# Pizza Ordering System (Factory + Decorator Pattern)

This project demonstrates the use of two structural and creational design patterns to build a flexible pizza ordering system. It allows for the creation of base pizzas and the dynamic addition of toppings without modifying existing code.

## 🛠 Design Patterns Used

### 1. Decorator Pattern (Structural)
* **Why:** To solve the "Class Explosion" problem. Instead of creating classes like `MargheritaWithCheese`, `MargheritaWithCheeseAndMushroom`, etc., we "decorate" a Pizza object with Topping objects at runtime.
* **Implementation:** `ToppingDecorator` extends `Pizza` and also contains a reference to a `Pizza` object.

### 2. Factory Pattern (Creational)
* **Why:** To encapsulate the object creation logic. The client code doesn't need to know exactly how a `Margherita` or `Farmhouse` is instantiated, it just asks the factory for one.
* **Implementation:** `PizzaFactory` class takes a string input and returns a concrete `Pizza` object.

## 📂 Project Structure

| File | Description |
| :--- | :--- |
| `Pizza.java` | Abstract base class defining the contract (description and cost). |
| `Margherita.java` | Concrete implementation of a base pizza. |
| `Farmhouse.java` | Concrete implementation of a base pizza. |
| `ToppingDecorator.java` | Abstract class that lets toppings act as Pizzas. |
| `ExtraCheese.java` | Concrete decorator that wraps a pizza and adds cheese cost. |
| `Mushroom.java` | Concrete decorator that wraps a pizza and adds mushroom cost. |
| `PizzaFactory.java` | Centralized logic to generate the Base Pizza. |
| `PizzaShop.java` | Main class containing the `main` method to run the program. |

## Folder Structure
```
PizzaProject/
├── README.md
└── src/
    └── com/
        └── pizzashop/
            ├── Main.java
            ├── base/
            │   └── Pizza.java
            ├── products/
            │   ├── Margherita.java
            │   └── Farmhouse.java
            ├── decorators/
            │   ├── ToppingDecorator.java
            │   ├── ExtraCheese.java
            │   └── Mushroom.java
            └── factory/
                └── PizzaFactory.java
```
## 🚀 How to Run

### Prerequisites
* Java Development Kit (JDK) installed (Version 8 or higher).

### Steps
1.  **Compile the source code:**
    Open your terminal in the project folder and run:
    ```bash
    javac *.java
    ```

2.  **Run the application:**
    ```bash
    java PizzaShop
    ```

## 📋 Expected Output

```text
Base Order: Margherita Pizza | Cost: $200.0
Final Order: Margherita Pizza, Extra Cheese, Mushroom | Total Cost: $290.0
```
---

## 🧠 Design Decisions (Q & A)

We prioritized the **DRY (Don't Repeat Yourself)** principle and logical inheritance structures. Below are the detailed explanations for the "Why" behind the code.

### ❓ Q1: Why did we use an Abstract Class for `Pizza` instead of an Interface?

**The Verdict:** We chose an **Abstract Class** primarily to manage the object's state efficiently. Since all Pizzas share a common state (the description), using an abstract class prevents us from duplicating the variable declaration and getter method in every single Pizza subclass (Margherita, Farmhouse, etc.).

| Feature | 🏛️ Abstract Class (`Pizza`) | 🔌 Interface (`Pizza`) |
| :--- | :--- | :--- |
| **Shared State** | ✅ **Can hold state.** (e.g., `String description`) | ❌ **Cannot hold instance variables.** (Only constants). |
| **Code Reusability** | ✅ **High.** `getDescription()` is written once in the parent. | ❌ **Low.** Every subclass must rewrite the getter method. |
| **Relationship** | Defines what the object **IS** (Identity). | Defines what the object **CAN DO** (Contract). |

---

### ❓ Q2: Why is the `getCost()` method NOT implemented in the `ToppingDecorator`?

Even though `ToppingDecorator` extends `Pizza`, we deliberately left `getCost()` abstract.

* **Logical Reason:** `ToppingDecorator` represents the *concept* of a topping, not a specific ingredient. It does not know if it is Cheese ($50) or Mushroom ($40), so it cannot provide a concrete price.
* **"Passing the Buck":** By remaining abstract, the class forces its children (the concrete decorators like `ExtraCheese`) to implement the actual pricing logic.
* **The Chain of Command:** The abstract decorator acts as a bridge (type matching) allowing toppings to wrap other toppings seamlessly.

> **Visual Flow:**
> `ExtraCheese` (Implements Cost) ➔ calls ➔ `Mushroom` (Implements Cost) ➔ calls ➔ `Margherita` (Base Cost)

---

### ❓ Q3: How is the Cost Calculated?

The cost is calculated using **recursion**. Each layer of the decorator adds its own cost to the result of the layer immediately below it.

The formula for the total cost is:

$$TotalCost = BasePrice + \sum_{i=1}^{n} (DecoratorPrice_i)$$

#### 🔍 Example Trace

If we order a **Margherita** with **Mushroom** and **Extra Cheese**:

1.  **Base:** Factory creates `Margherita` ($200).
2.  **Wrap 1:** `new Mushroom(margherita)` ➔ Cost is **$40** + $200.
3.  **Wrap 2:** `new ExtraCheese(mushroom)` ➔ Cost is **$50** + ($240).

💰 **Final Result:** **$290**

---

### 🔧 Tech Stack

* **Language:** Java
* **Paradigm:** Object-Oriented Programming (OOP)
* **Design Pattern:** Decorator Pattern