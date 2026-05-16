# Builder Pattern

This module demonstrates the `builder-pattern` using an order creation example.

Instead of calling a long constructor with many optional arguments, the code builds an `Order` step by step through a fluent builder API.

## What Is the Builder Pattern?

Builder is a creational design pattern used to construct complex objects in a readable and controlled way.

It is especially useful when:

- some fields are required
- many fields are optional
- constructors would otherwise become long and confusing

The main idea is simple:

- create a builder with required values
- set optional values only when needed
- call `build()` to create the final object

## Problem It Solves

Without Builder, creating an object like `Order` often leads to:

- constructors with too many parameters
- poor readability
- mistakes caused by parameter order
- lots of overloaded constructors

For example, a constructor like this becomes hard to understand quickly:

```java
new Order(orderId, customerName, discount, giftWrap, deliveryInstructions, priorityShipping, couponCode)
```

When several fields are optional, Builder gives a cleaner and safer construction style.

## Real Project Example

This module creates an order with:

- required fields:
  - `orderId`
  - `customerName`
- optional fields:
  - `discount`
  - `giftWrap`
  - `deliveryInstructions`
  - `priorityShipping`
  - `couponCode`

The `OrderService` uses the builder to assemble the final `Order` instance.

## Pattern Roles in This Module

### 1. Product

[`Order`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/model/Order.java)

This is the object being built.

It contains:

- required fields for basic order identity
- optional fields for customization

The constructor of `Order` is private and accepts the builder:

```java
private Order(OrderBuilder builder) {
    this.orderId = builder.orderId;
    this.customerName = builder.customerName;
    this.discount = builder.discount;
    this.giftWrap = builder.giftWrap;
    this.deliveryInstructions = builder.deliveryInstructions;
    this.priorityShipping = builder.priorityShipping;
    this.couponCode = builder.couponCode;
}
```

### 2. Builder

[`Order.OrderBuilder`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/model/Order.java)

This inner static class is the builder for `Order`.

It has:

- required constructor arguments: `orderId`, `customerName`
- fluent setter-style methods for optional fields
- a `build()` method that returns the final `Order`

Example:

```java
new Order.OrderBuilder(orderId, customerName)
        .discount(discount)
        .giftWrap(giftWrap)
        .deliveryInstructions(deliveryInstructions)
        .priorityShipping(priorityShipping)
        .couponCode(couponCode)
        .build();
```

### 3. Client

[`OrderService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/service/OrderService.java)

This service is the client of the builder. It does not assemble the `Order` through a long constructor. It uses the builder chain to create the object in a readable way.

### 4. API Layer

[`OrderController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/controller/OrderController.java)

This controller accepts request parameters and delegates order creation to `OrderService`.

## How the Flow Works

1. The client calls the order API with required and optional request parameters.
2. The controller forwards those values to `OrderService`.
3. `OrderService` creates `new Order.OrderBuilder(orderId, customerName)`.
4. Optional values are applied through fluent methods.
5. `build()` creates the final immutable-style `Order` object.
6. The API returns the created `Order`.

## Why This Fits Builder Pattern

This module has one object with several optional fields.

That makes Builder a good fit because:

- required fields are enforced up front
- optional fields stay readable
- the creation code becomes easy to scan

This is different from Factory:

- Factory selects which object to create
- Builder focuses on how to assemble one object step by step

## Project Structure

```text
builder-pattern
└── src/main/java/com/design/pattern/builder
    ├── controller
    │   └── OrderController.java
    ├── model
    │   └── Order.java
    ├── service
    │   └── OrderService.java
    └── Application.java
```

## API Endpoint

[`OrderController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/controller/OrderController.java) exposes:

- `POST /builder`

### Required request parameters

- `orderId`
- `customerName`

### Optional request parameters

- `discount`
- `giftWrap`
- `deliveryInstructions`
- `priorityShipping`
- `couponCode`

### Example request

```bash
curl -X POST "http://localhost:8080/builder?orderId=ORD-101&customerName=Alice&discount=10.0&giftWrap=true&deliveryInstructions=Leave%20at%20door&priorityShipping=true&couponCode=NEWUSER"
```

### Example response

```json
{
  "orderId": "ORD-101",
  "customerName": "Alice",
  "discount": 10.0,
  "giftWrap": true,
  "deliveryInstructions": "Leave at door",
  "priorityShipping": true,
  "couponCode": "NEWUSER"
}
```

### Minimal request example

Only the required values are needed:

```bash
curl -X POST "http://localhost:8080/builder?orderId=ORD-102&customerName=Bob"
```

## How to Run

From the repository root:

```bash
./gradlew :builder-pattern:bootRun
```

The application entry point is:

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/Application.java)

## How to Test Manually

### Full order

```bash
curl -X POST "http://localhost:8080/builder?orderId=ORD-201&customerName=Charlie&discount=15.0&giftWrap=true&deliveryInstructions=Call%20on%20arrival&priorityShipping=false&couponCode=SAVE15"
```

### Required fields only

```bash
curl -X POST "http://localhost:8080/builder?orderId=ORD-202&customerName=Diana"
```

## How to Extend the Order Model

Suppose you want to add a new optional field like `specialNote`.

You would update [`Order.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/model/Order.java) in these places:

1. Add the field to `Order`.
2. Add the field to `OrderBuilder`.
3. Add a fluent builder method such as `specialNote(String value)`.
4. Copy the value from builder to product in the private `Order` constructor.

Then update:

- [`OrderService.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/service/OrderService.java)
- [`OrderController.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/builder-pattern/src/main/java/com/design/pattern/builder/controller/OrderController.java)

to accept and pass the new field.

## Benefits of This Design

- improves readability when many fields are involved
- separates required and optional values clearly
- avoids telescoping constructors
- reduces bugs caused by incorrect parameter order
- makes object creation easier to extend

## Trade-offs

- introduces more code than a plain constructor
- can feel unnecessary for very small objects
- if validation is needed, it should be added carefully in the builder or service

## Key Takeaway

Use the Builder pattern when one object needs to be created with a mix of required and optional values, and you want construction code to stay readable.

In this project:

- the product is `Order`
- the builder is `Order.OrderBuilder`
- the client is `OrderService`
- the API shows how the builder handles both minimal and fully customized orders

That is the Builder pattern in a practical, easy-to-understand form.
