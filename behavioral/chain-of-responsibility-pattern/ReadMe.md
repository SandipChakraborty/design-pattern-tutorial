The **Chain of Responsibility** pattern is used when you want multiple handlers/processors to get a chance to process a request, one after another, without the caller knowing who actually handles it.

It helps you:

* Decouple sender from receiver
* Build flexible processing pipelines
* Add/remove handlers easily
* Reuse processing steps

---

# Simple Real-Life Analogy

Think of **customer support escalation**:

1. Support Agent tries to solve issue
2. If not possible → Team Lead
3. If still unresolved → Manager

The request flows through a **chain**.

---

# Real-Life Use Cases

## 1. Authentication & Authorization Pipelines

Very common in Java backend systems.

Example:

* JWT validation
* API key validation
* Role validation
* Rate limiting

Each handler either:

* processes and passes forward
* or stops the chain

Used heavily in:

* Spring Security
* Servlet filters
* API gateways

---

## 2. Logging Frameworks

Example:

* ERROR logs → File + Slack
* INFO logs → Console
* DEBUG logs → Ignore in production

Each logger decides whether to handle or pass.

---

## 3. Payment Processing

Example:

* Fraud check
* Balance check
* Currency validation
* Payment execution

Each step is a handler.

---

## 4. Order Processing Pipeline

Example:

* Validate order
* Check inventory
* Apply discount
* Calculate shipping
* Process payment

Very common in e-commerce systems.

---

## 5. Middleware Pipelines

Examples:

* Spring filters
* HTTP interceptors
* Kafka message processors
* MQ consumer pipelines

---

# Simple Java 21 Example

Let’s build a small **leave approval system**.

Flow:

* TeamLead can approve leave ≤ 3 days
* Manager ≤ 7 days
* Director ≤ 15 days

---

# Recommended Module Structure

```text
design-patterns/
 └── chain-of-responsibility/
      └── src/main/java/com/example/cor/
```

---

# Step 1 — Request Object

```java
package com.example.cor;

public record LeaveRequest(
        String employeeName,
        int numberOfDays
) {
}
```

Using Java 21 `record` keeps it clean.

---

# Step 2 — Abstract Handler

```java
package com.example.cor;

public abstract class LeaveApprover {

    protected LeaveApprover next;

    public LeaveApprover setNext(LeaveApprover next) {
        this.next = next;
        return next;
    }

    public abstract void approve(LeaveRequest request);
}
```

This creates the chain.

---

# Step 3 — Concrete Handlers

## Team Lead

```java
package com.example.cor;

public class TeamLead extends LeaveApprover {

    @Override
    public void approve(LeaveRequest request) {

        if (request.numberOfDays() <= 3) {
            System.out.println(
                    "TeamLead approved leave for "
                            + request.employeeName()
            );
            return;
        }

        if (next != null) {
            next.approve(request);
        }
    }
}
```

---

## Manager

```java
package com.example.cor;

public class Manager extends LeaveApprover {

    @Override
    public void approve(LeaveRequest request) {

        if (request.numberOfDays() <= 7) {
            System.out.println(
                    "Manager approved leave for "
                            + request.employeeName()
            );
            return;
        }

        if (next != null) {
            next.approve(request);
        }
    }
}
```

---

## Director

```java
package com.example.cor;

public class Director extends LeaveApprover {

    @Override
    public void approve(LeaveRequest request) {

        if (request.numberOfDays() <= 15) {
            System.out.println(
                    "Director approved leave for "
                            + request.employeeName()
            );
            return;
        }

        System.out.println(
                "Leave request rejected for "
                        + request.employeeName()
        );
    }
}
```

---

# Step 4 — Client

```java
package com.example.cor;

public class Main {

    public static void main(String[] args) {

        LeaveApprover teamLead = new TeamLead();
        LeaveApprover manager = new Manager();
        LeaveApprover director = new Director();

        // Build chain
        teamLead
                .setNext(manager)
                .setNext(director);

        // Requests
        teamLead.approve(new LeaveRequest("John", 2));

        teamLead.approve(new LeaveRequest("Alice", 5));

        teamLead.approve(new LeaveRequest("Bob", 10));

        teamLead.approve(new LeaveRequest("David", 20));
    }
}
```

---

# Output

```text
TeamLead approved leave for John
Manager approved leave for Alice
Director approved leave for Bob
Leave request rejected for David
```

---

# How This Works Internally

For `10 days leave`:

```text
TeamLead -> cannot handle
      ↓
Manager -> cannot handle
      ↓
Director -> approves
```

That is the “chain”.

---

# Key Advantages

## 1. Loose Coupling

Caller only knows:

```java
teamLead.approve(request);
```

Not who finally handles it.

---

## 2. Open for Extension

Add new handler easily:

```text
HR -> VP -> CEO
```

without changing existing code.

---

## 3. Cleaner Conditional Logic

Instead of giant:

```java
if ...
else if ...
else if ...
```

you separate responsibilities.

---

# Common Enterprise Java Usage

You’ll see this pattern in:

| Area             | Example                      |
| ---------------- | ---------------------------- |
| Spring Security  | Filter chains                |
| Servlet API      | Filters                      |
| Kafka consumers  | Message processing pipelines |
| IBM MQ consumers | Validation chains            |
| Logging          | Appenders/handlers           |
| API Gateway      | Middleware chains            |
| Batch processing | Sequential processors        |

---

# A More Realistic Backend Example

In your kind of systems (Spring Boot + MQ + high throughput), this pattern is extremely useful for:

```text
Message Received
      ↓
Schema Validation Handler
      ↓
Deduplication Handler
      ↓
Enrichment Handler
      ↓
Fraud Check Handler
      ↓
Persistence Handler
      ↓
Publish Event Handler
```

Each handler:

* does one thing
* is independently testable
* can short-circuit processing

This is very scalable and maintainable.

---

# Java 21 Improvement Ideas

Once comfortable, try:

* `sealed interfaces`
* Functional chains
* Spring Bean-based handler registration
* Async handlers using virtual threads
* Generic handlers

Example:

```java
public sealed interface Handler
        permits ValidationHandler, FraudHandler {
}
```

---

# When NOT to Use It

Avoid if:

* there is only one handler ever
* flow is extremely simple
* chain ordering becomes too complicated

Overusing it can make debugging harder.

---

# Interview-Level One-Liner

> Chain of Responsibility allows multiple objects to process a request sequentially without the sender knowing which object will ultimately handle it.
