<div align="center">

# 🚀 DOSW - Laboratory 6
## TDD, Test Coverage & Static Analysis
### Escuela Colombiana de Ingeniería Julio Garavito
#### Desarrollo y Operaciones de Software (DOSW)

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![JUnit5](https://img.shields.io/badge/JUnit-5-green?style=for-the-badge&logo=junit5)
![JaCoCo](https://img.shields.io/badge/JaCoCo-0.8.12-red?style=for-the-badge)
![SonarQube](https://img.shields.io/badge/SonarQube-26.3-blue?style=for-the-badge&logo=sonarqube)
![Maven](https://img.shields.io/badge/Maven-3.x-purple?style=for-the-badge&logo=apachemaven)

---

</div>

## 📋 Table of Contents

- [Objective](#-objective)
- [Team Members](#-team-members)
- [Project Structure](#-project-structure)
- [Unit Tests & TDD](#4--unit-tests--tdd)
- [Test Coverage](#5--test-coverage)
- [SonarQube Integration](#6--sonarqube-integration)

---

## 🎯 Objective

This laboratory applies **Test-Driven Development (TDD)** as the foundation for technically structuring software projects. It consists of two parts:

- **Part 1:** Library management system using TDD methodology.
- **Part 2:** Applying TDD to the first sprint of the main project.

The lab covers three key pillars:

| Pillar | Tool | Description |
|--------|------|-------------|
| 🧪 TDD | JUnit 5 | Write tests before implementation |
| 📊 Coverage | JaCoCo | Measure and enforce test coverage (≥85%) |
| 🔍 Static Analysis | SonarQube | Detect bugs, vulnerabilities and code smells |

---

## 👥 Team Members

<div align="center">

| # | Name | Role |
|---|------|------|
| 1 | Juan Pablo Vélez | Squad Member |
| 2 | Juan Daniel Bogotá | Squad Member |
| 3 | Kevin Segura Velandia | Squad Member |
| 4 | Cristian José González | Squad Member |
| 5 | Rafael Moreno | Squad Member |

</div>

---

## 📁 Project Structure
```
Library/
├── src/
│   ├── main/java/edu/eci/dosw/tdd/
│   │   └── library/
│   │       ├── Library.java
│   │       ├── book/
│   │       │   └── Book.java
│   │       ├── loan/
│   │       │   ├── Loan.java
│   │       │   └── LoanStatus.java
│   │       └── user/
│   │           └── User.java
│   └── test/java/edu/eci/dosw/tdd/
│       └── library/
│           └── LibraryTest.java
└── pom.xml
```

---

## 4- 🧪 Unit Tests & TDD

The TDD cycle followed for each method was:
```
1. 🔴 RED   → Write a failing test
2. 🟢 GREEN → Implement the minimum code to pass the test
3. 🔵 BLUE  → Refactor and clean up
```

The methods implemented using TDD were:
- `addBook()` — Adds a book to the library system
- `loanABook()` — Creates a loan for a user
- `returnLoan()` — Processes the return of a loan

---

### 👤 Juan Pablo Vélez

> Tests written for `addBook`, `loanABook` and `returnLoan` — basic happy path scenarios validating core functionality.

<div align="center">

**🔴 Tests failing before implementation**

![Juan Pablo Tests Failing](/images/testFailureJP.png)


![Juan Pablo Tests Passing](/images/testJP.png)

</div>

---

### 👤 Juan Daniel Bogotá

> Tests focused on quantity management — verifying book count increases when adding duplicates, decreases on loan, and increases on return.

<div align="center">

**🔴 Tests failing before implementation**

![Juan Daniel Tests Failing](/images/testFailureJB.png)


![Juan Daniel Tests Passing](/images/testJB.png)

</div>

---

### 👤 Cristian José González

> Tests covering edge cases — adding books to the library, creating loans when book and user exist, and handling non-existent loans on return.

<div align="center">


![Cristian Tests](/images/Cris.jpeg)

</div>

---

### 👤 Kevin Segura Velandia

> Tests focused on validation scenarios — returning true when adding a valid book, verifying ACTIVE loan status on creation, and checking return date is set on loan return.

<div align="center">

**🔴 Tests failing before implementation**

![Kevin Tests Failing](/images/pruebas1-kevinsegura.jpeg)


![Kevin Tests Passing](/images/pruebas2-kevinsegura.jpeg)

</div>

---

### ✅ All Tests Passing

After implementing all methods in `Library.java`, all **13 tests** pass successfully.

<div align="center">

![All Tests Passing](/images/PruebasOk.png)

</div>

---

## 5- 📊 Test Coverage

Coverage was measured using **JaCoCo 0.8.12**. The project was configured to enforce a minimum class coverage ratio of **85%** per package.

To generate the coverage report:
```bash
mvn clean test jacoco:report
```

The report is available at:
```
target/site/jacoco/index.html
```

### Coverage Summary

<div align="center">

![Test Coverage Report](/images/Covertura.png)

</div>

---

## 6- 🔍 SonarQube Integration

Static code analysis was performed using **SonarQube 26.3** running via Docker.

### Setup
```bash
# Pull SonarQube image
docker pull sonarqube

# Start SonarQube container
docker run -d --name sonarqube \
  -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true \
  -p 9000:9000 sonarqube:latest
```

### Running the Analysis
```powershell
mvn verify sonar:sonar "-Dsonar.token=YOUR_TOKEN_HERE" "-Dsonar.host.url=http://localhost:9000"
```

### Results

<div align="center">

![SonarQube Dashboard](/images/SonarQube.png)

![SonarQube Analysis Detail](/images/SonarQube2.png)

</div>

---

<div align="center">

**Escuela Colombiana de Ingeniería Julio Garavito — DOSW 2026**

</div>