# Spring Boot JWT Authentication and Authorization

This project demonstrates a secure backend API built with Spring Boot and Spring Security that uses JWT (JSON Web Token) for stateless authentication and authorization. It includes user registration, login, role-based access control, and custom exception handling.

## Requirements

- Java 17+ (or compatible version used in your setup)
- Spring Boot 3.x
- Maven or Gradle for dependency management
- PostgreSQL or any relational database for user persistence
- Modern IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
- Postman or any REST client for API testing

## Key Technologies

- Spring Boot
- Spring Security (with JWT integration)
- JWT (io.jsonwebtoken library)
- Lombok for boilerplate code reduction
- ModelMapper for DTO to Entity conversion
- Hibernate / JPA for database interaction
- BCryptPasswordEncoder for secure password hashing

## Features

### Authentication and Authorization

- User registration endpoint allowing new users to register with encoded passwords.
- User login endpoint that authenticates credentials and returns a JWT token.
- JWT token generation and validation with expiration handling.
- Stateless session management using JWT; no HTTP sessions.
- Role-based access restrictions to `/api/admin/**` endpoints for users with `ADMIN` role.
- Public access for registration and login endpoints.
- Custom JWT filter to validate tokens on every request and inject user authentication.
- Custom handlers for JWT expired, malformed token, unauthorized access, and access denied exceptions.

### Security Configurations

- Password encoding with BCrypt.
- Custom `UserDetailsService` loading user detail by user ID from JWT token.
- AuthenticationProvider configured to use custom `UserDetailsService`.
- SecurityFilterChain setup disabling CSRF, with stateless session policy.
- Exception handling with custom entry point and access denied handler returning consistent JSON responses.

### Project Structure

- `config/SecurityConfig.java` - Main security configuration with filter chain and authentication setup.
- `jwt/` - Contains JWT utility, filter, and authentication entry point.
- `service/CustomUserDetailService.java` - Loads user details by user ID for authentication.
- `model/CustomUserDetails.java` - Custom UserDetails implementation wrapping user entity.
- `controller/AuthController.java` - REST API for auth endpoints.
- `service/Impl/AuthServiceImpl.java` - Business logic for registering and logging in users.
- `payloads/` - DTOs and API response wrappers.
- `exception/` - Custom exceptions and handlers for JWT and access denied scenarios.

## How It Works

1. **Registration:** Users submit registration details. The password is encoded using BCrypt and saved.
2. **Login:** Users submit email and password. Password is verified, and a JWT token is generated with user ID as subject.
3. **JWT Token Usage:** Clients must send the JWT in the `Authorization` header with `Bearer ` prefix.
4. **Request Filtering:** `JwtFilter` intercepts requests, extracts and validates JWT, and sets authentication context.
5. **Authorization:** Calls to protected endpoints check user roles from the JWT-authenticated user.
6. **Error Handling:** Custom response messages are sent for token expiration, invalid tokens, access denied, and authentication failures.

## Usage

- Run the Spring Boot application.
- Use API endpoints:
  - `POST /api/auth/register` - Register a new user.
  - `POST /api/auth/login` - Login and receive a JWT token.
  - Other endpoints require Authorization header with the JWT token.
- Protect routes by roles, e.g., `/api/admin/**` requires `ADMIN` role.

## Environment Configuration

- Configure your database connection in `application.properties` or `application.yml`.
- Provide a base64-encoded JWT secret in `jwt.secret` property.
- Replace `your_base64_encoded_secret_here` with your actual base64-encoded JWT secret key, and adjust the database URL, username, and password as per your PostgreSQL setup.




## Notes

- Usernames are represented by user IDs internally for JWT and authentication.
- Passwords are securely stored using BCrypt hashing.
- The JWT token expires after 24 hours but can be adjusted in `JwtUtil`.
- Exception handling returns consistent JSON API responses for frontend integration.

---

This project serves as a comprehensive example illustrating JWT security implementation with Spring Boot, covering registration, login, token validation, role-based access control, and custom error handling.


