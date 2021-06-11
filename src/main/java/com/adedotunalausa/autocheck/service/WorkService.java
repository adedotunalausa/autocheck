package com.adedotunalausa.autocheck.service;

import com.adedotunalausa.autocheck.model.Work;

import java.util.List;

public interface WorkService {
    void addWork(Work newWork);
    List<Work> getAllWorks();
    List<Work> getAllWorksByVehicleId(Long vehicleId);
    List<Work> getAllPendingWorks();
    List<Work> getAllCompletedWorks();
    Work getWorkById(Long workId);
    void updateWork(Long workId, String employeeInCharge, String typeOfService, Double cost,
                    String currentMileage, int isCompleted, int isPaidFor);
}
