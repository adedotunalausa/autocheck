package com.adedotunalausa.week8taskadedotunalausa.service.implementation;

import com.adedotunalausa.week8taskadedotunalausa.model.Staff;
import com.adedotunalausa.week8taskadedotunalausa.repository.StaffRepository;
import com.adedotunalausa.week8taskadedotunalausa.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public void addUser(Staff staff) {
        this.staffRepository.save(staff);
    }

    @Override
    public Staff getUser(String email, String password) {
        return staffRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<Staff> getAllUsers() {
        return staffRepository.findAll();
    }
}
