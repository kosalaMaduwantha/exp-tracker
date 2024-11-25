package com.exp.prod.userManagement;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exp.prod.common.exceptions.Exceptions.UserAlreadyExistsException;
import com.exp.prod.dtos.UserDto;
import com.exp.prod.dtos.UserLoginDto;

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
        /*
         * This method registers a user
         * @param userDto: UserDto object
         * @return ResponseEntity<String>
         */
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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        /*
         * This method logs in a user
         * @param userLoginDto: UserLoginDto object
         * @return ResponseEntity<String>: JWT token if login is successful
         */
        try{
            String token = this.userService.loginUser(userLoginDto);
            if (token != null) {
                return new ResponseEntity<>(
                    token, 
                    HttpStatus.OK);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
