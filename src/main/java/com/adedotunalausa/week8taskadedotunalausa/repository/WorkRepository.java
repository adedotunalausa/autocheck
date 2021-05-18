package com.adedotunalausa.week8taskadedotunalausa.repository;

import com.adedotunalausa.week8taskadedotunalausa.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllByIsDeletedEqualsOrderByCreatedAtDesc(int value);
}
