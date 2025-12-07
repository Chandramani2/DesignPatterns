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