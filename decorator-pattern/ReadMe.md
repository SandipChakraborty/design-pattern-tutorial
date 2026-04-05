The **Decorator Design Pattern** is another structural pattern that lets you **add new behavior to objects dynamically without modifying their code**.

---

# 🎁 Key Idea

Instead of subclassing, you **wrap an object inside another object** that adds extra functionality.

👉 Think of it like adding toppings to a pizza — base stays same, features increase.

---

# 🍕 Real-Life Example

* Base: Plain Coffee ☕
* Add-ons (Decorators): Milk 🥛, Sugar 🍬, Whipped Cream 🍦

Each decorator **adds behavior** (cost, description) without changing the base class.

---

# 🏗️ Structure

1. **Component (interface)**
2. **Concrete Component (base object)**
3. **Decorator (abstract wrapper)**
4. **Concrete Decorators (add features)**

---

# 💻 Java Example (Coffee System)

---

### 1. Component Interface

```java
interface Coffee {
    String getDescription();
    double getCost();
}
```

---

### 2. Concrete Component

```java
class PlainCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 50.0;
    }
}
```

---

### 3. Abstract Decorator

```java
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription();
    }

    public double getCost() {
        return coffee.getCost();
    }
}
```

---

### 4. Concrete Decorators

#### Milk Decorator

```java
class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }
}
```

---

#### Sugar Decorator

```java
class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5.0;
    }
}
```

---

### 5. Main Class (Usage)

```java
public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Coffee coffee = new PlainCoffee();

        // Add Milk
        coffee = new MilkDecorator(coffee);

        // Add Sugar
        coffee = new SugarDecorator(coffee);

        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: ₹" + coffee.getCost());
    }
}
```

---

# 🔍 How It Works

* `PlainCoffee` → base object
* `MilkDecorator`, `SugarDecorator` → wrappers

👉 When you do:

```java
coffee = new MilkDecorator(coffee);
```

You are wrapping the original object.

👉 Then:

```java
coffee = new SugarDecorator(coffee);
```

You wrap it again.

So the call chain becomes:

```
Sugar → Milk → PlainCoffee
```

---

# 🎯 Benefits

* ✅ Add behavior dynamically (runtime flexibility)
* ✅ Avoid class explosion (no need for many subclasses)
* ✅ Follows Open/Closed Principle

---

# ⚠️ Drawbacks

* ❌ Can create many small classes
* ❌ Debugging can be harder (multiple layers)

---

# 📌 When to Use

Use Decorator Pattern when:

* You want to **add responsibilities dynamically**
* You want to avoid subclassing
* You need flexible combinations of features

---

# 🆚 Composite vs Decorator (Quick Difference)

| Feature | Composite            | Decorator             |
| ------- | -------------------- | --------------------- |
| Purpose | Tree structure       | Add behavior          |
| Focus   | Part-whole hierarchy | Dynamic functionality |
| Example | File system          | Coffee toppings       |

---
