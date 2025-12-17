# Singleton Design Pattern in Java

The **Singleton Pattern** ensures that a class has only one instance and provides a global point of access to it. It is a creational pattern used commonly for shared resources like database connections or configuration managers.



---

## 1. Eager Initialization
The instance is created at the time of class loading. This is the simplest method but lacks lazy loading (the instance is created even if it is never used).

```java
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```
## 2. Lazy Initialization
The instance is created only when it is needed. This is efficient for memory but has a significant drawback: it is **not thread-safe**. If two threads enter the `if` condition simultaneously, two different instances will be created.

```java
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```
## 3. Thread-Safe Singleton
A simple way to make the singleton thread-safe is to synchronize the entire global access method. This ensures that only one thread can execute the instance creation logic at a time, though it may introduce performance overhead in high-concurrency applications.

```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```
## 4. Double-Checked Locking
This approach minimizes the overhead of synchronization by only locking the code block the first time the instance is created. It uses the `volatile` keyword to ensure thread visibility, preventing issues where a thread might see a partially initialized object.



```java
public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```
## 5. Bill Pugh Singleton (Static Inner Class)
This is the most widely recommended approach for implementing a Singleton in standard Java classes. It uses a static helper class (inner class) that is not loaded into memory until `getInstance()` is called. This leverages the Java ClassLoader's mechanism to ensure thread safety without the performance cost of synchronization.



```java
public class BillPughSingleton {
    private BillPughSingleton() {}

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```
## 6. Enum Singleton
As suggested by Joshua Bloch in *Effective Java*, this is the most robust implementation. It provides implicit thread safety and is the only method that automatically protects against serialization and reflection attacks, which are common ways to "break" a singleton in Java.



```java
public enum EnumSingleton {
    INSTANCE;

    public void someMethod() {
        // Implementation logic
    }
}
```
---

### 📊 Comparison Table

| Implementation | Lazy Loading | Thread Safety | Performance | Reflection Safe |
| :--- | :---: | :---: | :---: | :---: |
| **1. Eager Initialization** | ❌ No | ✅ Yes | 🚀 High | ❌ No |
| **2. Lazy Initialization** | ✅ Yes | ❌ No | 🚀 High | ❌ No |
| **3. Thread-Safe Singleton** | ✅ Yes | ✅ Yes | 📉 Low | ❌ No |
| **4. Double-Checked Locking** | ✅ Yes | ✅ Yes | ⚖️ Medium | ❌ No |
| **5. Bill Pugh (Inner Class)** | ✅ Yes | ✅ Yes | 🚀 High | ❌ No |
| **6. Enum Singleton** | ❌ No | ✅ Yes | 🚀 High | ✅ Yes |

---

### 🔑 Key Takeaways

* **Best for Performance:** The **Bill Pugh method** and **Double-Checked Locking** offer the best balance of lazy loading and high performance without the overhead of synchronized methods.
* **Best for Security:** The **Enum implementation** is the only one that natively prevents multiple instances via Reflection or Serialization attacks.
* **Simplest for Single-Threaded:** **Lazy Initialization** is straightforward but should be avoided in any multi-threaded environment (like Web or Android apps) as it can lead to multiple instances.
* **Legacy/Simple:** **Eager Initialization** is efficient if your singleton doesn't consume many resources and is guaranteed to be used during the application lifecycle.

---

### 🛠 Implementation Advice
If you are working on a modern Java application, the **Enum Singleton** is generally the most recommended due to its inherent safety. If you absolutely require lazy loading, the **Bill Pugh (Static Inner Class)** approach is the cleanest and most efficient choice.

> **Note:** When using Double-Checked Locking, always remember to use the `volatile` keyword for the instance variable to ensure visibility across threads.