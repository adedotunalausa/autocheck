package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesByCustomerId(Long customerId);
    Vehicle getVehicleById(Long vehicleId);
    void updateVehicle(Long vehicleId, String manufacturer, String model, String color, String year, String registrationNumber,
                       String engineNumber, String chassisNumber, String currentMileage);
}
