package com.adedotunalausa.week8taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    protected String firstname;
    protected String lastname;
    protected String gender;
    protected String role;  // roles are admin and viewer for now
    protected String address;
    protected String email;
    protected String password;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public User(String firstname, String lastname, String gender, String role, String address, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.role = role;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}


