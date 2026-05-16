# Adapter Pattern

This module demonstrates the `adapter-pattern` using a student data example.

The core idea is that two classes represent similar data, but they expose different method names. The adapter makes one incompatible class behave like the interface the rest of the application expects.

## What Is the Adapter Pattern?

Adapter is a structural design pattern that allows incompatible interfaces to work together.

In simple terms:

- the client expects one interface
- an existing class has a different interface
- the adapter sits in between and translates calls

It is useful when you want to reuse an existing class without changing it.

## Problem It Solves

Suppose your application works with a common interface like `Student`:

```java
public interface Student {
    String getFirstName();
    String getLastName();
    String getEmail();
}
```

Now suppose you receive another student model from a different source:

```java
public class SchoolStudent {
    private String name;
    private String surname;
    private String emailAddress;
}
```

Even though both classes represent a student, their method names do not match.

That means:

- `SchoolStudent` cannot be used where `Student` is expected
- client code would need special-case conversion logic
- mixing multiple formats becomes messy

Adapter solves that by wrapping `SchoolStudent` and exposing the `Student` interface.

## Real Project Example

This module returns a list of students from one API endpoint.

The returned list contains:

- a `CollegeStudent`, which already implements `Student`
- a `SchoolStudent`, adapted through `SchoolStudentAdapter`

This lets the controller and service work with a single interface: `Student`.

## Pattern Roles in This Module

### 1. Target Interface

[`Student`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/Student.java)

This is the interface the application expects.

All student objects returned by the service must look like this interface.

### 2. Adaptee

[`SchoolStudent`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/SchoolStudent.java)

This is the incompatible class.

It contains similar data, but with different method names:

- `getName()`
- `getSurname()`
- `getEmailAddress()`

Because it does not implement `Student`, it cannot be directly added to `List<Student>`.

### 3. Adapter

[`SchoolStudentAdapter`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/SchoolStudentAdapter.java)

This class wraps a `SchoolStudent` and translates its methods into the `Student` interface:

```java
public class SchoolStudentAdapter implements Student {
    private final SchoolStudent student;

    @Override
    public String getFirstName() {
        return student.getName();
    }

    @Override
    public String getLastName() {
        return student.getSurname();
    }

    @Override
    public String getEmail() {
        return student.getEmailAddress();
    }
}
```

### 4. Already Compatible Class

[`CollegeStudent`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/CollegeStudent.java)

This class already implements `Student`, so it does not need an adapter.

### 5. Client

[`StudentService`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/StudentService.java)

This service is the client of the adapter pattern.

It builds a `List<Student>` and can store:

- `CollegeStudent` directly
- `SchoolStudentAdapter` for wrapped school student objects

### 6. API Layer

[`StudentController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/StudentController.java)

This controller exposes the adapted student list through a REST endpoint.

## How the Flow Works

1. The client calls the `/adapter` endpoint.
2. `StudentController` asks `StudentService` for the student list.
3. `StudentService` creates a `CollegeStudent`.
4. `StudentService` creates a `SchoolStudent`.
5. The school student is wrapped in `SchoolStudentAdapter`.
6. Both objects are returned as `List<Student>`.

The key point is that the caller only sees the `Student` interface.

## Why This Fits Adapter Pattern

This module has an existing class, `SchoolStudent`, that is conceptually compatible but structurally incompatible with the expected interface.

The adapter fixes that mismatch without changing `SchoolStudent`.

That is exactly what Adapter is meant for:

- keep old or third-party code unchanged
- make it work with your application’s interface

## Project Structure

```text
adapter-pattern
└── src/main/java/com/design/pattern/adapter
    ├── Application.java
    ├── CollegeStudent.java
    ├── SchoolStudent.java
    ├── SchoolStudentAdapter.java
    ├── Student.java
    ├── StudentController.java
    └── StudentService.java
```

## API Endpoint

[`StudentController`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/StudentController.java) exposes:

- `GET /adapter`

### Example request

```bash
curl http://localhost:8080/adapter
```

### Example response

The exact JSON shape depends on serialization, but conceptually the API returns student records like:

```json
[
  {
    "firstName": "Bob",
    "lastName": "Marley",
    "email": "bob.marley@mit.com"
  },
  {
    "firstName": "Alice",
    "lastName": "Hopper",
    "email": "alice.hopper@academia.edu"
  }
]
```

The second object comes from `SchoolStudent`, but after adaptation it looks like a normal `Student`.

## How to Run

From the repository root:

```bash
./gradlew :adapter-pattern:bootRun
```

The application entry point is:

[`Application.java`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/Application.java)

## How to Test Manually

### Fetch adapted students

```bash
curl http://localhost:8080/adapter
```

You should see both student sources returned in one unified format.

## How to Add Another Incompatible Student Source

Suppose you later receive a new model like `InternationalStudent` with fields such as:

- `givenName`
- `familyName`
- `mail`

You can support it by:

1. Creating a new adapter that implements [`Student`](/Users/sandipchakraborty/Work/design-pattern-tutorial/adapter-pattern/src/main/java/com/design/pattern/adapter/Student.java).
2. Wrapping the incompatible object inside that adapter.
3. Translating its field access methods to `getFirstName()`, `getLastName()`, and `getEmail()`.

The service can then return that adapter in the same `List<Student>` without changing the client-facing contract.

## Benefits of This Design

- reuses existing classes without modifying them
- keeps client code dependent on one interface
- hides conversion logic inside a dedicated class
- makes multiple data sources easier to combine
- improves maintainability when integrating external models

## Trade-offs

- adds an extra class for each incompatible type
- can become repetitive if many similar models must be adapted
- if the source model changes often, adapters must be kept in sync

## Key Takeaway

Use the Adapter pattern when you already have a class that contains the right kind of data or behavior, but its interface does not match what your application expects.

In this project:

- the target interface is `Student`
- the adaptee is `SchoolStudent`
- the adapter is `SchoolStudentAdapter`
- the client is `StudentService`

That is the Adapter pattern in a practical form: converting one interface into another without rewriting the original class.
