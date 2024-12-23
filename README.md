# EXP Tracker

**Description**: EXP Tracker is a personal Expense Tracking application that helps users to track their expenses and income. It provides functionalities to manage expenses and income, and generate reports.

---

## Table of Contents
1. [Overview](#overview)  
2. [Features](#features)  
3. [Getting Started](#getting-started) 
4. [Data model](#data-model) 
5. [Installation](#installation)  
6. [API Documentation](#api-documentation)  
7. [Configuration](#configuration)  

---

## Overview
This project is a Spring Boot application that provides Restful APIs for managing expenses and income. It uses MySQL as the database to store the data. The application provides functionalities to register a user, login, manage expenses and income, and generate reports.

## Features

### *Domain Features*

1. [User Management](#user-management)
2. [Expense Management](#expense-management)
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
    - Io.jsonwebtoken library in Spring is leveraged to generate the JWT token and HMAC-SHA Algorithm is used for the encoding.
    - As the claims, the user-name, email, firstName, lastName, and expiration time are added to the token.

- **Update User Profile**
    - Update the user profile by providing the user details such as first name, last name, phone number, and email.
    - The user details are validated before updating the user profile (***used DTOs to validate and transfer the data between layers***).
    - If the user does not exist, an error message is returned (***The custom exception UserNotFoundException is being thrown***).
    - If the user is successfully updated, a success message is returned.

- **Authorization security middleware**
    - The application checks the authetication token from the request header and validates it.
    - Validation done by the security middleware in the security fileter chain. and following are the steps taken by the middleware:
      - Check the path of the request and if it is not a login or register path, then check the token.
      - Authentication is done by the custom authentication filter (JTWFilter) added to the security filter chain.
      - JWTFilter extracts the token from the request header and validates it using the JWTUtil class (Util class has methods to extract the token claims and validate the expiration time etc).
      - If the token is valid, the request is forwarded to the controller with the decoded token information.
    - If the token is valid, the request is forwarded to the controller.
    - If the token is invalid, the request is rejected with a 401 Unauthorized error.
    - Security middleware used for this purpose is from the Springs Security chain.

#### Expense management

- **Adding expenses**
    - Add an expense by providing the expense details such as categoryName, amount, currency, and notes.
    - The expense details are validated before adding the expense (***used DTOs to validate and transfer the data between layers***).
    - If the user does not exist, an error message is returned (***The custom exception UserNotFoundException is being thrown***).
    - If the category does not exist, an error message is returned (***The custom exception CategoryNotFoundException is being thrown***).
    - If the expense is successfully added, a success message is returned.
- **Addign expenses categories**
    - Add an expense category by providing the category name.
    - The category details are validated before adding the category (***used DTOs to validate and transfer the data between layers***).
    - If the user does not exist, an error message is returned (***The custom exception UserNotFoundException is being thrown***).
    - If the category is successfully added, a success message is returned.
- **Recurring expenses**
- **Adding expense notes or files**
- **Multiple currency support**


  
### *Technical Features*

1. **Spring Boot Starter Dependencies**
    - spring-boot-starter: Core starter, including auto-configuration support, logging, and YAML.
    - spring-boot-starter-web: Starter for building web,including RESTful, applications using Spring MVC.
    - spring-boot-starter-data-jpa: Starter for using Spring Data JPA with Hibernate.
    - spring-boot-starter-validation: Starter for using Java Bean Validation with Hibernate Validator.
    - spring-boot-starter-test: Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest, and Mockito.
  
2. **Spring Security**
    - JWT (JSON Web Token) authentication is used for securing the application. The [loginUser] method in UserManagementService generates a JWT token upon successful login.
    - Token is validated by the JWTFilter class in the security package. The filter extracts the token from the request header and validates it using the JWTUtil class.

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

## Data model

### 1. **User Table**
   Stores information about each user.

   **`Users`**
   | Column Name    | Data Type     | Description                      |
   |----------------|---------------|----------------------------------|
   | `user_id`      | INT (PK)      | Unique identifier for each user. |
   | `user_name`    | VARCHAR(50)   | Unique username.                 |
   | `first_name`   | VARCHAR(50)   | First name of the user.          |
   | `last_name`    | VARCHAR(50)   | Last name of the user.           |
   | `email`        | VARCHAR(100)  | User email address.              |
   | `phone_number` | VARCHAR(15)   | User phone number.               |
   | `temp_password`| VARCHAR(255)  | tempory password                 |
   | `password`     | VARCHAR(255)  | Hashed password.                 |
   | `salt`         | VARCHAR(255)  | Salt used for password hashing.  |
   | `created_at`   | TIMESTAMP     | Date and time of user creation.  |
   | `updated_at`   | TIMESTAMP     | Date and time of last update.    |
   | *`currency`*   | VARCHAR(10)   | Default currency for the user.   |

---

### 2. **Expense Tracking**

**`Expenses`**
   | Column Name      | Data Type     | Description                              |
   |------------------|---------------|------------------------------------------|
   | `expense_id`     | INT (PK)      | Unique identifier for each expense.      |
   | `user_id`        | INT (FK)      | Reference to the user who made the expense. |
   | `category_id`    | INT (FK)      | Reference to the expense category.       |
   | `amount`         | DECIMAL(10, 2)| Expense amount.                         |
   | `currency`       | VARCHAR(10)   | Currency used for the expense.          |
   | `expense_date`   | DATE          | Date of the expense.                    |
   | `notes`          | TEXT          | Additional notes about the expense.     |

**`ExpenseCategories`**
   | Column Name      | Data Type     | Description                              |
   |------------------|---------------|------------------------------------------|
   | `category_id`    | INT (PK)      | Unique identifier for each category.     |
   | `user_id`        | INT (FK)      | Reference to the user for custom categories (nullable for predefined). |
   | `category_name`  | VARCHAR(50)   | Name of the category.                   |

**`RecurringExpenses`**
   | Column Name      | Data Type     | Description                              |
   |------------------|---------------|------------------------------------------|
   | `recurring_id`   | INT (PK)      | Unique identifier for each recurring expense. |
   | `user_id`        | INT (FK)      | Reference to the user.                  |
   | `category_id`    | INT (FK)      | Reference to the expense category.       |
   | `amount`         | DECIMAL(10, 2)| Recurring expense amount.               |
   | `currency`       | VARCHAR(10)   | Currency used for the recurring expense.|
   | `frequency`      | VARCHAR(20)   | Frequency (e.g., monthly, weekly).      |
   | `start_date`     | DATE          | Start date for the recurring expense.   |
   | `end_date`       | DATE (nullable)| End date (nullable for indefinite).    |

**`ExpenseTags`**
   | Column Name      | Data Type     | Description                              |
   |------------------|---------------|------------------------------------------|
   | `tag_id`         | INT (PK)      | Unique identifier for each tag.          |
   | `user_id`        | INT (FK)      | Reference to the user.                  |
   | `tag_name`       | VARCHAR(50)   | Name of the tag.                        |

**`ExpenseTagMap`**
   | Column Name      | Data Type     | Description                              |
   |------------------|---------------|------------------------------------------|
   | `expense_id`     | INT (FK)      | Reference to an expense.                |
   | `tag_id`         | INT (FK)      | Reference to a tag.                     |

---

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
```

### Login User
**URL:** `/user/login`
**Method:** `POST`
**Description:** Logs in a user.
**Request Body:**
```json
{
    "userName":"name",
    "password":1234
}
```

**Response Body:**
- Success - 201 Created
```json
{
    "token": "auth_token"
}
```
- Unsuccessful
```json
{
    "timestamp": "2024-12-16T22:00:58.400334845",
    "status": 404,
    "error": "User not found",
    "path": "/user/login"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/user/login"
}
```

### Update User Profile
**URL:** `/user/update_user`
**Method:** `POST`
**Description:** Updates the user profile.
**Request Body:**
```json
{
    "userName":"kosalaa",
    "firstName":"Kosalama",
    "lastName":"Maduwantha",
    "phoneNumber":123456789,
    "email":"kosalaaaa@gmail.com"
}
```

**Response Body:**
- Success - 200 OK
```json
{
    "message": "User profile updated successfully"
}
```
- Unsuccessful
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 404,
    "error": "User not found",
    "path": "/user/update_user"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/user/update_user"
}
```

### Add Expense
**URL:** `/expense/create_expense`
**Method:** `POST`
**Description:** Adds an expense.
**Request Body:**
```json
{
    "categoryName":"category",
    "amount":1000,
    "currency":"USD",
    "notes":"notes"
}
```

**Response Body:**
- Success - 201 Created
```json
{
    "message": "Expense added successfully"
}
```
- Unsuccessful
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 404,
    "error": "User not found",
    "path": "/expense/create_expense"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 404,
    "error": "Category not found",
    "path": "/expense/create_expense"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/expense/create_expense"
}
```

### Add Expense Category
**URL:** `/expense/add_expense_category`
**Method:** `POST`
**Description:** Adds an expense category.
**Request Body:**
```json
{
    "categoryName":"category"
}
```

**Response Body:**
- Success - 201 Created
```json
{
    "message": "Category added successfully"
}
```
- Unsuccessful
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 404,
    "error": "User not found",
    "path": "/expense/add_expense_category"
}
```
```json
{
    "timestamp": "2024-11-29T20:03:19.399309146",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/expense/add_expense_category"
}
```
