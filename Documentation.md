# Bloomberg FX Deals API Documentation

Bloomberg FX Deals API facilitates the management of Foreign Exchange (FX) deals through a robust and scalable RESTful interface. This documentation covers the `/api/deals` endpoint, outlining its functionality, input structure, validation, error handling, logging strategy, testing methodologies, and Docker deployment.

## API Endpoint

### Endpoint
[POST] http://localhost:8081/api/deals

### Request JSON Example
```json
{
    "id": 123456789,
    "fromCurrencyIsoCode": "USD",
    "toCurrencyIsoCode": "EUR",
    "timestamp": "2024-06-12T12:00:00",
    "amount": 10000.50
}
```
## Request Validation

The endpoint performs validation using Jakarta Validation annotations to ensure data integrity and correctness.

### Validation Rules

- **id:** Must be a non-null and non-empty numeric identifier.
- **fromCurrencyIsoCode, toCurrencyIsoCode:** Must be non-null, non-empty strings between 3 and 255 characters.
- **timestamp:** Must be a valid ISO 8601 date-time format.
- **amount:** Must be a positive numeric value.

Any invalid input triggers a structured error response handled by the `GlobalExceptionHandler`, returning HTTP status `BAD_REQUEST`.

## Database Interaction

The application interacts with MySQL database to manage FX deals:

### Insertion

- If the FX deal with the specified `id` does not exist:
    - The deal is inserted into the `bloomberg` database.
    - The inserted deal is returned as the response.

### Conflict Handling

- If an FX deal with the same `id` already exists:
    - An exception is thrown due to the uniqueness constraint.
    - The exception is caught and handled by the `GlobalExceptionHandler`, responding with HTTP status `BAD_REQUEST`.

## Aspect-Oriented Programming (AOP) Logging

AspectJ is employed for AOP logging to capture method executions within the service layer. This includes:

- Logging method entry and parameters.
- Logging successful method execution.
- Logging exceptions thrown during method execution.

Logs are formatted with ANSI colors for readability.

## Testing

### JUnit 5

- Unit tests are implemented using JUnit 5 for comprehensive coverage.
- Mockito is utilized for mocking dependencies to isolate components under test.

### Integration Tests

- Integration tests validate end-to-end behavior of the API using in-memory database configurations.

## Dockerization

The application is Dockerized using a multi-stage Dockerfile to streamline deployment and optimize runtime efficiency.

### Docker Compose

Docker Compose orchestrates the deployment of services:

- **Spring Boot Application:** Exposed on [PORT 8080].
- **MySQL Database:** Configured for data persistence.

## Running the Application

### Build and Run Docker Compose

```bash
docker-compose up --build
