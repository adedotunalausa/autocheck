package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.User;

public interface UserService {
    void addUser(User user);
    User getUser(String email, String password);
}
