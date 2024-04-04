# Blogging Application - Backend

## Introduction
This project serves as the backend for a blogging application, built with Spring Boot, leveraging Java 17, and managed with Maven within Spring Tool Suite (STS). It incorporates Spring Security with JWT for secure authentication and authorization, utilizes Spring Data JPA (Hibernate) alongside MySQL for efficient database management, provides well-designed and tested APIs for various components using Postman, and ensures comprehensive API documentation with Swagger, all the while securing APIs with Spring Security.

## Features
- **Spring Security with JWT:** Secure authentication and authorization.
- **Spring Data JPA (Hibernate) with MySQL:** Efficient database management.
- **API Design and Testing:** Reliable API integration and functionality testing using Postman.
- **API Documentation with Swagger:** Easy-to-use API documentation.
- **Spring Security:** Enhanced security for API endpoints.

## Prerequisites
Before you proceed, make sure you have the following installed:
- Java 17
- Maven
- MySQL
- Spring Tool Suite (STS) or any IDE with support for Spring Boot.

## Setup and Installation
1. **Clone the Repository**

git clone <repository-url>

2. **Configure MySQL**
Configure your MySQL database as per the `application.properties` file in the project.

3. **Build the Project**
Navigate to the project directory and build the project with Maven:

mvn clean install

4. **Run the Application**
Start the application with Maven:

mvn spring-boot:run

Or, run the application directly within STS or your IDE of choice.

## Usage
Access the API endpoints through the Swagger documentation at `/swagger-ui.html` after starting the application.

## Security
REST API endpoints are secured with JWT authentication via Spring Security. A valid JWT token is required to access protected resources.

## Contributing
We welcome contributions. To contribute:
1. Fork the repository.
2. Clone your fork to your local machine.
3. Create a new branch for your changes.
4. Make changes, commit, and push them to your branch.
5. Submit a pull request to the main repository.

## License
[Specify License] - This project is released under [License Name]. Please see the LICENSE file for more details.

## Contact
For support, feedback, or more information, contact us at [vaishnavisalaskar0@gmail.com].

