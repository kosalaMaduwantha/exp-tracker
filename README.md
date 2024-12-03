# EXP Tracker

**Description**: EXP Tracker is a personal Expense Tracking application that helps users to track their expenses and income. It provides functionalities to manage expenses and income, and generate reports.

---

## Table of Contents
1. [Overview](#overview)  
2. [Features](#features)  
3. [Getting Started](#getting-started)  
4. [Installation](#installation)  
5. [API Documentation](#api-documentation)  
6. [Configuration](#configuration)  

---

## Overview
This project is a Spring Boot application that provides Restful APIs for managing expenses and income. It uses MySQL as the database to store the data. The application provides functionalities to register a user, login, manage expenses and income, and generate reports.

## Features

### *Domain Features*

1. [User Management](#user-management)
     - Register User
     - Login User (JWT Authentication)
     - Update User Profile

2. Expense Management
3. Income Management

#### User Management
This module provides functionalities to manage users. It includes registering a new user, logging in, and updating the user profile. Currently, the User Management module provides user registration, login, and update user profile functionalities.

- **User Registration**
    - Register a new user by providing the user details such as username, first name, last name, phone number, email, and password.
    - The user details are validated before registering the user (***used DTOs to validate and transfer the data between layers***).
    - If the user already exists, an error message is returned (***The custom exception UserAlreadyExistsException is being thrown***).
    - The password is encrypted with a salt before storing it in the database (***used BCryptPasswordEncoder to encrypt the password***).
    - Once the data is validated and password is encrypted, the user data is saved in the database.
    - If the user is successfully registered, a success message is returned.

- **User Login**
    - Login a user by providing the username and password.
    - The user details are validated before logging in (***used DTOs to validate and transfer the data between layers***).
    - If the user does not exist, an error message is returned (***The custom exception UserNotFoundException is being thrown***).
    - If the password is incorrect, an error message is returned (***The custom exception UserAuthenticationError is being thrown***).
    - If the user is successfully logged in, a JWT token is generated and returned.

- **Update User Profile**
    - Update the user profile by providing the user details such as first name, last name, phone number, and email.
    - The user details are validated before updating the user profile (***used DTOs to validate and transfer the data between layers***).
    - If the user does not exist, an error message is returned (***The custom exception UserNotFoundException is being thrown***).
    - If the user is successfully updated, a success message is returned.


### *Technical Features*

1. **Spring Boot Starter Dependencies**
    - spring-boot-starter: Core starter, including auto-configuration support, logging, and YAML.
    - spring-boot-starter-web: Starter for building web,including RESTful, applications using Spring MVC.
    - spring-boot-starter-data-jpa: Starter for using Spring Data JPA with Hibernate.
    - spring-boot-starter-validation: Starter for using Java Bean Validation with Hibernate Validator.
    - spring-boot-starter-test: Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest, and Mockito.
  
2. **Spring Security**
    - JWT (JSON Web Token) authentication is used for securing the application. The [loginUser] method in UserManagementService generates a JWT token upon successful login.

3. **JPA (Java Persistence API)**
    - The project uses JPA for ORM (Object-Relational Mapping). The User entity in User is mapped to the users table in the database.
    - The UserRepository interface extends JpaRepository for CRUD operations on the User entity.

4. **Database Integration**
    - The project uses MySQL as the primary database, as indicated by the mysql-connector-java dependency in the pom.xml file.
    - H2 database is used for runtime, possibly for testing purposes.

5. **Lombok**
    - Lombok is used to reduce boilerplate code. Annotations like @Data, @NoArgsConstructor, and @AllArgsConstructor are used in the User class.
  
6. **Exception Handling**
    - Custom exceptions like UserAlreadyExistsException, UserAuthenticationException, and UserNotFoundException are used for specific error scenarios in the UserManagementService.

7. **Validation**
    - DTOs (Data Transfer Objects) are used for validating and transferring data between layers. This is mentioned in the README.md file under the User Management section.

## API Documentation

### Register User
**URL:** `/user/register_user`  
**Method:** `POST`  
**Description:** Registers a new user.  
**Request Body:**
```json
{
    "userName":"name",
    "firstName":"firstName",
    "lastName":"",
    "phoneNumber":123456789,
    "email":"entity@gmail.com",
    "password":1234
}
```
**Description:**
- `userName`: Username of the user.
- `firstName`: First name of the user.
- `lastName`: Last name of the user.
- `phoneNumber`: Phone number of the user.
- `email`: Email of the user.
- `password`: Password of the user.
  
**Response Body:**
- Success
```json
{
    "message": "User registered successfully"
}
```
- Unsuccessful
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 409,
    "error": "User already exists",
    "path": "/user/register_user"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 400,
    "error": "Bad Request",
    "path": "/user/register_user"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/user/register_user"
}
`

