# Factory Pattern

This module demonstrates the `factory-pattern` using a payment processing example.

Instead of creating concrete payment processors directly inside the service or controller, the code delegates that responsibility to a factory class which returns the correct implementation based on the payment type.

## What Is the Factory Pattern?

Factory is a creational design pattern used to create objects without exposing the full object creation logic to the client.

In simple terms:

- the client asks for an object
- the factory decides which concrete class to return
- the client works with a common interface

In this project, the common interface is `PaymentProcessor`, and the factory returns a processor such as:

- `CreditCardPaymentProcessor`
- `DebitCardPaymentProcessor`
- `PayPalPaymentProcessor`
- `UpiPaymentProcessor`

## Problem It Solves

Without a factory, business code usually contains object creation logic like:

- `new CreditCardPaymentProcessor()`
- `new DebitCardPaymentProcessor()`
- `if/else` or `switch` blocks spread across services

That creates a few problems:

- tight coupling to concrete classes
- repeated selection logic
- harder extension when new payment types are added
- more fragile service code

Using a factory centralizes that decision-making in one place.

## Real Project Example

This module exposes an API that processes payments for different types:

- credit card
- debit card
- PayPal
- UPI

The controller passes the payment type to the service. The service asks the factory for the correct processor. The returned processor handles the payment.

## Pattern Roles in This Module

### 1. Product Interface

[`PaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/processor/PaymentProcessor.java)

This is the abstraction used by the service:

```java
public interface PaymentProcessor {
    String processPayment(double amount);
}
```

### 2. Concrete Products

- [`CreditCardPaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/processor/CreditCardPaymentProcessor.java)
- [`DebitCardPaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/processor/DebitCardPaymentProcessor.java)
- [`PayPalPaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/processor/PayPalPaymentProcessor.java)
- [`UpiPaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/processor/UpiPaymentProcessor.java)

These are the actual implementations returned by the factory.

### 3. Factory

[`PaymentProcessorFactory`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/factory/PaymentProcessorFactory.java)

This class is responsible for choosing the right processor based on the given payment type.

It collects all Spring-managed `PaymentProcessor` beans and then maps supported request values such as:

- `creditcard`
- `debitcard`
- `paypal`
- `upi`

to the correct concrete implementation.

### 4. Client

[`PaymentService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/service/PaymentService.java)

This service is the client of the factory. It does not create processor objects directly. It asks the factory for one and then uses the `PaymentProcessor` interface.

### 5. API Layer

[`PaymentController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/controller/PaymentController.java)

This controller exposes the payment endpoint and converts invalid factory selections into `400 Bad Request`.

## How the Flow Works

1. The client calls the payment API with a payment type and amount.
2. The controller forwards the request to `PaymentService`.
3. `PaymentService` asks `PaymentProcessorFactory` for the correct processor.
4. The factory selects the matching implementation.
5. The service calls `processPayment(amount)`.
6. The processor returns the response message.

## Why This Fits Factory Pattern

In this module, the factory creates one product type: `PaymentProcessor`.

That is the key distinction from Abstract Factory:

- Factory pattern: creates one kind of product
- Abstract Factory: creates a family of related products

Since this module only selects one processor implementation per request, it fits the Factory pattern.

## Project Structure

```text
factory-pattern
└── src/main/java/com/design/pattern/factory
    ├── controller
    │   └── PaymentController.java
    ├── exception
    │   └── FactoryException.java
    ├── factory
    │   └── PaymentProcessorFactory.java
    ├── processor
    │   ├── CreditCardPaymentProcessor.java
    │   ├── DebitCardPaymentProcessor.java
    │   ├── PayPalPaymentProcessor.java
    │   ├── PaymentProcessor.java
    │   └── UpiPaymentProcessor.java
    ├── service
    │   └── PaymentService.java
    └── Application.java
```

## API Endpoint

[`PaymentController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/controller/PaymentController.java) exposes:

- `POST /factory/payments/{type}?amount={value}`

### Supported types

- `creditcard`
- `debitcard`
- `paypal`
- `upi`

### Example request

```bash
curl -X POST "http://localhost:8080/factory/payments/creditcard?amount=1200.0"
```

### Example response

```text
Processed Credit Card payment of ₹1200.0
```

### Invalid request example

```bash
curl -X POST "http://localhost:8080/factory/payments/bitcoin?amount=1200.0"
```

This returns a `400 Bad Request` with message:

```text
Invalid payment type
```

## Metrics Used in the Service

[`PaymentService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/service/PaymentService.java) also records:

- a counter named `payments_total`
- a timer named `payment_duration`

That means the module is not only demonstrating the pattern but also showing how business logic can stay clean while observability is added around it.

## How to Run

From the repository root:

```bash
./gradlew :factory-pattern:bootRun
```

The application entry point is:

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/Application.java)

## How to Test Manually

### Credit card payment

```bash
curl -X POST "http://localhost:8080/factory/payments/creditcard?amount=1000.0"
```

### PayPal payment

```bash
curl -X POST "http://localhost:8080/factory/payments/paypal?amount=850.0"
```

### UPI payment

```bash
curl -X POST "http://localhost:8080/factory/payments/upi?amount=499.0"
```

## Test Coverage

There is an existing service test here:

[`PaymentServiceTest.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/test/java/com/design/pattern/factory/service/PaymentServiceTest.java)

It verifies that:

- the service asks the factory for the correct processor
- the processor is invoked with the expected amount
- the service returns the processor response

## How to Add a New Payment Type

Suppose you want to support `wallet`.

1. Create a new class implementing `PaymentProcessor`.
2. Register it as a Spring bean using `@Component`.
3. Update [`PaymentProcessorFactory`](/Users/sandipchakraborty/Work/design-pattern-tutorial/factory-pattern/src/main/java/com/design/pattern/factory/factory/PaymentProcessorFactory.java) to map the new request type to the new processor.

Example:

- create `WalletPaymentProcessor`
- add a `case "wallet"` branch in the factory

The service and controller logic do not need major changes because object selection remains centralized in the factory.

## Benefits of This Design

- keeps creation logic out of the service
- reduces direct dependency on concrete classes
- makes supported payment types easy to locate
- improves readability and maintainability
- gives one central place for validation and selection

## Trade-offs

- adding new types still requires updating the factory
- too many `case` branches can become hard to maintain
- for larger related object families, Abstract Factory may be a better fit

## Key Takeaway

Use the Factory pattern when client code needs one object from several possible implementations, and you want object selection logic centralized.

In this project:

- the client is `PaymentService`
- the product is `PaymentProcessor`
- the concrete implementations are credit card, debit card, PayPal, and UPI processors
- the factory decides which one to return

That is the core idea of the Factory pattern in practice.
