package com.adedotunalausa.week8taskadedotunalausa.repository;

import com.adedotunalausa.week8taskadedotunalausa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAndPassword(String email, String password);
}
