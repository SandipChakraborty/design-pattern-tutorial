The **Facade Design Pattern** is a structural pattern that provides a **simplified interface to a complex system**.

---

# 🎯 Key Idea

Instead of interacting with multiple complex classes directly, you use a **single unified interface (Facade)**.

👉 It hides complexity and makes the system easier to use.

---

# 🏦 Real-Life Example

Think of a **banking system**:

* Checking balance
* Withdrawing money
* Depositing money

Instead of dealing with multiple subsystems, you interact with a **single bank interface**.

---

# 🏗️ Structure

1. **Subsystem Classes** → Complex logic
2. **Facade Class** → Simplified interface
3. **Client** → Uses facade instead of subsystems

---

# 💻 Java Example (Banking System)

---

### 1. Subsystem Classes

#### Account Number Check

```java
class AccountNumberCheck {
    public boolean isValidAccount(int accNo) {
        return accNo == 12345;
    }
}
```

---

#### Security Code Check

```java
class SecurityCodeCheck {
    public boolean isValidCode(int code) {
        return code == 999;
    }
}
```

---

#### Funds Check

```java
class FundsCheck {
    private double balance = 1000.0;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
}
```

---

### 2. Facade Class

```java
class BankFacade {

    private int accountNumber;
    private int securityCode;

    private AccountNumberCheck accCheck;
    private SecurityCodeCheck codeCheck;
    private FundsCheck fundCheck;

    public BankFacade(int accNo, int code) {
        this.accountNumber = accNo;
        this.securityCode = code;

        accCheck = new AccountNumberCheck();
        codeCheck = new SecurityCodeCheck();
        fundCheck = new FundsCheck();
    }

    public void depositCash(double amount) {
        if (accCheck.isValidAccount(accountNumber) &&
            codeCheck.isValidCode(securityCode)) {

            fundCheck.deposit(amount);
        } else {
            System.out.println("Invalid account details");
        }
    }

    public void withdrawCash(double amount) {
        if (accCheck.isValidAccount(accountNumber) &&
            codeCheck.isValidCode(securityCode)) {

            fundCheck.withdraw(amount);
        } else {
            System.out.println("Invalid account details");
        }
    }
}
```

---

### 3. Main Class (Client)

```java
public class FacadePatternDemo {
    public static void main(String[] args) {

        BankFacade bank = new BankFacade(12345, 999);

        bank.depositCash(500);
        bank.withdrawCash(200);
    }
}
```

---

# 🔍 How It Works

Without Facade ❌
You would need to:

```java
AccountNumberCheck accCheck = new AccountNumberCheck();
SecurityCodeCheck codeCheck = new SecurityCodeCheck();
FundsCheck fundCheck = new FundsCheck();
```

👉 Too many dependencies!

---

With Facade ✅

```java
BankFacade bank = new BankFacade(12345, 999);
bank.depositCash(500);
```

👉 One object handles everything internally.

---

# 🎯 Benefits

* ✅ Simplifies complex systems
* ✅ Reduces client dependency on subsystems
* ✅ Improves readability and usability
* ✅ Promotes loose coupling

---

# ⚠️ Drawbacks

* ❌ Facade can become a **god class** if overloaded
* ❌ May hide useful subsystem features

---

# 📌 When to Use

Use Facade Pattern when:

* You have a **complex system with many classes**
* You want a **simple API for clients**
* You want to **decouple client from system internals**

---

# 🆚 Decorator vs Facade (Quick Difference)

| Feature   | Decorator     | Facade             |
| --------- | ------------- | ------------------ |
| Purpose   | Add behavior  | Simplify system    |
| Structure | Wrapper chain | Single entry point |
| Focus     | Enhancement   | Abstraction        |

---
