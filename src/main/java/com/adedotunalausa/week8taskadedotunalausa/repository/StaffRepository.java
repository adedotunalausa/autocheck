package com.adedotunalausa.week8taskadedotunalausa.repository;

import com.adedotunalausa.week8taskadedotunalausa.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmailAndPassword(String email, String password);
}
