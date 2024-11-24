package com.exp.prod.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.exp.prod.common.Exceptions.UserAlreadyExistsException;
import com.exp.prod.dtos.ErrorResponse;

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
}
