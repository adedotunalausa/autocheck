package com.adedotunalausa.week8taskadedotunalausa.repository;

import com.adedotunalausa.week8taskadedotunalausa.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
}
