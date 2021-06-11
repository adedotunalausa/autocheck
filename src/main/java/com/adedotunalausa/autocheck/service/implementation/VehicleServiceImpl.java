package com.adedotunalausa.autocheck.service.implementation;

import com.adedotunalausa.autocheck.model.Vehicle;
import com.adedotunalausa.autocheck.repository.VehicleRepository;
import com.adedotunalausa.autocheck.service.VehicleService;
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
        return allVehicles.stream()
                .filter(x -> x.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        Vehicle vehicle = null;
        if (this.vehicleRepository.findById(vehicleId).isPresent()) {
            vehicle = this.vehicleRepository.findById(vehicleId).get();
        }

        return vehicle;
    }

    @Override
    public void updateVehicle(Long vehicleId, String manufacturer, String model, String color, String year, String registrationNumber,
                              String engineNumber, String chassisNumber, String currentMileage) {
        Vehicle vehicle = getVehicleById(vehicleId);
        vehicle.setManufacturer(manufacturer);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setYear(year);
        vehicle.setRegistrationNumber(registrationNumber);
        vehicle.setEngineNumber(engineNumber);
        vehicle.setChassisNumber(chassisNumber);
        vehicle.setCurrentMileage(currentMileage);
        vehicleRepository.save(vehicle);
    }
}
