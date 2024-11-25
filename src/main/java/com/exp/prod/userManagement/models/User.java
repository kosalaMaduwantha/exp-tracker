package com.exp.prod.userManagement.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")})
public class User {

    @Id @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.AUTO) 
    private int userId;

    @Column(name = "user_name") 
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "password")
    private String password; // hashed password

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "salt")
    private String salt;

    @Column(name = "temp_password")
    private String tempPassword;

    public User(
        String userName, String email, String firstName, String lastName, 
        int phoneNumber, String password, LocalDateTime createdAt, LocalDateTime updatedAt, 
        String salt, String tempPassword) { 

        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.salt = salt;
        this.tempPassword = tempPassword;
    }

}
