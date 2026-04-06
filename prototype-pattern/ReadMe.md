# Prototype Pattern

This module demonstrates the `prototype-pattern` using an order template example.

Instead of constructing every order from scratch, the application keeps ready-made prototype objects and creates new orders by cloning them.

## What Is the Prototype Pattern?

Prototype is a creational design pattern used to create new objects by copying an existing object, called the prototype.

In simple terms:

- prepare a fully configured object
- clone it when a new similar object is needed
- customize the clone if required

This is useful when creating a new object from scratch is repetitive, expensive, or requires many default values.

## Problem It Solves

Suppose your application frequently creates similar objects like:

- a basic order template
- a premium order template

Without Prototype, you may repeatedly write setup code like:

- create object
- fill default fields
- create default item list
- configure flags like shipping and insurance

That leads to:

- duplicated object creation code
- harder maintenance when defaults change
- greater chance of inconsistent setup

Prototype solves this by keeping a prepared object and cloning it when needed.

## Real Project Example

This module manages two ready-made order templates:

- `BASIC`
- `PREMIUM`

Each template has default values for:

- `orderType`
- `items`
- `priorityShipping`
- `insurance`

When a request arrives:

1. the service asks the registry for a clone of the requested prototype
2. the clone is customized by adding one extra item
3. the customized order is returned

## Pattern Roles in This Module

### 1. Prototype Interface

[`Prototype<T>`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/Prototype.java)

This interface defines the cloning contract:

```java
public interface Prototype<T> {
    T clone();
}
```

### 2. Concrete Prototype

[`Order`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/Order.java)

This is the object being cloned.

It implements `Prototype<Order>` and provides a deep-copy clone:

```java
@Override
public Order clone() {
    return new Order(this);
}
```

The copy constructor is important because the class contains a mutable `List<String> items`.

### 3. Prototype Registry

[`OrderPrototypeRegistry`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/OrderPrototypeRegistry.java)

This class stores reusable prototypes in memory.

It preloads two prototypes:

- `BASIC`
- `PREMIUM`

Then it exposes `getClone(type)` to return a cloned copy instead of the original object.

### 4. Client

[`OrderService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/service/OrderService.java)

This service is the client of the Prototype pattern.

It:

- requests a clone from the registry
- customizes the cloned object
- returns the customized result

### 5. API Layer

[`PrototypeController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/controller/PrototypeController.java)

This controller exposes the clone-and-customize behavior through a REST endpoint.

## How the Flow Works

1. The client calls the `/prototype` endpoint with a prototype type and an extra item.
2. The controller forwards the request to `OrderService`.
3. `OrderService` asks `OrderPrototypeRegistry` for a clone of the requested type.
4. The registry finds the stored prototype and returns `prototype.clone()`.
5. The service customizes the clone by adding the extra item to `items`.
6. The customized order is returned.

## Why Deep Copy Matters Here

`Order` contains a mutable list:

- `items`

If the clone reused the same list reference, modifying one cloned order would also modify the original prototype or other cloned objects.

This module avoids that problem with a deep-copy constructor:

```java
public Order(Order target) {
    this.orderType = target.getOrderType();
    this.items = new ArrayList<>(target.getItems());
    this.priorityShipping = target.isPriorityShipping();
    this.insurance = target.isInsurance();
}
```

That line creates a new list for every clone:

```java
this.items = new ArrayList<>(target.getItems());
```

This is a key detail in a correct Prototype implementation.

## Why This Fits Prototype Pattern

This module does not create orders through long constructors or factories every time.

Instead, it:

- stores ready-made templates
- creates copies from them
- customizes the copies

That is the exact purpose of the Prototype pattern.

## Project Structure

```text
prototype-pattern
└── src/main/java/com/design/pattern/prototype
    ├── controller
    │   └── PrototypeController.java
    ├── service
    │   └── OrderService.java
    ├── Application.java
    ├── Order.java
    ├── OrderPrototypeRegistry.java
    └── Prototype.java
```

## Predefined Prototypes

[`OrderPrototypeRegistry`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/OrderPrototypeRegistry.java) defines these templates:

### BASIC

- `orderType = BASIC`
- `items = ["item1"]`
- `priorityShipping = false`
- `insurance = false`

### PREMIUM

- `orderType = PREMIUM`
- `items = ["item1", "item2"]`
- `priorityShipping = true`
- `insurance = true`

## API Endpoint

[`PrototypeController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/controller/PrototypeController.java) exposes:

- `POST /prototype?type={BASIC|PREMIUM}&item={extraItem}`

### Example request

```bash
curl -X POST "http://localhost:8080/prototype?type=BASIC&item=item3"
```

### Example response

```json
{
  "orderType": "BASIC",
  "items": ["item1", "item3"],
  "priorityShipping": false,
  "insurance": false
}
```

### Premium example

```bash
curl -X POST "http://localhost:8080/prototype?type=PREMIUM&item=item3"
```

Possible response:

```json
{
  "orderType": "PREMIUM",
  "items": ["item1", "item2", "item3"],
  "priorityShipping": true,
  "insurance": true
}
```

### Invalid type example

```bash
curl -X POST "http://localhost:8080/prototype?type=VIP&item=item3"
```

The registry throws:

```text
Invalid order type
```

## How to Run

From the repository root:

```bash
./gradlew :prototype-pattern:bootRun
```

The application entry point is:

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/Application.java)

## How to Test Manually

### Clone basic order

```bash
curl -X POST "http://localhost:8080/prototype?type=BASIC&item=headphones"
```

### Clone premium order

```bash
curl -X POST "http://localhost:8080/prototype?type=PREMIUM&item=gift-card"
```

## How to Add Another Prototype Template

Suppose you want a new `EXPRESS` template.

You can add it in [`OrderPrototypeRegistry.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/prototype-pattern/src/main/java/com/design/pattern/prototype/OrderPrototypeRegistry.java) by:

1. Creating a new prepared `Order`.
2. Storing it in the `prototypes` map with key `EXPRESS`.
3. Returning clones from `getClone("EXPRESS")` automatically through the same registry logic.

Example idea:

- `orderType = EXPRESS`
- `items = ["item1", "express-kit"]`
- `priorityShipping = true`
- `insurance = false`

The service logic does not need to change because it already works with cloned templates.

## Benefits of This Design

- avoids repeating default object setup
- keeps template configuration centralized
- makes creation of similar objects fast and readable
- supports customization after cloning
- reduces errors from duplicated initialization code

## Trade-offs

- cloning must be implemented carefully for mutable fields
- deep copies can become more complex for large object graphs
- debugging may be less obvious than direct construction if cloning logic is hidden

## Key Takeaway

Use the Prototype pattern when you want to create new objects by copying existing configured objects instead of building everything from scratch.

In this project:

- the prototype contract is `Prototype<T>`
- the concrete prototype is `Order`
- the registry stores reusable templates
- the service clones and customizes them

That is the Prototype pattern in a practical form.
