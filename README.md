# Specmatic Coding Test

### Prerequisites:
JDK 17+

### Instructions:

### 1. Clone this repository to your local machine.  
From a terminal, run the following command:
```bash
mvn clean test
```
You should see two failing tests:
```bash
[ERROR] Tests run: 2, Failures: 2, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```
Your objective is to get these two tests to pass by following the rest of the instructions.

### 2. Implement REST endpoints:
This is a Kotlin based Spring Boot application.  
You are expected to implement the following endpoints to the **Products** controller:

#### A. POST /products
Implement a **POST** route ```/products``` which accepts a json payload.

##### Request Schema:
```json
{
  "name": "(string)",
  "type": "(string enum)",
  "inventory": "(integer)"
}
```
The ```type``` field is an enum which can take the following values:
```yaml
  - gadget
  - book
  - food
  - other
```
On successful creation of a product entity, the ```id``` of the created product should be returned:
##### Response Schema:
```json
{
  "id": "(integer)"
}
```
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;

#### B. GET /products
Implement a **GET** route ```/products``` which accepts an **optional** query parameter called ```type```, which is an enum of the following values:
```yaml
  - gadget
  - book
  - food
  - other
```
and returns a list of products with following schema.
##### Response Schema:
```json
[
    {
      "id": "(integer)",
      "name": "(string)",
      "type": "(string enum)",
      "inventory": "(integer)"
    }
]
```

### Note:
- You are **not** expected to use a database.
  You are free to use any datastructures like maps, lists, etc to store and retrieve products.

- Writing any unit tests that you deem fit will be appreciated.

- Please do not alter the following files:
  - ContractTest.kt
  - products_api.yaml
  - specmatic.json

### 3. Once you have both the tests passing, commit your changes and push to this repository.




