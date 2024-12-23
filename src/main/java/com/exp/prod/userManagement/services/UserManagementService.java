package com.exp.prod.userManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.exp.prod.common.Utils;
import com.exp.prod.common.dtos.request_dtos.UserDto;
import com.exp.prod.common.dtos.request_dtos.UserLoginDto;
import com.exp.prod.common.dtos.request_dtos.UserUpdateDto;
import com.exp.prod.common.exceptions.Exceptions.UserAlreadyExistsException;
import com.exp.prod.common.exceptions.Exceptions.UserAuthenticationException;
import com.exp.prod.common.exceptions.Exceptions.UserNotFoundException;
import com.exp.prod.userManagement.models.User;
import com.exp.prod.userManagement.models.UserPrinciple;
import com.exp.prod.userManagement.repositories.UserRepository;
import com.exp.prod.common.properties.JwtProperties;


@Service
public class UserManagementService {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    // @Autowired
    // public UserManagementService(UserRepository userRepository, JwtProperties jwtProperties, AuthenticationManager authManager) {
    //     this.userRepository = userRepository;
    //     this.jwtProperties = jwtProperties;
    //     this.authManager = authManager;
    // }

    public boolean createUser(UserDto userDto){
        logger.info("Creating user");
        try {
            String salt = Utils.generateSalt();
            String hashedPassword = Utils.hashPassword(userDto.getPassword(), salt);
            User user = new User(
                userDto.getUserName(), 
                userDto.getEmail(), 
                userDto.getFirstName(), 
                userDto.getLastName(), 
                userDto.getPhoneNumber(),   
                hashedPassword, 
                LocalDateTime.now(), 
                null, 
                salt, 
                null);
            this.userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")){
                logger.error("Error creating user: " + e.getMessage());
                e.printStackTrace();
                throw new UserAlreadyExistsException("User already exists");
            }else{
                logger.error("Error creating user: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            logger.error("Error creating user: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String loginUser(UserLoginDto userLoginDto){
        logger.info("Logging in user");
        try{
            // Authentication authentication = authManager.authenticate(
            //     new UsernamePasswordAuthenticationToken(
            //         userLoginDto.getUserName(), 
            //         userLoginDto.getPassword()));
            // if (authentication.isAuthenticated()){
            //     User user = this.userRepository.findByUserName(userLoginDto.getUserName());
            //     String token = generateToken(user);
            //     return token;
            // }else{
            //     throw new UserAuthenticationException("User authentication failed");
            // }
            User user = this.userRepository.findByUserName(userLoginDto.getUserName());
            if (user == null){
                throw new UserNotFoundException("User not found");
            }
            String hashedPassword = Utils.hashPassword(userLoginDto.getPassword(), user.getSalt());
            if (hashedPassword.equals(user.getPassword())){
                String token = generateToken(user);
                return token;
            }else{
                throw new UserAuthenticationException("User authentication failed");
            }
        } catch (Exception e) {
            logger.error("Error logging in user: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    
    }

    public boolean updateUser(UserUpdateDto userUpdateDto){
        logger.info("Updating user");
        try {
            User user = this.userRepository.findByUserName(userUpdateDto.getUserName());
            if (user == null){
                throw new UserNotFoundException("User not found");
            }
            Optional.ofNullable(userUpdateDto.getEmail()).ifPresent(user::setEmail);
            Optional.ofNullable(userUpdateDto.getFirstName()).ifPresent(user::setFirstName);
            Optional.ofNullable(userUpdateDto.getLastName()).ifPresent(user::setLastName);
            Optional.ofNullable(userUpdateDto.getPhoneNumber()).ifPresent(user::setPhoneNumber);
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.error("Error updating user: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private String generateToken(User user){
        try{
            Date now = new Date();
            Date expiration = new Date(now.getTime() + jwtProperties.getExpiration());
            Date notBefore = now;
            String jwt = Jwts.builder()
                .issuer("user-management-service")
                .subject(user.getUserName())
                .audience().add("user-client").and()
                .expiration(expiration)
                .notBefore(notBefore)
                .issuedAt(now)
                .id(UUID.randomUUID().toString())
                .claim("user-name", user.getUserName())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("phoneNumber", user.getPhoneNumber())
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .compact();
            return jwt;
        } catch (Exception e) {
            logger.error("Error generating token: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        
    }
    
}
