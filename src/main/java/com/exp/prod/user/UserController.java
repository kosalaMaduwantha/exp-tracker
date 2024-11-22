package com.exp.prod.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exp.prod.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import com.exp.prod.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService = new UserService();
    
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        if (userService.createUser(userDto)) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
        
        return null;
    }
}
