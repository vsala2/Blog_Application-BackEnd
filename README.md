# Blogging Application - Backend

## Introduction
This project serves as the backend for a blogging application, built using layered architecture with Spring Boot, leveraging Java 17, and managed with Maven within Spring Tool Suite (STS). It incorporates Spring Security with JWT for secure authentication and authorization, utilizes Spring Data JPA (Hibernate) alongside MySQL for efficient database management, provides well-designed and tested APIs for various components using Postman, and ensures comprehensive API documentation with Swagger, all the while securing APIs with Spring Security.

## Features

- **Spring Security with JWT:** Implements Spring Security with JSON Web Token (JWT) for secure authentication and authorization, ensuring that both the login and registration processes are protected and reliable.

- **Login & Register API:** Provides API endpoints for user authentication, including login and registration functionalities, leveraging Spring Security to manage user credentials and sessions securely.

- **Pagination & Sorting:** Implements pagination and sorting mechanisms to efficiently manage and display data, improving the performance and usability of the application by handling large datasets effectively.

- **User Input Validation:** Incorporates user input validation to ensure that all data entering the system is correct and valid, thereby preventing invalid data operations and enhancing application security.

- **Exception Handling:** Implements comprehensive exception handling throughout the application to gracefully manage and respond to unexpected conditions, improving the reliability and stability of the application.

- **Role-Based Authentication:** Utilizes role-based security to enforce different access controls and permissions based on user roles, ensuring that users can only access the features and data appropriate to their role.

- **Spring Data JPA (Hibernate) with MySQL:** Utilizes Spring Data JPA (Hibernate) for efficient database management with MySQL, enabling complex database operations and interactions with reduced boilerplate code.

- **API Design and Testing:** APIs are designed and tested for various components using Postman, ensuring reliable integration and functionality.

- **API Documentation with Swagger:** Comprehensive API documentation is provided with Swagger, facilitating easy usage and integration.

- **Spring Security:** Enhanced security for API endpoints, incorporating techniques such as encryption, token management, and secure HTTP headers to protect against common vulnerabilities.


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

## Deployment

This application is deployed on AWS, leveraging Elastic Beanstalk for application deployment, EC2 instances for computing power, and RDS for database services, providing a robust and scalable cloud-based environment.

### Accessing the Application

The application is accessible via the following domain:

[Access Application](http://bloggingappication-env.eba-vxf6mqw4.us-east-2.elasticbeanstalk.com/swagger-ui/index.html).

Please replace `http://your-domain.com` with the actual domain linked to your deployed application.

### Deployment Guide

To deploy this application using AWS services, follow the steps below:

1. **Elastic Beanstalk:**
   - Create a new Elastic Beanstalk application and environment.
   - Upload the packaged application (as a .jar or .war file) to Elastic Beanstalk.

2. **EC2 Instance:**
   - The EC2 instance is managed by Elastic Beanstalk. However, if additional configuration is needed, you can access the EC2 instances through the AWS Management Console.

3. **RDS:**
   - Set up an RDS instance for MySQL.
   - Configure the `application.properties` file in your project to use the RDS instance's endpoint, username, and password.

4. **Environment Configuration:**
   - Configure environment variables in Elastic Beanstalk for any sensitive information or configuration specifics.

5. **Domain Configuration:**
   - Use Route 53 or another domain management service to point your domain to the Elastic Beanstalk environment.

For a detailed guide on deploying applications on AWS using Elastic Beanstalk, EC2, and RDS, refer to the [AWS Documentation](https://aws.amazon.com/documentation/).

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

