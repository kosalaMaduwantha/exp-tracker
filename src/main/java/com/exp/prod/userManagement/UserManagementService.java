package com.exp.prod.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;

import com.exp.prod.common.Utils;
import com.exp.prod.dtos.UserDto;
import com.exp.prod.userManagement.models.User;
import com.exp.prod.userManagement.repositories.UserRepository;
import com.exp.prod.common.Exceptions.UserAlreadyExistsException;


@Service
public class UserManagementService {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
    private final UserRepository userRepository;
    
    @Autowired
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    
}
