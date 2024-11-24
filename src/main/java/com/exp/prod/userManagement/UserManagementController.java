package com.exp.prod.userManagement;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exp.prod.common.Exceptions.UserAlreadyExistsException;
import com.exp.prod.dtos.UserDto;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserManagementController {
     private final UserManagementService userService;
    
    @Autowired
    public UserManagementController(UserManagementService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register_user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto) {
        try{
            if (this.userService.createUser(userDto)) {
                return new ResponseEntity<>(
                    "User created successfully", 
                    HttpStatus.CREATED);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(@Valid @RequestBody UserDto userDto) {
    //     try{
    //         if (this.userService.loginUser(userDto)) {
    //             return new ResponseEntity<>(
    //                 "User logged in successfully", 
    //                 HttpStatus.OK);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(
    //             "Error logging in user", 
    //             HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    //     return null;
    // }
}
