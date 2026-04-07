The **Flyweight Design Pattern** is a structural pattern used to **minimize memory usage** by sharing common objects
instead of creating new ones repeatedly.

---

# 🎯 Key Idea

Instead of creating a large number of similar objects, you **reuse existing ones**.

👉 It separates:

* **Intrinsic state** (shared, reusable)
* **Extrinsic state** (unique, passed from outside)

---

# 🎮 Real-Life Example

Think of a **game with thousands of trees 🌳**:

* Tree type (color, texture) → shared
* Position (x, y) → unique

👉 You don’t create 10,000 tree objects — you reuse a few and change position.

---

# 🏗️ Structure

1. **Flyweight (interface)**
2. **Concrete Flyweight (shared object)**
3. **Flyweight Factory (manages caching)**
4. **Client (uses flyweights)**

---

# 💻 Java Example (Text Editor – Letters)

---

### 1. Flyweight Interface

```java id="2b7r3x"
interface Letter {
    void display(int x, int y);
}
```

---

### 2. Concrete Flyweight

```java id="8yzv6m"
class ConcreteLetter implements Letter {

    private char symbol; // intrinsic (shared)

    public ConcreteCLetter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Letter: " + symbol + " at (" + x + "," + y + ")");
    }
}
```

---

### 3. Flyweight Factory

```java id="d6a1lz"
import java.util.HashMap;
import java.util.Map;

class LetterFactory {

    private static Map<Character, Letter> lettererMap = new HashMap<>();

    public static Letter getLetter(char c) {

        if (!lettererMap.containsKey(c)) {
            lettererMap.put(c, new ConcreteLetter(c));
            System.out.println("Creating letter: " + c);
        }

        return letterMap.get(c);
    }
}
```

---

### 4. Main Class (Client)

```java id="8w6v3t"
public class Main {
    static void main(String[] args) {
        String text = "AABBA";

        int x = 0;

        for (char c : text.toCharArray()) {
            Letter letter = LetterFactory.getLetter(c);
            letter.display(x++, 10); // extrinsic state
        }
    }
}
```

---

# 🔍 How It Works

### Intrinsic State (Shared)

```java
char symbol;
```

👉 Stored inside object → reused

---

### Extrinsic State (External)

```java
display(int x, int y);
```

👉 Passed during method call → not stored

---

### Factory Behavior

```java
if(!letterMap.containsKey(c))
```

👉 Only creates object once
👉 Reuses it afterward

---

### Output Flow

```text
Creating letter: A
Creating letter: B
Letter: A at (0,10)
Letter: A at (1,10)
Letter: B at (2,10)
...
```

👉 Only 2 objects created instead of 5!

---

# 🎯 Benefits

* ✅ Reduces memory usage drastically
* ✅ Improves performance when handling many objects
* ✅ Object reuse via caching

---

# ⚠️ Drawbacks

* ❌ Increased complexity
* ❌ Requires careful separation of states
* ❌ Not useful for small-scale systems

---

# 📌 When to Use

Use Flyweight Pattern when:

* You have **large number of similar objects**
* Object creation is **expensive**
* You can separate **intrinsic vs extrinsic state**

---

# 🆚 Facade vs Flyweight (Quick Difference)

| Feature | Facade          | Flyweight                |
|---------|-----------------|--------------------------|
| Purpose | Simplify system | Save memory              |
| Focus   | Interface       | Object sharing           |
| Usage   | API design      | Performance optimization |

---

# 🚀 Real-World Examples

* Java String Pool (`String` reuse)
* Integer caching (`Integer.valueOf()`)
* Game development (trees, bullets, particles)
* Text editors (character rendering)

---
