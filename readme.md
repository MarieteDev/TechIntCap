# TechIntCap - Basic Application Startup Guide

## Prerequisites

Ensure you have the following installed on your system:
- Java 21
- Maven

## Installation

Clone the repository and navigate to the project directory:
```sh
git clone https://github.com/MarieteDev/TechIntCap.git
cd techintcap
```

Install dependencies and build the project:
```sh
mvn clean install
```

## Running the Application

To start the microservice, run:
```sh
mvn spring-boot:run
```

## API Documentation

Once the application is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```

## Testing the API

You can test the API directly in Swagger or via `curl`. Example request:

```sh
curl --request GET --url 'http://localhost:8080/v1/products?size=7&page=1&direction=desc&category=Electronics&sortBy=price'
```

## Database Initialization

The application uses **Flyway** to manage database migrations. It automatically loads the schema and initial data using an embedded **H2 database** when running locally.

## Explanation of Architectural Decisions

This project follows a Hexagonal Architecture, dividing responsibilities into separate layers:

Controller Layer: Handles HTTP requests and responses. Each controller has its own DTOs.

Use Case Layer: Implements business logic and uses domain objects.

Facade Layer: Acts as an intermediary between the use case layer and external data sources. It returns domain objects but may internally call repositories for database access or Feign clients for external services.

This approach ensures a clean separation of concerns, making the system more maintainable and testable.