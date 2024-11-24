package com.exp.prod.userManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exp.prod.userManagement.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

} 
