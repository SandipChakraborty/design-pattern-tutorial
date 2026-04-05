
## 🧠 Idea

We separate:

* **Abstraction** → Pizza type (Veg, Cheese, etc.)
* **Implementor** → Cooking style (Oven, Wood-fired)

So instead of creating classes like:

* `VegPizzaOven`
* `VegPizzaWoodFire`
* `CheesePizzaOven`
* `CheesePizzaWoodFire`

We combine them dynamically using the Bridge pattern.

---

## ✅ Java Example: Pizza + Cooking Method

---

### 1. Implementor (Cooking Method)

```java
interface PizzaMaker {
    void preparePizza(String pizzaType);
}
```

---

### 2. Concrete Implementors

```java
class OvenPizzaMaker implements PizzaMaker {
    public void preparePizza(String pizzaType) {
        System.out.println("Preparing " + pizzaType + " in Electric Oven");
    }
}

class WoodFirePizzaMaker implements PizzaMaker {
    public void preparePizza(String pizzaType) {
        System.out.println("Preparing " + pizzaType + " in Wood Fire Oven");
    }
}
```

---

### 3. Abstraction (Pizza)

```java
abstract class Pizza {
    protected PizzaMaker pizzaMaker;

    protected Pizza(PizzaMaker pizzaMaker) {
        this.pizzaMaker = pizzaMaker;
    }

    abstract void make();
}
```

---

### 4. Refined Abstractions

```java
class VegPizza extends Pizza {

    public VegPizza(PizzaMaker pizzaMaker) {
        super(pizzaMaker);
    }

    public void make() {
        pizzaMaker.preparePizza("Veg Pizza");
    }
}

class CheesePizza extends Pizza {

    public CheesePizza(PizzaMaker pizzaMaker) {
        super(pizzaMaker);
    }

    public void make() {
        pizzaMaker.preparePizza("Cheese Pizza");
    }
}
```

---

### 5. Client Code

```java
public class BridgePatternPizzaDemo {
    public static void main(String[] args) {
        Pizza vegOvenPizza = new VegPizza(new OvenPizzaMaker());
        Pizza cheeseWoodPizza = new CheesePizza(new WoodFirePizzaMaker());

        vegOvenPizza.make();
        cheeseWoodPizza.make();
    }
}
```

---

## 🔍 Output

```
Preparing Veg Pizza in Electric Oven
Preparing Cheese Pizza in Wood Fire Oven
```

---

## 💡 Why this is powerful

You can now mix and match without creating new classes:

| Pizza Type | Cooking Method | Works? |
| ---------- | -------------- | ------ |
| Veg        | Oven           | ✅      |
| Veg        | Wood Fire      | ✅      |
| Cheese     | Oven           | ✅      |
| Cheese     | Wood Fire      | ✅      |

No class explosion 🔥

---

## 🧩 Key Takeaway

* **Pizza (abstraction)** → what you order
* **PizzaMaker (implementation)** → how it's cooked
* Bridge connects them so both evolve independently
