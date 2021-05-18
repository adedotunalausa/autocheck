package com.adedotunalausa.week8taskadedotunalausa.service;

import com.adedotunalausa.week8taskadedotunalausa.model.Work;

import java.util.List;

public interface WorkService {
    void addWork(Work newWork);
    List<Work> getAllWorks();
}
