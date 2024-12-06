package com.exp.prod.common.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.exp.prod.common.dtos.response_dtos.ErrorResponse;
import com.exp.prod.common.exceptions.Exceptions.UserAlreadyExistsException;
import com.exp.prod.common.exceptions.Exceptions.UserNotFoundException;
import com.exp.prod.common.exceptions.Exceptions.UserAuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
     // Assuming CustomErrorResponse is a concrete implementation of ErrorResponse
     ErrorResponse error = new ErrorResponse(
         LocalDateTime.now(), 
         HttpStatus.CONFLICT.value(), 
         e.getMessage(), 
         "/user/register_user");   
     return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    } 

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
     // Assuming CustomErrorResponse is a concrete implementation of ErrorResponse
     ErrorResponse error = new ErrorResponse(
         LocalDateTime.now(), 
         HttpStatus.NOT_FOUND.value(), 
         e.getMessage(), 
         "/user/login");   
     return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleUserAuthenticationException(UserAuthenticationException e) {
     // Assuming CustomErrorResponse is a concrete implementation of ErrorResponse
     ErrorResponse error = new ErrorResponse(
         LocalDateTime.now(), 
         HttpStatus.UNAUTHORIZED.value(), 
         e.getMessage(), 
         "/user/login");   
     return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    
}
