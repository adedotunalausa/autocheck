package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.Staff;

import java.util.List;

public interface StaffService {
    void addUser(Staff staff);
    Staff getUser(String email, String password);
    List<Staff> getAllUsers();
}
