# 🧪 OrangeHRM Selenium Automation Framework

## 📌 Project Overview

This project is a Selenium-based automation framework designed to test key functionalities of the OrangeHRM web application.

The framework follows the **Page Object Model (POM)** design pattern to ensure:

* Code reusability
* Maintainability
* Clear separation of test logic and UI interactions

---

## 🚀 Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Extent Reports

---

## 📂 Project Structure

```
OrangeHRM-Automation/
│
├── src/
│   ├── main/java/
│   │   ├── base/                 → Common reusable methods (waits, actions)
│   │   │   └── BasePage.java     → Parent class for all pages
│   │   │
│   │   ├── pages/                → Page Object classes (UI interactions)
│   │   │   ├── LoginPage.java        → Login functionality
│   │   │   ├── DashboardPage.java    → Navigation and dashboard actions
│   │   │   ├── AdminPage.java        → User management actions
│   │   │   ├── PimPage.java          → Employee management
│   │   │   └── LeavePage.java        → Leave module actions
│   │   │
│   │   └── utils/                → Utility/helper classes
│   │       ├── ConfigReader.java    → Reads config.properties
│   │       ├── ExcelUtil.java       → Handles test data from Excel
│   │       └── ExtentManager.java   → Manages Extent Reports
│   │
│   ├── test/java/
│   │   ├── base/                → Test setup and teardown
│   │   │   └── BaseTest.java    → WebDriver initialization
│   │   │
│   │   ├── listeners/           → TestNG listeners
│   │   │   ├── TestListener.java   → Reporting & screenshots
│   │   │   └── RetryListener.java  → Retry failed tests
│   │   │
│   │   ├── tests/               → Test classes
│   │   │   ├── LoginTest.java         → Login test scenarios
│   │   │   ├── DashboardTest.java     → Dashboard validation
│   │   │   ├── AdminTest.java         → User management tests
│   │   │   ├── PimTest.java           → Employee tests
│   │   │   ├── LeaveTest.java         → Leave module tests
│   │   │   └── FormValidationTest.java→ Validation scenarios
│   │   │
│   │   └── utils/               → Test-level utilities
│   │       └── RetryAnalyzer.java → Retry logic implementation
│
│   ├── test/resources/
│   │   ├── config.properties    → Application configuration
│   │   └── testdata/            → Test data files
│   │       └── testdata.xlsx    → Excel test data
│
├── reports/                     → Generated Extent Reports
├── screenshots/                 → Failed test screenshots
├── pom.xml                      → Maven dependencies & build config
└── testng.xml                   → TestNG suite configuration
```

---

## ⚙️ Configuration

```
baseUrl=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
browser=chrome
timeout=10
username=admin
password=admin123
testDataPath=src/test/resources/testdata/testdata.xlsx
```

---

## ▶️ How to Run Tests

### Using Maven (Recommended)

Run all tests:

```
mvn test
```

---

## 📊 Reporting

* Extent Reports are generated in:

```
/reports/ExtentReport.html
```

* Screenshots for failed tests are saved in:

```
/screenshots/
```

---

## ✅ Features Implemented

* Page Object Model (POM)
* Explicit Wait Handling
* Data-driven testing using Excel
* Retry mechanism for flaky tests
* Extent Reports integration
* Screenshot capture on failure

---

## 📌 Test Modules and Scope

### User Authentication

* Verify login with valid credentials
* Validate error message for invalid login
* Ensure logout redirects to login page
* Check validation for empty fields

---

### Employee Management

* Add an employee and verify it is listed
* Search employee by name and validate results
* Open employee profile and verify details
* Verify no results for invalid search

---

### Leave Management

* Navigate to Leave module and verify page load
* Apply leave and validate response
* Verify leave records in "My Leave"
* Validate errors for past or invalid dates

---

### User Role Management

* Create a user and assign role
* Verify user appears in admin list
* Delete user and confirm removal

---

### Form Validations

* Validate required field errors
* Verify invalid date handling
* Check dropdown selection functionality

---

## 📌 Conclusion

This framework is designed to be simple, scalable, and maintainable.
By implementing POM, reusable utilities, and reporting features, it provides a solid foundation for real-world test automation.

---
