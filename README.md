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


### *Technical Features*

1. [Spring Boot](#spring-boot)
2. [Spring Security](#spring-security)
3. [JWT Authentication](#jwt-authentication)
4. [JPA](#jpa)
5. [MySQL](#mysql)

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

