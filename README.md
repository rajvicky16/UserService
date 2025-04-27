# User Service

## Description
This project is a **User Service** for an e-commerce platform. It is built using **Spring Boot** and integrates with a **SQL database**. The service includes user authentication and authorization features, leveraging **Spring Security** and **OAuth2 Authorization Server**.

## Features
- User authentication and authorization using OAuth2.
- Custom `UserDetailsService` for loading user-specific data.
- JPA-based persistence for users, roles, and authorization data.
- Token management for OAuth2 flows.
- Modular and extensible architecture.

## Prerequisites
- **Java 17** or higher
- **Maven 3.8** or higher
- A **SQL database** (e.g., MySQL)

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/rajvicky16/userservice.git
cd your-project-directory
```

### Build the Project
Run the following command to build the project:
```bash
mvn clean install
```

### Configuration
Update the application.properties or application.yml file in the src/main/resources directory to configure database connection details and other application settings.  
Example:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
### Key Components

#### Security Services
- `CustomUserDetailsService:` Implements UserDetailsService to load user details from the database.
- `JpaOAuth2AuthorizationService:` Manages OAuth2 authorization data using JPA.
- `JpaOAuth2AuthorizationConsentService:` Handles OAuth2 authorization consent persistence.

#### Models
- `User:` Represents a user in the system.
- `Role:` Represents user roles.
- `Authorization:` Stores OAuth2 authorization data.

#### Repositories
- `UserRepository:` For user data persistence.
- `AuthorizationRepository:` For OAuth2 authorization data.
- `AuthorizationConsentRepository:` For OAuth2 consent data.

#### Database
Ensure your SQL database is running and properly configured. You may need to create the required schema and tables before running the application. 

### Run the Application
Use the following command to start the application:
```bash
mvn spring-boot:run
```
