package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.Employee;

import java.util.List;

public interface EmployeeService {
    void addUser(Employee employee);
    Employee getUser(String email, String password);
    Employee getUserById(Long userId);
    List<Employee> getAllUsers();
}
