package com.adedotunalausa.autocheck.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    protected String firstname;
    protected String lastname;
    protected String gender;
    protected String role;  // roles are admin and viewer for now
    protected String position;
    protected String address;
    protected String city;
    protected String state;
    protected String phoneNo;
    protected String email;
    protected String password;
    private int isDeleted;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Employee(String firstname, String lastname, String gender, String role,
                    String position, String address, String city, String state,
                    String phoneNo, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.role = role;
        this.position = position;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }
}


