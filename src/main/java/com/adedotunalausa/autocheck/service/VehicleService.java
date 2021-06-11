package com.adedotunalausa.autocheck.service;

import com.adedotunalausa.autocheck.model.Vehicle;

import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesByCustomerId(Long customerId);
    Vehicle getVehicleById(Long vehicleId);
    void updateVehicle(Long vehicleId, String manufacturer, String model, String color, String year, String registrationNumber,
                       String engineNumber, String chassisNumber, String currentMileage);
}
