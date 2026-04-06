# Singleton Pattern

This module demonstrates the `singleton-pattern` using a simple class that exposes one shared instance across the application.

The example shows how a singleton guarantees that repeated access returns the same object reference.

## What Is the Singleton Pattern?

Singleton is a creational design pattern that ensures a class has only one instance and provides a global access point to that instance.

In simple terms:

- only one object of the class should exist
- everyone accesses that same object
- the class controls its own creation

Singleton is commonly used for shared resources such as:

- configuration holders
- caches
- loggers
- coordinators
- application-wide managers

## Problem It Solves

Sometimes an application should not create multiple copies of a class.

If multiple instances are created accidentally, you may get:

- inconsistent shared state
- unnecessary memory use
- conflicting behavior across components

Singleton solves that by keeping instance creation inside the class and exposing one shared object.

## Real Project Example

This module contains a class named `MySingleTonClass` that creates one instance eagerly:

```java
public class MySingleTonClass {
    @Getter
    public static MySingleTonClass instance = new MySingleTonClass();
    private MySingleTonClass() {}
}
```

Because the constructor is private, code outside the class cannot call:

```java
new MySingleTonClass()
```

Instead, the application must use:

```java
MySingleTonClass.getInstance()
```

That always returns the same shared object.

## Pattern Roles in This Module

### 1. Singleton Class

[`MySingleTonClass`](/Users/sandipchakraborty/Work/design-pattern-tutorial/singleton-pattern/src/main/java/com/design/pattern/singleton/MySingleTonClass.java)

This is the singleton implementation.

It has:

- a private constructor to block external instantiation
- a static instance field
- a Lombok-generated getter named `getInstance()`

This implementation uses eager initialization, meaning the instance is created when the class is loaded.

### 2. Client

[`SingletonController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/singleton-pattern/src/main/java/com/design/pattern/singleton/controller/SingletonController.java)

This controller is the client of the singleton.

It calls `MySingleTonClass.getInstance()` multiple times and returns the string form of the object reference each time.

If the returned strings are identical, that shows the same object instance is being reused.

### 3. Application Entry Point

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/singleton-pattern/src/main/java/com/design/pattern/singleton/Application.java)

This is the Spring Boot startup class for the module.

## How the Flow Works

1. The client calls the `/singleton` endpoint with a `size` value.
2. `SingletonController` loops `size` times.
3. On each iteration it calls `MySingleTonClass.getInstance()`.
4. The controller stores the returned object's `toString()` value.
5. The API returns the list of those values.

Because the same singleton instance is returned every time, all entries in the list should be identical.

## Why This Fits Singleton Pattern

This module enforces one shared instance by:

- hiding the constructor
- storing a static instance in the class
- exposing a global access method

That is the core idea of the Singleton pattern.

## Eager Initialization in This Example

This implementation creates the instance immediately:

```java
public static MySingleTonClass instance = new MySingleTonClass();
```

That means:

- the object is created even if it is never used
- access is simple and fast
- class loading creates the single instance automatically

This style is often called eager singleton initialization.

## Project Structure

```text
singleton-pattern
└── src/main/java/com/design/pattern/singleton
    ├── controller
    │   └── SingletonController.java
    ├── Application.java
    └── MySingleTonClass.java
```

## API Endpoint

[`SingletonController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/singleton-pattern/src/main/java/com/design/pattern/singleton/controller/SingletonController.java) exposes:

- `GET /singleton?size={number}`

### Example request

```bash
curl "http://localhost:8080/singleton?size=3"
```

### Example response

```json
[
  "com.design.pattern.singleton.MySingleTonClass@4a54c0de",
  "com.design.pattern.singleton.MySingleTonClass@4a54c0de",
  "com.design.pattern.singleton.MySingleTonClass@4a54c0de"
]
```

The exact hash value will vary, but all entries should be the same within one response because they refer to the same object.

## How to Run

From the repository root:

```bash
./gradlew :singleton-pattern:bootRun
```

The application entry point is:

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/singleton-pattern/src/main/java/com/design/pattern/singleton/Application.java)

## How to Test Manually

### Get one instance reference

```bash
curl "http://localhost:8080/singleton?size=1"
```

### Get the same instance multiple times

```bash
curl "http://localhost:8080/singleton?size=5"
```

If the pattern is working as expected, all returned values should be the same object reference string.

## Important Notes About This Implementation

This example is good for teaching the pattern, but a production-grade singleton often needs more care.

Things to notice in this implementation:

- the singleton instance is public
- the instance field is not declared `final`
- the example relies on Lombok `@Getter` to expose `getInstance()`

For stronger encapsulation, a more defensive version would usually make the instance field `private static final`.

Example style:

```java
private static final MySingleTonClass INSTANCE = new MySingleTonClass();

public static MySingleTonClass getInstance() {
    return INSTANCE;
}
```

That said, the current implementation still demonstrates the core singleton idea clearly.

## Benefits of This Design

- guarantees one shared instance
- provides a simple global access point
- avoids accidental repeated construction
- useful for shared coordination objects

## Trade-offs

- global access can increase coupling
- singletons can make testing harder if shared state is introduced
- poor implementations can become unsafe or mutable in unwanted ways
- eager initialization creates the instance even when unused

## Key Takeaway

Use the Singleton pattern when exactly one instance of a class should exist and the application needs a consistent shared access point to it.

In this project:

- the singleton class is `MySingleTonClass`
- the client is `SingletonController`
- repeated calls return the same object reference

That is the Singleton pattern in its simplest practical form.
