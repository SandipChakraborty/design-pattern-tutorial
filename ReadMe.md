# Design Pattern Tutorial Monorepo

## TODO: Update this

This project is a **multi-module Spring Boot 3 project** demonstrating basic CRUD operations with **Java 21**, **Gradle Kotlin DSL**, **JUnit 5** tests, and H2 in-memory database. It is structured as a **monorepo**.

## Project Structure

```
design-pattern-tutorial/
│── settings.gradle.kts
│── build.gradle.kts
│
├── abstract-factory-pattern/
│   ├── build.gradle.kts
│   └── src/main/java/com/design/pattern/abs/factory/...
```

## Modules

* **abstract-factory-pattern**: CRUD operations for Books entity

Modules have:

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
# abstract-factory-pattern Service
./gradlew :abstract-factory-pattern:bootRun
```

### Run Tests

```bash
./gradlew test
```

## Notes

* All data is stored in **H2 in-memory database**, so it resets on restart.
* Designed as a **monorepo** with each module independent.
* Enforces **Java 21** using Gradle toolchain.
