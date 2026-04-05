# Abstract Factory Pattern

This module demonstrates the `abstract-factory-pattern` using a payment system.

Instead of creating payment and refund implementations directly inside business services, the code asks a factory to provide a matching family of objects for a selected provider such as `STRIPE` or `PAYPAL`.

## What Is the Abstract Factory Pattern?

Abstract Factory is a creational design pattern that provides an interface for creating related objects without exposing their concrete classes.

In simple terms:

- A client needs a group of related objects.
- The exact group depends on some choice at runtime.
- The client should not know how those objects are created.

In this project, each payment provider needs two related objects:

- a `PaymentProcessor`
- a `RefundProcessor`

The abstract factory makes sure both objects come from the same provider family.

## Problem It Solves

Without Abstract Factory, service classes usually end up with logic like:

- `if provider == STRIPE` then create Stripe classes
- `if provider == PAYPAL` then create PayPal classes

That causes:

- tight coupling to concrete classes
- repeated conditional logic
- harder extension when adding a new provider
- risk of mixing incompatible implementations

Abstract Factory removes that creation logic from the services.

## Real Project Example

This module models checkout and refund operations for multiple payment providers.

For each provider, we need a matching pair of implementations:

- Stripe payment + Stripe refund
- PayPal payment + PayPal refund

The services do not create these classes directly. They ask a factory to give them the correct pair.

## Pattern Roles in This Module

### 1. Abstract Factory

[`PaymentFactory`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/factory/PaymentFactory.java)

This interface defines the contract for creating related objects:

```java
public interface PaymentFactory {
    PaymentProcessor createPaymentProcessor();
    RefundProcessor createRefundProcessor();
}
```

### 2. Concrete Factories

- [`PaypalPaymentFactory`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/factory/PaypalPaymentFactory.java)
- [`StripePaymentFactory`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/factory/StripePaymentFactory.java)

Each concrete factory returns provider-specific implementations.

Example:

- `StripePaymentFactory` creates `StripePaymentProcessor` and `StripeRefundProcessor`
- `PaypalPaymentFactory` creates `PaypalPaymentProcessor` and `PaypalRefundProcessor`

### 3. Abstract Products

- [`PaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/processor/PaymentProcessor.java)
- [`RefundProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/processor/RefundProcessor.java)

These are the common interfaces used by the services.

### 4. Concrete Products

- [`StripePaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/processor/StripePaymentProcessor.java)
- [`StripeRefundProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/processor/StripeRefundProcessor.java)
- [`PaypalPaymentProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/processor/PaypalPaymentProcessor.java)
- [`PaypalRefundProcessor`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/processor/PaypalRefundProcessor.java)

These classes perform the actual provider-specific behavior.

### 5. Factory Selector

[`PaymentFactoryProvider`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/factory/PaymentFactoryProvider.java)

This class chooses the correct concrete factory at runtime by using `PaymentProvider`.

### 6. Client

- [`CheckoutService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/service/CheckoutService.java)
- [`RefundService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/service/RefundService.java)

These services are the clients of the abstract factory. They depend only on abstractions and do not know concrete implementation details.

## How the Flow Works

### Checkout flow

1. The controller receives `PaymentDetails`.
2. The service reads `providerId`.
3. The service converts it to `PaymentProvider`.
4. `PaymentFactoryProvider` returns the matching factory.
5. The factory creates the correct `PaymentProcessor`.
6. The service executes `pay(amount)`.

### Refund flow

1. The controller receives `PaymentDetails`.
2. The service finds the matching factory.
3. The factory creates the correct `RefundProcessor`.
4. The service executes `refund(amount)`.

## Why This Is Abstract Factory and Not Simple Factory

Simple Factory usually creates one product type.

Abstract Factory creates a family of related products.

In this module, one factory creates:

- one payment processor
- one refund processor

That related object family is the main reason this example fits Abstract Factory.

## Project Structure

```text
abstract-factory-pattern
└── src/main/java/com/design/pattern/abs/factory
    ├── controller
    │   └── CheckoutController.java
    ├── factory
    │   ├── PaymentFactory.java
    │   ├── PaymentFactoryProvider.java
    │   ├── PaypalPaymentFactory.java
    │   └── StripePaymentFactory.java
    ├── model
    │   ├── PaymentDetails.java
    │   └── PaymentProvider.java
    ├── processor
    │   ├── PaymentProcessor.java
    │   ├── RefundProcessor.java
    │   ├── PaypalPaymentProcessor.java
    │   ├── PaypalRefundProcessor.java
    │   ├── StripePaymentProcessor.java
    │   └── StripeRefundProcessor.java
    ├── service
    │   ├── CheckoutService.java
    │   └── RefundService.java
    └── Application.java
```

## API Endpoints

[`CheckoutController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/controller/CheckoutController.java) exposes:

- `POST /abs/factory/pay`
- `POST /abs/factory/refund`

### Request body

```json
{
  "providerId": "STRIPE",
  "amount": 1500.0
}
```

You can also use `PAYPAL` as the provider.

### Example responses

For payment:

```text
Stripe Payment done for amount : 1500.0
```

For refund:

```text
Stripe Refund done for amount : 1500.0
```

## How to Run

From the repository root:

```bash
./gradlew :abstract-factory-pattern:bootRun
```

The application entry point is:

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/Application.java)

## How to Test Manually

### Pay with Stripe

```bash
curl -X POST http://localhost:8080/abs/factory/pay \
  -H "Content-Type: application/json" \
  -d '{"providerId":"STRIPE","amount":1500.0}'
```

### Refund with PayPal

```bash
curl -X POST http://localhost:8080/abs/factory/refund \
  -H "Content-Type: application/json" \
  -d '{"providerId":"PAYPAL","amount":800.0}'
```

## How to Add a New Provider

Suppose you want to support `RAZORPAY`.

1. Add `RAZORPAY` to [`PaymentProvider`](/Users/sandipchakraborty/Work/design-pattern-tutorial/abstract-factory-pattern/src/main/java/com/design/pattern/abs/factory/model/PaymentProvider.java).
2. Create `RazorpayPaymentProcessor` implementing `PaymentProcessor`.
3. Create `RazorpayRefundProcessor` implementing `RefundProcessor`.
4. Create `RazorpayPaymentFactory` implementing `PaymentFactory`.
5. Register the factory as a Spring bean with the same name as the enum value, for example `@Component("RAZORPAY")`.

The services do not need to change. That is one of the main benefits of this pattern.

## Benefits of This Design

- keeps object creation separate from business logic
- ensures related objects are used together
- makes the code easier to extend
- reduces `if/else` and `switch` statements in services
- improves maintainability and testability

## Trade-offs

- adds more classes than a direct implementation
- may feel heavy for very small applications
- introducing a new product type means updating all factories

Example:

If you add a third product like `InvoiceProcessor`, every concrete factory must implement it.

## Key Takeaway

Use Abstract Factory when your application needs to create groups of related objects and you want the client code to remain independent from concrete implementations.

In this project:

- the family is payment-related processors
- the variants are providers like Stripe and PayPal
- the clients are checkout and refund services

That is exactly the kind of problem Abstract Factory is designed to solve.
