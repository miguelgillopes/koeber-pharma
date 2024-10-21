# Koerber Pharma API

This project is a WIP RESTful API built with Spring Boot for managing patient records and consultations.. 
- It includes endpoints for retrieving patient data with pagination, sorting. 
- The API interacts with an H2 in-memory database for easy testing and prototyping.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)

## Features

- **Patient Management:** Retrieve a paginated list of patients with sorting filters.
- **In-memory Database:** Uses H2 for easy setup and testing.
- **OpenAPI Documentation:** Exposes the API documentation via OpenAPI.

## Technologies Used

- **Java 21**
- **Spring Boot:** Web, Data JPA, H2 Database
- **Hibernate:** ORM for interacting with the database
- **Mockito:** For mocking service calls during testing
- **Maven:** For dependency management and project build
- **H2 Database:** In-memory database for testing

## Installation

### Prerequisites

- **Java 17** or later
- **Maven** (if building from source)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/miguelgillopes/koeber-pharma.git

2. Build the project:
   ```bash
   mvn clean install

3. Run the application:
    ```bash
   mvn spring-boot:run

The API will be available at http://localhost:9090/pharma.


## Database Schema

The project uses an in-memory H2 database. The schema includes a Patient table with the following fields:

- id, name and age

You can access the H2 database console at http://localhost:9090/h2-console using the following credentials:

- **JDBC URL:** jdbc:h2:mem:pharmadb
- **Username:** sa
- **Password:** password

## API Endpoints

### Get All Patients
   - **URL:** /pharma/patients
   - **Method:** GET
   - **Description:** Retrieves a paginated list of patients with optional sorting by age or name.
   - **Parameters:** 

| Name     | Type | Description                             | Default |
|----------|------|-----------------------------------------|---------|
| page     | int  | The page number                         | 0       |
| size     | int  | The number of records per page         | 10      |

#### Response Example
```bash
{
    "content": [
        {
            "id": 1,
            "name": "John Doe",
            "age": 32
        },
        {
            "id": 2,
            "name": "Jane Smith",
            "age": 53
        }
    ],
    "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalPages": 1,
    "totalElements": 2
    }
}
```

## Testing

This project includes unit tests for the controller layer.

### Running Tests
You can run all the tests using Maven:

```bash
    mvn test
 ```

### Test Coverage
**Controller Tests:** Tests the API endpoints for patient retrieval, pagination, sorting, and error handling.
