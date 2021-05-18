package com.adedotunalausa.week8taskadedotunalausa.service.implementation;

import com.adedotunalausa.week8taskadedotunalausa.model.Work;
import com.adedotunalausa.week8taskadedotunalausa.repository.WorkRepository;
import com.adedotunalausa.week8taskadedotunalausa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public void addWork(Work newWork) {
        this.workRepository.save(newWork);
    }

    @Override
    public List<Work> getAllWorks() {
        return this.workRepository.findAllByIsDeletedEqualsOrderByCreatedAtDesc(0);
    }
}
