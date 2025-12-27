**FakeStoreAPI – REST Assured API Automation Framework**
**Project Overview:**
This project demonstrates a real-time REST Assured API automation framework built from scratch using the FakeStoreAPI public APIs. The goal of this project was to convert manual API test cases into a scalable automation framework, following industry-standard SDET best practices.
API Documentation: https://fakestoreapi.com/docs
This project was developed as a self-paced learning initiative to gain hands-on, real-world experience in API automation, framework design, and test strategy—similar to what is expected in enterprise projects.

**Project Objectives:**
Convert manual API test cases (validated in Postman) into automated REST Assured tests
Build a maintainable, reusable, and scalable API automation framework
Follow real-time SDET framework standards
Implement best practices used in IT/enterprise projects
Make the project interview-ready and GitHub-review-friendly

**End-to-End API Flow:**
Authentication Login API → Login & generate token
Add New Product API (Auth required)
Get Single Product API (using generated product ID)
Get All Products API
Update Product API (specific product ID)
Delete Product API (specific product ID)
This flow simulates a real production API lifecycle, not just isolated API calls.

**Tech Stack & Tools**
Tool	Purpose
Java	Programming language
REST Assured	API automation
TestNG	Test execution & assertions
Maven	Build & dependency management
IntelliJ IDEA	IDE
Postman	Manual API validation
Extent Reports	HTML test reporting
Git & GitHub	Version control
Dependencies Used
REST Assured – 5.5.5
TestNG – 7.10.0
Jackson Databind – 2.20.0 (POJO serialization/deserialization)
JSON Schema Validator - 5.5.5
Apache Commons IO
Simple JSON -1.1.1
Extent Reports - 5.1.1

**Framework Design (Real-Time Standard)**
The framework follows clean architecture and separation of concerns:
FakeStoreAPI_Testing
│
├── .idea/                         IntelliJ IDE configuration files
├── .mvn/                          Maven wrapper files
├── Reports/                       Extent HTML reports generated after execution
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── constant/           Constants & enums
│   │   │   │   ├── Endpoints.java
│   │   │   │
│   │   │   ├── core/               Base classes (framework core)
│   │   │   │   ├── BaseTest_Extent.java
│   │   │   │   └── Statuscode_Repo.java
│   │   │   │
│   │   │   ├── helper/             Helper & reusable methods
│   │   │   │   ├── BasetestHelper.java
│   │   │   │
│   │   │   ├── pojo/               POJO classes for request bodies
│   │   │   │   ├── Authentication_Login_API.java
│   │   │   │   └── AddNewProduct_API.java
│   │   │   │   └── Updateproduct_API.java
│   │   │   │
│   │   │   └── utils/              Utilities
│   │   │       ├── ConfigReader.java
│   │   │       ├── ExtentReport.java
│   │   │       └── FailedRetryAnalyzerReader.java
│   │   │       └── JsonReader.java
│   │   │       └── RetryTransformerReader.java
│   │   │       └── SoftAssertionsReader.java
│   │   │
│   │   └── resources
│   │
│   └── test
│       ├── java
│       │   ├── Auth/               Authentication related tests
│       │   │   └── LoginApiTest.java
│       │   │
│       │   ├── CommonAPI/           # Common/shared API flows
│       │   │   └── Api_AutomationScripts.java
│       │   │   └── AutomateRequestBody_withThreeWays.java
│       │   │
│       │   └── Products/            # Product CRUD APIs
│       │       ├── AddNewProductApiTest.java
│       │       ├── DeleteProductApiTest.java
│       │       ├── Get_allProductsApiTest.java
│       │       └── GetSingleProductApiTest.java
│       │       └── Update_productApiTest.java
│       │
│       └── resources
│           ├── config/              # Environment configuration
│           │   └── Config_dev.properties
│           │   └── Config_prod.properties
│           │   └── Config_qa.properties
│           │   └── Config_stagging.properties
│           │
│           ├── payloads/            # External JSON request bodies
│           │   ├── Authentication_Login_API_testdata.json
│           │   ├── AddNewProduct_API_testdata.json
│           │   └── UpdateProduct_API_testdata.json
│           │
│           ├── schema/              # JSON Schema validation files
│           │   └── AddNewProductApiTest.json
│           │   └── LoginApiTest.json
│           │   └── UpdateproductApiTest.json
│           │
│           └── testsuites/          # TestNG suite XMLs
│               ├── E2Etestsuite.xml
│               └── Regressiontestsuite.xml
│               └── Sanitytestsuite.xml
│               └── Smoketestsuite.xml
│
├── extent-config.xml               # Extent Report configuration
├── target/                         # Maven build output
├── .gitignore                      # Git ignored files
└── pom.xml                         # Maven dependencies & plugins


**Request Body Automation (3 Real-Time Approaches)**
1.String-Based JSON (Basic)
Used for quick validations.
2.External JSON Files (Industry Standard)
Test data stored in src/test/resources/payload_testdata
Improves maintainability & reusability
Code optimized using reusable FileInputStream utility
3.POJO Classes (Best Practice)
Java objects mapped to JSON
Uses Jackson serialization
Enables dynamic test data handling


**Configuration Management**
Environment-specific values stored in .properties files
Centralized ConfigReader utility
No hard-coded URLs in test scripts

**Retry Mechanism (Enterprise Level)**
Implemented framework-level retry using:
ITestListener
AnnotationTransformer
Enabled once in testng.xml, applied to all tests automatically
Why Retry?
Temporary server failures (502 / 503 / 504)
Network instability
Token expiry issues
Note: Retry is used only for flaky failures, not to hide real bugs.

**Assertions Strategy**
Soft Assertions utility implemented
Reduces repetitive assertion code
Allows multiple validations in a single test
Improves readability & debugging

**JSON Schema Validation**
Validates response structure against schema
Ensures API contract consistency
Used for POST / PUT - APIs

**Reporting**
Integrated Extent HTML Reports
Includes:
Test steps
Request & response logging
Pass/Fail status
Supports serialization & deserialization

**Test Coverage**
**Authentication API**
Valid credentials → 201 Created
Empty payload → 400 Bad Request
Invalid credentials → 401 Un-authorized
Invalid username / password combinations
**Add Product API**
Valid request → 201 Created
Empty request body → 400 Bad Request
Invalid key-value pairs → API behaviour validated

**CI/CD & Version Control**
Project pushed from local to GitHub
Maven-based execution ready for Jenkins CI
Designed for easy pipeline integration

**What I Learned from This Project**
How real API automation frameworks are built in IT companies
How to convert manual test cases into automation logically
Framework-level retry handling
Data-driven API testing
Clean code & reusability principles
Interview-ready explanation of API automation concepts


This project demonstrates my hands-on experience in building a real-time REST Assured API automation framework from scratch, covering authentication, CRUD operations, data-driven testing, retry mechanisms,
schema validation, and reporting—following enterprise SDET best practices.

Author: Dipti Ranjan Dash 
