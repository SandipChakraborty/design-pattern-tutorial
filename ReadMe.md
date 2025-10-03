# Design Pattern Tutorial Monorepo

This project is a **multi-module Spring Boot 3 project** demonstrating basic CRUD operations with **Java 21**, **Gradle Kotlin DSL**, **JUnit 5** tests, and H2 in-memory database. It is structured as a **monorepo**.

## Project Structure

```
design-pattern-tutorial/
│── settings.gradle.kts
│── build.gradle.kts
│
├── customer-service/
│   ├── build.gradle.kts
│   └── src/main/java/com/design/pattern/customer/... 
│
└── order-service/
    ├── build.gradle.kts
    └── src/main/java/com/design/pattern/order/...
```

## Modules

* **customer-service**: CRUD operations for Customer entity
* **order-service**: CRUD operations for Order entity

Both modules have:

* REST controllers
* Spring Data JPA repositories
* H2 in-memory DB
* JUnit 5 unit tests

## Requirements

* Java 21
* Gradle 8+
* (Optional) IDE with Gradle support

## Build & Run

### Build all modules

```bash
./gradlew clean build
```

### Run individual modules

```bash
# Customer Service
./gradlew :customer-service:bootRun

# Order Service
./gradlew :order-service:bootRun
```

### Run Tests

```bash
./gradlew test
```

## Endpoints

### Customer Service

* `GET /customers` → List all customers
* `POST /customers` → Create a customer

### Order Service

* `GET /orders` → List all orders
* `POST /orders` → Create an order

## Notes

* All data is stored in **H2 in-memory database**, so it resets on restart.
* Designed as a **monorepo** with each module independent.
* Enforces **Java 21** using Gradle toolchain.
