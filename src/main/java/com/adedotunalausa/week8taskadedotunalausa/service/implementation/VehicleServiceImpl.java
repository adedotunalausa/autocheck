package com.adedotunalausa.week8taskadedotunalausa.service.implementation;

import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;
import com.adedotunalausa.week8taskadedotunalausa.repository.VehicleRepository;
import com.adedotunalausa.week8taskadedotunalausa.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void addVehicle(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAllByIsDeletedEqualsOrderByCreatedAtDesc(0);
    }

    @Override
    public List<Vehicle> getAllVehiclesByCustomerId(Long customerId) {
        List<Vehicle> allVehicles = this.vehicleRepository.findAllByIsDeletedEqualsOrderByCreatedAtDesc(0);
        List<Vehicle> allVehiclesWithCustomerId = allVehicles.stream()
                .filter(x -> x.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());
        return allVehiclesWithCustomerId;
    }
}
