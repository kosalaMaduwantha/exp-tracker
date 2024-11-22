package com.exp.prod.user;

// import com.example.myapp.entity.Product;
// import com.example.myapp.repository.ProductRepository;
// import com.example.myapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exp.prod.dtos.UserDto;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createUser(UserDto userDto){
        System.out.println("Creating user");
        User user = new User(
            userDto.getUserId(), 
            userDto.getUserName(), 
            userDto.getEmail(), 
            userDto.getPassword());
        return userRepository.save(user) != null;
    }
    
}
