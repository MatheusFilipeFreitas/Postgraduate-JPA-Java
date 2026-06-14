# JPA UNIPDS

REST API for managing conferences, sessions, users, and session subscriptions. Built with Spring Boot, JPA/Hibernate, PostgreSQL, OpenAPI, and HATEOAS.

## Tech stack

- Java 25
- Spring Boot 4.1
- Spring Data JPA
- PostgreSQL 17
- Spring HATEOAS
- springdoc-openapi (Swagger UI)
- Docker & Docker Compose

## Features

- CRUD endpoints for users, conferences, sessions, and subscriptions
- Layered architecture: controller, service, mapper, repository, entity, and DTO
- Service and mapper interfaces with implementation classes
- HATEOAS links in API responses
- OpenAPI documentation with Swagger UI
- Pagination and sorting support
- Dockerized app and database

## Getting started

### Prerequisites

- Docker and Docker Compose

For local development without Docker:

- Java 25
- Maven 3.9+
- PostgreSQL 17

### Run with Docker

```bash
docker compose up --build
```

The API will be available at:

- Base URL: [http://localhost:8080/api](http://localhost:8080/api)
- Swagger UI: [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)
- OpenAPI JSON: [http://localhost:8080/api/v3/api-docs](http://localhost:8080/api/v3/api-docs)

### Run locally

1. Start PostgreSQL and create the database `jpa_unipds`.
2. Update credentials in `src/main/resources/application.properties` if needed.
3. Run the application:

```bash
./mvnw spring-boot:run
```

## API overview

| Resource | Base path |
|----------|-----------|
| Users | `/api/users` |
| Conferences | `/api/conferences` |
| Sessions | `/api/sessions` |
| Subscriptions | `/api/subscriptions` |

### Examples

```bash
# List users
curl http://localhost:8080/api/users

# Get user by id
curl http://localhost:8080/api/users/1

# Create user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane.doe@example.com"}'
```

Paginated list endpoints accept `page`, `size`, and `sort` query parameters:

```bash
curl "http://localhost:8080/api/users?page=0&size=20&sort=id,asc"
```

HATEOAS paginated responses return data under `_embedded`, for example `_embedded.userDtoList`.

## Project structure

```text
src/main/java/com/mathffreitas/jpaunipds/
├── config/          # OpenAPI configuration
├── controller/      # REST controllers
├── exceptions/      # Exception handling
├── hateoas/         # HATEOAS resource assemblers
├── mapper/          # DTO <-> entity mappers
├── model/
│   ├── dto/         # Request/response DTOs
│   └── entity/      # JPA entities
├── repository/      # Spring Data repositories
├── service/         # Business logic
└── util/            # Shared utilities
```

## Configuration

Main settings are in `src/main/resources/application.properties`:

- Context path: `/api`
- Database: PostgreSQL
- JPA DDL mode: `update`
- Swagger UI enabled at `/api/swagger-ui.html`

## Development

Build the project:

```bash
./mvnw clean package
```

Run tests:

```bash
./mvnw test
```

Stop Docker containers:

```bash
docker compose down
```
