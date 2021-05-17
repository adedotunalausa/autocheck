package com.adedotunalausa.week8taskadedotunalausa.repository;

import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByIsDeletedEqualsOrderByCreatedAtDesc(int value);
}
