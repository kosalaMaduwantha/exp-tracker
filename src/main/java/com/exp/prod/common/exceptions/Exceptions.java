package com.exp.prod.common.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

public class Exceptions {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exists")
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User authentication failed")
    public static class UserAuthenticationException extends RuntimeException {
        public UserAuthenticationException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Category not found")
    public static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }
}
