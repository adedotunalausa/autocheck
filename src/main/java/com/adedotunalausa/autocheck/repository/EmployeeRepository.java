package com.adedotunalausa.autocheck.repository;

import com.adedotunalausa.autocheck.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAndPassword(String email, String password);
    Optional<Employee> findAllByRole(String role);
}
