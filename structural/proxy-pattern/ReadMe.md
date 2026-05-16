The **Proxy Design Pattern** is a structural pattern that provides a **substitute (proxy) object to control access to another object**.

---

# 🎯 Key Idea

A proxy acts as a **middle layer** between the client and the real object.

👉 It can:

* Delay object creation (lazy loading)
* Add security checks
* Log access
* Control or restrict usage

---

# 🧠 Real-Life Example

Think of a **credit card 💳**:

* You don’t pay cash directly
* The credit card acts as a **proxy** to your bank account

---

# 🏗️ Structure

1. **Subject (interface)**
2. **Real Subject (actual object)**
3. **Proxy (controls access)**
4. **Client**

---

# 💻 Java Example (Image Loading – Virtual Proxy)

---

### 1. Subject Interface

```java id="6h1m4c"
interface Image {
    void display();
}
```

---

### 2. Real Subject (Heavy Object)

```java id="1s9z8k"
class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
```

---

### 3. Proxy Class

```java id="n2k7qp"
class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // Lazy initialization
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
```

---

### 4. Main Class (Client)

```java id="0i2t9x"
public class ProxyPatternDemo {
    public static void main(String[] args) {

        Image image = new ProxyImage("photo.jpg");

        // Image will be loaded only when needed
        image.display(); // loads + displays
        image.display(); // only displays (no loading again)
    }
}
```

---

# 🔍 How It Works

### Without Proxy ❌

```java id="l67z3r"
Image image = new RealImage("photo.jpg");
```

👉 Loads image immediately (even if not used)

---

### With Proxy ✅

```java id="b4v2qn"
Image image = new ProxyImage("photo.jpg");
```

👉 Real object is created **only when needed**

---

### Execution Flow

1. Client calls `display()`
2. Proxy checks:

```java id="z6w8qa"
if (realImage == null)
```

3. Creates `RealImage` only once
4. Delegates call to real object

---

# 🎯 Benefits

* ✅ Lazy loading (performance improvement)
* ✅ Access control (security proxy)
* ✅ Logging and monitoring
* ✅ Reduces unnecessary object creation

---

# ⚠️ Drawbacks

* ❌ Adds extra layer → slight complexity
* ❌ Can increase response time (indirection)

---

# 📌 Types of Proxy

* **Virtual Proxy** → Lazy initialization (used above)
* **Protection Proxy** → Access control
* **Remote Proxy** → Represents remote object
* **Smart Proxy** → Adds extra behavior (logging, caching)

---

# 🆚 Flyweight vs Proxy (Quick Difference)

| Feature  | Flyweight           | Proxy          |
| -------- | ------------------- | -------------- |
| Purpose  | Share objects       | Control access |
| Focus    | Memory optimization | Access control |
| Behavior | Reuse instances     | Wrap object    |

---

# 🚀 Real-World Examples

* Image lazy loading in web apps
* ORM frameworks (Hibernate proxies)
* Spring AOP proxies
* Java RMI (Remote Method Invocation)

---
