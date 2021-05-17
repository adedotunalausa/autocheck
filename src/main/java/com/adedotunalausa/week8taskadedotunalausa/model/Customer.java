package com.adedotunalausa.week8taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    protected String firstname;
    protected String lastname;
    protected String gender;
    protected String occupation;
    protected String address;
    protected String city;
    protected String state;
    protected String email;
    protected String phoneNo;
    private int isDeleted;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Customer(String firstname, String lastname, String gender, String occupation,
                    String address, String city, String state, String email, String phoneNo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.occupation = occupation;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phoneNo = phoneNo;
    }
}
