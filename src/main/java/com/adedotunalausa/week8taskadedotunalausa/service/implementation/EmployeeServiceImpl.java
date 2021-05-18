package com.adedotunalausa.week8taskadedotunalausa.service.implementation;

import com.adedotunalausa.week8taskadedotunalausa.model.Employee;
import com.adedotunalausa.week8taskadedotunalausa.repository.EmployeeRepository;
import com.adedotunalausa.week8taskadedotunalausa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addUser(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getUser(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Employee getUserById(Long userId) {
        Employee foundEmployee = null;
        if (employeeRepository.findById(userId).isPresent()) {
            foundEmployee = employeeRepository.findById(userId).get();
        }
        return foundEmployee;
    }

    @Override
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }
}
