package com.adedotunalausa.autocheck.repository;

import com.adedotunalausa.autocheck.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllByIsDeletedEqualsOrderByCreatedAtDesc(int value);
    List<Work> findAllByIsCompletedEqualsOrderByCreatedAtDesc(int value);
}
