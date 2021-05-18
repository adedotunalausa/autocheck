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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long serviceId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Employee createdBy;

    protected String employeeInCharge;
    protected String typeOfService;
    protected Double cost;
    protected String currentMileage;
    protected int isCompleted;
    protected int isPaidFor;
    protected int isDeleted;
    protected Timestamp createdAt = new Timestamp(System.currentTimeMillis());


}
