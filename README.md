# Virtual Engine (Spring Boot)

This repository contains a minimal Spring Boot 3.x skeleton implementing:

- JWT-based stateless security (Spring Security + jjwt)
- WebSocket STOMP chat for live chat and notifications
- JPA entities and Postgres configuration
- Redis integration for real-time analytics counters
- Controllers for media signed-URL stub and Slido event ID
- Analytics REST endpoint with AOP tracking

Notes:

- Replace `jwt.secret` in `application.properties` with a strong 256-bit secret.
- Configure Postgres and Redis credentials as needed.
- SSL should be enabled in production: configure `server.ssl.*` with a valid keystore.

How to build:

1. mvn package
2. java -jar target/virtual-engine-0.0.1-SNAPSHOT.jar
