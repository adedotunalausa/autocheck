package com.adedotunalausa.week8taskadedotunalausa.service.implementation;

import com.adedotunalausa.week8taskadedotunalausa.model.Work;
import com.adedotunalausa.week8taskadedotunalausa.repository.WorkRepository;
import com.adedotunalausa.week8taskadedotunalausa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Work> getAllWorksByVehicleId(Long vehicleId) {
        List<Work> allWorks = this.workRepository.findAllByIsDeletedEqualsOrderByCreatedAtDesc(0);
        return allWorks.stream()
                .filter(x -> x.getVehicle().getVehicleId().equals(vehicleId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Work> getAllPendingWorks() {
        return this.workRepository.findAllByIsCompletedEqualsOrderByCreatedAtDesc(0);
    }

    @Override
    public List<Work> getAllCompletedWorks() {
        return this.workRepository.findAllByIsCompletedEqualsOrderByCreatedAtDesc(1);
    }

    @Override
    public Work getWorkById(Long workId) {
        Work foundWork = null;
        if (this.workRepository.findById(workId).isPresent()) {
            foundWork = this.workRepository.findById(workId).get();
        }
        return foundWork;
    }

    @Override
    public void updateWork(Long workId, String employeeInCharge, String typeOfService,
                           Double cost, String currentMileage, int isCompleted, int isPaidFor) {
        Work work = getWorkById(workId);
        work.setEmployeeInCharge(employeeInCharge);
        work.setTypeOfService(typeOfService);
        work.setCost(cost);
        work.setCurrentMileage(currentMileage);
        work.setIsCompleted(isCompleted);
        work.setIsPaidFor(isPaidFor);
        workRepository.save(work);
    }
}
