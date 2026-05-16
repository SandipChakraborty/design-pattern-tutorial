The **Composite Design Pattern** is a structural pattern that lets you treat **individual objects and groups of objects uniformly**. It’s especially useful when working with **tree-like structures** (e.g., file systems, UI components).

---

# 🌳 Key Idea

Instead of treating single objects and collections differently, you define a **common interface** for both.

---

# 🧩 Real-Life Example

Think of a **folder system**:

* A **file** → leaf node
* A **folder** → can contain files or other folders

You can perform operations (like display or delete) on both in the same way.

---

# 🏗️ Structure

1. **Component (Interface / Abstract class)**
2. **Leaf (Individual object)**
3. **Composite (Group of objects)**

---

# 💻 Java Example

### 1. Component Interface

```java
interface Employee {
    void showEmployeeDetails();
}
```

---

### 2. Leaf Class

```java
class Developer implements Employee {
    private String name;
    private String position;

    public Developer(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(name + " works as " + position);
    }
}
```

---

### 3. Another Leaf Class

```java
class Manager implements Employee {
    private String name;
    private String position;

    public Manager(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(name + " works as " + position);
    }
}
```

---

### 4. Composite Class

```java
import java.util.ArrayList;
import java.util.List;

class CompanyDirectory implements Employee {
    private List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employeeList.add(emp);
    }

    public void removeEmployee(Employee emp) {
        employeeList.remove(emp);
    }

    @Override
    public void showEmployeeDetails() {
        for (Employee emp : employeeList) {
            emp.showEmployeeDetails();
        }
    }
}
```

---

### 5. Main Class (Usage)

```java
public class CompositePatternDemo {
    public static void main(String[] args) {

        Employee dev1 = new Developer("Alice", "Backend Developer");
        Employee dev2 = new Developer("Bob", "Frontend Developer");

        Employee manager1 = new Manager("Charlie", "Team Lead");

        CompanyDirectory engineeringTeam = new CompanyDirectory();
        engineeringTeam.addEmployee(dev1);
        engineeringTeam.addEmployee(dev2);

        CompanyDirectory company = new CompanyDirectory();
        company.addEmployee(manager1);
        company.addEmployee(engineeringTeam);

        company.showEmployeeDetails();
    }
}
```

---

# 🔍 How It Works

* `Employee` → common interface
* `Developer`, `Manager` → **Leaf nodes**
* `CompanyDirectory` → **Composite node**

👉 The composite (`CompanyDirectory`) can hold:

* individual employees (leaf)
* other directories (composite)

👉 When you call:

```java
company.showEmployeeDetails();
```

It recursively calls the same method on all children.

---

# 🎯 Benefits

* ✅ Treat individual and group objects the same way
* ✅ Makes tree structures easy to manage
* ✅ Supports recursive composition
* ✅ Simplifies client code

---

# ⚠️ Drawbacks

* ❌ Can make design overly general
* ❌ Harder to restrict certain operations on leaf nodes

---

# 📌 When to Use

Use Composite Pattern when:

* You have a **hierarchical structure (tree)**
* You want **uniform operations** on objects and groups
* Example use cases:

    * File systems
    * UI component trees
    * Organization structures

---
