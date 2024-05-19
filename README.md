# Specmatic Coding Test

### Prerequisites:
JDK 17+

### Instructions:

### 1. Fork this repository on Github. Checkout your forked repo to your local machine.  
From a terminal, run the following command:
```bash
mvn clean test
```
You should see 38 failing tests:
```bash
[ERROR] Tests run: 38, Failures: 38, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```
Your objective is to get all the tests to pass by following the rest of the instructions.

### 2. Part 1 - Implement REST endpoints:
- This is a Kotlin based Spring Boot application.  
- You are expected to implement the missing endpoints in the **Products** controller.

### Note:
- You are **not** expected to use a database. Instead, use an in memory map to store and retrieve products.

- Please do not alter the following files:
  - ContractTest.kt
  - products_api.yaml
  - specmatic.json

### 3. Part 1 - Definition of Done
- All 38 tests are passing
- 100% API Coverage from 1 path is achieved
- Code changes are committed to your Github repo.

### 4. Part 2 - Added mandatory property 'cost' to the ProductDetails schema
- Update the products_api.yaml OpenAPI specification such that 'cost' is added as a new mandatory property to ProductDetails
- This will increase the test count to 50 and cause a few tests to fail
- Update your kotlin code to make all the tests pass again.

### 5. Part 2 - Definition of Done
- All 50 tests are passing
- 100% API Coverage is still maintained
- Code changes are committed to your Github repo.

### 6. Trigger the CI pipeline under Github Actions. 
- Make sure all tests are passing on the pipeline as well.
- Email the following details to coding.test@specmatic.in:
  - Screenshot of passing CI pipeline
  - Screenshot of API Coverage report
  - Link to you github repo
