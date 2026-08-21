<div style="font-family: 'Courier New', monospace;">

# E-Commerce Microservices

A production-oriented e-commerce backend built with **Java 21, Spring Boot, Spring Cloud, PostgreSQL, Kafka, Keycloak, Docker, and GitHub Actions**.

The project follows a microservices architecture where each business capability is independently developed, persisted, secured, and containerized.

## Architecture

```text
                         ┌──────────────┐
                         │   Keycloak   │
                         │     :8080    │
                         └──────┬───────┘
                                │ JWT
                                ▼
                         ┌──────────────┐
                         │ API Gateway  │
                         │     :8090    │
                         └──────┬───────┘
                                │
                         ┌──────▼───────┐
                         │    Eureka    │
                         │     :8761    │
                         └──────┬───────┘
                                │
          ┌─────────────────────┼──────────────────────┐
          │                     │                      │
          ▼                     ▼                      ▼
 ┌────────────────┐   ┌────────────────┐    ┌────────────────┐
 │ Catalog Service│   │Customer Service│    │ Order Service  │
 │     :8081      │   │     :8082      │    │     :8083      │
 └───────┬────────┘   └───────┬────────┘    └───────┬────────┘
         │                     │                     │
         ▼                     ▼                     ▼
   PostgreSQL            PostgreSQL             PostgreSQL

                         ┌──────────────┐
                         │    Kafka     │
                         │    :9092     │
                         └──────┬───────┘
                                │
                   ┌────────────┴────────────┐
                   ▼                         ▼
          ┌────────────────┐        ┌──────────────────┐
          │Payment Service │        │Notification      │
          │     :8084      │        │Service :8086     │
          └───────┬────────┘        └──────────────────┘
                  │
                  ▼
             PostgreSQL
```

## Services

| Service | Port | Responsibility |
|---|---:|---|
| Discovery Server | 8761 | Service discovery with Eureka |
| API Gateway | 8090 | Single entry point and routing |
| Catalog Service | 8081 | Products and categories |
| Customer Service | 8082 | Customers and addresses |
| Order Service | 8083 | Order management |
| Payment Service | 8084 | Payment processing |
| Notification Service | 8086 | Kafka-based notifications |
| Keycloak | 8080 | Authentication and authorization |
| Kafka | 9092 | Event-driven communication |

## Technology Stack

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- OAuth2 Resource Server
- JWT
- MapStruct
- Bean Validation

### Microservices
- Spring Cloud Gateway
- Netflix Eureka
- REST APIs
- Service discovery
- Inter-service communication

### Data
- PostgreSQL
- One PostgreSQL database per service

### Messaging
- Apache Kafka
- Event-driven communication
- Order confirmation events
- Payment completed events

### Security
- Keycloak
- OAuth2
- JWT
- Role-based authorization
- `CUSTOMER` / `ADMIN` roles

### DevOps
- Docker
- Docker Compose
- GitHub Actions
- Continuous Integration

## Project Structure

```text
ecommerce-microservices/
│
├── api-gateway/
├── catalog-service/
├── customer-service/
├── discovery-server/
├── order-service/
├── payment-service/
├── notification-service/
│
├── keycloak/
│   └── realm-export.json
│
├── docker-compose.yml
│
└── .github/
    └── workflows/
        └── ci.yml
```

## Main Features

### Catalog

Provides APIs for:

```text
Products
Categories
Pagination
Dynamic filtering
Swagger / OpenAPI
```

### Customer

Provides:

```text
Customer management
Address management
PostgreSQL persistence
JWT-secured endpoints
```

### Order

Provides:

```text
Create order
Find orders
Find order by ID
Update order status
Delete order
Customer validation
Catalog/product validation
Order confirmation event
```

### Payment

Provides payment processing and publishes:

```text
payment-completed
```

to Kafka.

### Notification

Consumes Kafka events such as:

```text
order-confirmation
payment-completed
```

and processes notification messages.

## Event-Driven Communication

The application uses Kafka to decouple asynchronous operations.

### Order confirmation

```text
Order Service
     │
     │ publish
     ▼
Kafka
     │
     │ consume
     ▼
Notification Service
```

### Payment completion

```text
Payment Service
     │
     │ payment-completed
     ▼
Kafka
     │
     ▼
Notification Service
```

This prevents the Order and Payment services from being tightly coupled to notification processing.

## Security Flow

Authentication is handled by Keycloak.

```text
Client
   │
   │ Login
   ▼
Keycloak
   │
   │ JWT
   ▼
API Gateway
   │
   │ Validate JWT
   ▼
Microservice
```

The JWT contains Keycloak roles which are mapped to Spring Security authorities.

Example:

```text
CUSTOMER
ADMIN
```

## API Gateway Routes

The Gateway exposes the microservices through a single entry point:

```text
/api/products/**       → catalog-service
/api/categories/**     → catalog-service

/api/customers/**      → customer-service

/api/orders/**         → order-service

/api/payments/**       → payment-service

/api/notifications/** → notification-service
```

Clients therefore communicate with the Gateway instead of directly addressing individual services.

## Dockerization

The complete application can run through a single Docker Compose configuration.

Infrastructure includes:

```text
Eureka
Keycloak
Kafka
PostgreSQL
```

and the application containers include:

```text
API Gateway
Catalog Service
Customer Service
Order Service
Payment Service
Notification Service
Discovery Server
```

All application containers communicate through the shared:

```text
ecommerce-network
```

Inside Docker, services use container names instead of `localhost`.

For example:

```text
Kafka:
kafka:29092

Eureka:
eureka-server:8761

Customer PostgreSQL:
customer-postgres:5432

Order PostgreSQL:
order-postgres:5432
```

## Running the Application

### Prerequisites

Install:

```text
Docker
Docker Compose
Git
```

Java and Maven are useful for local development, but the complete application is containerized.

### Start the complete stack

From the project root:

```bash
docker compose up -d --build
```

Check containers:

```bash
docker compose ps
```

Stop the application:

```bash
docker compose down
```

View logs:

```bash
docker compose logs -f
```

View a specific service:

```bash
docker compose logs -f order-service
```

## Service Discovery

Eureka Dashboard:

```text
http://localhost:8761
```

The following services should register:

```text
API-GATEWAY
CATALOG-SERVICE
CUSTOMER-SERVICE
ORDER-SERVICE
PAYMENT-SERVICE
NOTIFICATION-SERVICE
```

## Keycloak

Keycloak:

```text
http://localhost:8080
```

Realm:

```text
ecommerce
```

The project uses Keycloak as the OAuth2/OpenID Connect identity provider.

## API Gateway

Gateway:

```text
http://localhost:8090
```

Example:

```bash
curl -i \
  -H "Authorization: Bearer YOUR_TOKEN" \
  http://localhost:8090/api/customers
```

Create an order through the Gateway:

```bash
curl -i -X POST \
  http://localhost:8090/api/orders \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "CUSTOMER_ID",
    "orderLineRequests": [
      {
        "productId": "PRODUCT_ID",
        "quantity": 2
      }
    ]
  }'
```

## Swagger / OpenAPI

Each Spring Boot service exposes its OpenAPI documentation through Springdoc.

Example:

```text
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
http://localhost:8083/swagger-ui/index.html
http://localhost:8084/swagger-ui/index.html
```

## CI with GitHub Actions

The project includes:

```text
.github/workflows/ci.yml
```

The CI pipeline:

```text
Git Push
   │
   ▼
GitHub Actions
   │
   ├── Checkout
   ├── Setup Java 21
   ├── Build services with Maven
   └── Build Docker Compose images
```

The workflow validates that the services can be packaged and that their Docker images can be built successfully.

## Database Architecture

Each microservice owns its own PostgreSQL database.

```text
Catalog Service
      │
      ▼
catalog_db

Customer Service
      │
      ▼
customer_db

Order Service
      │
      ▼
order_db

Payment Service
      │
      ▼
payment_db
```

This follows the **database-per-service** principle and prevents direct database sharing between business domains.

## Architectural Principles

The project applies:

- Microservices architecture
- Database per service
- API Gateway pattern
- Service discovery
- Event-driven architecture
- Asynchronous communication
- OAuth2 / JWT security
- Containerization
- Continuous Integration
- Separation of business responsibilities

## Current Scope

The project intentionally does **not** include:

```text
Kubernetes
AWS deployment
Zipkin / distributed tracing
```

The deployment target for this version is:

```text
Docker Compose
+
GitHub Actions
```

## Development Status

```text
Catalog Service             ✓
Customer Service            ✓
Order Service               ✓
Payment Service             ✓
Notification Service        ✓

PostgreSQL                  ✓
Kafka                       ✓
Keycloak                    ✓
Eureka                      ✓
API Gateway                 ✓

Docker Compose              ✓
GitHub Actions CI           ✓
```

## License

This project is intended as a learning and portfolio project.

</div>
