package com.adedotunalausa.week8taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    private String manufacturer;
    private String model;
    private String color;
    private String year;
    private String registrationNumber;
    private String engineNumber;
    private String chassisNumber;
    private String currentMileage;
    private int isDeleted;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Vehicle(String manufacturer, String model, String color, String year, String registrationNumber,
                   String engineNumber, String chassisNumber, String currentMileage) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.engineNumber = engineNumber;
        this.chassisNumber = chassisNumber;
        this.currentMileage = currentMileage;
    }
}
