# Student & Fee Management System

A Spring Boot backend project consisting of **two services**:

1. **Student Service** – Manages student details
2. **Fee Service** – Manages fee collection and generates Skiply-style PDF receipts

This document explains how to **run, deploy, and test both services from GitHub**, including **Swagger documentation** and **Postman collections**.

---

## Services Overview

### Student Service
- Create student records
- Fetch student details
- REST APIs documented with Swagger

### Fee Service
- Collect student fees
- Fetch receipts by student ID
- Generate professional PDF receipts
- REST APIs documented with Swagger

---

## Technology Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / MySQL
- iText (Lowagie) PDF
- Swagger (OpenAPI 3)
- Maven
- JUnit 5 & Mockito
- GitHub Actions

---

## Repository Structure

```
student-fee-management
│
├── student-service
│   ├── src/main/java/com/example/student
│   ├── src/test/java/com/example/student
│   └── pom.xml
│
├── fee-service
│   ├── src/main/java/com/example/fee
│   ├── src/test/java/com/example/fee
│   └── pom.xml
│
├── postman
│   ├── Student-Service.postman_collection.json
│   └── Fee-Service.postman_collection.json
│
├── README.md
└── .github/workflows/maven.yml
```

---

## Prerequisites

Ensure the following are installed:
- Java 17 or higher
- Maven 3.8+
- Git
- IDE (IntelliJ / Eclipse recommended)

---

## Running the Services Locally

### Clone the Repository

```bash
git clone https://github.com/your-username/student-fee-management.git
cd student-fee-management
```

---

### Run Student Service

```bash
cd student-service
mvn clean spring-boot:run
```

**Available URLs**
- Application: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI Docs: http://localhost:8080/v3/api-docs
- H2 Console: http://localhost:8080/h2-console

---

### Run Fee Service

Open a new terminal window:

```bash
cd fee-service
mvn clean spring-boot:run
```

**Available URLs**
- Application: http://localhost:8081
- Swagger UI: http://localhost:8081/swagger-ui/index.html
- OpenAPI Docs: http://localhost:8081/v3/api-docs
- H2 Console: http://localhost:8081/h2-console

---

## Swagger API Documentation

Swagger provides interactive API documentation.

- Student Service: http://localhost:8080/swagger-ui/index.html
- Fee Service: http://localhost:8081/swagger-ui/index.html

Swagger can be used to:
- Explore all APIs
- Test endpoints
- Validate request and response models

---

## Postman Collection

### Import Instructions

1. Open Postman
2. Click **Import**
3. Select files from the `postman` folder:
   - Student-Service.postman_collection.json
   - Fee-Service.postman_collection.json

These collections contain preconfigured requests for all APIs.

---

## Sample API Requests

### Add Student

```
POST /students
```

```json
{
  "studentName": "Farhan",
  "grade": "5",
  "mobileNumber": "9876543210",
  "schoolName": "ABC School"
}
```

---

### Collect Fee

```
POST /fees
```

```json
{
  "studentId": 1,
  "studentName": "Farhan",
  "amount": 2000,
  "paymentDate": "2026-02-08"
}
```

---

### Generate PDF Receipt

```
GET /fees/receipt/pdf/{receiptId}
```

---

## Running Tests

### Student Service Tests

```bash
cd student-service
mvn test
```

### Fee Service Tests

```bash
cd fee-service
mvn test
```

All tests are unit tests using Mockito and do not require a database.

---

## Deployment Using GitHub

### Option 1: GitHub Codespaces

1. Open the repository on GitHub
2. Click **Code → Codespaces → Create Codespace**
3. Run services using Maven commands

---

### Option 2: GitHub Actions (CI Pipeline)

Create the following file:

```
.github/workflows/maven.yml
```

```yaml
name: Java CI

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - run: mvn clean test
```

This pipeline builds and tests both services automatically on every push.

---

## Configuration

Edit `application.properties` as needed.

Example:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## Author

**Farhan**  
Backend Developer – Java & Spring Boot

---

## Future Enhancements

- Docker & Docker Compose
- Cloud deployment (AWS / Railway / Render)
- Authentication & Authorization
- API Gateway

