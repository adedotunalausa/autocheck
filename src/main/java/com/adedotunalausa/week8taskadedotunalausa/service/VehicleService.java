package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;

import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesByCustomerId(Long customerId);
}
