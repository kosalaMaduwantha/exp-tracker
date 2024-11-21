package com.exp.prod.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exp.prod.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        System.out.println(userDto);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
            "User created successfully", HttpStatus.CREATED);
        return responseEntity;
    }
}
